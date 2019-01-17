import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrderDelivery } from 'app/shared/model/order-delivery.model';
import { OrderDeliveryService } from './order-delivery.service';

@Component({
    selector: 'jhi-order-delivery-delete-dialog',
    templateUrl: './order-delivery-delete-dialog.component.html'
})
export class OrderDeliveryDeleteDialogComponent {
    orderDelivery: IOrderDelivery;

    constructor(
        protected orderDeliveryService: OrderDeliveryService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.orderDeliveryService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'orderDeliveryListModification',
                content: 'Deleted an orderDelivery'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-order-delivery-delete-popup',
    template: ''
})
export class OrderDeliveryDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ orderDelivery }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(OrderDeliveryDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.orderDelivery = orderDelivery;
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
