/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterJasperReportTestModule } from '../../../test.module';
import { SettlementReportDeleteDialogComponent } from 'app/entities/settlement-report/settlement-report-delete-dialog.component';
import { SettlementReportService } from 'app/entities/settlement-report/settlement-report.service';

describe('Component Tests', () => {
    describe('SettlementReport Management Delete Component', () => {
        let comp: SettlementReportDeleteDialogComponent;
        let fixture: ComponentFixture<SettlementReportDeleteDialogComponent>;
        let service: SettlementReportService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterJasperReportTestModule],
                declarations: [SettlementReportDeleteDialogComponent]
            })
                .overrideTemplate(SettlementReportDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SettlementReportDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SettlementReportService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
