package com.chaung.jasper.service;

import com.chaung.jasper.service.dto.SettlementReportDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SettlementReport.
 */
public interface SettlementReportService {

    /**
     * Save a settlementReport.
     *
     * @param settlementReportDTO the entity to save
     * @return the persisted entity
     */
    SettlementReportDTO save(SettlementReportDTO settlementReportDTO);

    /**
     * Get all the settlementReports.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SettlementReportDTO> findAll(Pageable pageable);


    /**
     * Get the "id" settlementReport.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SettlementReportDTO> findOne(Long id);

    /**
     * Delete the "id" settlementReport.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
