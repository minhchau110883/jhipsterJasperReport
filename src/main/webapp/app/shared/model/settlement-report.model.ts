import { Moment } from 'moment';

export interface ISettlementReport {
    id?: number;
    partnerTransactionId?: string;
    transactionId?: string;
    contactId?: string;
    outletId?: string;
    outletName?: string;
    amount?: number;
    fee?: number;
    vat?: number;
    feeIncluded?: number;
    settlement?: number;
    currency?: string;
    wht?: number;
    paymentTime?: Moment;
    settlementTime?: Moment;
    type?: string;
    sof?: string;
    merchantId?: string;
    paymentChannel?: string;
}

export class SettlementReport implements ISettlementReport {
    constructor(
        public id?: number,
        public partnerTransactionId?: string,
        public transactionId?: string,
        public contactId?: string,
        public outletId?: string,
        public outletName?: string,
        public amount?: number,
        public fee?: number,
        public vat?: number,
        public feeIncluded?: number,
        public settlement?: number,
        public currency?: string,
        public wht?: number,
        public paymentTime?: Moment,
        public settlementTime?: Moment,
        public type?: string,
        public sof?: string,
        public merchantId?: string,
        public paymentChannel?: string
    ) {}
}
