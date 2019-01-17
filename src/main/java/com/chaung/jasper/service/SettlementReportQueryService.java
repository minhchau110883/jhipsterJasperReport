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

import com.chaung.jasper.domain.SettlementReport;
import com.chaung.jasper.domain.*; // for static metamodels
import com.chaung.jasper.repository.SettlementReportRepository;
import com.chaung.jasper.service.dto.SettlementReportCriteria;
import com.chaung.jasper.service.dto.SettlementReportDTO;
import com.chaung.jasper.service.mapper.SettlementReportMapper;

/**
 * Service for executing complex queries for SettlementReport entities in the database.
 * The main input is a {@link SettlementReportCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SettlementReportDTO} or a {@link Page} of {@link SettlementReportDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SettlementReportQueryService extends QueryService<SettlementReport> {

    private final Logger log = LoggerFactory.getLogger(SettlementReportQueryService.class);

    private final SettlementReportRepository settlementReportRepository;

    private final SettlementReportMapper settlementReportMapper;

    public SettlementReportQueryService(SettlementReportRepository settlementReportRepository, SettlementReportMapper settlementReportMapper) {
        this.settlementReportRepository = settlementReportRepository;
        this.settlementReportMapper = settlementReportMapper;
    }

    /**
     * Return a {@link List} of {@link SettlementReportDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SettlementReportDTO> findByCriteria(SettlementReportCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SettlementReport> specification = createSpecification(criteria);
        return settlementReportMapper.toDto(settlementReportRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SettlementReportDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SettlementReportDTO> findByCriteria(SettlementReportCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SettlementReport> specification = createSpecification(criteria);
        return settlementReportRepository.findAll(specification, page)
            .map(settlementReportMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SettlementReportCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SettlementReport> specification = createSpecification(criteria);
        return settlementReportRepository.count(specification);
    }

    /**
     * Function to convert SettlementReportCriteria to a {@link Specification}
     */
    private Specification<SettlementReport> createSpecification(SettlementReportCriteria criteria) {
        Specification<SettlementReport> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SettlementReport_.id));
            }
            if (criteria.getPartnerTransactionId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPartnerTransactionId(), SettlementReport_.partnerTransactionId));
            }
            if (criteria.getTransactionId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTransactionId(), SettlementReport_.transactionId));
            }
            if (criteria.getContactId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContactId(), SettlementReport_.contactId));
            }
            if (criteria.getOutletId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOutletId(), SettlementReport_.outletId));
            }
            if (criteria.getOutletName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOutletName(), SettlementReport_.outletName));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), SettlementReport_.amount));
            }
            if (criteria.getFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFee(), SettlementReport_.fee));
            }
            if (criteria.getVat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVat(), SettlementReport_.vat));
            }
            if (criteria.getFeeIncluded() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeeIncluded(), SettlementReport_.feeIncluded));
            }
            if (criteria.getSettlement() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSettlement(), SettlementReport_.settlement));
            }
            if (criteria.getCurrency() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCurrency(), SettlementReport_.currency));
            }
            if (criteria.getWht() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWht(), SettlementReport_.wht));
            }
            if (criteria.getPaymentTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPaymentTime(), SettlementReport_.paymentTime));
            }
            if (criteria.getSettlementTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSettlementTime(), SettlementReport_.settlementTime));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType(), SettlementReport_.type));
            }
            if (criteria.getSof() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSof(), SettlementReport_.sof));
            }
            if (criteria.getMerchantId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMerchantId(), SettlementReport_.merchantId));
            }
            if (criteria.getPaymentChannel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPaymentChannel(), SettlementReport_.paymentChannel));
            }
        }
        return specification;
    }
}
