import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OrderDelivery } from 'app/shared/model/order-delivery.model';
import { OrderDeliveryService } from './order-delivery.service';
import { OrderDeliveryComponent } from './order-delivery.component';
import { OrderDeliveryDetailComponent } from './order-delivery-detail.component';
import { OrderDeliveryUpdateComponent } from './order-delivery-update.component';
import { OrderDeliveryDeletePopupComponent } from './order-delivery-delete-dialog.component';
import { IOrderDelivery } from 'app/shared/model/order-delivery.model';

@Injectable({ providedIn: 'root' })
export class OrderDeliveryResolve implements Resolve<IOrderDelivery> {
    constructor(private service: OrderDeliveryService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<OrderDelivery> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<OrderDelivery>) => response.ok),
                map((orderDelivery: HttpResponse<OrderDelivery>) => orderDelivery.body)
            );
        }
        return of(new OrderDelivery());
    }
}

export const orderDeliveryRoute: Routes = [
    {
        path: 'order-delivery',
        component: OrderDeliveryComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'OrderDeliveries'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'order-delivery/:id/view',
        component: OrderDeliveryDetailComponent,
        resolve: {
            orderDelivery: OrderDeliveryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'OrderDeliveries'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'order-delivery/new',
        component: OrderDeliveryUpdateComponent,
        resolve: {
            orderDelivery: OrderDeliveryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'OrderDeliveries'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'order-delivery/:id/edit',
        component: OrderDeliveryUpdateComponent,
        resolve: {
            orderDelivery: OrderDeliveryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'OrderDeliveries'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const orderDeliveryPopupRoute: Routes = [
    {
        path: 'order-delivery/:id/delete',
        component: OrderDeliveryDeletePopupComponent,
        resolve: {
            orderDelivery: OrderDeliveryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'OrderDeliveries'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
