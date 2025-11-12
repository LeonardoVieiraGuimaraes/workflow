"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const sdk_1 = require("@camunda8/sdk");
const workers_1 = require("./workers");
const client = new sdk_1.Camunda8({
    CAMUNDA_AUTH_STRATEGY: 'NONE',
    ZEEBE_REST_ADDRESS: 'http://localhost:8080'
}).getCamundaRestClient();
/* We inject the client to allow the workers to be tested independently using @camunda8/process-test. */
(0, workers_1.startWorkers)(client);
console.log('Job workers started. Waiting for jobs...\n');
