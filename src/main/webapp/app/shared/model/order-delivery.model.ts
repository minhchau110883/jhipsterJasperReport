import { Moment } from 'moment';

export interface IOrderDelivery {
    id?: number;
    employeeId?: number;
    customerName?: string;
    customerAddress?: string;
    customerPhone?: string;
    menu?: string;
    totalAmount?: number;
    orderDate?: Moment;
    shippedDate?: Moment;
    shipVia?: string;
}

export class OrderDelivery implements IOrderDelivery {
    constructor(
        public id?: number,
        public employeeId?: number,
        public customerName?: string,
        public customerAddress?: string,
        public customerPhone?: string,
        public menu?: string,
        public totalAmount?: number,
        public orderDate?: Moment,
        public shippedDate?: Moment,
        public shipVia?: string
    ) {}
}
