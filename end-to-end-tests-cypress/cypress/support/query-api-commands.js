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
    ALERTS_PAGE_QUERY,
    CACHE_PAGE_QUERY,
    DEVICES_PAGE_QUERY,
    ERRORS_BY_CATEGORY_PAGE_QUERY,
    ERRORS_BY_STATUS_CODE_PAGE_QUERY,
    LATENCY_PAGE_QUERY,
    LIST_APIS_QUERY,
    LIST_APPLICATIONS_QUERY,
    LIST_ENVIRONMENTS_QUERY,
    LIST_TENANTS_QUERY,
    OVERVIEW_PAGE_QUERY,
    TRAFFIC_PAGE_QUERY,
    ALL_PAGES_QUERY
} from './query';

Cypress.on('uncaught:exception', (err, runnable) => {
    console.log(err);
    return false;
});

Cypress.Commands.add('listEnvironmentsQuery', (queryApiUrl, queryApiToken, organizationId) => {
    return cy.request({
        method: 'POST',
        url: `${queryApiUrl}`,
        headers: {
            Authorization: `Bearer ${queryApiToken}`
        },
        body: {
            query: LIST_ENVIRONMENTS_QUERY,
            variables: {
                org: {
                    orgId: organizationId
                }
            }
        },
        failOnStatusCode: false
    });
});

Cypress.Commands.add('listTenantsQuery', (queryApiUrl, queryApiToken, organizationId, environmentId) => {
    return cy.request({
        method: 'POST',
        url: `${queryApiUrl}`,
        headers: {
            Authorization: `Bearer ${queryApiToken}`
        },
        body: {
            query: LIST_TENANTS_QUERY,
            variables: {
                tenantDataFilter: {
                    orgId: organizationId,
                    envId: environmentId
                }
            }
        },
        failOnStatusCode: false
    });
});

Cypress.Commands.add('listApisQuery', (queryApiUrl, queryApiToken, organizationId, environmentId, tenant) => {
    return cy.request({
        method: 'POST',
        url: `${queryApiUrl}`,
        headers: {
            Authorization: `Bearer ${queryApiToken}`
        },
        body: {
            query: LIST_APIS_QUERY,
            variables: {
                dataFilter: {
                    orgId: organizationId,
                    environmentId,
                    tenant
                }
            }
        },
        failOnStatusCode: false
    });
});

Cypress.Commands.add('listApplicationsQuery', (queryApiUrl, queryApiToken, organizationId, environmentId, tenant) => {
    return cy.request({
        method: 'POST',
        url: `${queryApiUrl}`,
        headers: {
            Authorization: `Bearer ${queryApiToken}`
        },
        body: {
            query: LIST_APPLICATIONS_QUERY,
            variables: {
                dataFilter: {
                    orgId: organizationId,
                    environmentId,
                    tenant
                },
                applicationFilter: {
                    owner: '',
                    apiIds: []
                }
            }
        },
        failOnStatusCode: false
    });
});

Cypress.Commands.add(
    'overviewPageQuery',
    (queryApiUrl, queryApiToken, organizationId, environmentId, tenant, timeFrom, timeTo, granularity) => {
        return cy.request({
            method: 'POST',
            url: `${queryApiUrl}`,
            headers: {
                Authorization: `Bearer ${queryApiToken}`
            },
            body: {
                query: OVERVIEW_PAGE_QUERY,
                variables: {
                    timeFilter: {
                        from: timeFrom,
                        to: timeTo
                    },
                    dataFilter: {
                        orgId: organizationId,
                        environmentId,
                        tenant
                    },
                    latencySummaryFilter: {
                        granularity
                    },
                    granularity
                }
            },
            failOnStatusCode: false
        });
    }
);

Cypress.Commands.add(
    'trafficPageQuery',
    (queryApiUrl, queryApiToken, organizationId, environmentId, tenant, timeFrom, timeTo, granularity, apiId) => {
        return cy.request({
            method: 'POST',
            url: `${queryApiUrl}`,
            headers: {
                Authorization: `Bearer ${queryApiToken}`
            },
            body: {
                query: TRAFFIC_PAGE_QUERY,
                variables: {
                    timeFilter: {
                        from: timeFrom,
                        to: timeTo
                    },
                    dataFilter: {
                        orgId: organizationId,
                        environmentId,
                        tenant
                    },
                    apiUsageOvertimeFilter: {
                        apiIds: [apiId],
                        appIds: [],
                        granularity
                    },
                    resourceUsageFilter: {
                        searchFilter: {
                            searchText: '',
                            apiIds:[]
                        },
                        paginationFilter: {
                            limit: 100,
                            offset: 0,
                            sortBy: 'count',
                            sortOrder: 'desc'
                        }
                    },
                    apiUsageByBackendOverTimeFilter: {
                        apiIds: [apiId],
                        granularity
                    }
                }
            },
            failOnStatusCode: false
        });
    }
);

Cypress.Commands.add(
    'errorsByCategoryPageQuery',
    (queryApiUrl, queryApiToken, organizationId, environmentId, tenant, timeFrom, timeTo, granularity, apiId) => {
        return cy.request({
            method: 'POST',
            url: `${queryApiUrl}`,
            headers: {
                Authorization: `Bearer ${queryApiToken}`
            },
            body: {
                query: ERRORS_BY_CATEGORY_PAGE_QUERY,
                variables: {
                    timeFilter: {
                        from: timeFrom,
                        to: timeTo
                    },
                    dataFilter: {
                        orgId: organizationId,
                        environmentId,
                        tenant
                    },
                    errorsByCategoryFilter: {
                        apiId,
                        categories: [],
                        granularity
                    },
                    errorsDetailsFilter: {
                        apiId,
                        appId: '',
                        category: '',
                        granularity,
                        searchFilter: {
                            searchText: '',
                            apiIds: []
                        },
                        paginationFilter: {
                            limit: 100,
                            offset: 0,
                            sortBy: 'count',
                            sortOrder: 'desc'
                        }
                    }
                }
            },
            failOnStatusCode: false
        });
    }
);

Cypress.Commands.add(
    'errorsByStatusCodePageQuery',
    (queryApiUrl, queryApiToken, organizationId, environmentId, tenant, timeFrom, timeTo, apiId, granularity) => {
        return cy.request({
            method: 'POST',
            url: `${queryApiUrl}`,
            headers: {
                Authorization: `Bearer ${queryApiToken}`
            },
            body: {
                query: ERRORS_BY_STATUS_CODE_PAGE_QUERY,
                variables: {
                    timeFilter: {
                        from: timeFrom,
                        to: timeTo
                    },
                    dataFilter: {
                        orgId: organizationId,
                        environmentId,
                        tenant
                    },
                    errorsByStatusCodeFilter: {
                        apiId,
                        granularity
                    },
                    errorCountByStatusCodeFilterProxy: {
                        apiId,
                        errorType: 'PROXY',
                        errorCodeType: null,
                        paginationFilter: {
                            limit: 100,
                            offset: 0,
                            sortBy: 'total',
                            sortOrder: 'desc'
                        }
                    },
                    errorCountByStatusCodeFilterTarget: {
                        apiId,
                        errorType: 'TARGET',
                        errorCodeType: null,
                        paginationFilter: {
                            limit: 100,
                            offset: 0,
                            sortBy: 'total',
                            sortOrder: 'desc'
                        }
                    }
                }
            },
            failOnStatusCode: false
        });
    }
);

Cypress.Commands.add(
    'latencyPageQuery',
    (queryApiUrl, queryApiToken, organizationId, environmentId, tenant, timeFrom, timeTo, granularity, apiId) => {
        return cy.request({
            method: 'POST',
            url: `${queryApiUrl}`,
            headers: {
                Authorization: `Bearer ${queryApiToken}`
            },
            body: {
                query: LATENCY_PAGE_QUERY,
                variables: {
                    timeFilter: {
                        from: timeFrom,
                        to: timeTo
                    },
                    dataFilter: {
                        orgId: organizationId,
                        environmentId,
                        tenant
                    },
                    latencyFilter: {
                        apiId,
                        granularity
                    },
                    limit: 10
                }
            },
            failOnStatusCode: false
        });
    }
);

Cypress.Commands.add(
    'cachePageQuery',
    (queryApiUrl, queryApiToken, organizationId, environmentId, tenant, timeFrom, timeTo, granularity, apiId) => {
        return cy.request({
            method: 'POST',
            url: `${queryApiUrl}`,
            headers: {
                Authorization: `Bearer ${queryApiToken}`
            },
            body: {
                query: CACHE_PAGE_QUERY,
                variables: {
                    timeFilter: {
                        from: timeFrom,
                        to: timeTo
                    },
                    dataFilter: {
                        orgId: organizationId,
                        environmentId,
                        tenant
                    },
                    latencySummaryFilter: {
                        apiId,
                        granularity
                    },
                    cacheFilter: {
                        apiId,
                        granularity
                    }
                }
            },
            failOnStatusCode: false
        });
    }
);

Cypress.Commands.add(
    'devicesPageQuery',
    (queryApiUrl, queryApiToken, organizationId, environmentId, tenant, timeFrom, timeTo, apiId) => {
        return cy.request({
            method: 'POST',
            url: `${queryApiUrl}`,
            headers: {
                Authorization: `Bearer ${queryApiToken}`
            },
            body: {
                query: DEVICES_PAGE_QUERY,
                variables: {
                    timeFilter: {
                        from: timeFrom,
                        to: timeTo
                    },
                    dataFilter: {
                        orgId: organizationId,
                        environmentId,
                        tenant
                    },
                    deviceFilter: {
                        apiIds: [apiId]
                    }
                }
            },
            failOnStatusCode: false
        });
    }
);

Cypress.Commands.add(
    'alertsPageQuery',
    (queryApiUrl, queryApiToken, organizationId, environmentId, tenant, timeFrom, timeTo, apiId) => {
        return cy.request({
            method: 'POST',
            url: `${queryApiUrl}`,
            headers: {
                Authorization: `Bearer ${queryApiToken}`
            },
            body: {
                query: ALERTS_PAGE_QUERY,
                variables: {
                    timeFilter: {
                        from: timeFrom,
                        to: timeTo
                    },
                    dataFilter: {
                        orgId: organizationId,
                        environmentId,
                        tenant
                    },
                    alertSummaryFilter: {
                        searchFilter: {
                            searchText: '',
                            apiIds: []
                        },
                        paginationFilter: {
                            limit: 100,
                            offset: 0,
                            sortBy: 'timestamp',
                            sortOrder: 'desc'
                        },
                        apiIds: [apiId],
                        category: ''
                    },
                    topAPIsByAlertCountFilter: {
                        searchFilter: {
                            searchText: '',
                            apiIds: []
                        },
                        paginationFilter: {
                            limit: 100,
                            offset: 0,
                            sortBy: 'count',
                            sortOrder: 'desc'
                        }
                    }
                }
            },
            failOnStatusCode: false
        });
    }
);

Cypress.Commands.add(
    'allPagesQuery',
    (queryApiUrl, queryApiToken, organizationId, environmentId, tenant, timeFrom, timeTo, granularity, apiId) => {
        return cy.request({
            method: 'POST',
            url: `${queryApiUrl}`,
            headers: {
                Authorization: `Bearer ${queryApiToken}`
            },
            body: {
                query: ALL_PAGES_QUERY,
                variables: {
                    org: {
                        orgId: organizationId
                    },
                    tenantDataFilter: {
                        orgId: organizationId,
                        envId: environmentId
                    },
                    timeFilter: {
                        from: timeFrom,
                        to: timeTo
                    },
                    dataFilter: {
                        orgId: organizationId,
                        environmentId,
                        tenant
                    },
                    applicationFilter: {
                        owner: '',
                        apiIds: []
                    },
                    latencySummaryFilterOverview: {
                        granularity
                    },
                    granularity,
                    apiUsageOvertimeFilter: {
                        apiIds: [apiId],
                        appIds: [],
                        granularity
                    },
                    resourceUsageFilter: {
                        // searchFilter: {
                        //     searchText: '',
                        //     apiIds: []
                        // },
                        paginationFilter: {
                            limit: 100,
                            offset: 0,
                            sortBy: 'count',
                            sortOrder: 'desc'
                        }
                    },
                    apiUsageByBackendOverTimeFilter: {
                        apiIds: [apiId],
                        granularity
                    },
                    errorsByCategoryFilter: {
                        apiId,
                        categories: [],
                        granularity
                    },
                    errorsDetailsFilter: {
                        apiId,
                        appId: '',
                        category: '',
                        granularity,
                        searchFilter: {
                            searchText: '',
                            apiIds: []
                        },
                        paginationFilter: {
                            limit: 100,
                            offset: 0,
                            sortBy: 'count',
                            sortOrder: 'desc'
                        }
                    },
                    errorsByStatusCodeFilter: {
                        apiId,
                        granularity
                    },
                    errorCountByStatusCodeFilterProxy: {
                        apiId,
                        errorType: 'PROXY',
                        errorCodeType: null,
                        paginationFilter: {
                            limit: 100,
                            offset: 0,
                            sortBy: 'total',
                            sortOrder: 'desc'
                        }
                    },
                    errorCountByStatusCodeFilterTarget: {
                        apiId,
                        errorType: 'TARGET',
                        errorCodeType: null,
                        paginationFilter: {
                            limit: 100,
                            offset: 0,
                            sortBy: 'total',
                            sortOrder: 'desc'
                        }
                    },
                    latencyFilter: {
                        apiId,
                        granularity
                    },
                    limit: 10,
                    latencySummaryFilter: {
                        apiId,
                        granularity
                    },
                    cacheFilter: {
                        apiId,
                        granularity
                    },
                    deviceFilter: {
                        apiIds: [apiId]
                    },
                    alertSummaryFilter: {
                        searchFilter: {
                            searchText: '',
                            apiIds: []
                        },
                        paginationFilter: {
                            limit: 100,
                            offset: 0,
                            sortBy: 'timestamp',
                            sortOrder: 'desc'
                        },
                        apiIds: [apiId],
                        category: ''
                    },
                    topAPIsByAlertCountFilter: {
                        searchFilter: {
                            searchText: '',
                            apiIds: []
                        },
                        paginationFilter: {
                            limit: 100,
                            offset: 0,
                            sortBy: 'count',
                            sortOrder: 'desc'
                        }
                    }
                }
            },
            log: true,
            failOnStatusCode: false
        });
    }
);
