/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterJasperReportTestModule } from '../../../test.module';
import { OrderDeliveryDeleteDialogComponent } from 'app/entities/order-delivery/order-delivery-delete-dialog.component';
import { OrderDeliveryService } from 'app/entities/order-delivery/order-delivery.service';

describe('Component Tests', () => {
    describe('OrderDelivery Management Delete Component', () => {
        let comp: OrderDeliveryDeleteDialogComponent;
        let fixture: ComponentFixture<OrderDeliveryDeleteDialogComponent>;
        let service: OrderDeliveryService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterJasperReportTestModule],
                declarations: [OrderDeliveryDeleteDialogComponent]
            })
                .overrideTemplate(OrderDeliveryDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OrderDeliveryDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OrderDeliveryService);
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
