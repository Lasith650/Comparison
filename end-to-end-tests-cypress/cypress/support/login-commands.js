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
import qs from 'qs';
import { USER_INFO, USER_TOKEN } from '../integration/constants';

Cypress.on('uncaught:exception', (err, runnable) => {
    console.log(err);
    return false;
});

Cypress.Commands.add('consoleUserLogin', () => {
    const idpURL = Cypress.env('idpURL');
    const idpUsername = Cypress.env('idpUsername');
    const idpPassword = Cypress.env('idpPassword');
    const idpAuthHeader = Cypress.env('idpAuthHeader');
    const name = Cypress.env('name');
    const email = Cypress.env('email');
    const picURL = Cypress.env('picURL');
    const orgs = Cypress.env('orgs');
    const analyticsQueryApiUrl = Cypress.env('analyticsQueryApiUrl');
    try {
        cy.request({
            method: 'POST',
            url: idpURL,
            form: true,
            body: qs.stringify({
                grant_type: "password",
                scope: "name openid user",
                username: idpUsername,
                password: idpPassword,
            }),
            headers: {
                Authorization: idpAuthHeader,
            }
        }
        ).then((response) => {
            const data = response["body"];
            cy.log('Data received from the IDP');
            const fragments = data["access_token"].split(".");
            const token = fragments[0] + "." + fragments[1];
            const cwatf = fragments[2];

            Cypress.Cookies.defaults({
                preserve: ['cwatf']
            });

            localStorage.setItem(
                USER_INFO,
                JSON.stringify({
                    name: name,
                    email: email,
                    token: token,
                    picURL: picURL,
                    orgs: orgs,
                })
            );
            cy.setStoredValue(USER_TOKEN, token);
            cy.setCookie('cwatf', cwatf, { domain: analyticsQueryApiUrl.substring(8, analyticsQueryApiUrl.length - 10) });
            cy.log('Local storage set successful!');
        });
    } catch (err) {
        throw new Error("Retrieving Access token failed : " + err);
    }
});

// todo social login commands needs to be verified and used when testing portal

// Cypress.Commands.add('userLoginWithGmail', () => {
//     const username = Cypress.env('username');
//     const password = Cypress.env('password');
//     const loginUrl = Cypress.env('loginURL');
//     const cookieName = Cypress.env('analyticsCookies');
//     const socialLoginOptions = {
//         username: Cypress.env('username'),
//         password: Cypress.env('password'),
//         loginUrl: Cypress.env('loginURL'),
//         headless: false,
//         logs: true,
//         loginSelector: '[id="google-sign-in"]',
//         // postLoginSelector: '[id="current-user"]',
//         popupDelay: 2000,
//         cookieDelay: 2000,
//         args: ['--no-sandbox'],
//         isPopup: false,
//         getAllBrowserCookies: true
//     };

//     cy.clearCookies();
//     cy.task('GoogleSocialLogin', socialLoginOptions).then(({ cookies, lsd, ssd }) => {

//         cookies.map((cookie) => {
//             cy.setCookie(cookie.name, cookie.value, {
//                 domain: cookie.domain,
//                 expiry: cookie.expires,
//                 httpOnly: cookie.httpOnly,
//                 path: cookie.path,
//                 secure: cookie.secure
//             });

//             Cypress.Cookies.defaults({
//                 preserve: cookie.name
//             });
//         });

//         cy.window().then(window => {
//             Object.keys(ssd).forEach(key => window.sessionStorage.setItem(key, ssd[key]));
//             Object.keys(lsd).forEach(key => window.localStorage.setItem(key, lsd[key]));
//         });

//         cy.log('login successful. Visiting to APIM Analytics Portal');
//         cy.visit('/');

//     });
// });

// Cypress.Commands.add('userLoginWithGithub', () => {
//     const username = Cypress.env('username');
//     const password = Cypress.env('password');
//     const loginUrl = Cypress.env('loginURL');
//     const cookieName = Cypress.env('analyticsCookies');
//     const socialLoginOptions = {
//         username: Cypress.env('username'),
//         password: Cypress.env('password'),
//         loginUrl: Cypress.env('loginURL'),
//         headless: false,
//         logs: true,
//         loginSelector: '[id="github-sign-in"]',
//         postLoginSelector: '[id="current-user"]',
//         popupDelay: 2000,
//         cookieDelay: 2000,
//         args: ['--no-sandbox'],
//         isPopup: false,
//         getAllBrowserCookies: true
//     };

//     cy.clearCookies();
//     return cy.task('GitHubSocialLogin', socialLoginOptions).then(({ cookies, lsd, ssd }) => {
//         cy.log('cokies: ', cookies);
//         // cy.clearCookies()

//         const cookie = cookies.filter(cookie => cookie.name === cookieName).pop();
//         if (cookie) {
//             cy.setCookie(cookie.name, cookie.value, {
//                 domain: cookie.domain,
//                 expiry: cookie.expires,
//                 httpOnly: cookie.httpOnly,
//                 path: cookie.path,
//                 secure: cookie.secure
//             });

//             Cypress.Cookies.defaults({
//                 preserve: cookieName
//             });
//         }

//         cy.window().then(window => {
//             Object.keys(ssd).forEach(key => window.sessionStorage.setItem(key, ssd[key]));
//             Object.keys(lsd).forEach(key => window.localStorage.setItem(key, lsd[key]));
//         });

//         cy.log('login successful. Visiting APIM Analytics Portal');
//         cy.visit('/');
//     });
// });

// Cypress.Commands.add('userLogout', () => {
//     cy.log("Logout from APIM Analytics Portal");
//     cy.get('[id="current-user"]').click();
//     cy.contains('Logout').click();
//     cy.url().should('include', '/login');
//     cy.clearCookies();
//     cy.clearLocalStorage();
//     cy.window().then((win) => {
//         win.sessionStorage.clear();
//     });
//     cy.log("logout successfully");
// });
