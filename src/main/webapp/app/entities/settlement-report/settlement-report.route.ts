import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SettlementReport } from 'app/shared/model/settlement-report.model';
import { SettlementReportService } from './settlement-report.service';
import { SettlementReportComponent } from './settlement-report.component';
import { SettlementReportDetailComponent } from './settlement-report-detail.component';
import { SettlementReportUpdateComponent } from './settlement-report-update.component';
import { SettlementReportDeletePopupComponent } from './settlement-report-delete-dialog.component';
import { ISettlementReport } from 'app/shared/model/settlement-report.model';

@Injectable({ providedIn: 'root' })
export class SettlementReportResolve implements Resolve<ISettlementReport> {
    constructor(private service: SettlementReportService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SettlementReport> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SettlementReport>) => response.ok),
                map((settlementReport: HttpResponse<SettlementReport>) => settlementReport.body)
            );
        }
        return of(new SettlementReport());
    }
}

export const settlementReportRoute: Routes = [
    {
        path: 'settlement-report',
        component: SettlementReportComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SettlementReports'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'settlement-report/:id/view',
        component: SettlementReportDetailComponent,
        resolve: {
            settlementReport: SettlementReportResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SettlementReports'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'settlement-report/new',
        component: SettlementReportUpdateComponent,
        resolve: {
            settlementReport: SettlementReportResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SettlementReports'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'settlement-report/:id/edit',
        component: SettlementReportUpdateComponent,
        resolve: {
            settlementReport: SettlementReportResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SettlementReports'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const settlementReportPopupRoute: Routes = [
    {
        path: 'settlement-report/:id/delete',
        component: SettlementReportDeletePopupComponent,
        resolve: {
            settlementReport: SettlementReportResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SettlementReports'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
