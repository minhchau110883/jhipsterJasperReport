import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOrderDelivery } from 'app/shared/model/order-delivery.model';
import { OrderDeliveryService } from './order-delivery.service';

@Component({
    selector: 'jhi-order-delivery-update',
    templateUrl: './order-delivery-update.component.html'
})
export class OrderDeliveryUpdateComponent implements OnInit {
    orderDelivery: IOrderDelivery;
    isSaving: boolean;
    orderDate: string;
    shippedDate: string;

    constructor(protected orderDeliveryService: OrderDeliveryService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ orderDelivery }) => {
            this.orderDelivery = orderDelivery;
            this.orderDate = this.orderDelivery.orderDate != null ? this.orderDelivery.orderDate.format(DATE_TIME_FORMAT) : null;
            this.shippedDate = this.orderDelivery.shippedDate != null ? this.orderDelivery.shippedDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.orderDelivery.orderDate = this.orderDate != null ? moment(this.orderDate, DATE_TIME_FORMAT) : null;
        this.orderDelivery.shippedDate = this.shippedDate != null ? moment(this.shippedDate, DATE_TIME_FORMAT) : null;
        if (this.orderDelivery.id !== undefined) {
            this.subscribeToSaveResponse(this.orderDeliveryService.update(this.orderDelivery));
        } else {
            this.subscribeToSaveResponse(this.orderDeliveryService.create(this.orderDelivery));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderDelivery>>) {
        result.subscribe((res: HttpResponse<IOrderDelivery>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
