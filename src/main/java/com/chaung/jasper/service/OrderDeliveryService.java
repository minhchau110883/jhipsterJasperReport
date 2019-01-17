package com.chaung.jasper.service;

import com.chaung.jasper.domain.OrderDelivery;
import com.chaung.jasper.service.dto.OrderDeliveryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing OrderDelivery.
 */
public interface OrderDeliveryService {

    /**
     * Save a orderDelivery.
     *
     * @param orderDeliveryDTO the entity to save
     * @return the persisted entity
     */
    OrderDeliveryDTO save(OrderDeliveryDTO orderDeliveryDTO);

    /**
     * Get all the orderDeliveries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderDeliveryDTO> findAll(Pageable pageable);

    List<OrderDelivery> findAll();


    /**
     * Get the "id" orderDelivery.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OrderDeliveryDTO> findOne(Long id);

    /**
     * Delete the "id" orderDelivery.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
