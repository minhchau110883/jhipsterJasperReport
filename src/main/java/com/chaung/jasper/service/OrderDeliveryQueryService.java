package com.chaung.jasper.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.chaung.jasper.domain.OrderDelivery;
import com.chaung.jasper.domain.*; // for static metamodels
import com.chaung.jasper.repository.OrderDeliveryRepository;
import com.chaung.jasper.service.dto.OrderDeliveryCriteria;
import com.chaung.jasper.service.dto.OrderDeliveryDTO;
import com.chaung.jasper.service.mapper.OrderDeliveryMapper;

/**
 * Service for executing complex queries for OrderDelivery entities in the database.
 * The main input is a {@link OrderDeliveryCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrderDeliveryDTO} or a {@link Page} of {@link OrderDeliveryDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrderDeliveryQueryService extends QueryService<OrderDelivery> {

    private final Logger log = LoggerFactory.getLogger(OrderDeliveryQueryService.class);

    private final OrderDeliveryRepository orderDeliveryRepository;

    private final OrderDeliveryMapper orderDeliveryMapper;

    public OrderDeliveryQueryService(OrderDeliveryRepository orderDeliveryRepository, OrderDeliveryMapper orderDeliveryMapper) {
        this.orderDeliveryRepository = orderDeliveryRepository;
        this.orderDeliveryMapper = orderDeliveryMapper;
    }

    /**
     * Return a {@link List} of {@link OrderDeliveryDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrderDeliveryDTO> findByCriteria(OrderDeliveryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<OrderDelivery> specification = createSpecification(criteria);
        return orderDeliveryMapper.toDto(orderDeliveryRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrderDeliveryDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderDeliveryDTO> findByCriteria(OrderDeliveryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<OrderDelivery> specification = createSpecification(criteria);
        return orderDeliveryRepository.findAll(specification, page)
            .map(orderDeliveryMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OrderDeliveryCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<OrderDelivery> specification = createSpecification(criteria);
        return orderDeliveryRepository.count(specification);
    }

    /**
     * Function to convert OrderDeliveryCriteria to a {@link Specification}
     */
    private Specification<OrderDelivery> createSpecification(OrderDeliveryCriteria criteria) {
        Specification<OrderDelivery> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OrderDelivery_.id));
            }
            if (criteria.getEmployeeId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEmployeeId(), OrderDelivery_.employeeId));
            }
            if (criteria.getCustomerName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCustomerName(), OrderDelivery_.customerName));
            }
            if (criteria.getCustomerAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCustomerAddress(), OrderDelivery_.customerAddress));
            }
            if (criteria.getCustomerPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCustomerPhone(), OrderDelivery_.customerPhone));
            }
            if (criteria.getMenu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMenu(), OrderDelivery_.menu));
            }
            if (criteria.getTotalAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalAmount(), OrderDelivery_.totalAmount));
            }
            if (criteria.getOrderDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderDate(), OrderDelivery_.orderDate));
            }
            if (criteria.getShippedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getShippedDate(), OrderDelivery_.shippedDate));
            }
            if (criteria.getShipVia() != null) {
                specification = specification.and(buildStringSpecification(criteria.getShipVia(), OrderDelivery_.shipVia));
            }
        }
        return specification;
    }
}
