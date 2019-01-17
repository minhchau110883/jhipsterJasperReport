/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterJasperReportTestModule } from '../../../test.module';
import { OrderDeliveryDetailComponent } from 'app/entities/order-delivery/order-delivery-detail.component';
import { OrderDelivery } from 'app/shared/model/order-delivery.model';

describe('Component Tests', () => {
    describe('OrderDelivery Management Detail Component', () => {
        let comp: OrderDeliveryDetailComponent;
        let fixture: ComponentFixture<OrderDeliveryDetailComponent>;
        const route = ({ data: of({ orderDelivery: new OrderDelivery(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterJasperReportTestModule],
                declarations: [OrderDeliveryDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OrderDeliveryDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OrderDeliveryDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.orderDelivery).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
