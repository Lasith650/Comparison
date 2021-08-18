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

current_dir="$(dirname "$0")"
. ${current_dir}/config.conf

sh ${PUBLISHER_GATEWAY_RESOURCE_DIR}/wso2am-4.0.0/bin/api-manager.sh stop
# Wait for server shut down
sleep 30
#rm -rf ${PUBLISHER_GATEWAY_RESOURCE_DIR}/wso2am-4.0.0

exit 0
