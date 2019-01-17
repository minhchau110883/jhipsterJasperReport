/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterJasperReportTestModule } from '../../../test.module';
import { SettlementReportDetailComponent } from 'app/entities/settlement-report/settlement-report-detail.component';
import { SettlementReport } from 'app/shared/model/settlement-report.model';

describe('Component Tests', () => {
    describe('SettlementReport Management Detail Component', () => {
        let comp: SettlementReportDetailComponent;
        let fixture: ComponentFixture<SettlementReportDetailComponent>;
        const route = ({ data: of({ settlementReport: new SettlementReport(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterJasperReportTestModule],
                declarations: [SettlementReportDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SettlementReportDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SettlementReportDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.settlementReport).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
