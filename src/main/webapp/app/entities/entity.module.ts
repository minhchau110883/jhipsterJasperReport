import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterJasperReportEmployeeModule } from './employee/employee.module';
import { JhipsterJasperReportSettlementReportModule } from './settlement-report/settlement-report.module';
import { JhipsterJasperReportOrderDeliveryModule } from './order-delivery/order-delivery.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        JhipsterJasperReportEmployeeModule,
        JhipsterJasperReportSettlementReportModule,
        JhipsterJasperReportOrderDeliveryModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterJasperReportEntityModule {}
