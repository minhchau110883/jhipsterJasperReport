import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterJasperReportSharedModule } from 'app/shared';
import {
    SettlementReportComponent,
    SettlementReportDetailComponent,
    SettlementReportUpdateComponent,
    SettlementReportDeletePopupComponent,
    SettlementReportDeleteDialogComponent,
    settlementReportRoute,
    settlementReportPopupRoute
} from './';

const ENTITY_STATES = [...settlementReportRoute, ...settlementReportPopupRoute];

@NgModule({
    imports: [JhipsterJasperReportSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SettlementReportComponent,
        SettlementReportDetailComponent,
        SettlementReportUpdateComponent,
        SettlementReportDeleteDialogComponent,
        SettlementReportDeletePopupComponent
    ],
    entryComponents: [
        SettlementReportComponent,
        SettlementReportUpdateComponent,
        SettlementReportDeleteDialogComponent,
        SettlementReportDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterJasperReportSettlementReportModule {}
