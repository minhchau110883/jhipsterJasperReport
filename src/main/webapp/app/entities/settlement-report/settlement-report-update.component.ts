import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISettlementReport } from 'app/shared/model/settlement-report.model';
import { SettlementReportService } from './settlement-report.service';

@Component({
    selector: 'jhi-settlement-report-update',
    templateUrl: './settlement-report-update.component.html'
})
export class SettlementReportUpdateComponent implements OnInit {
    settlementReport: ISettlementReport;
    isSaving: boolean;
    paymentTime: string;
    settlementTime: string;

    constructor(protected settlementReportService: SettlementReportService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ settlementReport }) => {
            this.settlementReport = settlementReport;
            this.paymentTime =
                this.settlementReport.paymentTime != null ? this.settlementReport.paymentTime.format(DATE_TIME_FORMAT) : null;
            this.settlementTime =
                this.settlementReport.settlementTime != null ? this.settlementReport.settlementTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.settlementReport.paymentTime = this.paymentTime != null ? moment(this.paymentTime, DATE_TIME_FORMAT) : null;
        this.settlementReport.settlementTime = this.settlementTime != null ? moment(this.settlementTime, DATE_TIME_FORMAT) : null;
        if (this.settlementReport.id !== undefined) {
            this.subscribeToSaveResponse(this.settlementReportService.update(this.settlementReport));
        } else {
            this.subscribeToSaveResponse(this.settlementReportService.create(this.settlementReport));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISettlementReport>>) {
        result.subscribe((res: HttpResponse<ISettlementReport>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
