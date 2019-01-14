import { NgModule } from '@angular/core';

import { JhipsterJasperReportSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [JhipsterJasperReportSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [JhipsterJasperReportSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class JhipsterJasperReportSharedCommonModule {}
