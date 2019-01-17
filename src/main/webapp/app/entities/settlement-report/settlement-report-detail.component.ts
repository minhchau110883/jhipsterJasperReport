import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISettlementReport } from 'app/shared/model/settlement-report.model';

@Component({
    selector: 'jhi-settlement-report-detail',
    templateUrl: './settlement-report-detail.component.html'
})
export class SettlementReportDetailComponent implements OnInit {
    settlementReport: ISettlementReport;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ settlementReport }) => {
            this.settlementReport = settlementReport;
        });
    }

    previousState() {
        window.history.back();
    }
}
