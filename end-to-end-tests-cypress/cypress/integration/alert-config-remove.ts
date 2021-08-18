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

import { USER_TOKEN, pizzaShackAPI } from './constants';

// data filter constants used
const organizationId = Cypress.env('organizationId');
const environmentId = Cypress.env('environmentId');
const tenant = Cypress.env('tenant');

describe('Delete alert config', () => {
    before(() => {
        cy.consoleUserLogin();
    });

    it('Delete alert config', () => {
        cy.getStoredValue(USER_TOKEN).then((token: string) => {
            cy.getLatencyAlertConfig(token, organizationId, environmentId, tenant).then((res) => {
                const {
                    status: statusCode,
                    body: { alertConfiguration }
                } = res;

                expect(statusCode).to.eq(200);
                const alertConfig = alertConfiguration.find((config) => {
                    return config.apiName === pizzaShackAPI.name && config.apiVersion === pizzaShackAPI.version;
                });

                if (alertConfig) {
                    cy.deleteLatencyAlertConfig(token, organizationId, environmentId, tenant, alertConfig.id).then(
                        (res) => {
                            const { status: statusCode } = res;

                            expect(statusCode).to.eq(200);
                        }
                    );
                }
            });

            cy.getTrafficAlertConfig(token, organizationId, environmentId, tenant).then((res) => {
                const {
                    status: statusCode,
                    body: { alertConfiguration }
                } = res;

                expect(statusCode).to.eq(200);
                const alertConfig = alertConfiguration.find((config) => {
                    return config.apiName === pizzaShackAPI.name && config.apiVersion === pizzaShackAPI.version;
                });

                if (alertConfig) {
                    cy.deleteTrafficAlertConfig(token, organizationId, environmentId, tenant, alertConfig.id).then(
                        (res) => {
                            const { status: statusCode } = res;

                            expect(statusCode).to.eq(200);
                        }
                    );
                }
            });
        });
    });
});
