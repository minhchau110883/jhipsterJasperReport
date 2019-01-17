package com.chaung.jasper.service.impl;

import com.chaung.jasper.service.OrderDeliveryService;
import com.chaung.jasper.domain.OrderDelivery;
import com.chaung.jasper.repository.OrderDeliveryRepository;
import com.chaung.jasper.service.dto.OrderDeliveryDTO;
import com.chaung.jasper.service.mapper.OrderDeliveryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing OrderDelivery.
 */
@Service
@Transactional
public class OrderDeliveryServiceImpl implements OrderDeliveryService {

    private final Logger log = LoggerFactory.getLogger(OrderDeliveryServiceImpl.class);

    private final OrderDeliveryRepository orderDeliveryRepository;

    private final OrderDeliveryMapper orderDeliveryMapper;

    public OrderDeliveryServiceImpl(OrderDeliveryRepository orderDeliveryRepository, OrderDeliveryMapper orderDeliveryMapper) {
        this.orderDeliveryRepository = orderDeliveryRepository;
        this.orderDeliveryMapper = orderDeliveryMapper;
    }

    /**
     * Save a orderDelivery.
     *
     * @param orderDeliveryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderDeliveryDTO save(OrderDeliveryDTO orderDeliveryDTO) {
        log.debug("Request to save OrderDelivery : {}", orderDeliveryDTO);

        OrderDelivery orderDelivery = orderDeliveryMapper.toEntity(orderDeliveryDTO);
        orderDelivery = orderDeliveryRepository.save(orderDelivery);
        return orderDeliveryMapper.toDto(orderDelivery);
    }

    /**
     * Get all the orderDeliveries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderDeliveryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderDeliveries");
        return orderDeliveryRepository.findAll(pageable)
            .map(orderDeliveryMapper::toDto);
    }

    @Override
    public List<OrderDelivery> findAll() {
        return orderDeliveryRepository.findAll();
    }


    /**
     * Get one orderDelivery by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrderDeliveryDTO> findOne(Long id) {
        log.debug("Request to get OrderDelivery : {}", id);
        return orderDeliveryRepository.findById(id)
            .map(orderDeliveryMapper::toDto);
    }

    /**
     * Delete the orderDelivery by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderDelivery : {}", id);
        orderDeliveryRepository.deleteById(id);
    }
}
