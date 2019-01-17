import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterJasperReportSharedModule } from 'app/shared';
import {
    OrderDeliveryComponent,
    OrderDeliveryDetailComponent,
    OrderDeliveryUpdateComponent,
    OrderDeliveryDeletePopupComponent,
    OrderDeliveryDeleteDialogComponent,
    orderDeliveryRoute,
    orderDeliveryPopupRoute
} from './';

const ENTITY_STATES = [...orderDeliveryRoute, ...orderDeliveryPopupRoute];

@NgModule({
    imports: [JhipsterJasperReportSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OrderDeliveryComponent,
        OrderDeliveryDetailComponent,
        OrderDeliveryUpdateComponent,
        OrderDeliveryDeleteDialogComponent,
        OrderDeliveryDeletePopupComponent
    ],
    entryComponents: [
        OrderDeliveryComponent,
        OrderDeliveryUpdateComponent,
        OrderDeliveryDeleteDialogComponent,
        OrderDeliveryDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterJasperReportOrderDeliveryModule {}
