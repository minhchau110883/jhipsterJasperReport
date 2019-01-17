package com.chaung.jasper.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A SettlementReport.
 */
@Entity
@Table(name = "settlement_report")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SettlementReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "partner_transaction_id")
    private String partnerTransactionId;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "contact_id")
    private String contactId;

    @Column(name = "outlet_id")
    private String outletId;

    @Column(name = "outlet_name")
    private String outletName;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "fee")
    private Double fee;

    @Column(name = "vat")
    private Float vat;

    @Column(name = "fee_included")
    private Double feeIncluded;

    @Column(name = "settlement")
    private Double settlement;

    @Column(name = "currency")
    private String currency;

    @Column(name = "wht")
    private Float wht;

    @Column(name = "payment_time")
    private ZonedDateTime paymentTime;

    @Column(name = "settlement_time")
    private ZonedDateTime settlementTime;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "sof")
    private String sof;

    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "payment_channel")
    private String paymentChannel;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartnerTransactionId() {
        return partnerTransactionId;
    }

    public SettlementReport partnerTransactionId(String partnerTransactionId) {
        this.partnerTransactionId = partnerTransactionId;
        return this;
    }

    public void setPartnerTransactionId(String partnerTransactionId) {
        this.partnerTransactionId = partnerTransactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public SettlementReport transactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getContactId() {
        return contactId;
    }

    public SettlementReport contactId(String contactId) {
        this.contactId = contactId;
        return this;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getOutletId() {
        return outletId;
    }

    public SettlementReport outletId(String outletId) {
        this.outletId = outletId;
        return this;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    public String getOutletName() {
        return outletName;
    }

    public SettlementReport outletName(String outletName) {
        this.outletName = outletName;
        return this;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public Double getAmount() {
        return amount;
    }

    public SettlementReport amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getFee() {
        return fee;
    }

    public SettlementReport fee(Double fee) {
        this.fee = fee;
        return this;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Float getVat() {
        return vat;
    }

    public SettlementReport vat(Float vat) {
        this.vat = vat;
        return this;
    }

    public void setVat(Float vat) {
        this.vat = vat;
    }

    public Double getFeeIncluded() {
        return feeIncluded;
    }

    public SettlementReport feeIncluded(Double feeIncluded) {
        this.feeIncluded = feeIncluded;
        return this;
    }

    public void setFeeIncluded(Double feeIncluded) {
        this.feeIncluded = feeIncluded;
    }

    public Double getSettlement() {
        return settlement;
    }

    public SettlementReport settlement(Double settlement) {
        this.settlement = settlement;
        return this;
    }

    public void setSettlement(Double settlement) {
        this.settlement = settlement;
    }

    public String getCurrency() {
        return currency;
    }

    public SettlementReport currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getWht() {
        return wht;
    }

    public SettlementReport wht(Float wht) {
        this.wht = wht;
        return this;
    }

    public void setWht(Float wht) {
        this.wht = wht;
    }

    public ZonedDateTime getPaymentTime() {
        return paymentTime;
    }

    public SettlementReport paymentTime(ZonedDateTime paymentTime) {
        this.paymentTime = paymentTime;
        return this;
    }

    public void setPaymentTime(ZonedDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public ZonedDateTime getSettlementTime() {
        return settlementTime;
    }

    public SettlementReport settlementTime(ZonedDateTime settlementTime) {
        this.settlementTime = settlementTime;
        return this;
    }

    public void setSettlementTime(ZonedDateTime settlementTime) {
        this.settlementTime = settlementTime;
    }

    public String getType() {
        return type;
    }

    public SettlementReport type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSof() {
        return sof;
    }

    public SettlementReport sof(String sof) {
        this.sof = sof;
        return this;
    }

    public void setSof(String sof) {
        this.sof = sof;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public SettlementReport merchantId(String merchantId) {
        this.merchantId = merchantId;
        return this;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public SettlementReport paymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
        return this;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SettlementReport settlementReport = (SettlementReport) o;
        if (settlementReport.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), settlementReport.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SettlementReport{" +
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
