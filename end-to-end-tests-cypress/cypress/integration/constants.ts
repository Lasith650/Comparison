/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.com). All Rights Reserved.
 *
 * This software is the property of WSO2 Inc. and its suppliers, if any.
 * Dissemination of any information or reproduction of any material contained
 * herein is strictly forbidden, unless permitted by WSO2 in accordance with
 * the WSO2 Commercial License available at http://wso2.com/licenses.
 * For specific language governing the permissions and limitations under
 * this license, please see the license as well as any agreement you’ve
 * entered into with WSO2 governing the purchase of this software and any
 * associated services.
 */

export const UTC_BROWSER_LOCALE_FORMAT = "yyy-MM-dd'T'HH:mm:ss.SSSXXX";

export const USER_INFO = 'user-info';

export const USER_TOKEN = 'user-token';

export const APIM_DIST_LOCATION = 'cypress/resources/publisher/gateway';

export const EVENT_PUBLISH_FAILED = 'event-publish-failed';

export const EXISTING_STATS = 'existing-stats';

export const EXISTING_STATS_TIME_FROM = 'existing-stats-time-from';

export const PIZZA_API_ID = 'pizza-api-id';

export const PIZZA_APPLICATION_ID = 'pizza-app-id';

export const TRAFFIC_ALERT_ID = 'traffic-alert-id';

export const LATENCY_ALERT_ID = 'latency-alert-id';

export enum ALERT_TYPE {
    LATENCY = 'LATENCY',
    TRAFFIC = 'TRAFFIC'
}

export enum LATENCY_ALERT_METRICS {
    RESPONSE_LATENCY = 'RESPONSE_LATENCY'
}

export enum TRAFFIC_ALERT_METRICS {
    HIT_COUNT = 'HIT_COUNT'
}

export const pizzaShackAPI = {
    name: 'PizzaShackAPI',
    context: '/pizzashack',
    version: '1.0.0',
    method: 'GET',
    resource: '/menu'
};

export const pizzaApplication = {
    name: 'Application',
    owner: 'admin'
};

export const trafficAlertConfiguration = {
    "metric": "HIT_COUNT",
    "apiName": "{{apiName}}",
    "apiVersion": "{{apiVersion}}",
    "threshold": "{{threshold}}",
    "emails": [],
};

export const latencyAlertConfiguration = {
    "metric": "RESPONSE_LATENCY",
    "apiName": "{{apiName}}",
    "apiVersion": "{{apiVersion}}",
    "threshold": "{{threshold}}",
    "emails": [],
};
