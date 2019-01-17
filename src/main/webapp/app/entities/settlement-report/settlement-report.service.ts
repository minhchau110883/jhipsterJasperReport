import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISettlementReport } from 'app/shared/model/settlement-report.model';

type EntityResponseType = HttpResponse<ISettlementReport>;
type EntityArrayResponseType = HttpResponse<ISettlementReport[]>;

@Injectable({ providedIn: 'root' })
export class SettlementReportService {
    public resourceUrl = SERVER_API_URL + 'api/settlement-reports';

    constructor(protected http: HttpClient) {}

    create(settlementReport: ISettlementReport): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(settlementReport);
        return this.http
            .post<ISettlementReport>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(settlementReport: ISettlementReport): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(settlementReport);
        return this.http
            .put<ISettlementReport>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISettlementReport>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISettlementReport[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(settlementReport: ISettlementReport): ISettlementReport {
        const copy: ISettlementReport = Object.assign({}, settlementReport, {
            paymentTime:
                settlementReport.paymentTime != null && settlementReport.paymentTime.isValid()
                    ? settlementReport.paymentTime.toJSON()
                    : null,
            settlementTime:
                settlementReport.settlementTime != null && settlementReport.settlementTime.isValid()
                    ? settlementReport.settlementTime.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.paymentTime = res.body.paymentTime != null ? moment(res.body.paymentTime) : null;
            res.body.settlementTime = res.body.settlementTime != null ? moment(res.body.settlementTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((settlementReport: ISettlementReport) => {
                settlementReport.paymentTime = settlementReport.paymentTime != null ? moment(settlementReport.paymentTime) : null;
                settlementReport.settlementTime = settlementReport.settlementTime != null ? moment(settlementReport.settlementTime) : null;
            });
        }
        return res;
    }
}
