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
import { differenceInHours } from 'date-fns';
import { format } from 'date-fns-tz';

import {
    APIM_DIST_LOCATION,
    USER_TOKEN,
    UTC_BROWSER_LOCALE_FORMAT,
    ALERT_TYPE,
    TRAFFIC_ALERT_METRICS,
    LATENCY_ALERT_METRICS
} from './constants';

// data filter constants used
const analyticsQueryApiUrl = Cypress.env('analyticsQueryApiUrl');
const organizationId = Cypress.env('organizationId');
const environmentId = Cypress.env('environmentId');
const tenant = Cypress.env('tenant');

let existingStats;
let existingStatsTimeHourAgo;
let pizzaShackAPI;
let pizzaApplication;
let initialDateFrom;

describe('Query existing analytics data', () => {
    before(() => {
        cy.consoleUserLogin();
    });

    it('Overview Page', () => {
        const date = new Date();
        initialDateFrom = format(new Date(date.setHours(date.getHours() - 24)), UTC_BROWSER_LOCALE_FORMAT);
        const dateTo = format(new Date(), UTC_BROWSER_LOCALE_FORMAT);
        const granularity = '1h';

        cy.getStoredValue(USER_TOKEN).then((token: string) => {
            cy.overviewPageQuery(
                analyticsQueryApiUrl,
                token,
                organizationId,
                environmentId,
                tenant,
                initialDateFrom,
                dateTo,
                granularity
            ).then((res) => {
                const {
                    body: {
                        data: {
                            getTotalTraffic,
                            getTotalErrors,
                            getSuccessSummary: { summary: requestCountSummary = [] } = {},
                            getErrorSummary: { summary: errorCountSummary = [] } = {}
                        }
                    }
                } = res;

                let requestCount = 0;
                let errorCount = 0;

                const timeHourAgo = new Date();
                timeHourAgo.setMinutes(0);
                timeHourAgo.setSeconds(0);
                timeHourAgo.setMilliseconds(0);
                existingStatsTimeHourAgo = timeHourAgo;

                if (requestCountSummary.length > 0) {
                    const filteredRequestCountSummary = requestCountSummary
                        .sort((a, b) => new Date(b.timeSpan).getTime() - new Date(a.timeSpan).getTime())
                        .find((data) => differenceInHours(new Date(data.timeSpan), timeHourAgo) == 0);
                    if (filteredRequestCountSummary) {
                        requestCount = filteredRequestCountSummary.requestCount;
                    }
                }

                if (errorCountSummary.length > 0) {
                    const sortedErrorCountSummary = errorCountSummary
                        .sort((a, b) => new Date(b.timeSpan).getTime() - new Date(a.timeSpan).getTime())
                        .find((data) => differenceInHours(new Date(data.timeSpan), timeHourAgo) == 0);
                    if (sortedErrorCountSummary) {
                        errorCount = sortedErrorCountSummary.errorCount;
                    }
                }

                existingStats = {
                    overview: {
                        getTotalTraffic,
                        getTotalErrors,
                        requestCount,
                        errorCount
                    }
                };
            });
        });
    });
});

describe('Publish data from gateway', { execTimeout: 600000 }, () => {
    let publishedEvents = 0;

    it('Publish data', () => {
        cy.exec('sh cypress/scripts/data_publish.sh');

        /*
        cy.readFile() requires file to be present, therefore use a custom task incase the was an issue in server
        start which would result in log file being unavailable
        */
        cy.task('readFile', APIM_DIST_LOCATION + '/wso2am-4.0.0/repository/logs/wso2carbon.log').then(
            (data: string) => {
                const publishLogs = data.match(/Flushed (\d)+ events to Analytics cluster/g);
                if (publishLogs) {
                    publishedEvents = publishLogs.reduce((acc, cur) => {
                        const number = cur.replace(/[^0-9]/gi, '');
                        if (number) {
                            return (acc += parseInt(number, 10));
                        } else {
                            return acc;
                        }
                    }, 0);
                }

                expect(publishedEvents).to.eq(100);
            }
        );
    });

    after(() => {
        if (!(publishedEvents === 100)) {
            // @ts-ignore
            Cypress.runner.stop();
        }
    });

    after(() => {
        cy.fixture('sp.json').then((spData) => {
            cy.addSP(spData, Buffer.from('admin:admin').toString('base64')).then((spResponse) => {
                const {
                    body: { clientId, clientSecret }
                } = spResponse;
                cy.getAccessToken(Buffer.from(clientId + ':' + clientSecret).toString('base64')).then(
                    (tokenResponse) => {
                        const {
                            body: { access_token }
                        } = tokenResponse;
                        cy.getApis(access_token).then((apiResponse) => {
                            const {
                                body: { list: [api] = [] }
                            } = apiResponse;
                            pizzaShackAPI = api;
                        });
                        cy.getApplications(access_token).then((appResponse) => {
                            const {
                                body: {
                                    list: [app]
                                }
                            } = appResponse;

                            pizzaApplication = app;
                        });
                    }
                );
            });
        });
    });
});

describe('Validate data', () => {
    let responseData;

    before(() => {
        cy.consoleUserLogin();
    });

    it('Query data', () => {
        const dateTo = format(new Date(), UTC_BROWSER_LOCALE_FORMAT);
        const granularity = '1h';

        cy.getStoredValue(USER_TOKEN).then((analyticsToken: string) => {
            cy.allPagesQuery(
                analyticsQueryApiUrl,
                analyticsToken,
                organizationId,
                environmentId,
                tenant,
                initialDateFrom,
                dateTo,
                granularity,
                pizzaShackAPI.id
            ).then((res) => {
                const {
                    body: { data, error: errors = false, errors: gqlErrors = [] },
                    status: statusCode
                } = res;
                responseData = data;

                expect(statusCode).to.eq(200);
                expect(errors).to.eq(false);
                expect(gqlErrors.length).to.eq(0);
            });
        });
    });
    it('General : Environment list', () => {
        const { listEnvironments } = responseData;
        expect(listEnvironments.length).to.be.greaterThan(0);
        expect(listEnvironments.some((val) => val.id === environmentId)).to.eq(true);
    });

    it('General : Tenants list', () => {
        const { listTenants } = responseData;
        expect(listTenants.length).to.be.greaterThan(0);
        expect(listTenants.some((val) => val === tenant)).to.eq(true);
    });

    it('General : APIs list', () => {
        const { listAllAPI } = responseData;

        expect(listAllAPI.length).to.be.greaterThan(0);
        expect(listAllAPI.some((val) => val.id === pizzaShackAPI.id)).to.eq(true);
    });

    it('General : Applications list', () => {
        const { listApplications } = responseData;

        expect(listApplications.length).to.be.greaterThan(0);
        expect(listApplications.some((val) => val.id === pizzaApplication.applicationId)).to.eq(true);
    });

    it('Overview page', () => {
        const {
            getTotalTraffic,
            getOverallLatency,
            getTotalErrors,
            getLatencySummary: { summary: latencyTimeSummary = [] } = {},
            getSuccessSummary: { summary: requestCountSummary = [] } = {},
            getErrorSummary: { summary: errorCountSummary = [] } = {}
        } = responseData;

        let latencyTime = 0;
        let requestCount = 0;
        let errorCount = 0;

        const timeHourAgo = new Date();
        timeHourAgo.setMinutes(0);
        timeHourAgo.setSeconds(0);
        timeHourAgo.setMilliseconds(0);

        if (latencyTimeSummary.length > 0) {
            const filteredLatencyTimeSummary = latencyTimeSummary
                .sort((a, b) => new Date(b.timeSpan).getTime() - new Date(a.timeSpan).getTime())
                .find((data) => differenceInHours(new Date(data.timeSpan), timeHourAgo) == 0);
            if (filteredLatencyTimeSummary) {
                latencyTime = filteredLatencyTimeSummary.latencyTime;
            }
        }

        if (requestCountSummary.length > 0) {
            const filteredRequestCountSummary = requestCountSummary
                .sort((a, b) => new Date(b.timeSpan).getTime() - new Date(a.timeSpan).getTime())
                .find((data) => differenceInHours(new Date(data.timeSpan), timeHourAgo) == 0);
            if (filteredRequestCountSummary) {
                requestCount = filteredRequestCountSummary.requestCount;
            }
        }

        if (errorCountSummary.length > 0) {
            const sortedErrorCountSummary = errorCountSummary
                .sort((a, b) => new Date(b.timeSpan).getTime() - new Date(a.timeSpan).getTime())
                .find((data) => differenceInHours(new Date(data.timeSpan), timeHourAgo) == 0);
            if (sortedErrorCountSummary) {
                errorCount = sortedErrorCountSummary.errorCount;
            }
        }

        if (existingStats.overview) {
            expect(getTotalTraffic).to.eq(existingStats.overview.getTotalTraffic + 100);
            expect(getOverallLatency).to.be.greaterThan(0);
            expect(getTotalErrors).to.eq(existingStats.overview.getTotalErrors + 20);
            expect(latencyTime).to.be.greaterThan(0);

            if (differenceInHours(timeHourAgo, existingStatsTimeHourAgo) === 0) {
                expect(requestCount).to.eq(existingStats.overview.requestCount + 80);
                expect(errorCount).to.eq(existingStats.overview.errorCount + 20);
            } else {
                expect(requestCount).to.eq(80);
                expect(errorCount).to.eq(20);
            }
        } else {
            expect(1).to.eq(1);
            expect(getTotalTraffic).to.eq(100);
            expect(getOverallLatency).to.be.greaterThan(0);
            expect(getTotalErrors).to.eq(20);
            expect(latencyTime).to.be.greaterThan(0);
            expect(requestCount).to.eq(80);
            expect(errorCount).to.eq(20);
        }
    });

    it('Traffic page', () => {
        const {
            getAPIUsageByAppOverTime: { usage: apiUsageByApp = [] } = {},
            getAPIUsageByBackendOverTime: {
                usage: [{ usage: [{ count: apiUsageByBackend = 0 } = {}] = [] } = {}] = []
            } = {},
            getAPIUsageOverTime: [{ usage: [{ count: apiUsageOverTime = 0 } = {}] = [] }] = [],
            getResourceUsage: { usage: allApiResourceUsage = [] } = {}
        } = responseData;

        expect(apiUsageByApp.length).to.eq(2);
        expect(apiUsageByApp.some((val) => val.applicationName === 'UNKNOWN' && val.usage[0].count === 20)).to.eq(true);
        expect(
            apiUsageByApp.some((val) => val.applicationName === pizzaApplication.name && val.usage[0].count === 80)
        ).to.eq(true);

        expect(apiUsageByBackend).to.eq(80);
        expect(apiUsageOverTime).to.eq(100);

        const apiResourceUsage = allApiResourceUsage.filter((usage) => usage.apiId === pizzaShackAPI.id);
        expect(apiResourceUsage.length).to.eq(2);
        expect(
            apiResourceUsage.some(
                (val) =>
                    val.apiId === pizzaShackAPI.id &&
                    val.apiMethod === 'GET' &&
                    val.apiResourceTemplate === '/menu' &&
                    val.count === 80
            )
        ).to.eq(true);
        expect(
            apiResourceUsage.some(
                (val) =>
                    val.apiId === pizzaShackAPI.id &&
                    val.apiMethod === '' &&
                    val.apiResourceTemplate === '' &&
                    val.count === 20
            )
        ).to.eq(true);
    });

    it('Errors by Category Page', () => {
        const {} = responseData;
        const date = new Date();
        const dateFrom = format(new Date(date.setHours(date.getHours() - 24)), UTC_BROWSER_LOCALE_FORMAT);
        const dateTo = format(new Date(), UTC_BROWSER_LOCALE_FORMAT);
        const granularity = '1h';

        cy.getStoredValue(USER_TOKEN).then((token: string) => {
            cy.errorsByCategoryPageQuery(
                analyticsQueryApiUrl,
                token,
                organizationId,
                environmentId,
                tenant,
                dateFrom,
                dateTo,
                granularity,
                pizzaShackAPI.id
            ).then((res) => {
                const {
                    body: {
                        data: {
                            getErrorsByCategory: {
                                errors: [{ auth = 0, other = 0, targetConnectivity = 0, throttled = 0 } = {}] = []
                            } = {},
                            getErrorsDetails: { usage: [{ count = 0, reason = '' } = {}] = [] } = {}
                        },
                        error: errors = false,
                        errors: gqlErrors = []
                    },
                    status: statusCode
                } = res;

                expect(statusCode).to.eq(200);
                expect(errors).to.eq(false);
                expect(gqlErrors.length).to.eq(0);

                expect(auth).to.eq(20);
                expect(other).to.eq(0);
                expect(targetConnectivity).to.eq(0);
                expect(throttled).to.eq(0);

                expect(count).to.eq(20);
                expect(reason).to.eq('AUTH');
            });
        });
    });

    it('Errors by Status Code Page', () => {
        const {} = responseData;
        const date = new Date();
        const dateFrom = format(new Date(date.setHours(date.getHours() - 24)), UTC_BROWSER_LOCALE_FORMAT);
        const dateTo = format(new Date(), UTC_BROWSER_LOCALE_FORMAT);
        const granularity = '1h';

        cy.getStoredValue(USER_TOKEN).then((token: string) => {
            cy.errorsByStatusCodePageQuery(
                analyticsQueryApiUrl,
                token,
                organizationId,
                environmentId,
                tenant,
                dateFrom,
                dateTo,
                pizzaShackAPI.id,
                granularity
            ).then((res) => {
                const {
                    body: {
                        data: {
                            getErrorsByStatusCodeProxy: { errors: [{ errorCountByCode = [] } = {}] = [] } = {},
                            getErrorsByStatusCodeTarget: { errors: targetErrors = [] } = {},
                            getProxyTargetErrorsOverTime: [
                                { proxy = { total: 0, _4xx: 0, _5xx: 0 }, target = { total: 0, _4xx: 0, _5xx: 0 } } = {}
                            ]
                        },
                        error: errors = false,
                        errors: gqlErrors = []
                    },
                    status: statusCode
                } = res;

                expect(statusCode).to.eq(200);
                expect(errors).to.eq(false);
                expect(gqlErrors.length).to.eq(0);

                expect(errorCountByCode.length).to.eq(2);
                expect(errorCountByCode.some((val) => val.statusCode === '401' && val.count === 20)).to.eq(true);
                expect(errorCountByCode.some((val) => val.statusCode === 'total' && val.count === 20)).to.eq(true);

                expect(targetErrors.length).to.eq(0);

                expect(proxy.total).to.eq(20);
                expect(proxy._4xx).to.eq(20);
                expect(proxy._5xx).to.eq(0);
                expect(target.total).to.eq(0);
                expect(target._4xx).to.eq(0);
                expect(target._5xx).to.eq(0);
            });
        });
    });

    it('Latency page', () => {
        const {
            getLatency: {
                summary: [
                    {
                        backend = 0,
                        backendMedian = 0,
                        requestMediation = 0,
                        requestMediationMedian = 0,
                        response = 0,
                        responseMedian = 0
                    } = {}
                ] = []
            } = {},
            topSlowestAPIs
        } = responseData;

        expect(backend).to.be.greaterThan(0);
        expect(backendMedian).to.be.greaterThan(0);
        expect(requestMediation).to.be.greaterThan(0);
        expect(requestMediationMedian).to.be.greaterThan(0);
        expect(response).to.be.greaterThan(0);
        expect(responseMedian).to.be.greaterThan(0);

        expect(topSlowestAPIs.length).to.be.greaterThan(0);
    });

    it('Cache Page', () => {
        const {
            getCacheHitsAndMisses: { summary: [{ hitPercentage = 0, hits = 0, misses = 0 } = {}] = [] } = {},
            getLatencySummary: { summary: [{ latencyTime = 0 } = {}] = [] } = {}
        } = responseData;

        expect(hitPercentage).to.eq(0);
        expect(hits).to.eq(0);
        expect(misses).to.eq(80);
        expect(latencyTime).to.greaterThan(0);
    });

    it('Devices Page', () => {
        const { getTopPlatforms: platforms, getTopUserAgents: userAgents } = responseData;

        expect(platforms.length).to.eq(3);
        expect(platforms.some((val) => val.platform === 'Linux' && val.count === 40)).to.eq(true);
        expect(platforms.some((val) => val.platform === 'Mac OS X' && val.count === 40)).to.eq(true);
        expect(platforms.some((val) => val.platform === 'Other' && val.count === 0)).to.eq(true);

        expect(userAgents.length).to.eq(3);
        expect(userAgents.some((val) => val.userAgent === 'Chrome' && val.count === 40)).to.eq(true);
        expect(userAgents.some((val) => val.userAgent === 'Firefox' && val.count === 40)).to.eq(true);
        expect(userAgents.some((val) => val.userAgent === 'Other' && val.count === 0)).to.eq(true);
    });

    it('Alerts Page', () => {
        const {
            getAlertSummary: { usage: alertSummary },
            getTopAPIsByAlertCount: { usage: topAPIsByAlertCount }
        } = responseData;

        // test Traffic alert
        expect(
            alertSummary.some((alert) => {
                return (
                    alert.apiId === pizzaShackAPI.id &&
                    alert.category === ALERT_TYPE.TRAFFIC &&
                    alert.metric === TRAFFIC_ALERT_METRICS.HIT_COUNT
                );
            })
        ).to.eq(true);
        // test Latency alert
        expect(
            alertSummary.some((alert) => {
                return (
                    alert.apiId === pizzaShackAPI.id &&
                    alert.category === ALERT_TYPE.LATENCY &&
                    alert.metric === LATENCY_ALERT_METRICS.RESPONSE_LATENCY
                );
            })
        ).to.eq(true);
        expect(
            topAPIsByAlertCount.some((alert) => {
                return alert.apiId === pizzaShackAPI.id && alert.count === 2;
            })
        ).to.eq(true);
    });
});
