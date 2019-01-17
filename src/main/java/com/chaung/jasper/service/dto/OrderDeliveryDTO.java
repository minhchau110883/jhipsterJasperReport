package com.chaung.jasper.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the OrderDelivery entity.
 */
public class OrderDeliveryDTO implements Serializable {

    private Long id;

    private Long employeeId;

    private String customerName;

    private String customerAddress;

    private String customerPhone;

    private String menu;

    private Double totalAmount;

    private ZonedDateTime orderDate;

    private ZonedDateTime shippedDate;

    private String shipVia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public ZonedDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(ZonedDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public ZonedDateTime getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(ZonedDateTime shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getShipVia() {
        return shipVia;
    }

    public void setShipVia(String shipVia) {
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

        OrderDeliveryDTO orderDeliveryDTO = (OrderDeliveryDTO) o;
        if (orderDeliveryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderDeliveryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderDeliveryDTO{" +
            "id=" + getId() +
            ", employeeId=" + getEmployeeId() +
            ", customerName='" + getCustomerName() + "'" +
            ", customerAddress='" + getCustomerAddress() + "'" +
            ", customerPhone='" + getCustomerPhone() + "'" +
            ", menu='" + getMenu() + "'" +
            ", totalAmount=" + getTotalAmount() +
            ", orderDate='" + getOrderDate() + "'" +
            ", shippedDate='" + getShippedDate() + "'" +
            ", shipVia='" + getShipVia() + "'" +
            "}";
    }
}
