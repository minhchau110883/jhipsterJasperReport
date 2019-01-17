package com.chaung.jasper.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A OrderDelivery.
 */
@Entity
@Table(name = "order_delivery")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderDelivery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "menu")
    private String menu;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "order_date")
    private ZonedDateTime orderDate;

    @Column(name = "shipped_date")
    private ZonedDateTime shippedDate;

    @Column(name = "ship_via")
    private String shipVia;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public OrderDelivery employeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public OrderDelivery customerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public OrderDelivery customerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
        return this;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public OrderDelivery customerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
        return this;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getMenu() {
        return menu;
    }

    public OrderDelivery menu(String menu) {
        this.menu = menu;
        return this;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public OrderDelivery totalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public ZonedDateTime getOrderDate() {
        return orderDate;
    }

    public OrderDelivery orderDate(ZonedDateTime orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public void setOrderDate(ZonedDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public ZonedDateTime getShippedDate() {
        return shippedDate;
    }

    public OrderDelivery shippedDate(ZonedDateTime shippedDate) {
        this.shippedDate = shippedDate;
        return this;
    }

    public void setShippedDate(ZonedDateTime shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getShipVia() {
        return shipVia;
    }

    public OrderDelivery shipVia(String shipVia) {
        this.shipVia = shipVia;
        return this;
    }

    public void setShipVia(String shipVia) {
        this.shipVia = shipVia;
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
        OrderDelivery orderDelivery = (OrderDelivery) o;
        if (orderDelivery.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderDelivery.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderDelivery{" +
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
