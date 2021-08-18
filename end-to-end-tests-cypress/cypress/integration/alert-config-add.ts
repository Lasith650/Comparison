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
    ALERT_TYPE,
    LATENCY_ALERT_METRICS,
    USER_TOKEN,
    pizzaShackAPI,
    latencyAlertConfiguration,
    trafficAlertConfiguration,
    TRAFFIC_ALERT_METRICS,
    LATENCY_ALERT_ID,
    TRAFFIC_ALERT_ID
} from './constants';

// data filter constants used
const organizationId = Cypress.env('organizationId');
const environmentId = Cypress.env('environmentId');
const tenant = Cypress.env('tenant');

describe('Add alert config', () => {
    before(() => {
        cy.consoleUserLogin();
    });

    it('Add Latency alert config', () => {
        const thresholdValue = 10;

        const alertConfig = { ...latencyAlertConfiguration };
        alertConfig.apiName = pizzaShackAPI.name;
        alertConfig.apiVersion = pizzaShackAPI.version;
        alertConfig.threshold = thresholdValue.toString();

        cy.getStoredValue(USER_TOKEN).then((token: string) =>
            cy.addLatencyAlertConfig(token, organizationId, environmentId, tenant, alertConfig).then((res) => {
                const {
                    body: {
                        tenant,
                        environment,
                        organization,
                        alertConfiguration: { apiName, apiVersion, category, metric, emails, id, threshold },
                        error: errors = false
                    },
                    status: statusCode
                } = res;

                expect(statusCode).to.eq(200);
                expect(errors).to.eq(false);

                expect(tenant).to.eq(tenant);
                expect(environment).to.eq(environmentId);
                expect(organization).to.eq(organizationId);
                expect(apiName).to.eq(pizzaShackAPI.name);
                expect(apiVersion).to.eq(pizzaShackAPI.version);
                expect(category).to.eq(ALERT_TYPE.LATENCY);
                expect(metric).to.eq(LATENCY_ALERT_METRICS.RESPONSE_LATENCY);
                expect(emails.length).to.eq(0);
                expect(threshold).to.eq(thresholdValue);
            })
        );
    });

    it('Add Traffic alert config', () => {
        const thresholdValue = 10;

        const alertConfig = { ...trafficAlertConfiguration };
        alertConfig.apiName = pizzaShackAPI.name;
        alertConfig.apiVersion = pizzaShackAPI.version;
        alertConfig.threshold = thresholdValue.toString();

        cy.getStoredValue(USER_TOKEN).then((token: string) =>
            cy.addTrafficAlertConfig(token, organizationId, environmentId, tenant, alertConfig).then((res) => {
                const {
                    body: {
                        tenant,
                        environment,
                        organization,
                        alertConfiguration: { apiName, apiVersion, category, metric, emails, id, threshold },
                        error: errors = false
                    },
                    status: statusCode
                } = res;

                expect(statusCode).to.eq(200);
                expect(errors).to.eq(false);

                expect(tenant).to.eq(tenant);
                expect(environment).to.eq(environmentId);
                expect(organization).to.eq(organizationId);
                expect(apiName).to.eq(pizzaShackAPI.name);
                expect(apiVersion).to.eq(pizzaShackAPI.version);
                expect(category).to.eq(ALERT_TYPE.TRAFFIC);
                expect(metric).to.eq(TRAFFIC_ALERT_METRICS.HIT_COUNT);
                expect(emails.length).to.eq(0);
                expect(threshold).to.eq(thresholdValue);
            })
        );
    });

    after(() => {
        // wait for 5 mins since steaming jobs may take upto 5 mins to create alert stream
        cy.wait(300000);
    });
});
