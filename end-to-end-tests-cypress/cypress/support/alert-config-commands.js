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

Cypress.on('uncaught:exception', (err, runnable) => {
    console.log(err);
    return false;
});

const alertApiUrl = Cypress.env('analyticsAlertsApiUrl');

Cypress.Commands.add(
    'addLatencyAlertConfig',
    (alertApiToken, organizationId, environmentId, tenant, alertConfiguration) => {
        return cy.request({
            method: 'POST',
            url: `${alertApiUrl}/latencyConfigs?organization=${organizationId}&environment=${environmentId}&tenant=${tenant}`,
            headers: {
                Authorization: `Bearer ${alertApiToken}`
            },
            body: {
                alertConfiguration
            },
            failOnStatusCode: false
        });
    }
);

Cypress.Commands.add(
    'addTrafficAlertConfig',
    (alertApiToken, organizationId, environmentId, tenant, alertConfiguration) => {
        return cy.request({
            method: 'POST',
            url: `${alertApiUrl}/trafficConfigs?organization=${organizationId}&environment=${environmentId}&tenant=${tenant}`,
            headers: {
                Authorization: `Bearer ${alertApiToken}`
            },
            body: {
                alertConfiguration
            },
            failOnStatusCode: false
        });
    }
);

Cypress.Commands.add('deleteLatencyAlertConfig', (alertApiToken, organizationId, environmentId, tenant, configId) => {
    return cy.request({
        method: 'DELETE',
        url: `${alertApiUrl}/latencyConfigs/${configId}?organization=${organizationId}&environment=${environmentId}&tenant=${tenant}`,
        headers: {
            Authorization: `Bearer ${alertApiToken}`
        },
        failOnStatusCode: false
    });
});

Cypress.Commands.add('deleteTrafficAlertConfig', (alertApiToken, organizationId, environmentId, tenant, configId) => {
    return cy.request({
        method: 'DELETE',
        url: `${alertApiUrl}/trafficConfigs/${configId}?organization=${organizationId}&environment=${environmentId}&tenant=${tenant}`,
        headers: {
            Authorization: `Bearer ${alertApiToken}`
        },
        failOnStatusCode: false
    });
});

Cypress.Commands.add('getLatencyAlertConfig', (alertApiToken, organizationId, environmentId, tenant) => {
    return cy.request({
        method: 'GET',
        url: `${alertApiUrl}/latencyConfigs?organization=${organizationId}&environment=${environmentId}&tenant=${tenant}`,
        headers: {
            Authorization: `Bearer ${alertApiToken}`
        },
        failOnStatusCode: false
    });
});

Cypress.Commands.add('getTrafficAlertConfig', (alertApiToken, organizationId, environmentId, tenant) => {
    return cy.request({
        method: 'GET',
        url: `${alertApiUrl}/trafficConfigs?organization=${organizationId}&environment=${environmentId}&tenant=${tenant}`,
        headers: {
            Authorization: `Bearer ${alertApiToken}`
        },
        failOnStatusCode: false
    });
});
