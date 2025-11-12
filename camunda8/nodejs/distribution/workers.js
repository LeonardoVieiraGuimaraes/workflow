"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.startWorkers = startWorkers;
/**
 * This file contains the job workers for the Camunda 8 process.
 * It defines three job workers: check-inventory, charge-payment, and ship-items.
 * Each worker processes jobs of a specific type and simulates some work.
 */
const sdk_1 = require("@camunda8/sdk");
// This is a Dto class that defines the input variables for the job worker.
class Variables extends sdk_1.Dto.LosslessDto {
}
/** Client is injected to allow testing with @camunda8/process-test */
function startWorkers(client) {
    const inventoryWorker = client.createJobWorker({
        type: 'check-inventory',
        timeout: 10000, // Timeout for the job worker to complete the job before it is available for another worker poll
        maxJobsToActivate: 5, // Maximum number of jobs to process concurrently
        worker: 'check-inventory-worker',
        jobHandler: (job, log) => __awaiter(this, void 0, void 0, function* () {
            var _a;
            log.info('Processing check-inventory job:', job.jobKey);
            const item = (_a = job.variables.item) !== null && _a !== void 0 ? _a : 'default-item';
            log.info(`Checking inventory for item: ${item}`);
            // Simulate checking inventory
            yield new Promise((resolve) => setTimeout(resolve, 2000));
            log.info(`check-inventory job completed: ${job.jobKey}`);
            return job.complete({ item: `${item} allocated` });
        })
    });
    const paymentChargeWorker = client.createJobWorker({
        type: 'charge-payment',
        timeout: 10000,
        maxJobsToActivate: 5,
        worker: 'charge-payment-worker',
        jobHandler: (job, log) => __awaiter(this, void 0, void 0, function* () {
            log.info('Processing charge-payment job:', job.jobKey);
            // Simulate some work
            yield new Promise((resolve) => setTimeout(resolve, 2000));
            log.info(`charge-payment job completed: ${job.jobKey}`);
            return job.complete();
        })
    });
    const shippingWorker = client.createJobWorker({
        type: 'ship-items',
        timeout: 10000,
        maxJobsToActivate: 5,
        worker: 'ship-items-worker',
        jobHandler: (job, log) => __awaiter(this, void 0, void 0, function* () {
            log.info('Processing ship-items job:', job.jobKey);
            // Simulate some work
            yield new Promise((resolve) => setTimeout(resolve, 2000));
            log.info(`ship-items job completed: ${job.jobKey}`);
            return job.complete();
        })
    });
    return {
        inventoryWorker,
        paymentChargeWorker,
        shippingWorker
    };
}
