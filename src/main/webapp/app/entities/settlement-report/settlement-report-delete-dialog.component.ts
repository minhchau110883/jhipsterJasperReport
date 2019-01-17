import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISettlementReport } from 'app/shared/model/settlement-report.model';
import { SettlementReportService } from './settlement-report.service';

@Component({
    selector: 'jhi-settlement-report-delete-dialog',
    templateUrl: './settlement-report-delete-dialog.component.html'
})
export class SettlementReportDeleteDialogComponent {
    settlementReport: ISettlementReport;

    constructor(
        protected settlementReportService: SettlementReportService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.settlementReportService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'settlementReportListModification',
                content: 'Deleted an settlementReport'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-settlement-report-delete-popup',
    template: ''
})
export class SettlementReportDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ settlementReport }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SettlementReportDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.settlementReport = settlementReport;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
