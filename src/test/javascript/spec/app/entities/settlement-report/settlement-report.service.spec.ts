/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { SettlementReportService } from 'app/entities/settlement-report/settlement-report.service';
import { ISettlementReport, SettlementReport } from 'app/shared/model/settlement-report.model';

describe('Service Tests', () => {
    describe('SettlementReport Service', () => {
        let injector: TestBed;
        let service: SettlementReportService;
        let httpMock: HttpTestingController;
        let elemDefault: ISettlementReport;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(SettlementReportService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new SettlementReport(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                0,
                0,
                0,
                0,
                'AAAAAAA',
                0,
                currentDate,
                currentDate,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        paymentTime: currentDate.format(DATE_TIME_FORMAT),
                        settlementTime: currentDate.format(DATE_TIME_FORMAT)
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

            it('should create a SettlementReport', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        paymentTime: currentDate.format(DATE_TIME_FORMAT),
                        settlementTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        paymentTime: currentDate,
                        settlementTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new SettlementReport(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a SettlementReport', async () => {
                const returnedFromService = Object.assign(
                    {
                        partnerTransactionId: 'BBBBBB',
                        transactionId: 'BBBBBB',
                        contactId: 'BBBBBB',
                        outletId: 'BBBBBB',
                        outletName: 'BBBBBB',
                        amount: 1,
                        fee: 1,
                        vat: 1,
                        feeIncluded: 1,
                        settlement: 1,
                        currency: 'BBBBBB',
                        wht: 1,
                        paymentTime: currentDate.format(DATE_TIME_FORMAT),
                        settlementTime: currentDate.format(DATE_TIME_FORMAT),
                        type: 'BBBBBB',
                        sof: 'BBBBBB',
                        merchantId: 'BBBBBB',
                        paymentChannel: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        paymentTime: currentDate,
                        settlementTime: currentDate
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

            it('should return a list of SettlementReport', async () => {
                const returnedFromService = Object.assign(
                    {
                        partnerTransactionId: 'BBBBBB',
                        transactionId: 'BBBBBB',
                        contactId: 'BBBBBB',
                        outletId: 'BBBBBB',
                        outletName: 'BBBBBB',
                        amount: 1,
                        fee: 1,
                        vat: 1,
                        feeIncluded: 1,
                        settlement: 1,
                        currency: 'BBBBBB',
                        wht: 1,
                        paymentTime: currentDate.format(DATE_TIME_FORMAT),
                        settlementTime: currentDate.format(DATE_TIME_FORMAT),
                        type: 'BBBBBB',
                        sof: 'BBBBBB',
                        merchantId: 'BBBBBB',
                        paymentChannel: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        paymentTime: currentDate,
                        settlementTime: currentDate
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

            it('should delete a SettlementReport', async () => {
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
