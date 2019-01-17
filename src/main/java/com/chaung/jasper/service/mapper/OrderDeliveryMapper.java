package com.chaung.jasper.service.mapper;

import com.chaung.jasper.domain.*;
import com.chaung.jasper.service.dto.OrderDeliveryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderDelivery and its DTO OrderDeliveryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderDeliveryMapper extends EntityMapper<OrderDeliveryDTO, OrderDelivery> {



    default OrderDelivery fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderDelivery orderDelivery = new OrderDelivery();
        orderDelivery.setId(id);
        return orderDelivery;
    }
}
