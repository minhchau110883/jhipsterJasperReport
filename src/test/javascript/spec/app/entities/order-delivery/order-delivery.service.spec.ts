/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { OrderDeliveryService } from 'app/entities/order-delivery/order-delivery.service';
import { IOrderDelivery, OrderDelivery } from 'app/shared/model/order-delivery.model';

describe('Service Tests', () => {
    describe('OrderDelivery Service', () => {
        let injector: TestBed;
        let service: OrderDeliveryService;
        let httpMock: HttpTestingController;
        let elemDefault: IOrderDelivery;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(OrderDeliveryService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new OrderDelivery(0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, currentDate, currentDate, 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        orderDate: currentDate.format(DATE_TIME_FORMAT),
                        shippedDate: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a OrderDelivery', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        orderDate: currentDate.format(DATE_TIME_FORMAT),
                        shippedDate: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        orderDate: currentDate,
                        shippedDate: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new OrderDelivery(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a OrderDelivery', async () => {
                const returnedFromService = Object.assign(
                    {
                        employeeId: 1,
                        customerName: 'BBBBBB',
                        customerAddress: 'BBBBBB',
                        customerPhone: 'BBBBBB',
                        menu: 'BBBBBB',
                        totalAmount: 1,
                        orderDate: currentDate.format(DATE_TIME_FORMAT),
                        shippedDate: currentDate.format(DATE_TIME_FORMAT),
                        shipVia: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        orderDate: currentDate,
                        shippedDate: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of OrderDelivery', async () => {
                const returnedFromService = Object.assign(
                    {
                        employeeId: 1,
                        customerName: 'BBBBBB',
                        customerAddress: 'BBBBBB',
                        customerPhone: 'BBBBBB',
                        menu: 'BBBBBB',
                        totalAmount: 1,
                        orderDate: currentDate.format(DATE_TIME_FORMAT),
                        shippedDate: currentDate.format(DATE_TIME_FORMAT),
                        shipVia: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        orderDate: currentDate,
                        shippedDate: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a OrderDelivery', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
