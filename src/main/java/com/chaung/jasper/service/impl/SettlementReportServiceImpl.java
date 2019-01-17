package com.chaung.jasper.service.impl;

import com.chaung.jasper.service.SettlementReportService;
import com.chaung.jasper.domain.SettlementReport;
import com.chaung.jasper.repository.SettlementReportRepository;
import com.chaung.jasper.service.dto.SettlementReportDTO;
import com.chaung.jasper.service.mapper.SettlementReportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing SettlementReport.
 */
@Service
@Transactional
public class SettlementReportServiceImpl implements SettlementReportService {

    private final Logger log = LoggerFactory.getLogger(SettlementReportServiceImpl.class);

    private final SettlementReportRepository settlementReportRepository;

    private final SettlementReportMapper settlementReportMapper;

    public SettlementReportServiceImpl(SettlementReportRepository settlementReportRepository, SettlementReportMapper settlementReportMapper) {
        this.settlementReportRepository = settlementReportRepository;
        this.settlementReportMapper = settlementReportMapper;
    }

    /**
     * Save a settlementReport.
     *
     * @param settlementReportDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SettlementReportDTO save(SettlementReportDTO settlementReportDTO) {
        log.debug("Request to save SettlementReport : {}", settlementReportDTO);

        SettlementReport settlementReport = settlementReportMapper.toEntity(settlementReportDTO);
        settlementReport = settlementReportRepository.save(settlementReport);
        return settlementReportMapper.toDto(settlementReport);
    }

    /**
     * Get all the settlementReports.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SettlementReportDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SettlementReports");
        return settlementReportRepository.findAll(pageable)
            .map(settlementReportMapper::toDto);
    }


    /**
     * Get one settlementReport by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SettlementReportDTO> findOne(Long id) {
        log.debug("Request to get SettlementReport : {}", id);
        return settlementReportRepository.findById(id)
            .map(settlementReportMapper::toDto);
    }

    /**
     * Delete the settlementReport by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SettlementReport : {}", id);
        settlementReportRepository.deleteById(id);
    }
}
