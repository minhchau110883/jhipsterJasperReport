package com.chaung.jasper.web.rest;

import com.chaung.jasper.JhipsterJasperReportApp;

import com.chaung.jasper.domain.SettlementReport;
import com.chaung.jasper.repository.SettlementReportRepository;
import com.chaung.jasper.service.SettlementReportService;
import com.chaung.jasper.service.dto.SettlementReportDTO;
import com.chaung.jasper.service.mapper.SettlementReportMapper;
import com.chaung.jasper.web.rest.errors.ExceptionTranslator;
import com.chaung.jasper.service.dto.SettlementReportCriteria;
import com.chaung.jasper.service.SettlementReportQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
 * Test class for the SettlementReportResource REST controller.
 *
 * @see SettlementReportResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterJasperReportApp.class)
public class SettlementReportResourceIntTest {

    private static final String DEFAULT_PARTNER_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OUTLET_ID = "AAAAAAAAAA";
    private static final String UPDATED_OUTLET_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OUTLET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OUTLET_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final Double DEFAULT_FEE = 1D;
    private static final Double UPDATED_FEE = 2D;

    private static final Float DEFAULT_VAT = 1F;
    private static final Float UPDATED_VAT = 2F;

    private static final Double DEFAULT_FEE_INCLUDED = 1D;
    private static final Double UPDATED_FEE_INCLUDED = 2D;

    private static final Double DEFAULT_SETTLEMENT = 1D;
    private static final Double UPDATED_SETTLEMENT = 2D;

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final Float DEFAULT_WHT = 1F;
    private static final Float UPDATED_WHT = 2F;

    private static final ZonedDateTime DEFAULT_PAYMENT_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_PAYMENT_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SETTLEMENT_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SETTLEMENT_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SOF = "AAAAAAAAAA";
    private static final String UPDATED_SOF = "BBBBBBBBBB";

    private static final String DEFAULT_MERCHANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_MERCHANT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_CHANNEL = "BBBBBBBBBB";

    @Autowired
    private SettlementReportRepository settlementReportRepository;

    @Autowired
    private SettlementReportMapper settlementReportMapper;

    @Autowired
    private SettlementReportService settlementReportService;

    @Autowired
    private SettlementReportQueryService settlementReportQueryService;

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

    private MockMvc restSettlementReportMockMvc;

    private SettlementReport settlementReport;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SettlementReportResource settlementReportResource = new SettlementReportResource(settlementReportService, settlementReportQueryService);
        this.restSettlementReportMockMvc = MockMvcBuilders.standaloneSetup(settlementReportResource)
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
    public static SettlementReport createEntity(EntityManager em) {
        SettlementReport settlementReport = new SettlementReport()
            .partnerTransactionId(DEFAULT_PARTNER_TRANSACTION_ID)
            .transactionId(DEFAULT_TRANSACTION_ID)
            .contactId(DEFAULT_CONTACT_ID)
            .outletId(DEFAULT_OUTLET_ID)
            .outletName(DEFAULT_OUTLET_NAME)
            .amount(DEFAULT_AMOUNT)
            .fee(DEFAULT_FEE)
            .vat(DEFAULT_VAT)
            .feeIncluded(DEFAULT_FEE_INCLUDED)
            .settlement(DEFAULT_SETTLEMENT)
            .currency(DEFAULT_CURRENCY)
            .wht(DEFAULT_WHT)
            .paymentTime(DEFAULT_PAYMENT_TIME)
            .settlementTime(DEFAULT_SETTLEMENT_TIME)
            .type(DEFAULT_TYPE)
            .sof(DEFAULT_SOF)
            .merchantId(DEFAULT_MERCHANT_ID)
            .paymentChannel(DEFAULT_PAYMENT_CHANNEL);
        return settlementReport;
    }

    @Before
    public void initTest() {
        settlementReport = createEntity(em);
    }

    @Test
    @Transactional
    public void createSettlementReport() throws Exception {
        int databaseSizeBeforeCreate = settlementReportRepository.findAll().size();

        // Create the SettlementReport
        SettlementReportDTO settlementReportDTO = settlementReportMapper.toDto(settlementReport);
        restSettlementReportMockMvc.perform(post("/api/settlement-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(settlementReportDTO)))
            .andExpect(status().isCreated());

        // Validate the SettlementReport in the database
        List<SettlementReport> settlementReportList = settlementReportRepository.findAll();
        assertThat(settlementReportList).hasSize(databaseSizeBeforeCreate + 1);
        SettlementReport testSettlementReport = settlementReportList.get(settlementReportList.size() - 1);
        assertThat(testSettlementReport.getPartnerTransactionId()).isEqualTo(DEFAULT_PARTNER_TRANSACTION_ID);
        assertThat(testSettlementReport.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testSettlementReport.getContactId()).isEqualTo(DEFAULT_CONTACT_ID);
        assertThat(testSettlementReport.getOutletId()).isEqualTo(DEFAULT_OUTLET_ID);
        assertThat(testSettlementReport.getOutletName()).isEqualTo(DEFAULT_OUTLET_NAME);
        assertThat(testSettlementReport.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testSettlementReport.getFee()).isEqualTo(DEFAULT_FEE);
        assertThat(testSettlementReport.getVat()).isEqualTo(DEFAULT_VAT);
        assertThat(testSettlementReport.getFeeIncluded()).isEqualTo(DEFAULT_FEE_INCLUDED);
        assertThat(testSettlementReport.getSettlement()).isEqualTo(DEFAULT_SETTLEMENT);
        assertThat(testSettlementReport.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testSettlementReport.getWht()).isEqualTo(DEFAULT_WHT);
        assertThat(testSettlementReport.getPaymentTime()).isEqualTo(DEFAULT_PAYMENT_TIME);
        assertThat(testSettlementReport.getSettlementTime()).isEqualTo(DEFAULT_SETTLEMENT_TIME);
        assertThat(testSettlementReport.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testSettlementReport.getSof()).isEqualTo(DEFAULT_SOF);
        assertThat(testSettlementReport.getMerchantId()).isEqualTo(DEFAULT_MERCHANT_ID);
        assertThat(testSettlementReport.getPaymentChannel()).isEqualTo(DEFAULT_PAYMENT_CHANNEL);
    }

    @Test
    @Transactional
    public void createSettlementReportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = settlementReportRepository.findAll().size();

        // Create the SettlementReport with an existing ID
        settlementReport.setId(1L);
        SettlementReportDTO settlementReportDTO = settlementReportMapper.toDto(settlementReport);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSettlementReportMockMvc.perform(post("/api/settlement-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(settlementReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SettlementReport in the database
        List<SettlementReport> settlementReportList = settlementReportRepository.findAll();
        assertThat(settlementReportList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSettlementReports() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList
        restSettlementReportMockMvc.perform(get("/api/settlement-reports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(settlementReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].partnerTransactionId").value(hasItem(DEFAULT_PARTNER_TRANSACTION_ID.toString())))
            .andExpect(jsonPath("$.[*].transactionId").value(hasItem(DEFAULT_TRANSACTION_ID.toString())))
            .andExpect(jsonPath("$.[*].contactId").value(hasItem(DEFAULT_CONTACT_ID.toString())))
            .andExpect(jsonPath("$.[*].outletId").value(hasItem(DEFAULT_OUTLET_ID.toString())))
            .andExpect(jsonPath("$.[*].outletName").value(hasItem(DEFAULT_OUTLET_NAME.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].fee").value(hasItem(DEFAULT_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].vat").value(hasItem(DEFAULT_VAT.doubleValue())))
            .andExpect(jsonPath("$.[*].feeIncluded").value(hasItem(DEFAULT_FEE_INCLUDED.doubleValue())))
            .andExpect(jsonPath("$.[*].settlement").value(hasItem(DEFAULT_SETTLEMENT.doubleValue())))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.toString())))
            .andExpect(jsonPath("$.[*].wht").value(hasItem(DEFAULT_WHT.doubleValue())))
            .andExpect(jsonPath("$.[*].paymentTime").value(hasItem(sameInstant(DEFAULT_PAYMENT_TIME))))
            .andExpect(jsonPath("$.[*].settlementTime").value(hasItem(sameInstant(DEFAULT_SETTLEMENT_TIME))))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].sof").value(hasItem(DEFAULT_SOF.toString())))
            .andExpect(jsonPath("$.[*].merchantId").value(hasItem(DEFAULT_MERCHANT_ID.toString())))
            .andExpect(jsonPath("$.[*].paymentChannel").value(hasItem(DEFAULT_PAYMENT_CHANNEL.toString())));
    }
    
    @Test
    @Transactional
    public void getSettlementReport() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get the settlementReport
        restSettlementReportMockMvc.perform(get("/api/settlement-reports/{id}", settlementReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(settlementReport.getId().intValue()))
            .andExpect(jsonPath("$.partnerTransactionId").value(DEFAULT_PARTNER_TRANSACTION_ID.toString()))
            .andExpect(jsonPath("$.transactionId").value(DEFAULT_TRANSACTION_ID.toString()))
            .andExpect(jsonPath("$.contactId").value(DEFAULT_CONTACT_ID.toString()))
            .andExpect(jsonPath("$.outletId").value(DEFAULT_OUTLET_ID.toString()))
            .andExpect(jsonPath("$.outletName").value(DEFAULT_OUTLET_NAME.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.fee").value(DEFAULT_FEE.doubleValue()))
            .andExpect(jsonPath("$.vat").value(DEFAULT_VAT.doubleValue()))
            .andExpect(jsonPath("$.feeIncluded").value(DEFAULT_FEE_INCLUDED.doubleValue()))
            .andExpect(jsonPath("$.settlement").value(DEFAULT_SETTLEMENT.doubleValue()))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.toString()))
            .andExpect(jsonPath("$.wht").value(DEFAULT_WHT.doubleValue()))
            .andExpect(jsonPath("$.paymentTime").value(sameInstant(DEFAULT_PAYMENT_TIME)))
            .andExpect(jsonPath("$.settlementTime").value(sameInstant(DEFAULT_SETTLEMENT_TIME)))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.sof").value(DEFAULT_SOF.toString()))
            .andExpect(jsonPath("$.merchantId").value(DEFAULT_MERCHANT_ID.toString()))
            .andExpect(jsonPath("$.paymentChannel").value(DEFAULT_PAYMENT_CHANNEL.toString()));
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByPartnerTransactionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where partnerTransactionId equals to DEFAULT_PARTNER_TRANSACTION_ID
        defaultSettlementReportShouldBeFound("partnerTransactionId.equals=" + DEFAULT_PARTNER_TRANSACTION_ID);

        // Get all the settlementReportList where partnerTransactionId equals to UPDATED_PARTNER_TRANSACTION_ID
        defaultSettlementReportShouldNotBeFound("partnerTransactionId.equals=" + UPDATED_PARTNER_TRANSACTION_ID);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByPartnerTransactionIdIsInShouldWork() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where partnerTransactionId in DEFAULT_PARTNER_TRANSACTION_ID or UPDATED_PARTNER_TRANSACTION_ID
        defaultSettlementReportShouldBeFound("partnerTransactionId.in=" + DEFAULT_PARTNER_TRANSACTION_ID + "," + UPDATED_PARTNER_TRANSACTION_ID);

        // Get all the settlementReportList where partnerTransactionId equals to UPDATED_PARTNER_TRANSACTION_ID
        defaultSettlementReportShouldNotBeFound("partnerTransactionId.in=" + UPDATED_PARTNER_TRANSACTION_ID);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByPartnerTransactionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where partnerTransactionId is not null
        defaultSettlementReportShouldBeFound("partnerTransactionId.specified=true");

        // Get all the settlementReportList where partnerTransactionId is null
        defaultSettlementReportShouldNotBeFound("partnerTransactionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByTransactionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where transactionId equals to DEFAULT_TRANSACTION_ID
        defaultSettlementReportShouldBeFound("transactionId.equals=" + DEFAULT_TRANSACTION_ID);

        // Get all the settlementReportList where transactionId equals to UPDATED_TRANSACTION_ID
        defaultSettlementReportShouldNotBeFound("transactionId.equals=" + UPDATED_TRANSACTION_ID);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByTransactionIdIsInShouldWork() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where transactionId in DEFAULT_TRANSACTION_ID or UPDATED_TRANSACTION_ID
        defaultSettlementReportShouldBeFound("transactionId.in=" + DEFAULT_TRANSACTION_ID + "," + UPDATED_TRANSACTION_ID);

        // Get all the settlementReportList where transactionId equals to UPDATED_TRANSACTION_ID
        defaultSettlementReportShouldNotBeFound("transactionId.in=" + UPDATED_TRANSACTION_ID);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByTransactionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where transactionId is not null
        defaultSettlementReportShouldBeFound("transactionId.specified=true");

        // Get all the settlementReportList where transactionId is null
        defaultSettlementReportShouldNotBeFound("transactionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByContactIdIsEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where contactId equals to DEFAULT_CONTACT_ID
        defaultSettlementReportShouldBeFound("contactId.equals=" + DEFAULT_CONTACT_ID);

        // Get all the settlementReportList where contactId equals to UPDATED_CONTACT_ID
        defaultSettlementReportShouldNotBeFound("contactId.equals=" + UPDATED_CONTACT_ID);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByContactIdIsInShouldWork() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where contactId in DEFAULT_CONTACT_ID or UPDATED_CONTACT_ID
        defaultSettlementReportShouldBeFound("contactId.in=" + DEFAULT_CONTACT_ID + "," + UPDATED_CONTACT_ID);

        // Get all the settlementReportList where contactId equals to UPDATED_CONTACT_ID
        defaultSettlementReportShouldNotBeFound("contactId.in=" + UPDATED_CONTACT_ID);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByContactIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where contactId is not null
        defaultSettlementReportShouldBeFound("contactId.specified=true");

        // Get all the settlementReportList where contactId is null
        defaultSettlementReportShouldNotBeFound("contactId.specified=false");
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByOutletIdIsEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where outletId equals to DEFAULT_OUTLET_ID
        defaultSettlementReportShouldBeFound("outletId.equals=" + DEFAULT_OUTLET_ID);

        // Get all the settlementReportList where outletId equals to UPDATED_OUTLET_ID
        defaultSettlementReportShouldNotBeFound("outletId.equals=" + UPDATED_OUTLET_ID);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByOutletIdIsInShouldWork() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where outletId in DEFAULT_OUTLET_ID or UPDATED_OUTLET_ID
        defaultSettlementReportShouldBeFound("outletId.in=" + DEFAULT_OUTLET_ID + "," + UPDATED_OUTLET_ID);

        // Get all the settlementReportList where outletId equals to UPDATED_OUTLET_ID
        defaultSettlementReportShouldNotBeFound("outletId.in=" + UPDATED_OUTLET_ID);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByOutletIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where outletId is not null
        defaultSettlementReportShouldBeFound("outletId.specified=true");

        // Get all the settlementReportList where outletId is null
        defaultSettlementReportShouldNotBeFound("outletId.specified=false");
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByOutletNameIsEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where outletName equals to DEFAULT_OUTLET_NAME
        defaultSettlementReportShouldBeFound("outletName.equals=" + DEFAULT_OUTLET_NAME);

        // Get all the settlementReportList where outletName equals to UPDATED_OUTLET_NAME
        defaultSettlementReportShouldNotBeFound("outletName.equals=" + UPDATED_OUTLET_NAME);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByOutletNameIsInShouldWork() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where outletName in DEFAULT_OUTLET_NAME or UPDATED_OUTLET_NAME
        defaultSettlementReportShouldBeFound("outletName.in=" + DEFAULT_OUTLET_NAME + "," + UPDATED_OUTLET_NAME);

        // Get all the settlementReportList where outletName equals to UPDATED_OUTLET_NAME
        defaultSettlementReportShouldNotBeFound("outletName.in=" + UPDATED_OUTLET_NAME);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByOutletNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where outletName is not null
        defaultSettlementReportShouldBeFound("outletName.specified=true");

        // Get all the settlementReportList where outletName is null
        defaultSettlementReportShouldNotBeFound("outletName.specified=false");
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where amount equals to DEFAULT_AMOUNT
        defaultSettlementReportShouldBeFound("amount.equals=" + DEFAULT_AMOUNT);

        // Get all the settlementReportList where amount equals to UPDATED_AMOUNT
        defaultSettlementReportShouldNotBeFound("amount.equals=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByAmountIsInShouldWork() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where amount in DEFAULT_AMOUNT or UPDATED_AMOUNT
        defaultSettlementReportShouldBeFound("amount.in=" + DEFAULT_AMOUNT + "," + UPDATED_AMOUNT);

        // Get all the settlementReportList where amount equals to UPDATED_AMOUNT
        defaultSettlementReportShouldNotBeFound("amount.in=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where amount is not null
        defaultSettlementReportShouldBeFound("amount.specified=true");

        // Get all the settlementReportList where amount is null
        defaultSettlementReportShouldNotBeFound("amount.specified=false");
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByFeeIsEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where fee equals to DEFAULT_FEE
        defaultSettlementReportShouldBeFound("fee.equals=" + DEFAULT_FEE);

        // Get all the settlementReportList where fee equals to UPDATED_FEE
        defaultSettlementReportShouldNotBeFound("fee.equals=" + UPDATED_FEE);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByFeeIsInShouldWork() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where fee in DEFAULT_FEE or UPDATED_FEE
        defaultSettlementReportShouldBeFound("fee.in=" + DEFAULT_FEE + "," + UPDATED_FEE);

        // Get all the settlementReportList where fee equals to UPDATED_FEE
        defaultSettlementReportShouldNotBeFound("fee.in=" + UPDATED_FEE);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByFeeIsNullOrNotNull() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where fee is not null
        defaultSettlementReportShouldBeFound("fee.specified=true");

        // Get all the settlementReportList where fee is null
        defaultSettlementReportShouldNotBeFound("fee.specified=false");
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByVatIsEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where vat equals to DEFAULT_VAT
        defaultSettlementReportShouldBeFound("vat.equals=" + DEFAULT_VAT);

        // Get all the settlementReportList where vat equals to UPDATED_VAT
        defaultSettlementReportShouldNotBeFound("vat.equals=" + UPDATED_VAT);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByVatIsInShouldWork() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where vat in DEFAULT_VAT or UPDATED_VAT
        defaultSettlementReportShouldBeFound("vat.in=" + DEFAULT_VAT + "," + UPDATED_VAT);

        // Get all the settlementReportList where vat equals to UPDATED_VAT
        defaultSettlementReportShouldNotBeFound("vat.in=" + UPDATED_VAT);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByVatIsNullOrNotNull() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where vat is not null
        defaultSettlementReportShouldBeFound("vat.specified=true");

        // Get all the settlementReportList where vat is null
        defaultSettlementReportShouldNotBeFound("vat.specified=false");
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByFeeIncludedIsEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where feeIncluded equals to DEFAULT_FEE_INCLUDED
        defaultSettlementReportShouldBeFound("feeIncluded.equals=" + DEFAULT_FEE_INCLUDED);

        // Get all the settlementReportList where feeIncluded equals to UPDATED_FEE_INCLUDED
        defaultSettlementReportShouldNotBeFound("feeIncluded.equals=" + UPDATED_FEE_INCLUDED);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByFeeIncludedIsInShouldWork() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where feeIncluded in DEFAULT_FEE_INCLUDED or UPDATED_FEE_INCLUDED
        defaultSettlementReportShouldBeFound("feeIncluded.in=" + DEFAULT_FEE_INCLUDED + "," + UPDATED_FEE_INCLUDED);

        // Get all the settlementReportList where feeIncluded equals to UPDATED_FEE_INCLUDED
        defaultSettlementReportShouldNotBeFound("feeIncluded.in=" + UPDATED_FEE_INCLUDED);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByFeeIncludedIsNullOrNotNull() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where feeIncluded is not null
        defaultSettlementReportShouldBeFound("feeIncluded.specified=true");

        // Get all the settlementReportList where feeIncluded is null
        defaultSettlementReportShouldNotBeFound("feeIncluded.specified=false");
    }

    @Test
    @Transactional
    public void getAllSettlementReportsBySettlementIsEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where settlement equals to DEFAULT_SETTLEMENT
        defaultSettlementReportShouldBeFound("settlement.equals=" + DEFAULT_SETTLEMENT);

        // Get all the settlementReportList where settlement equals to UPDATED_SETTLEMENT
        defaultSettlementReportShouldNotBeFound("settlement.equals=" + UPDATED_SETTLEMENT);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsBySettlementIsInShouldWork() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where settlement in DEFAULT_SETTLEMENT or UPDATED_SETTLEMENT
        defaultSettlementReportShouldBeFound("settlement.in=" + DEFAULT_SETTLEMENT + "," + UPDATED_SETTLEMENT);

        // Get all the settlementReportList where settlement equals to UPDATED_SETTLEMENT
        defaultSettlementReportShouldNotBeFound("settlement.in=" + UPDATED_SETTLEMENT);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsBySettlementIsNullOrNotNull() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where settlement is not null
        defaultSettlementReportShouldBeFound("settlement.specified=true");

        // Get all the settlementReportList where settlement is null
        defaultSettlementReportShouldNotBeFound("settlement.specified=false");
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByCurrencyIsEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where currency equals to DEFAULT_CURRENCY
        defaultSettlementReportShouldBeFound("currency.equals=" + DEFAULT_CURRENCY);

        // Get all the settlementReportList where currency equals to UPDATED_CURRENCY
        defaultSettlementReportShouldNotBeFound("currency.equals=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByCurrencyIsInShouldWork() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where currency in DEFAULT_CURRENCY or UPDATED_CURRENCY
        defaultSettlementReportShouldBeFound("currency.in=" + DEFAULT_CURRENCY + "," + UPDATED_CURRENCY);

        // Get all the settlementReportList where currency equals to UPDATED_CURRENCY
        defaultSettlementReportShouldNotBeFound("currency.in=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByCurrencyIsNullOrNotNull() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where currency is not null
        defaultSettlementReportShouldBeFound("currency.specified=true");

        // Get all the settlementReportList where currency is null
        defaultSettlementReportShouldNotBeFound("currency.specified=false");
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByWhtIsEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where wht equals to DEFAULT_WHT
        defaultSettlementReportShouldBeFound("wht.equals=" + DEFAULT_WHT);

        // Get all the settlementReportList where wht equals to UPDATED_WHT
        defaultSettlementReportShouldNotBeFound("wht.equals=" + UPDATED_WHT);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByWhtIsInShouldWork() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where wht in DEFAULT_WHT or UPDATED_WHT
        defaultSettlementReportShouldBeFound("wht.in=" + DEFAULT_WHT + "," + UPDATED_WHT);

        // Get all the settlementReportList where wht equals to UPDATED_WHT
        defaultSettlementReportShouldNotBeFound("wht.in=" + UPDATED_WHT);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByWhtIsNullOrNotNull() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where wht is not null
        defaultSettlementReportShouldBeFound("wht.specified=true");

        // Get all the settlementReportList where wht is null
        defaultSettlementReportShouldNotBeFound("wht.specified=false");
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByPaymentTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where paymentTime equals to DEFAULT_PAYMENT_TIME
        defaultSettlementReportShouldBeFound("paymentTime.equals=" + DEFAULT_PAYMENT_TIME);

        // Get all the settlementReportList where paymentTime equals to UPDATED_PAYMENT_TIME
        defaultSettlementReportShouldNotBeFound("paymentTime.equals=" + UPDATED_PAYMENT_TIME);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByPaymentTimeIsInShouldWork() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where paymentTime in DEFAULT_PAYMENT_TIME or UPDATED_PAYMENT_TIME
        defaultSettlementReportShouldBeFound("paymentTime.in=" + DEFAULT_PAYMENT_TIME + "," + UPDATED_PAYMENT_TIME);

        // Get all the settlementReportList where paymentTime equals to UPDATED_PAYMENT_TIME
        defaultSettlementReportShouldNotBeFound("paymentTime.in=" + UPDATED_PAYMENT_TIME);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByPaymentTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where paymentTime is not null
        defaultSettlementReportShouldBeFound("paymentTime.specified=true");

        // Get all the settlementReportList where paymentTime is null
        defaultSettlementReportShouldNotBeFound("paymentTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByPaymentTimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where paymentTime greater than or equals to DEFAULT_PAYMENT_TIME
        defaultSettlementReportShouldBeFound("paymentTime.greaterOrEqualThan=" + DEFAULT_PAYMENT_TIME);

        // Get all the settlementReportList where paymentTime greater than or equals to UPDATED_PAYMENT_TIME
        defaultSettlementReportShouldNotBeFound("paymentTime.greaterOrEqualThan=" + UPDATED_PAYMENT_TIME);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByPaymentTimeIsLessThanSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where paymentTime less than or equals to DEFAULT_PAYMENT_TIME
        defaultSettlementReportShouldNotBeFound("paymentTime.lessThan=" + DEFAULT_PAYMENT_TIME);

        // Get all the settlementReportList where paymentTime less than or equals to UPDATED_PAYMENT_TIME
        defaultSettlementReportShouldBeFound("paymentTime.lessThan=" + UPDATED_PAYMENT_TIME);
    }


    @Test
    @Transactional
    public void getAllSettlementReportsBySettlementTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where settlementTime equals to DEFAULT_SETTLEMENT_TIME
        defaultSettlementReportShouldBeFound("settlementTime.equals=" + DEFAULT_SETTLEMENT_TIME);

        // Get all the settlementReportList where settlementTime equals to UPDATED_SETTLEMENT_TIME
        defaultSettlementReportShouldNotBeFound("settlementTime.equals=" + UPDATED_SETTLEMENT_TIME);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsBySettlementTimeIsInShouldWork() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where settlementTime in DEFAULT_SETTLEMENT_TIME or UPDATED_SETTLEMENT_TIME
        defaultSettlementReportShouldBeFound("settlementTime.in=" + DEFAULT_SETTLEMENT_TIME + "," + UPDATED_SETTLEMENT_TIME);

        // Get all the settlementReportList where settlementTime equals to UPDATED_SETTLEMENT_TIME
        defaultSettlementReportShouldNotBeFound("settlementTime.in=" + UPDATED_SETTLEMENT_TIME);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsBySettlementTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where settlementTime is not null
        defaultSettlementReportShouldBeFound("settlementTime.specified=true");

        // Get all the settlementReportList where settlementTime is null
        defaultSettlementReportShouldNotBeFound("settlementTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllSettlementReportsBySettlementTimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where settlementTime greater than or equals to DEFAULT_SETTLEMENT_TIME
        defaultSettlementReportShouldBeFound("settlementTime.greaterOrEqualThan=" + DEFAULT_SETTLEMENT_TIME);

        // Get all the settlementReportList where settlementTime greater than or equals to UPDATED_SETTLEMENT_TIME
        defaultSettlementReportShouldNotBeFound("settlementTime.greaterOrEqualThan=" + UPDATED_SETTLEMENT_TIME);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsBySettlementTimeIsLessThanSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where settlementTime less than or equals to DEFAULT_SETTLEMENT_TIME
        defaultSettlementReportShouldNotBeFound("settlementTime.lessThan=" + DEFAULT_SETTLEMENT_TIME);

        // Get all the settlementReportList where settlementTime less than or equals to UPDATED_SETTLEMENT_TIME
        defaultSettlementReportShouldBeFound("settlementTime.lessThan=" + UPDATED_SETTLEMENT_TIME);
    }


    @Test
    @Transactional
    public void getAllSettlementReportsByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where type equals to DEFAULT_TYPE
        defaultSettlementReportShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the settlementReportList where type equals to UPDATED_TYPE
        defaultSettlementReportShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultSettlementReportShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the settlementReportList where type equals to UPDATED_TYPE
        defaultSettlementReportShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where type is not null
        defaultSettlementReportShouldBeFound("type.specified=true");

        // Get all the settlementReportList where type is null
        defaultSettlementReportShouldNotBeFound("type.specified=false");
    }

    @Test
    @Transactional
    public void getAllSettlementReportsBySofIsEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where sof equals to DEFAULT_SOF
        defaultSettlementReportShouldBeFound("sof.equals=" + DEFAULT_SOF);

        // Get all the settlementReportList where sof equals to UPDATED_SOF
        defaultSettlementReportShouldNotBeFound("sof.equals=" + UPDATED_SOF);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsBySofIsInShouldWork() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where sof in DEFAULT_SOF or UPDATED_SOF
        defaultSettlementReportShouldBeFound("sof.in=" + DEFAULT_SOF + "," + UPDATED_SOF);

        // Get all the settlementReportList where sof equals to UPDATED_SOF
        defaultSettlementReportShouldNotBeFound("sof.in=" + UPDATED_SOF);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsBySofIsNullOrNotNull() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where sof is not null
        defaultSettlementReportShouldBeFound("sof.specified=true");

        // Get all the settlementReportList where sof is null
        defaultSettlementReportShouldNotBeFound("sof.specified=false");
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByMerchantIdIsEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where merchantId equals to DEFAULT_MERCHANT_ID
        defaultSettlementReportShouldBeFound("merchantId.equals=" + DEFAULT_MERCHANT_ID);

        // Get all the settlementReportList where merchantId equals to UPDATED_MERCHANT_ID
        defaultSettlementReportShouldNotBeFound("merchantId.equals=" + UPDATED_MERCHANT_ID);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByMerchantIdIsInShouldWork() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where merchantId in DEFAULT_MERCHANT_ID or UPDATED_MERCHANT_ID
        defaultSettlementReportShouldBeFound("merchantId.in=" + DEFAULT_MERCHANT_ID + "," + UPDATED_MERCHANT_ID);

        // Get all the settlementReportList where merchantId equals to UPDATED_MERCHANT_ID
        defaultSettlementReportShouldNotBeFound("merchantId.in=" + UPDATED_MERCHANT_ID);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByMerchantIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where merchantId is not null
        defaultSettlementReportShouldBeFound("merchantId.specified=true");

        // Get all the settlementReportList where merchantId is null
        defaultSettlementReportShouldNotBeFound("merchantId.specified=false");
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByPaymentChannelIsEqualToSomething() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where paymentChannel equals to DEFAULT_PAYMENT_CHANNEL
        defaultSettlementReportShouldBeFound("paymentChannel.equals=" + DEFAULT_PAYMENT_CHANNEL);

        // Get all the settlementReportList where paymentChannel equals to UPDATED_PAYMENT_CHANNEL
        defaultSettlementReportShouldNotBeFound("paymentChannel.equals=" + UPDATED_PAYMENT_CHANNEL);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByPaymentChannelIsInShouldWork() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where paymentChannel in DEFAULT_PAYMENT_CHANNEL or UPDATED_PAYMENT_CHANNEL
        defaultSettlementReportShouldBeFound("paymentChannel.in=" + DEFAULT_PAYMENT_CHANNEL + "," + UPDATED_PAYMENT_CHANNEL);

        // Get all the settlementReportList where paymentChannel equals to UPDATED_PAYMENT_CHANNEL
        defaultSettlementReportShouldNotBeFound("paymentChannel.in=" + UPDATED_PAYMENT_CHANNEL);
    }

    @Test
    @Transactional
    public void getAllSettlementReportsByPaymentChannelIsNullOrNotNull() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        // Get all the settlementReportList where paymentChannel is not null
        defaultSettlementReportShouldBeFound("paymentChannel.specified=true");

        // Get all the settlementReportList where paymentChannel is null
        defaultSettlementReportShouldNotBeFound("paymentChannel.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultSettlementReportShouldBeFound(String filter) throws Exception {
        restSettlementReportMockMvc.perform(get("/api/settlement-reports?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(settlementReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].partnerTransactionId").value(hasItem(DEFAULT_PARTNER_TRANSACTION_ID.toString())))
            .andExpect(jsonPath("$.[*].transactionId").value(hasItem(DEFAULT_TRANSACTION_ID.toString())))
            .andExpect(jsonPath("$.[*].contactId").value(hasItem(DEFAULT_CONTACT_ID.toString())))
            .andExpect(jsonPath("$.[*].outletId").value(hasItem(DEFAULT_OUTLET_ID.toString())))
            .andExpect(jsonPath("$.[*].outletName").value(hasItem(DEFAULT_OUTLET_NAME.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].fee").value(hasItem(DEFAULT_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].vat").value(hasItem(DEFAULT_VAT.doubleValue())))
            .andExpect(jsonPath("$.[*].feeIncluded").value(hasItem(DEFAULT_FEE_INCLUDED.doubleValue())))
            .andExpect(jsonPath("$.[*].settlement").value(hasItem(DEFAULT_SETTLEMENT.doubleValue())))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.toString())))
            .andExpect(jsonPath("$.[*].wht").value(hasItem(DEFAULT_WHT.doubleValue())))
            .andExpect(jsonPath("$.[*].paymentTime").value(hasItem(sameInstant(DEFAULT_PAYMENT_TIME))))
            .andExpect(jsonPath("$.[*].settlementTime").value(hasItem(sameInstant(DEFAULT_SETTLEMENT_TIME))))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].sof").value(hasItem(DEFAULT_SOF.toString())))
            .andExpect(jsonPath("$.[*].merchantId").value(hasItem(DEFAULT_MERCHANT_ID.toString())))
            .andExpect(jsonPath("$.[*].paymentChannel").value(hasItem(DEFAULT_PAYMENT_CHANNEL.toString())));

        // Check, that the count call also returns 1
        restSettlementReportMockMvc.perform(get("/api/settlement-reports/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultSettlementReportShouldNotBeFound(String filter) throws Exception {
        restSettlementReportMockMvc.perform(get("/api/settlement-reports?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSettlementReportMockMvc.perform(get("/api/settlement-reports/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSettlementReport() throws Exception {
        // Get the settlementReport
        restSettlementReportMockMvc.perform(get("/api/settlement-reports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSettlementReport() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        int databaseSizeBeforeUpdate = settlementReportRepository.findAll().size();

        // Update the settlementReport
        SettlementReport updatedSettlementReport = settlementReportRepository.findById(settlementReport.getId()).get();
        // Disconnect from session so that the updates on updatedSettlementReport are not directly saved in db
        em.detach(updatedSettlementReport);
        updatedSettlementReport
            .partnerTransactionId(UPDATED_PARTNER_TRANSACTION_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .contactId(UPDATED_CONTACT_ID)
            .outletId(UPDATED_OUTLET_ID)
            .outletName(UPDATED_OUTLET_NAME)
            .amount(UPDATED_AMOUNT)
            .fee(UPDATED_FEE)
            .vat(UPDATED_VAT)
            .feeIncluded(UPDATED_FEE_INCLUDED)
            .settlement(UPDATED_SETTLEMENT)
            .currency(UPDATED_CURRENCY)
            .wht(UPDATED_WHT)
            .paymentTime(UPDATED_PAYMENT_TIME)
            .settlementTime(UPDATED_SETTLEMENT_TIME)
            .type(UPDATED_TYPE)
            .sof(UPDATED_SOF)
            .merchantId(UPDATED_MERCHANT_ID)
            .paymentChannel(UPDATED_PAYMENT_CHANNEL);
        SettlementReportDTO settlementReportDTO = settlementReportMapper.toDto(updatedSettlementReport);

        restSettlementReportMockMvc.perform(put("/api/settlement-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(settlementReportDTO)))
            .andExpect(status().isOk());

        // Validate the SettlementReport in the database
        List<SettlementReport> settlementReportList = settlementReportRepository.findAll();
        assertThat(settlementReportList).hasSize(databaseSizeBeforeUpdate);
        SettlementReport testSettlementReport = settlementReportList.get(settlementReportList.size() - 1);
        assertThat(testSettlementReport.getPartnerTransactionId()).isEqualTo(UPDATED_PARTNER_TRANSACTION_ID);
        assertThat(testSettlementReport.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testSettlementReport.getContactId()).isEqualTo(UPDATED_CONTACT_ID);
        assertThat(testSettlementReport.getOutletId()).isEqualTo(UPDATED_OUTLET_ID);
        assertThat(testSettlementReport.getOutletName()).isEqualTo(UPDATED_OUTLET_NAME);
        assertThat(testSettlementReport.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testSettlementReport.getFee()).isEqualTo(UPDATED_FEE);
        assertThat(testSettlementReport.getVat()).isEqualTo(UPDATED_VAT);
        assertThat(testSettlementReport.getFeeIncluded()).isEqualTo(UPDATED_FEE_INCLUDED);
        assertThat(testSettlementReport.getSettlement()).isEqualTo(UPDATED_SETTLEMENT);
        assertThat(testSettlementReport.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testSettlementReport.getWht()).isEqualTo(UPDATED_WHT);
        assertThat(testSettlementReport.getPaymentTime()).isEqualTo(UPDATED_PAYMENT_TIME);
        assertThat(testSettlementReport.getSettlementTime()).isEqualTo(UPDATED_SETTLEMENT_TIME);
        assertThat(testSettlementReport.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testSettlementReport.getSof()).isEqualTo(UPDATED_SOF);
        assertThat(testSettlementReport.getMerchantId()).isEqualTo(UPDATED_MERCHANT_ID);
        assertThat(testSettlementReport.getPaymentChannel()).isEqualTo(UPDATED_PAYMENT_CHANNEL);
    }

    @Test
    @Transactional
    public void updateNonExistingSettlementReport() throws Exception {
        int databaseSizeBeforeUpdate = settlementReportRepository.findAll().size();

        // Create the SettlementReport
        SettlementReportDTO settlementReportDTO = settlementReportMapper.toDto(settlementReport);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSettlementReportMockMvc.perform(put("/api/settlement-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(settlementReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SettlementReport in the database
        List<SettlementReport> settlementReportList = settlementReportRepository.findAll();
        assertThat(settlementReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSettlementReport() throws Exception {
        // Initialize the database
        settlementReportRepository.saveAndFlush(settlementReport);

        int databaseSizeBeforeDelete = settlementReportRepository.findAll().size();

        // Get the settlementReport
        restSettlementReportMockMvc.perform(delete("/api/settlement-reports/{id}", settlementReport.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SettlementReport> settlementReportList = settlementReportRepository.findAll();
        assertThat(settlementReportList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SettlementReport.class);
        SettlementReport settlementReport1 = new SettlementReport();
        settlementReport1.setId(1L);
        SettlementReport settlementReport2 = new SettlementReport();
        settlementReport2.setId(settlementReport1.getId());
        assertThat(settlementReport1).isEqualTo(settlementReport2);
        settlementReport2.setId(2L);
        assertThat(settlementReport1).isNotEqualTo(settlementReport2);
        settlementReport1.setId(null);
        assertThat(settlementReport1).isNotEqualTo(settlementReport2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SettlementReportDTO.class);
        SettlementReportDTO settlementReportDTO1 = new SettlementReportDTO();
        settlementReportDTO1.setId(1L);
        SettlementReportDTO settlementReportDTO2 = new SettlementReportDTO();
        assertThat(settlementReportDTO1).isNotEqualTo(settlementReportDTO2);
        settlementReportDTO2.setId(settlementReportDTO1.getId());
        assertThat(settlementReportDTO1).isEqualTo(settlementReportDTO2);
        settlementReportDTO2.setId(2L);
        assertThat(settlementReportDTO1).isNotEqualTo(settlementReportDTO2);
        settlementReportDTO1.setId(null);
        assertThat(settlementReportDTO1).isNotEqualTo(settlementReportDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(settlementReportMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(settlementReportMapper.fromId(null)).isNull();
    }
}
