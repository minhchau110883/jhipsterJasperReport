import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOrderDelivery } from 'app/shared/model/order-delivery.model';

type EntityResponseType = HttpResponse<IOrderDelivery>;
type EntityArrayResponseType = HttpResponse<IOrderDelivery[]>;

@Injectable({ providedIn: 'root' })
export class OrderDeliveryService {
    public resourceUrl = SERVER_API_URL + 'api/order-deliveries';

    constructor(protected http: HttpClient) {}

    create(orderDelivery: IOrderDelivery): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(orderDelivery);
        return this.http
            .post<IOrderDelivery>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(orderDelivery: IOrderDelivery): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(orderDelivery);
        return this.http
            .put<IOrderDelivery>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IOrderDelivery>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IOrderDelivery[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(orderDelivery: IOrderDelivery): IOrderDelivery {
        const copy: IOrderDelivery = Object.assign({}, orderDelivery, {
            orderDate: orderDelivery.orderDate != null && orderDelivery.orderDate.isValid() ? orderDelivery.orderDate.toJSON() : null,
            shippedDate:
                orderDelivery.shippedDate != null && orderDelivery.shippedDate.isValid() ? orderDelivery.shippedDate.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.orderDate = res.body.orderDate != null ? moment(res.body.orderDate) : null;
            res.body.shippedDate = res.body.shippedDate != null ? moment(res.body.shippedDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((orderDelivery: IOrderDelivery) => {
                orderDelivery.orderDate = orderDelivery.orderDate != null ? moment(orderDelivery.orderDate) : null;
                orderDelivery.shippedDate = orderDelivery.shippedDate != null ? moment(orderDelivery.shippedDate) : null;
            });
        }
        return res;
    }
}
