# end-to-end-tests-cypress

## Setup
1. Proceed to `end-to-end-tests-cypress` and run 
`npm install`

2. Configure the properties in `cypress.env.json`

3. Update the `[apim.analytics]` configuration in `cypress/resources/publisher/gateway/deployment.toml` file

4. Run the following command to run the tests,
	a. In Interactive mode:
		`npx cypress open`

	b. In Headless mode (this will create videos and reports. Screenshotos will be created if the test fails):
		`npm run test`
