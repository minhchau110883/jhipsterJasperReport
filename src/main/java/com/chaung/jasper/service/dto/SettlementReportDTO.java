package com.chaung.jasper.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the SettlementReport entity.
 */
public class SettlementReportDTO implements Serializable {

    private Long id;

    private String partnerTransactionId;

    private String transactionId;

    private String contactId;

    private String outletId;

    private String outletName;

    private Double amount;

    private Double fee;

    private Float vat;

    private Double feeIncluded;

    private Double settlement;

    private String currency;

    private Float wht;

    private ZonedDateTime paymentTime;

    private ZonedDateTime settlementTime;

    private String type;

    private String sof;

    private String merchantId;

    private String paymentChannel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartnerTransactionId() {
        return partnerTransactionId;
    }

    public void setPartnerTransactionId(String partnerTransactionId) {
        this.partnerTransactionId = partnerTransactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Float getVat() {
        return vat;
    }

    public void setVat(Float vat) {
        this.vat = vat;
    }

    public Double getFeeIncluded() {
        return feeIncluded;
    }

    public void setFeeIncluded(Double feeIncluded) {
        this.feeIncluded = feeIncluded;
    }

    public Double getSettlement() {
        return settlement;
    }

    public void setSettlement(Double settlement) {
        this.settlement = settlement;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getWht() {
        return wht;
    }

    public void setWht(Float wht) {
        this.wht = wht;
    }

    public ZonedDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(ZonedDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public ZonedDateTime getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(ZonedDateTime settlementTime) {
        this.settlementTime = settlementTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSof() {
        return sof;
    }

    public void setSof(String sof) {
        this.sof = sof;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
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

        SettlementReportDTO settlementReportDTO = (SettlementReportDTO) o;
        if (settlementReportDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), settlementReportDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SettlementReportDTO{" +
            "id=" + getId() +
            ", partnerTransactionId='" + getPartnerTransactionId() + "'" +
            ", transactionId='" + getTransactionId() + "'" +
            ", contactId='" + getContactId() + "'" +
            ", outletId='" + getOutletId() + "'" +
            ", outletName='" + getOutletName() + "'" +
            ", amount=" + getAmount() +
            ", fee=" + getFee() +
            ", vat=" + getVat() +
            ", feeIncluded=" + getFeeIncluded() +
            ", settlement=" + getSettlement() +
            ", currency='" + getCurrency() + "'" +
            ", wht=" + getWht() +
            ", paymentTime='" + getPaymentTime() + "'" +
            ", settlementTime='" + getSettlementTime() + "'" +
            ", type='" + getType() + "'" +
            ", sof='" + getSof() + "'" +
            ", merchantId='" + getMerchantId() + "'" +
            ", paymentChannel='" + getPaymentChannel() + "'" +
            "}";
    }
}
