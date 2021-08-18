#!/bin/sh

# Copyright (c) 2021, WSO2 Inc. (http://www.wso2.com). All Rights Reserved.
#
# This software is the property of WSO2 Inc. and its suppliers, if any.
# Dissemination of any information or reproduction of any material contained
# herein is strictly forbidden, unless permitted by WSO2 in accordance with
# the WSO2 Commercial License available at http://wso2.com/licenses.
# For specific language governing the permissions and limitations under
# this license, please see the license as well as any agreement you’ve
# entered into with WSO2 governing the purchase of this software and any
# associated services.

echo "--------------------------------------------------------------"
echo "--------------------- Load config file -----------------------"
echo "--------------------------------------------------------------"
current_dir="$(dirname "$0")"
. ${current_dir}/config.conf

# Function to access json objects
jsonValue() {
  KEY=$1
  num=$2
  awk -F"[,:}]" '{for(i=1;i<=NF;i++){if($i~/'$KEY'\042/){print $(i+1)}}}' | tr -d '"' | sed -n ${num}p
}

# Wait for server start up
while true; do
  curl -X POST "${SERVER_URL}/client-registration/v0.17/register" \
    --header 'Authorization: Basic YWRtaW46YWRtaW4=' \
    --header 'Content-Type: application/json' \
    --data '{}'
  if [ "$?" = "7" ]; then
    sleep 15
  else
    break
  fi
done

# Create a SP
echo "Create a SP"
spResult=$(curl -X POST "${SERVER_URL}/client-registration/v0.17/register" \
  --header 'Authorization: Basic YWRtaW46YWRtaW4=' \
  --header 'Content-Type: application/json' \
  --data @${PUBLISHER_DATA_RESOURCE_DIR}/sp.json -k)

clientId=$(printf "$spResult" | jsonValue clientId 1)
clientSecret=$(printf "$spResult" | jsonValue clientSecret 1)
encodedIdSecret=$(printf "${clientId}:${clientSecret}" | base64)

# Create access token
echo "Create access token"
tokenResult=$(curl -X POST "${SERVER_URL}/oauth2/token?grant_type=password&username=admin&password=admin&scope=apim:api_view%20apim:api_create%20apim:api_publish%20apim:subscribe%20apim:subscription_block%20apim:subscription_view" \
  --header "Authorization: Basic \"${encodedIdSecret}\"" \
  --header "Content-Type: application/json" -k)

accessToken=$(printf "$tokenResult" | jsonValue access_token 1)

# Create API
echo "Create PizzaShack API"
apiResult=$(curl -X POST "${PUBLISHER_URL}/apis" \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer "${accessToken}"" \
  --data @${PUBLISHER_DATA_RESOURCE_DIR}/api.json -k)

apiId=$(printf "$apiResult" | jsonValue id 1)

# Create API Revision
echo "Create API revision"
apiRevisionResult=$(curl -X POST "${PUBLISHER_URL}/apis/"${apiId}"/revisions" \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer "${accessToken}"" \
  --data @${PUBLISHER_DATA_RESOURCE_DIR}/apiRevision.json -k)

apiRevisionId=$(printf "$apiRevisionResult" | jsonValue id 1)

# Deploy API Revision
echo "Deploy API revision"
apiRevisionDeploymentResult=$(curl -X POST "${PUBLISHER_URL}/apis/"${apiId}"/deploy-revision?revisionId="${apiRevisionId}"" \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer "${accessToken}"" \
  --data @${PUBLISHER_DATA_RESOURCE_DIR}/apiRevisionDeployment.json -k)

# Publish API Revision
echo "Publish API revision"
apiPublisheResult=$(curl -X POST "${PUBLISHER_URL}/apis/change-lifecycle?action=Publish&apiId="${apiId}"" \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer "${accessToken}"" -k)

# Get application list
echo "Get application list"
getApplicationsResult=$(curl -X GET "${DEVPORTAL_URL}/applications" \
  --header "Authorization: Bearer "${accessToken}"" \
  --header "Content-Type: application/json" -k)

applicationId=$(printf "$getApplicationsResult" | jsonValue applicationId 1)

# Delete Default application
echo "Delete Default application"
deleteDefaultApplicationResult=$(curl -X DELETE "${DEVPORTAL_URL}/applications/${applicationId}" \
  --header "Authorization:Bearer "${accessToken}"" \
  --header "Content-Type: application/json" -k)

# Create application
echo "Create application"
applicationResult=$(curl -X POST "${DEVPORTAL_URL}/applications" \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer "${accessToken}"" \
  --data @${PUBLISHER_DATA_RESOURCE_DIR}/application.json -k)

applicationId=$(printf "$applicationResult" | jsonValue applicationId 1)

# Subscribe application to API
echo "Subscribe application to API"
subscribeData=$(printf "$(cat ${PUBLISHER_DATA_RESOURCE_DIR}/subscribe.json)" |
  sed "s/{{apiId}}/${apiId}/" |
  sed "s/{{applicationId}}/${applicationId}/")

subscriptionResult=$(curl -X POST "${DEVPORTAL_URL}/subscriptions" \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer "${accessToken}"" \
  --data "${subscribeData}" -k)

# Get key manager list
echo "Get key manager list"
keyManagerResult=$(curl -X GET "${DEVPORTAL_URL}/key-managers" \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer "${accessToken}"" -k)

keyManagerId=$(printf "$keyManagerResult" | jsonValue id 1)

# Generate keys/token for application
echo "Generate keys for application"
keyGenerationData=$(printf "$(cat ${PUBLISHER_DATA_RESOURCE_DIR}/keyGenaration.json)" | sed "s/{{keyManagerId}}/${keyManagerId}/")

generateKeysResult=$(curl -X POST "${DEVPORTAL_URL}/applications/"${applicationId}"/generate-keys" \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer "${accessToken}"" \
  --data "${keyGenerationData}" -k)

applicationToken=$(printf "$generateKeysResult" | jsonValue accessToken 1)

# Invoke AI
sleep 15
echo "Invoke API"
COUNTER=1
while [ ${COUNTER} -le 50 ]; do
  if [ $(( ${COUNTER} % 5 )) != 0 ]; then
    if [ $(( ${COUNTER} % 2 )) != 0 ]; then
      # Publish with user-agent firefox and platform mac osx
      curl -X GET "${GATEWAY_URL}/pizzashack/1.0.0/menu" \
        --header "Content-Type: application/json" \
        --header "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/43.4" \
        --header "Authorization: Bearer "${applicationToken}"" -k
    else
      # Publish with user-agent chrome and platform linux
      curl -X GET "${GATEWAY_URL}/pizzashack/1.0.0/menu" \
        --header "Content-Type: application/json" \
        --header "User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36" \
        --header "Authorization: Bearer "${applicationToken}"" -k
    fi
  else
    # Authentication error
    curl -X GET "${GATEWAY_URL}/pizzashack/1.0.0/menu" \
      --header "Content-Type: application/json" \
      --header "Authorization: Bearer abc" -k
  fi
  COUNTER=$(($COUNTER + 1))
done

# add sleep to ensure events gets mapped to 2 windows in streaming job
sleep 10

COUNTER=1
while [ ${COUNTER} -le 50 ]; do
  if [ $(( ${COUNTER} % 5 )) != 0 ]; then
    if [ $(( ${COUNTER} % 2 )) != 0 ]; then
      # Publish with user-agent firefox and platform mac osx
      curl -X GET "${GATEWAY_URL}/pizzashack/1.0.0/menu" \
        --header "Content-Type: application/json" \
        --header "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/43.4" \
        --header "Authorization: Bearer "${applicationToken}"" -k
    else
      # Publish with user-agent chrome and platform linux
      curl -X GET "${GATEWAY_URL}/pizzashack/1.0.0/menu" \
        --header "Content-Type: application/json" \
        --header "User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36" \
        --header "Authorization: Bearer "${applicationToken}"" -k
    fi
  else
    # Authentication error
    curl -X GET "${GATEWAY_URL}/pizzashack/1.0.0/menu" \
      --header "Content-Type: application/json" \
      --header "Authorization: Bearer abc" -k
  fi
  COUNTER=$(($COUNTER + 1))
done

# Wait for analytics data to be available in adx
sleep 90
exit 0
