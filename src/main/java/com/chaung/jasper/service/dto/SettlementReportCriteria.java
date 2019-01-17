package com.chaung.jasper.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the SettlementReport entity. This class is used in SettlementReportResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /settlement-reports?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SettlementReportCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter partnerTransactionId;

    private StringFilter transactionId;

    private StringFilter contactId;

    private StringFilter outletId;

    private StringFilter outletName;

    private DoubleFilter amount;

    private DoubleFilter fee;

    private FloatFilter vat;

    private DoubleFilter feeIncluded;

    private DoubleFilter settlement;

    private StringFilter currency;

    private FloatFilter wht;

    private ZonedDateTimeFilter paymentTime;

    private ZonedDateTimeFilter settlementTime;

    private StringFilter type;

    private StringFilter sof;

    private StringFilter merchantId;

    private StringFilter paymentChannel;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getPartnerTransactionId() {
        return partnerTransactionId;
    }

    public void setPartnerTransactionId(StringFilter partnerTransactionId) {
        this.partnerTransactionId = partnerTransactionId;
    }

    public StringFilter getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(StringFilter transactionId) {
        this.transactionId = transactionId;
    }

    public StringFilter getContactId() {
        return contactId;
    }

    public void setContactId(StringFilter contactId) {
        this.contactId = contactId;
    }

    public StringFilter getOutletId() {
        return outletId;
    }

    public void setOutletId(StringFilter outletId) {
        this.outletId = outletId;
    }

    public StringFilter getOutletName() {
        return outletName;
    }

    public void setOutletName(StringFilter outletName) {
        this.outletName = outletName;
    }

    public DoubleFilter getAmount() {
        return amount;
    }

    public void setAmount(DoubleFilter amount) {
        this.amount = amount;
    }

    public DoubleFilter getFee() {
        return fee;
    }

    public void setFee(DoubleFilter fee) {
        this.fee = fee;
    }

    public FloatFilter getVat() {
        return vat;
    }

    public void setVat(FloatFilter vat) {
        this.vat = vat;
    }

    public DoubleFilter getFeeIncluded() {
        return feeIncluded;
    }

    public void setFeeIncluded(DoubleFilter feeIncluded) {
        this.feeIncluded = feeIncluded;
    }

    public DoubleFilter getSettlement() {
        return settlement;
    }

    public void setSettlement(DoubleFilter settlement) {
        this.settlement = settlement;
    }

    public StringFilter getCurrency() {
        return currency;
    }

    public void setCurrency(StringFilter currency) {
        this.currency = currency;
    }

    public FloatFilter getWht() {
        return wht;
    }

    public void setWht(FloatFilter wht) {
        this.wht = wht;
    }

    public ZonedDateTimeFilter getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(ZonedDateTimeFilter paymentTime) {
        this.paymentTime = paymentTime;
    }

    public ZonedDateTimeFilter getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(ZonedDateTimeFilter settlementTime) {
        this.settlementTime = settlementTime;
    }

    public StringFilter getType() {
        return type;
    }

    public void setType(StringFilter type) {
        this.type = type;
    }

    public StringFilter getSof() {
        return sof;
    }

    public void setSof(StringFilter sof) {
        this.sof = sof;
    }

    public StringFilter getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(StringFilter merchantId) {
        this.merchantId = merchantId;
    }

    public StringFilter getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(StringFilter paymentChannel) {
        this.paymentChannel = paymentChannel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SettlementReportCriteria that = (SettlementReportCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(partnerTransactionId, that.partnerTransactionId) &&
            Objects.equals(transactionId, that.transactionId) &&
            Objects.equals(contactId, that.contactId) &&
            Objects.equals(outletId, that.outletId) &&
            Objects.equals(outletName, that.outletName) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(fee, that.fee) &&
            Objects.equals(vat, that.vat) &&
            Objects.equals(feeIncluded, that.feeIncluded) &&
            Objects.equals(settlement, that.settlement) &&
            Objects.equals(currency, that.currency) &&
            Objects.equals(wht, that.wht) &&
            Objects.equals(paymentTime, that.paymentTime) &&
            Objects.equals(settlementTime, that.settlementTime) &&
            Objects.equals(type, that.type) &&
            Objects.equals(sof, that.sof) &&
            Objects.equals(merchantId, that.merchantId) &&
            Objects.equals(paymentChannel, that.paymentChannel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        partnerTransactionId,
        transactionId,
        contactId,
        outletId,
        outletName,
        amount,
        fee,
        vat,
        feeIncluded,
        settlement,
        currency,
        wht,
        paymentTime,
        settlementTime,
        type,
        sof,
        merchantId,
        paymentChannel
        );
    }

    @Override
    public String toString() {
        return "SettlementReportCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (partnerTransactionId != null ? "partnerTransactionId=" + partnerTransactionId + ", " : "") +
                (transactionId != null ? "transactionId=" + transactionId + ", " : "") +
                (contactId != null ? "contactId=" + contactId + ", " : "") +
                (outletId != null ? "outletId=" + outletId + ", " : "") +
                (outletName != null ? "outletName=" + outletName + ", " : "") +
                (amount != null ? "amount=" + amount + ", " : "") +
                (fee != null ? "fee=" + fee + ", " : "") +
                (vat != null ? "vat=" + vat + ", " : "") +
                (feeIncluded != null ? "feeIncluded=" + feeIncluded + ", " : "") +
                (settlement != null ? "settlement=" + settlement + ", " : "") +
                (currency != null ? "currency=" + currency + ", " : "") +
                (wht != null ? "wht=" + wht + ", " : "") +
                (paymentTime != null ? "paymentTime=" + paymentTime + ", " : "") +
                (settlementTime != null ? "settlementTime=" + settlementTime + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
                (sof != null ? "sof=" + sof + ", " : "") +
                (merchantId != null ? "merchantId=" + merchantId + ", " : "") +
                (paymentChannel != null ? "paymentChannel=" + paymentChannel + ", " : "") +
            "}";
    }

}
