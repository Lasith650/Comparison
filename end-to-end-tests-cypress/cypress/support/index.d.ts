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

declare namespace Cypress {
    interface Chainable {
        consoleUserLogin(): Chainable<Element>;
        // userLoginWithGmail(): Chainable<Element>,
        // userLoginWithGithub(): Chainable<Element>,
        listEnvironmentsQuery(queryApiUrl: string, queryApiToken: string, organizationId: string): Chainable<Response>;
        listTenantsQuery(
            queryApiUrl: string,
            queryApiToken: string,
            organizationId: string,
            environmentId: string
        ): Chainable<Response>;
        listApisQuery(
            queryApiUrl: string,
            queryApiToken: string,
            organizationId: string,
            environmentId: string,
            tenant: string
        ): Chainable<Response>;
        listApplicationsQuery(
            queryApiUrl: string,
            queryApiToken: string,
            organizationId: string,
            environmentId: string,
            tenant: string
        ): Chainable<Response>;
        overviewPageQuery(
            queryApiUrl: string,
            queryApiToken: string,
            organizationId: string,
            environmentId: string,
            tenant: string,
            timeFrom: string,
            timeTo: string,
            granularity: string
        ): Chainable<Response>;
        trafficPageQuery(
            queryApiUrl: string,
            queryApiToken: string,
            organizationId: string,
            environmentId: string,
            tenant: string,
            timeFrom: string,
            timeTo: string,
            granularity: string,
            apiId: string
        ): Chainable<Response>;
        errorsByCategoryPageQuery(
            queryApiUrl: string,
            queryApiToken: string,
            organizationId: string,
            environmentId: string,
            tenant: string,
            timeFrom: string,
            timeTo: string,
            granularity: string,
            apiId: string
        ): Chainable<Response>;
        errorsByStatusCodePageQuery(
            queryApiUrl: string,
            queryApiToken: string,
            organizationId: string,
            environmentId: string,
            tenant: string,
            timeFrom: string,
            timeTo: string,
            apiId: string,
            granularity: string
        ): Chainable<Response>;
        latencyPageQuery(
            queryApiUrl: string,
            queryApiToken: string,
            organizationId: string,
            environmentId: string,
            tenant: string,
            timeFrom: string,
            timeTo: string,
            granularity: string,
            apiId: string
        ): Chainable<Response>;
        cachePageQuery(
            queryApiUrl: string,
            queryApiToken: string,
            organizationId: string,
            environmentId: string,
            tenant: string,
            timeFrom: string,
            timeTo: string,
            granularity: string,
            apiId: string
        ): Chainable<Response>;
        devicesPageQuery(
            queryApiUrl: string,
            queryApiToken: string,
            organizationId: string,
            environmentId: string,
            tenant: string,
            timeFrom: string,
            timeTo: string,
            apiId: string
        ): Chainable<Response>;
        alertsPageQuery(
            queryApiUrl: string,
            queryApiToken: string,
            organizationId: string,
            environmentId: string,
            tenant: string,
            timeFrom: string,
            timeTo: string,
            apiId: string
        ): Chainable<Response>;
        allPagesQueries(
            queryApiUrl: string,
            queryApiToken: string,
            organizationId: string,
            environmentId: string,
            tenant: string,
            timeFrom: string,
            timeTo: string,
            granularity: string
        ): Chainable<Response>;
        addLatencyAlertConfig(
            accessToken: string,
            organizationId: string,
            environmentId: string,
            tenant: string,
            alertConfiguration: any
        ): Chainable<Response>;
        addTrafficAlertConfig(
            accessToken: string,
            organizationId: string,
            environmentId: string,
            tenant: string,
            alertConfiguration: any
        ): Chainable<Response>;
        deleteLatencyAlertConfig(
            accessToken: string,
            organizationId: string,
            environmentId: string,
            tenant: string,
            alertConfigId: any
        ): Chainable<Response>;
        deleteTrafficAlertConfig(
            accessToken: string,
            organizationId: string,
            environmentId: string,
            tenant: string,
            alertConfigId: any
        ): Chainable<Response>;
        getStoredValue(name: string): Chainable<any>;
        setStoredValue(name: string, value: any): Chainable<any>;
        addSP(spData: string, token: string): Chainable<Response>;
        getAccessToken(token: string): Chainable<Response>;
        getApis(accessToken: string): Chainable<Response>;
        getApplications(accessToken: string): Chainable<Response>;
        allPagesQuery(
            queryApiUrl: string,
            queryApiToken: string,
            organizationId: string,
            environmentId: string,
            tenant: string,
            timeFrom: string,
            timeTo: string,
            granularity: string,
            apiId: string
        ): Chainable<Response>;
        getLatencyAlertConfig(
            accessToken: string,
            organizationId: string,
            environmentId: string,
            tenant: string
        ): Chainable<Response>;
        getTrafficAlertConfig(
            accessToken: string,
            organizationId: string,
            environmentId: string,
            tenant: string
        ): Chainable<Response>;
    }
}
