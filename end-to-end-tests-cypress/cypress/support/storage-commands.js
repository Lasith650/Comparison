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
import {
    EVENT_PUBLISH_FAILED, EXISTING_STATS, PIZZA_API_ID, TRAFFIC_ALERT_ID, LATENCY_ALERT_ID, PIZZA_APPLICATION_ID,
    EXISTING_STATS_TIME_FROM
} from '../integration/constants';

const storage = {
    [EVENT_PUBLISH_FAILED]: false,
    [EXISTING_STATS]: {},
    [PIZZA_API_ID]: '-1',
    [PIZZA_APPLICATION_ID]: '-1',
    [TRAFFIC_ALERT_ID]: '',
    [LATENCY_ALERT_ID]: '',
    [EXISTING_STATS_TIME_FROM]: ''
};

Cypress.on('uncaught:exception', (err, runnable) => {
    console.log(err);
    return false;
});

Cypress.Commands.add('getStoredValue', (name) => {
    if (name in storage) {
        return storage[name];
    }
    const msg = `Missing item "${name}"`;
    throw new Error(msg);
});

Cypress.Commands.add('setStoredValue', (name, value) => {
    if (typeof value === 'undefined') {
        // since we cannot return undefined from the cy.task
        // let's not allow storing undefined
        throw new Error(`Cannot store undefined value for item "${name}"`);
    }

    storage[name] = value;
    return null;
});
