/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterJasperReportTestModule } from '../../../test.module';
import { SettlementReportUpdateComponent } from 'app/entities/settlement-report/settlement-report-update.component';
import { SettlementReportService } from 'app/entities/settlement-report/settlement-report.service';
import { SettlementReport } from 'app/shared/model/settlement-report.model';

describe('Component Tests', () => {
    describe('SettlementReport Management Update Component', () => {
        let comp: SettlementReportUpdateComponent;
        let fixture: ComponentFixture<SettlementReportUpdateComponent>;
        let service: SettlementReportService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterJasperReportTestModule],
                declarations: [SettlementReportUpdateComponent]
            })
                .overrideTemplate(SettlementReportUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SettlementReportUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SettlementReportService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SettlementReport(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.settlementReport = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SettlementReport();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.settlementReport = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
