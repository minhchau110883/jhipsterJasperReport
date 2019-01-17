package com.chaung.jasper.web.rest;

import com.chaung.jasper.JhipsterJasperReportApp;

import com.chaung.jasper.domain.OrderDelivery;
import com.chaung.jasper.repository.OrderDeliveryRepository;
import com.chaung.jasper.service.OrderDeliveryService;
import com.chaung.jasper.service.dto.OrderDeliveryDTO;
import com.chaung.jasper.service.mapper.OrderDeliveryMapper;
import com.chaung.jasper.web.rest.errors.ExceptionTranslator;
import com.chaung.jasper.service.dto.OrderDeliveryCriteria;
import com.chaung.jasper.service.OrderDeliveryQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;


import static com.chaung.jasper.web.rest.TestUtil.sameInstant;
import static com.chaung.jasper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the OrderDeliveryResource REST controller.
 *
 * @see OrderDeliveryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterJasperReportApp.class)
public class OrderDeliveryResourceIntTest {

    private static final Long DEFAULT_EMPLOYEE_ID = 1L;
    private static final Long UPDATED_EMPLOYEE_ID = 2L;

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_MENU = "AAAAAAAAAA";
    private static final String UPDATED_MENU = "BBBBBBBBBB";

    private static final Double DEFAULT_TOTAL_AMOUNT = 1D;
    private static final Double UPDATED_TOTAL_AMOUNT = 2D;

    private static final ZonedDateTime DEFAULT_ORDER_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ORDER_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SHIPPED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SHIPPED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_SHIP_VIA = "AAAAAAAAAA";
    private static final String UPDATED_SHIP_VIA = "BBBBBBBBBB";

    @Autowired
    private OrderDeliveryRepository orderDeliveryRepository;

    @Autowired
    private OrderDeliveryMapper orderDeliveryMapper;

    @Autowired
    private OrderDeliveryService orderDeliveryService;

    @Autowired
    private OrderDeliveryQueryService orderDeliveryQueryService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restOrderDeliveryMockMvc;

    private OrderDelivery orderDelivery;



    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderDeliveryResource orderDeliveryResource = new OrderDeliveryResource(orderDeliveryService, orderDeliveryQueryService, resourceLoader);
        this.restOrderDeliveryMockMvc = MockMvcBuilders.standaloneSetup(orderDeliveryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderDelivery createEntity(EntityManager em) {
        OrderDelivery orderDelivery = new OrderDelivery()
            .employeeId(DEFAULT_EMPLOYEE_ID)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .customerAddress(DEFAULT_CUSTOMER_ADDRESS)
            .customerPhone(DEFAULT_CUSTOMER_PHONE)
            .menu(DEFAULT_MENU)
            .totalAmount(DEFAULT_TOTAL_AMOUNT)
            .orderDate(DEFAULT_ORDER_DATE)
            .shippedDate(DEFAULT_SHIPPED_DATE)
            .shipVia(DEFAULT_SHIP_VIA);
        return orderDelivery;
    }

    @Before
    public void initTest() {
        orderDelivery = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderDelivery() throws Exception {
        int databaseSizeBeforeCreate = orderDeliveryRepository.findAll().size();

        // Create the OrderDelivery
        OrderDeliveryDTO orderDeliveryDTO = orderDeliveryMapper.toDto(orderDelivery);
        restOrderDeliveryMockMvc.perform(post("/api/order-deliveries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderDeliveryDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderDelivery in the database
        List<OrderDelivery> orderDeliveryList = orderDeliveryRepository.findAll();
        assertThat(orderDeliveryList).hasSize(databaseSizeBeforeCreate + 1);
        OrderDelivery testOrderDelivery = orderDeliveryList.get(orderDeliveryList.size() - 1);
        assertThat(testOrderDelivery.getEmployeeId()).isEqualTo(DEFAULT_EMPLOYEE_ID);
        assertThat(testOrderDelivery.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testOrderDelivery.getCustomerAddress()).isEqualTo(DEFAULT_CUSTOMER_ADDRESS);
        assertThat(testOrderDelivery.getCustomerPhone()).isEqualTo(DEFAULT_CUSTOMER_PHONE);
        assertThat(testOrderDelivery.getMenu()).isEqualTo(DEFAULT_MENU);
        assertThat(testOrderDelivery.getTotalAmount()).isEqualTo(DEFAULT_TOTAL_AMOUNT);
        assertThat(testOrderDelivery.getOrderDate()).isEqualTo(DEFAULT_ORDER_DATE);
        assertThat(testOrderDelivery.getShippedDate()).isEqualTo(DEFAULT_SHIPPED_DATE);
        assertThat(testOrderDelivery.getShipVia()).isEqualTo(DEFAULT_SHIP_VIA);
    }

    @Test
    @Transactional
    public void createOrderDeliveryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderDeliveryRepository.findAll().size();

        // Create the OrderDelivery with an existing ID
        orderDelivery.setId(1L);
        OrderDeliveryDTO orderDeliveryDTO = orderDeliveryMapper.toDto(orderDelivery);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderDeliveryMockMvc.perform(post("/api/order-deliveries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderDeliveryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderDelivery in the database
        List<OrderDelivery> orderDeliveryList = orderDeliveryRepository.findAll();
        assertThat(orderDeliveryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveries() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList
        restOrderDeliveryMockMvc.perform(get("/api/order-deliveries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderDelivery.getId().intValue())))
            .andExpect(jsonPath("$.[*].employeeId").value(hasItem(DEFAULT_EMPLOYEE_ID.intValue())))
            .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME.toString())))
            .andExpect(jsonPath("$.[*].customerAddress").value(hasItem(DEFAULT_CUSTOMER_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].customerPhone").value(hasItem(DEFAULT_CUSTOMER_PHONE.toString())))
            .andExpect(jsonPath("$.[*].menu").value(hasItem(DEFAULT_MENU.toString())))
            .andExpect(jsonPath("$.[*].totalAmount").value(hasItem(DEFAULT_TOTAL_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].orderDate").value(hasItem(sameInstant(DEFAULT_ORDER_DATE))))
            .andExpect(jsonPath("$.[*].shippedDate").value(hasItem(sameInstant(DEFAULT_SHIPPED_DATE))))
            .andExpect(jsonPath("$.[*].shipVia").value(hasItem(DEFAULT_SHIP_VIA.toString())));
    }
    
    @Test
    @Transactional
    public void getOrderDelivery() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get the orderDelivery
        restOrderDeliveryMockMvc.perform(get("/api/order-deliveries/{id}", orderDelivery.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orderDelivery.getId().intValue()))
            .andExpect(jsonPath("$.employeeId").value(DEFAULT_EMPLOYEE_ID.intValue()))
            .andExpect(jsonPath("$.customerName").value(DEFAULT_CUSTOMER_NAME.toString()))
            .andExpect(jsonPath("$.customerAddress").value(DEFAULT_CUSTOMER_ADDRESS.toString()))
            .andExpect(jsonPath("$.customerPhone").value(DEFAULT_CUSTOMER_PHONE.toString()))
            .andExpect(jsonPath("$.menu").value(DEFAULT_MENU.toString()))
            .andExpect(jsonPath("$.totalAmount").value(DEFAULT_TOTAL_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.orderDate").value(sameInstant(DEFAULT_ORDER_DATE)))
            .andExpect(jsonPath("$.shippedDate").value(sameInstant(DEFAULT_SHIPPED_DATE)))
            .andExpect(jsonPath("$.shipVia").value(DEFAULT_SHIP_VIA.toString()));
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByEmployeeIdIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where employeeId equals to DEFAULT_EMPLOYEE_ID
        defaultOrderDeliveryShouldBeFound("employeeId.equals=" + DEFAULT_EMPLOYEE_ID);

        // Get all the orderDeliveryList where employeeId equals to UPDATED_EMPLOYEE_ID
        defaultOrderDeliveryShouldNotBeFound("employeeId.equals=" + UPDATED_EMPLOYEE_ID);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByEmployeeIdIsInShouldWork() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where employeeId in DEFAULT_EMPLOYEE_ID or UPDATED_EMPLOYEE_ID
        defaultOrderDeliveryShouldBeFound("employeeId.in=" + DEFAULT_EMPLOYEE_ID + "," + UPDATED_EMPLOYEE_ID);

        // Get all the orderDeliveryList where employeeId equals to UPDATED_EMPLOYEE_ID
        defaultOrderDeliveryShouldNotBeFound("employeeId.in=" + UPDATED_EMPLOYEE_ID);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByEmployeeIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where employeeId is not null
        defaultOrderDeliveryShouldBeFound("employeeId.specified=true");

        // Get all the orderDeliveryList where employeeId is null
        defaultOrderDeliveryShouldNotBeFound("employeeId.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByEmployeeIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where employeeId greater than or equals to DEFAULT_EMPLOYEE_ID
        defaultOrderDeliveryShouldBeFound("employeeId.greaterOrEqualThan=" + DEFAULT_EMPLOYEE_ID);

        // Get all the orderDeliveryList where employeeId greater than or equals to UPDATED_EMPLOYEE_ID
        defaultOrderDeliveryShouldNotBeFound("employeeId.greaterOrEqualThan=" + UPDATED_EMPLOYEE_ID);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByEmployeeIdIsLessThanSomething() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where employeeId less than or equals to DEFAULT_EMPLOYEE_ID
        defaultOrderDeliveryShouldNotBeFound("employeeId.lessThan=" + DEFAULT_EMPLOYEE_ID);

        // Get all the orderDeliveryList where employeeId less than or equals to UPDATED_EMPLOYEE_ID
        defaultOrderDeliveryShouldBeFound("employeeId.lessThan=" + UPDATED_EMPLOYEE_ID);
    }


    @Test
    @Transactional
    public void getAllOrderDeliveriesByCustomerNameIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where customerName equals to DEFAULT_CUSTOMER_NAME
        defaultOrderDeliveryShouldBeFound("customerName.equals=" + DEFAULT_CUSTOMER_NAME);

        // Get all the orderDeliveryList where customerName equals to UPDATED_CUSTOMER_NAME
        defaultOrderDeliveryShouldNotBeFound("customerName.equals=" + UPDATED_CUSTOMER_NAME);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByCustomerNameIsInShouldWork() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where customerName in DEFAULT_CUSTOMER_NAME or UPDATED_CUSTOMER_NAME
        defaultOrderDeliveryShouldBeFound("customerName.in=" + DEFAULT_CUSTOMER_NAME + "," + UPDATED_CUSTOMER_NAME);

        // Get all the orderDeliveryList where customerName equals to UPDATED_CUSTOMER_NAME
        defaultOrderDeliveryShouldNotBeFound("customerName.in=" + UPDATED_CUSTOMER_NAME);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByCustomerNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where customerName is not null
        defaultOrderDeliveryShouldBeFound("customerName.specified=true");

        // Get all the orderDeliveryList where customerName is null
        defaultOrderDeliveryShouldNotBeFound("customerName.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByCustomerAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where customerAddress equals to DEFAULT_CUSTOMER_ADDRESS
        defaultOrderDeliveryShouldBeFound("customerAddress.equals=" + DEFAULT_CUSTOMER_ADDRESS);

        // Get all the orderDeliveryList where customerAddress equals to UPDATED_CUSTOMER_ADDRESS
        defaultOrderDeliveryShouldNotBeFound("customerAddress.equals=" + UPDATED_CUSTOMER_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByCustomerAddressIsInShouldWork() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where customerAddress in DEFAULT_CUSTOMER_ADDRESS or UPDATED_CUSTOMER_ADDRESS
        defaultOrderDeliveryShouldBeFound("customerAddress.in=" + DEFAULT_CUSTOMER_ADDRESS + "," + UPDATED_CUSTOMER_ADDRESS);

        // Get all the orderDeliveryList where customerAddress equals to UPDATED_CUSTOMER_ADDRESS
        defaultOrderDeliveryShouldNotBeFound("customerAddress.in=" + UPDATED_CUSTOMER_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByCustomerAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where customerAddress is not null
        defaultOrderDeliveryShouldBeFound("customerAddress.specified=true");

        // Get all the orderDeliveryList where customerAddress is null
        defaultOrderDeliveryShouldNotBeFound("customerAddress.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByCustomerPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where customerPhone equals to DEFAULT_CUSTOMER_PHONE
        defaultOrderDeliveryShouldBeFound("customerPhone.equals=" + DEFAULT_CUSTOMER_PHONE);

        // Get all the orderDeliveryList where customerPhone equals to UPDATED_CUSTOMER_PHONE
        defaultOrderDeliveryShouldNotBeFound("customerPhone.equals=" + UPDATED_CUSTOMER_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByCustomerPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where customerPhone in DEFAULT_CUSTOMER_PHONE or UPDATED_CUSTOMER_PHONE
        defaultOrderDeliveryShouldBeFound("customerPhone.in=" + DEFAULT_CUSTOMER_PHONE + "," + UPDATED_CUSTOMER_PHONE);

        // Get all the orderDeliveryList where customerPhone equals to UPDATED_CUSTOMER_PHONE
        defaultOrderDeliveryShouldNotBeFound("customerPhone.in=" + UPDATED_CUSTOMER_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByCustomerPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where customerPhone is not null
        defaultOrderDeliveryShouldBeFound("customerPhone.specified=true");

        // Get all the orderDeliveryList where customerPhone is null
        defaultOrderDeliveryShouldNotBeFound("customerPhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByMenuIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where menu equals to DEFAULT_MENU
        defaultOrderDeliveryShouldBeFound("menu.equals=" + DEFAULT_MENU);

        // Get all the orderDeliveryList where menu equals to UPDATED_MENU
        defaultOrderDeliveryShouldNotBeFound("menu.equals=" + UPDATED_MENU);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByMenuIsInShouldWork() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where menu in DEFAULT_MENU or UPDATED_MENU
        defaultOrderDeliveryShouldBeFound("menu.in=" + DEFAULT_MENU + "," + UPDATED_MENU);

        // Get all the orderDeliveryList where menu equals to UPDATED_MENU
        defaultOrderDeliveryShouldNotBeFound("menu.in=" + UPDATED_MENU);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByMenuIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where menu is not null
        defaultOrderDeliveryShouldBeFound("menu.specified=true");

        // Get all the orderDeliveryList where menu is null
        defaultOrderDeliveryShouldNotBeFound("menu.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByTotalAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where totalAmount equals to DEFAULT_TOTAL_AMOUNT
        defaultOrderDeliveryShouldBeFound("totalAmount.equals=" + DEFAULT_TOTAL_AMOUNT);

        // Get all the orderDeliveryList where totalAmount equals to UPDATED_TOTAL_AMOUNT
        defaultOrderDeliveryShouldNotBeFound("totalAmount.equals=" + UPDATED_TOTAL_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByTotalAmountIsInShouldWork() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where totalAmount in DEFAULT_TOTAL_AMOUNT or UPDATED_TOTAL_AMOUNT
        defaultOrderDeliveryShouldBeFound("totalAmount.in=" + DEFAULT_TOTAL_AMOUNT + "," + UPDATED_TOTAL_AMOUNT);

        // Get all the orderDeliveryList where totalAmount equals to UPDATED_TOTAL_AMOUNT
        defaultOrderDeliveryShouldNotBeFound("totalAmount.in=" + UPDATED_TOTAL_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByTotalAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where totalAmount is not null
        defaultOrderDeliveryShouldBeFound("totalAmount.specified=true");

        // Get all the orderDeliveryList where totalAmount is null
        defaultOrderDeliveryShouldNotBeFound("totalAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByOrderDateIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where orderDate equals to DEFAULT_ORDER_DATE
        defaultOrderDeliveryShouldBeFound("orderDate.equals=" + DEFAULT_ORDER_DATE);

        // Get all the orderDeliveryList where orderDate equals to UPDATED_ORDER_DATE
        defaultOrderDeliveryShouldNotBeFound("orderDate.equals=" + UPDATED_ORDER_DATE);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByOrderDateIsInShouldWork() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where orderDate in DEFAULT_ORDER_DATE or UPDATED_ORDER_DATE
        defaultOrderDeliveryShouldBeFound("orderDate.in=" + DEFAULT_ORDER_DATE + "," + UPDATED_ORDER_DATE);

        // Get all the orderDeliveryList where orderDate equals to UPDATED_ORDER_DATE
        defaultOrderDeliveryShouldNotBeFound("orderDate.in=" + UPDATED_ORDER_DATE);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByOrderDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where orderDate is not null
        defaultOrderDeliveryShouldBeFound("orderDate.specified=true");

        // Get all the orderDeliveryList where orderDate is null
        defaultOrderDeliveryShouldNotBeFound("orderDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByOrderDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where orderDate greater than or equals to DEFAULT_ORDER_DATE
        defaultOrderDeliveryShouldBeFound("orderDate.greaterOrEqualThan=" + DEFAULT_ORDER_DATE);

        // Get all the orderDeliveryList where orderDate greater than or equals to UPDATED_ORDER_DATE
        defaultOrderDeliveryShouldNotBeFound("orderDate.greaterOrEqualThan=" + UPDATED_ORDER_DATE);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByOrderDateIsLessThanSomething() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where orderDate less than or equals to DEFAULT_ORDER_DATE
        defaultOrderDeliveryShouldNotBeFound("orderDate.lessThan=" + DEFAULT_ORDER_DATE);

        // Get all the orderDeliveryList where orderDate less than or equals to UPDATED_ORDER_DATE
        defaultOrderDeliveryShouldBeFound("orderDate.lessThan=" + UPDATED_ORDER_DATE);
    }


    @Test
    @Transactional
    public void getAllOrderDeliveriesByShippedDateIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where shippedDate equals to DEFAULT_SHIPPED_DATE
        defaultOrderDeliveryShouldBeFound("shippedDate.equals=" + DEFAULT_SHIPPED_DATE);

        // Get all the orderDeliveryList where shippedDate equals to UPDATED_SHIPPED_DATE
        defaultOrderDeliveryShouldNotBeFound("shippedDate.equals=" + UPDATED_SHIPPED_DATE);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByShippedDateIsInShouldWork() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where shippedDate in DEFAULT_SHIPPED_DATE or UPDATED_SHIPPED_DATE
        defaultOrderDeliveryShouldBeFound("shippedDate.in=" + DEFAULT_SHIPPED_DATE + "," + UPDATED_SHIPPED_DATE);

        // Get all the orderDeliveryList where shippedDate equals to UPDATED_SHIPPED_DATE
        defaultOrderDeliveryShouldNotBeFound("shippedDate.in=" + UPDATED_SHIPPED_DATE);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByShippedDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where shippedDate is not null
        defaultOrderDeliveryShouldBeFound("shippedDate.specified=true");

        // Get all the orderDeliveryList where shippedDate is null
        defaultOrderDeliveryShouldNotBeFound("shippedDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByShippedDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where shippedDate greater than or equals to DEFAULT_SHIPPED_DATE
        defaultOrderDeliveryShouldBeFound("shippedDate.greaterOrEqualThan=" + DEFAULT_SHIPPED_DATE);

        // Get all the orderDeliveryList where shippedDate greater than or equals to UPDATED_SHIPPED_DATE
        defaultOrderDeliveryShouldNotBeFound("shippedDate.greaterOrEqualThan=" + UPDATED_SHIPPED_DATE);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByShippedDateIsLessThanSomething() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where shippedDate less than or equals to DEFAULT_SHIPPED_DATE
        defaultOrderDeliveryShouldNotBeFound("shippedDate.lessThan=" + DEFAULT_SHIPPED_DATE);

        // Get all the orderDeliveryList where shippedDate less than or equals to UPDATED_SHIPPED_DATE
        defaultOrderDeliveryShouldBeFound("shippedDate.lessThan=" + UPDATED_SHIPPED_DATE);
    }


    @Test
    @Transactional
    public void getAllOrderDeliveriesByShipViaIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where shipVia equals to DEFAULT_SHIP_VIA
        defaultOrderDeliveryShouldBeFound("shipVia.equals=" + DEFAULT_SHIP_VIA);

        // Get all the orderDeliveryList where shipVia equals to UPDATED_SHIP_VIA
        defaultOrderDeliveryShouldNotBeFound("shipVia.equals=" + UPDATED_SHIP_VIA);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByShipViaIsInShouldWork() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where shipVia in DEFAULT_SHIP_VIA or UPDATED_SHIP_VIA
        defaultOrderDeliveryShouldBeFound("shipVia.in=" + DEFAULT_SHIP_VIA + "," + UPDATED_SHIP_VIA);

        // Get all the orderDeliveryList where shipVia equals to UPDATED_SHIP_VIA
        defaultOrderDeliveryShouldNotBeFound("shipVia.in=" + UPDATED_SHIP_VIA);
    }

    @Test
    @Transactional
    public void getAllOrderDeliveriesByShipViaIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        // Get all the orderDeliveryList where shipVia is not null
        defaultOrderDeliveryShouldBeFound("shipVia.specified=true");

        // Get all the orderDeliveryList where shipVia is null
        defaultOrderDeliveryShouldNotBeFound("shipVia.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultOrderDeliveryShouldBeFound(String filter) throws Exception {
        restOrderDeliveryMockMvc.perform(get("/api/order-deliveries?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderDelivery.getId().intValue())))
            .andExpect(jsonPath("$.[*].employeeId").value(hasItem(DEFAULT_EMPLOYEE_ID.intValue())))
            .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME.toString())))
            .andExpect(jsonPath("$.[*].customerAddress").value(hasItem(DEFAULT_CUSTOMER_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].customerPhone").value(hasItem(DEFAULT_CUSTOMER_PHONE.toString())))
            .andExpect(jsonPath("$.[*].menu").value(hasItem(DEFAULT_MENU.toString())))
            .andExpect(jsonPath("$.[*].totalAmount").value(hasItem(DEFAULT_TOTAL_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].orderDate").value(hasItem(sameInstant(DEFAULT_ORDER_DATE))))
            .andExpect(jsonPath("$.[*].shippedDate").value(hasItem(sameInstant(DEFAULT_SHIPPED_DATE))))
            .andExpect(jsonPath("$.[*].shipVia").value(hasItem(DEFAULT_SHIP_VIA.toString())));

        // Check, that the count call also returns 1
        restOrderDeliveryMockMvc.perform(get("/api/order-deliveries/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultOrderDeliveryShouldNotBeFound(String filter) throws Exception {
        restOrderDeliveryMockMvc.perform(get("/api/order-deliveries?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restOrderDeliveryMockMvc.perform(get("/api/order-deliveries/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingOrderDelivery() throws Exception {
        // Get the orderDelivery
        restOrderDeliveryMockMvc.perform(get("/api/order-deliveries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderDelivery() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        int databaseSizeBeforeUpdate = orderDeliveryRepository.findAll().size();

        // Update the orderDelivery
        OrderDelivery updatedOrderDelivery = orderDeliveryRepository.findById(orderDelivery.getId()).get();
        // Disconnect from session so that the updates on updatedOrderDelivery are not directly saved in db
        em.detach(updatedOrderDelivery);
        updatedOrderDelivery
            .employeeId(UPDATED_EMPLOYEE_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .customerAddress(UPDATED_CUSTOMER_ADDRESS)
            .customerPhone(UPDATED_CUSTOMER_PHONE)
            .menu(UPDATED_MENU)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .orderDate(UPDATED_ORDER_DATE)
            .shippedDate(UPDATED_SHIPPED_DATE)
            .shipVia(UPDATED_SHIP_VIA);
        OrderDeliveryDTO orderDeliveryDTO = orderDeliveryMapper.toDto(updatedOrderDelivery);

        restOrderDeliveryMockMvc.perform(put("/api/order-deliveries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderDeliveryDTO)))
            .andExpect(status().isOk());

        // Validate the OrderDelivery in the database
        List<OrderDelivery> orderDeliveryList = orderDeliveryRepository.findAll();
        assertThat(orderDeliveryList).hasSize(databaseSizeBeforeUpdate);
        OrderDelivery testOrderDelivery = orderDeliveryList.get(orderDeliveryList.size() - 1);
        assertThat(testOrderDelivery.getEmployeeId()).isEqualTo(UPDATED_EMPLOYEE_ID);
        assertThat(testOrderDelivery.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testOrderDelivery.getCustomerAddress()).isEqualTo(UPDATED_CUSTOMER_ADDRESS);
        assertThat(testOrderDelivery.getCustomerPhone()).isEqualTo(UPDATED_CUSTOMER_PHONE);
        assertThat(testOrderDelivery.getMenu()).isEqualTo(UPDATED_MENU);
        assertThat(testOrderDelivery.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testOrderDelivery.getOrderDate()).isEqualTo(UPDATED_ORDER_DATE);
        assertThat(testOrderDelivery.getShippedDate()).isEqualTo(UPDATED_SHIPPED_DATE);
        assertThat(testOrderDelivery.getShipVia()).isEqualTo(UPDATED_SHIP_VIA);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderDelivery() throws Exception {
        int databaseSizeBeforeUpdate = orderDeliveryRepository.findAll().size();

        // Create the OrderDelivery
        OrderDeliveryDTO orderDeliveryDTO = orderDeliveryMapper.toDto(orderDelivery);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderDeliveryMockMvc.perform(put("/api/order-deliveries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderDeliveryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderDelivery in the database
        List<OrderDelivery> orderDeliveryList = orderDeliveryRepository.findAll();
        assertThat(orderDeliveryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderDelivery() throws Exception {
        // Initialize the database
        orderDeliveryRepository.saveAndFlush(orderDelivery);

        int databaseSizeBeforeDelete = orderDeliveryRepository.findAll().size();

        // Get the orderDelivery
        restOrderDeliveryMockMvc.perform(delete("/api/order-deliveries/{id}", orderDelivery.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OrderDelivery> orderDeliveryList = orderDeliveryRepository.findAll();
        assertThat(orderDeliveryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderDelivery.class);
        OrderDelivery orderDelivery1 = new OrderDelivery();
        orderDelivery1.setId(1L);
        OrderDelivery orderDelivery2 = new OrderDelivery();
        orderDelivery2.setId(orderDelivery1.getId());
        assertThat(orderDelivery1).isEqualTo(orderDelivery2);
        orderDelivery2.setId(2L);
        assertThat(orderDelivery1).isNotEqualTo(orderDelivery2);
        orderDelivery1.setId(null);
        assertThat(orderDelivery1).isNotEqualTo(orderDelivery2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderDeliveryDTO.class);
        OrderDeliveryDTO orderDeliveryDTO1 = new OrderDeliveryDTO();
        orderDeliveryDTO1.setId(1L);
        OrderDeliveryDTO orderDeliveryDTO2 = new OrderDeliveryDTO();
        assertThat(orderDeliveryDTO1).isNotEqualTo(orderDeliveryDTO2);
        orderDeliveryDTO2.setId(orderDeliveryDTO1.getId());
        assertThat(orderDeliveryDTO1).isEqualTo(orderDeliveryDTO2);
        orderDeliveryDTO2.setId(2L);
        assertThat(orderDeliveryDTO1).isNotEqualTo(orderDeliveryDTO2);
        orderDeliveryDTO1.setId(null);
        assertThat(orderDeliveryDTO1).isNotEqualTo(orderDeliveryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(orderDeliveryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(orderDeliveryMapper.fromId(null)).isNull();
    }
}
