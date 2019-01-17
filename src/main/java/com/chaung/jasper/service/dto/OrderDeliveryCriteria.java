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
 * Criteria class for the OrderDelivery entity. This class is used in OrderDeliveryResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /order-deliveries?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrderDeliveryCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter employeeId;

    private StringFilter customerName;

    private StringFilter customerAddress;

    private StringFilter customerPhone;

    private StringFilter menu;

    private DoubleFilter totalAmount;

    private ZonedDateTimeFilter orderDate;

    private ZonedDateTimeFilter shippedDate;

    private StringFilter shipVia;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(LongFilter employeeId) {
        this.employeeId = employeeId;
    }

    public StringFilter getCustomerName() {
        return customerName;
    }

    public void setCustomerName(StringFilter customerName) {
        this.customerName = customerName;
    }

    public StringFilter getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(StringFilter customerAddress) {
        this.customerAddress = customerAddress;
    }

    public StringFilter getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(StringFilter customerPhone) {
        this.customerPhone = customerPhone;
    }

    public StringFilter getMenu() {
        return menu;
    }

    public void setMenu(StringFilter menu) {
        this.menu = menu;
    }

    public DoubleFilter getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(DoubleFilter totalAmount) {
        this.totalAmount = totalAmount;
    }

    public ZonedDateTimeFilter getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(ZonedDateTimeFilter orderDate) {
        this.orderDate = orderDate;
    }

    public ZonedDateTimeFilter getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(ZonedDateTimeFilter shippedDate) {
        this.shippedDate = shippedDate;
    }

    public StringFilter getShipVia() {
        return shipVia;
    }

    public void setShipVia(StringFilter shipVia) {
        this.shipVia = shipVia;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OrderDeliveryCriteria that = (OrderDeliveryCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(employeeId, that.employeeId) &&
            Objects.equals(customerName, that.customerName) &&
            Objects.equals(customerAddress, that.customerAddress) &&
            Objects.equals(customerPhone, that.customerPhone) &&
            Objects.equals(menu, that.menu) &&
            Objects.equals(totalAmount, that.totalAmount) &&
            Objects.equals(orderDate, that.orderDate) &&
            Objects.equals(shippedDate, that.shippedDate) &&
            Objects.equals(shipVia, that.shipVia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        employeeId,
        customerName,
        customerAddress,
        customerPhone,
        menu,
        totalAmount,
        orderDate,
        shippedDate,
        shipVia
        );
    }

    @Override
    public String toString() {
        return "OrderDeliveryCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (employeeId != null ? "employeeId=" + employeeId + ", " : "") +
                (customerName != null ? "customerName=" + customerName + ", " : "") +
                (customerAddress != null ? "customerAddress=" + customerAddress + ", " : "") +
                (customerPhone != null ? "customerPhone=" + customerPhone + ", " : "") +
                (menu != null ? "menu=" + menu + ", " : "") +
                (totalAmount != null ? "totalAmount=" + totalAmount + ", " : "") +
                (orderDate != null ? "orderDate=" + orderDate + ", " : "") +
                (shippedDate != null ? "shippedDate=" + shippedDate + ", " : "") +
                (shipVia != null ? "shipVia=" + shipVia + ", " : "") +
            "}";
    }

}
