package com.chaung.jasper.web.rest;

import com.chaung.jasper.domain.DataBean;
import com.chaung.jasper.domain.DataBeanList;
import com.chaung.jasper.domain.OrderDelivery;
import com.chaung.jasper.service.report.XlsxReportService;
import com.codahale.metrics.annotation.Timed;
import com.chaung.jasper.service.OrderDeliveryService;
import com.chaung.jasper.web.rest.errors.BadRequestAlertException;
import com.chaung.jasper.web.rest.util.HeaderUtil;
import com.chaung.jasper.web.rest.util.PaginationUtil;
import com.chaung.jasper.service.dto.OrderDeliveryDTO;
import com.chaung.jasper.service.dto.OrderDeliveryCriteria;
import com.chaung.jasper.service.OrderDeliveryQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * REST controller for managing OrderDelivery.
 */
@RestController
@RequestMapping("/api")
public class OrderDeliveryResource {

    private final Logger log = LoggerFactory.getLogger(OrderDeliveryResource.class);

    private static final String ENTITY_NAME = "orderDelivery";

    private final OrderDeliveryService orderDeliveryService;

    private final OrderDeliveryQueryService orderDeliveryQueryService;

    private final ResourceLoader resourceLoader;

    public OrderDeliveryResource(OrderDeliveryService orderDeliveryService,
                                 OrderDeliveryQueryService orderDeliveryQueryService,
                                 ResourceLoader resourceLoader) {
        this.orderDeliveryService = orderDeliveryService;
        this.orderDeliveryQueryService = orderDeliveryQueryService;
        this.resourceLoader = resourceLoader;
    }

    /**
     * POST  /order-deliveries : Create a new orderDelivery.
     *
     * @param orderDeliveryDTO the orderDeliveryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderDeliveryDTO, or with status 400 (Bad Request) if the orderDelivery has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-deliveries")
    @Timed
    public ResponseEntity<OrderDeliveryDTO> createOrderDelivery(@RequestBody OrderDeliveryDTO orderDeliveryDTO) throws URISyntaxException {
        log.debug("REST request to save OrderDelivery : {}", orderDeliveryDTO);
        if (orderDeliveryDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderDelivery cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderDeliveryDTO result = orderDeliveryService.save(orderDeliveryDTO);
        return ResponseEntity.created(new URI("/api/order-deliveries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-deliveries : Updates an existing orderDelivery.
     *
     * @param orderDeliveryDTO the orderDeliveryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderDeliveryDTO,
     * or with status 400 (Bad Request) if the orderDeliveryDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderDeliveryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-deliveries")
    @Timed
    public ResponseEntity<OrderDeliveryDTO> updateOrderDelivery(@RequestBody OrderDeliveryDTO orderDeliveryDTO) throws URISyntaxException {
        log.debug("REST request to update OrderDelivery : {}", orderDeliveryDTO);
        if (orderDeliveryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrderDeliveryDTO result = orderDeliveryService.save(orderDeliveryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderDeliveryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-deliveries : get all the orderDeliveries.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of orderDeliveries in body
     */
    @GetMapping("/order-deliveries")
    @Timed
    public ResponseEntity<List<OrderDeliveryDTO>> getAllOrderDeliveries(OrderDeliveryCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OrderDeliveries by criteria: {}", criteria);
        Page<OrderDeliveryDTO> page = orderDeliveryQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-deliveries");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /order-deliveries/count : count all the orderDeliveries.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/order-deliveries/count")
    @Timed
    public ResponseEntity<Long> countOrderDeliveries(OrderDeliveryCriteria criteria) {
        log.debug("REST request to count OrderDeliveries by criteria: {}", criteria);
        return ResponseEntity.ok().body(orderDeliveryQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /order-deliveries/:id : get the "id" orderDelivery.
     *
     * @param id the id of the orderDeliveryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderDeliveryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/order-deliveries/{id}")
    @Timed
    public ResponseEntity<OrderDeliveryDTO> getOrderDelivery(@PathVariable Long id) {
        log.debug("REST request to get OrderDelivery : {}", id);
        Optional<OrderDeliveryDTO> orderDeliveryDTO = orderDeliveryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderDeliveryDTO);
    }

    /**
     * DELETE  /order-deliveries/:id : delete the "id" orderDelivery.
     *
     * @param id the id of the orderDeliveryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-deliveries/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderDelivery(@PathVariable Long id) {
        log.debug("REST request to delete OrderDelivery : {}", id);
        orderDeliveryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping(value = "/report/delivery")
    public HttpEntity<byte[]> getEmployeeReportXlsx() throws Exception {

        String sourceFileName = resourceLoader.getResource("classpath:templates/report/order_delivery_by_shipper_report.jrxml").getURI().getPath();
        String imagesSourceFolder = resourceLoader.getResource("classpath:templates/report/images").getURI().getPath();

        System.out.println("==============>" + imagesSourceFolder);
        JasperReport jasperReport = JasperCompileManager.compileReport(sourceFileName);

        List<OrderDelivery> orderDeliveryList = orderDeliveryService.findAll();
        JRBeanCollectionDataSource orderDeliveryDataSource = new JRBeanCollectionDataSource(orderDeliveryList);

        Map parameters = new HashMap();
        parameters.put("shipperName", "Sam Sip");
        parameters.put("reportDate", ZonedDateTime.now());
        parameters.put("imagesSourceFolder", imagesSourceFolder);
        parameters.put("totalOrder", orderDeliveryList.size());
        parameters.put("totalAmount", orderDeliveryList.stream().mapToDouble(value -> value.getTotalAmount()).sum());

        try {
            // JasperFillManager jasperFillManager = new JasperFillManager();
            JasperPrint jasperPrint = JasperFillManager.getInstance(DefaultJasperReportsContext.getInstance())
                                        .fill(jasperReport, parameters, orderDeliveryDataSource);

            final byte[] data = XlsxReportService.getReportXlsx(jasperPrint);

            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=coffeeDeliveryReport.xlsx");
            header.setContentLength(data.length);

            return new HttpEntity<byte[]>(data, header);


        } catch (JRException e) {
            e.printStackTrace();
        }
        return null;
    }
}
