package com.chaung.jasper.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.chaung.jasper.service.SettlementReportService;
import com.chaung.jasper.web.rest.errors.BadRequestAlertException;
import com.chaung.jasper.web.rest.util.HeaderUtil;
import com.chaung.jasper.web.rest.util.PaginationUtil;
import com.chaung.jasper.service.dto.SettlementReportDTO;
import com.chaung.jasper.service.dto.SettlementReportCriteria;
import com.chaung.jasper.service.SettlementReportQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SettlementReport.
 */
@RestController
@RequestMapping("/api")
public class SettlementReportResource {

    private final Logger log = LoggerFactory.getLogger(SettlementReportResource.class);

    private static final String ENTITY_NAME = "settlementReport";

    private final SettlementReportService settlementReportService;

    private final SettlementReportQueryService settlementReportQueryService;

    public SettlementReportResource(SettlementReportService settlementReportService, SettlementReportQueryService settlementReportQueryService) {
        this.settlementReportService = settlementReportService;
        this.settlementReportQueryService = settlementReportQueryService;
    }

    /**
     * POST  /settlement-reports : Create a new settlementReport.
     *
     * @param settlementReportDTO the settlementReportDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new settlementReportDTO, or with status 400 (Bad Request) if the settlementReport has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/settlement-reports")
    @Timed
    public ResponseEntity<SettlementReportDTO> createSettlementReport(@RequestBody SettlementReportDTO settlementReportDTO) throws URISyntaxException {
        log.debug("REST request to save SettlementReport : {}", settlementReportDTO);
        if (settlementReportDTO.getId() != null) {
            throw new BadRequestAlertException("A new settlementReport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SettlementReportDTO result = settlementReportService.save(settlementReportDTO);
        return ResponseEntity.created(new URI("/api/settlement-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /settlement-reports : Updates an existing settlementReport.
     *
     * @param settlementReportDTO the settlementReportDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated settlementReportDTO,
     * or with status 400 (Bad Request) if the settlementReportDTO is not valid,
     * or with status 500 (Internal Server Error) if the settlementReportDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/settlement-reports")
    @Timed
    public ResponseEntity<SettlementReportDTO> updateSettlementReport(@RequestBody SettlementReportDTO settlementReportDTO) throws URISyntaxException {
        log.debug("REST request to update SettlementReport : {}", settlementReportDTO);
        if (settlementReportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SettlementReportDTO result = settlementReportService.save(settlementReportDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, settlementReportDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /settlement-reports : get all the settlementReports.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of settlementReports in body
     */
    @GetMapping("/settlement-reports")
    @Timed
    public ResponseEntity<List<SettlementReportDTO>> getAllSettlementReports(SettlementReportCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SettlementReports by criteria: {}", criteria);
        Page<SettlementReportDTO> page = settlementReportQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/settlement-reports");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /settlement-reports/count : count all the settlementReports.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/settlement-reports/count")
    @Timed
    public ResponseEntity<Long> countSettlementReports(SettlementReportCriteria criteria) {
        log.debug("REST request to count SettlementReports by criteria: {}", criteria);
        return ResponseEntity.ok().body(settlementReportQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /settlement-reports/:id : get the "id" settlementReport.
     *
     * @param id the id of the settlementReportDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the settlementReportDTO, or with status 404 (Not Found)
     */
    @GetMapping("/settlement-reports/{id}")
    @Timed
    public ResponseEntity<SettlementReportDTO> getSettlementReport(@PathVariable Long id) {
        log.debug("REST request to get SettlementReport : {}", id);
        Optional<SettlementReportDTO> settlementReportDTO = settlementReportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(settlementReportDTO);
    }

    /**
     * DELETE  /settlement-reports/:id : delete the "id" settlementReport.
     *
     * @param id the id of the settlementReportDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/settlement-reports/{id}")
    @Timed
    public ResponseEntity<Void> deleteSettlementReport(@PathVariable Long id) {
        log.debug("REST request to delete SettlementReport : {}", id);
        settlementReportService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
