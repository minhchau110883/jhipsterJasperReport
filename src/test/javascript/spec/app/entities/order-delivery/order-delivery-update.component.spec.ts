/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterJasperReportTestModule } from '../../../test.module';
import { OrderDeliveryUpdateComponent } from 'app/entities/order-delivery/order-delivery-update.component';
import { OrderDeliveryService } from 'app/entities/order-delivery/order-delivery.service';
import { OrderDelivery } from 'app/shared/model/order-delivery.model';

describe('Component Tests', () => {
    describe('OrderDelivery Management Update Component', () => {
        let comp: OrderDeliveryUpdateComponent;
        let fixture: ComponentFixture<OrderDeliveryUpdateComponent>;
        let service: OrderDeliveryService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterJasperReportTestModule],
                declarations: [OrderDeliveryUpdateComponent]
            })
                .overrideTemplate(OrderDeliveryUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OrderDeliveryUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OrderDeliveryService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new OrderDelivery(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.orderDelivery = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new OrderDelivery();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.orderDelivery = entity;
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
