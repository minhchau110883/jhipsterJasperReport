import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrderDelivery } from 'app/shared/model/order-delivery.model';

@Component({
    selector: 'jhi-order-delivery-detail',
    templateUrl: './order-delivery-detail.component.html'
})
export class OrderDeliveryDetailComponent implements OnInit {
    orderDelivery: IOrderDelivery;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ orderDelivery }) => {
            this.orderDelivery = orderDelivery;
        });
    }

    previousState() {
        window.history.back();
    }
}
