package com.virtualsoundnw.quotes.web.rest;

import com.virtualsoundnw.quotes.MQuoteApp;

import com.virtualsoundnw.quotes.domain.Quote;
import com.virtualsoundnw.quotes.repository.QuoteRepository;
import com.virtualsoundnw.quotes.service.QuoteService;
import com.virtualsoundnw.quotes.service.dto.QuoteDTO;
import com.virtualsoundnw.quotes.service.mapper.QuoteMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the QuoteResource REST controller.
 *
 * @see QuoteResource
 */
@RunWith(SpringRunner.class)

@SpringBootTest(classes = MQuoteApp.class)

public class QuoteResourceIntTest {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("Z"));

    private static final Double DEFAULT_LAST = 1D;
    private static final Double UPDATED_LAST = 2D;

    private static final Double DEFAULT_CHANGEAMOUNT = 1D;
    private static final Double UPDATED_CHANGEAMOUNT = 2D;

    private static final Double DEFAULT_CHANGEPERCENT = 1D;
    private static final Double UPDATED_CHANGEPERCENT = 2D;

    private static final Double DEFAULT_SHARESOUTSTANDING = 1D;
    private static final Double UPDATED_SHARESOUTSTANDING = 2D;

    private static final Double DEFAULT_ASK = 1D;
    private static final Double UPDATED_ASK = 2D;

    private static final Double DEFAULT_BID = 1D;
    private static final Double UPDATED_BID = 2D;

    private static final Double DEFAULT_VOLUME = 1D;
    private static final Double UPDATED_VOLUME = 2D;

    private static final Double DEFAULT_TENDAYAVERAGEVOLUME = 1D;
    private static final Double UPDATED_TENDAYAVERAGEVOLUME = 2D;

    private static final ZonedDateTime DEFAULT_LASTTRADETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LASTTRADETIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LASTTRADETIME_STR = dateTimeFormatter.format(DEFAULT_LASTTRADETIME);

    @Inject
    private QuoteRepository quoteRepository;

    @Inject
    private QuoteMapper quoteMapper;

    @Inject
    private QuoteService quoteService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restQuoteMockMvc;

    private Quote quote;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        QuoteResource quoteResource = new QuoteResource();
        ReflectionTestUtils.setField(quoteResource, "quoteService", quoteService);
        this.restQuoteMockMvc = MockMvcBuilders.standaloneSetup(quoteResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Quote createEntity() {
        Quote quote = new Quote()
                .last(DEFAULT_LAST)
                .changeamount(DEFAULT_CHANGEAMOUNT)
                .changepercent(DEFAULT_CHANGEPERCENT)
                .sharesoutstanding(DEFAULT_SHARESOUTSTANDING)
                .ask(DEFAULT_ASK)
                .bid(DEFAULT_BID)
                .volume(DEFAULT_VOLUME)
                .tendayaveragevolume(DEFAULT_TENDAYAVERAGEVOLUME)
                .lasttradetime(DEFAULT_LASTTRADETIME);
        return quote;
    }

    @Before
    public void initTest() {
        quoteRepository.deleteAll();
        quote = createEntity();
    }

    @Test
    public void createQuote() throws Exception {
        int databaseSizeBeforeCreate = quoteRepository.findAll().size();

        // Create the Quote
        QuoteDTO quoteDTO = quoteMapper.quoteToQuoteDTO(quote);

        restQuoteMockMvc.perform(post("/api/quotes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(quoteDTO)))
                .andExpect(status().isCreated());

        // Validate the Quote in the database
        List<Quote> quotes = quoteRepository.findAll();
        assertThat(quotes).hasSize(databaseSizeBeforeCreate + 1);
        Quote testQuote = quotes.get(quotes.size() - 1);
        assertThat(testQuote.getLast()).isEqualTo(DEFAULT_LAST);
        assertThat(testQuote.getChangeamount()).isEqualTo(DEFAULT_CHANGEAMOUNT);
        assertThat(testQuote.getChangepercent()).isEqualTo(DEFAULT_CHANGEPERCENT);
        assertThat(testQuote.getSharesoutstanding()).isEqualTo(DEFAULT_SHARESOUTSTANDING);
        assertThat(testQuote.getAsk()).isEqualTo(DEFAULT_ASK);
        assertThat(testQuote.getBid()).isEqualTo(DEFAULT_BID);
        assertThat(testQuote.getVolume()).isEqualTo(DEFAULT_VOLUME);
        assertThat(testQuote.getTendayaveragevolume()).isEqualTo(DEFAULT_TENDAYAVERAGEVOLUME);
        assertThat(testQuote.getLasttradetime()).isEqualTo(DEFAULT_LASTTRADETIME);
    }

    @Test
    public void getAllQuotes() throws Exception {
        // Initialize the database
        quoteRepository.save(quote);

        // Get all the quotes
        restQuoteMockMvc.perform(get("/api/quotes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(quote.getId())))
                .andExpect(jsonPath("$.[*].last").value(hasItem(DEFAULT_LAST.doubleValue())))
                .andExpect(jsonPath("$.[*].changeamount").value(hasItem(DEFAULT_CHANGEAMOUNT.doubleValue())))
                .andExpect(jsonPath("$.[*].changepercent").value(hasItem(DEFAULT_CHANGEPERCENT.doubleValue())))
                .andExpect(jsonPath("$.[*].sharesoutstanding").value(hasItem(DEFAULT_SHARESOUTSTANDING.doubleValue())))
                .andExpect(jsonPath("$.[*].ask").value(hasItem(DEFAULT_ASK.doubleValue())))
                .andExpect(jsonPath("$.[*].bid").value(hasItem(DEFAULT_BID.doubleValue())))
                .andExpect(jsonPath("$.[*].volume").value(hasItem(DEFAULT_VOLUME.doubleValue())))
                .andExpect(jsonPath("$.[*].tendayaveragevolume").value(hasItem(DEFAULT_TENDAYAVERAGEVOLUME.doubleValue())))
                .andExpect(jsonPath("$.[*].lasttradetime").value(hasItem(DEFAULT_LASTTRADETIME_STR)));
    }

    @Test
    public void getQuote() throws Exception {
        // Initialize the database
        quoteRepository.save(quote);

        // Get the quote
        restQuoteMockMvc.perform(get("/api/quotes/{id}", quote.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(quote.getId()))
            .andExpect(jsonPath("$.last").value(DEFAULT_LAST.doubleValue()))
            .andExpect(jsonPath("$.changeamount").value(DEFAULT_CHANGEAMOUNT.doubleValue()))
            .andExpect(jsonPath("$.changepercent").value(DEFAULT_CHANGEPERCENT.doubleValue()))
            .andExpect(jsonPath("$.sharesoutstanding").value(DEFAULT_SHARESOUTSTANDING.doubleValue()))
            .andExpect(jsonPath("$.ask").value(DEFAULT_ASK.doubleValue()))
            .andExpect(jsonPath("$.bid").value(DEFAULT_BID.doubleValue()))
            .andExpect(jsonPath("$.volume").value(DEFAULT_VOLUME.doubleValue()))
            .andExpect(jsonPath("$.tendayaveragevolume").value(DEFAULT_TENDAYAVERAGEVOLUME.doubleValue()))
            .andExpect(jsonPath("$.lasttradetime").value(DEFAULT_LASTTRADETIME_STR));
    }

    @Test
    public void getNonExistingQuote() throws Exception {
        // Get the quote
        restQuoteMockMvc.perform(get("/api/quotes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateQuote() throws Exception {
        // Initialize the database
        quoteRepository.save(quote);
        int databaseSizeBeforeUpdate = quoteRepository.findAll().size();

        // Update the quote
        Quote updatedQuote = quoteRepository.findOne(quote.getId());
        updatedQuote
                .last(UPDATED_LAST)
                .changeamount(UPDATED_CHANGEAMOUNT)
                .changepercent(UPDATED_CHANGEPERCENT)
                .sharesoutstanding(UPDATED_SHARESOUTSTANDING)
                .ask(UPDATED_ASK)
                .bid(UPDATED_BID)
                .volume(UPDATED_VOLUME)
                .tendayaveragevolume(UPDATED_TENDAYAVERAGEVOLUME)
                .lasttradetime(UPDATED_LASTTRADETIME);
        QuoteDTO quoteDTO = quoteMapper.quoteToQuoteDTO(updatedQuote);

        restQuoteMockMvc.perform(put("/api/quotes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(quoteDTO)))
                .andExpect(status().isOk());

        // Validate the Quote in the database
        List<Quote> quotes = quoteRepository.findAll();
        assertThat(quotes).hasSize(databaseSizeBeforeUpdate);
        Quote testQuote = quotes.get(quotes.size() - 1);
        assertThat(testQuote.getLast()).isEqualTo(UPDATED_LAST);
        assertThat(testQuote.getChangeamount()).isEqualTo(UPDATED_CHANGEAMOUNT);
        assertThat(testQuote.getChangepercent()).isEqualTo(UPDATED_CHANGEPERCENT);
        assertThat(testQuote.getSharesoutstanding()).isEqualTo(UPDATED_SHARESOUTSTANDING);
        assertThat(testQuote.getAsk()).isEqualTo(UPDATED_ASK);
        assertThat(testQuote.getBid()).isEqualTo(UPDATED_BID);
        assertThat(testQuote.getVolume()).isEqualTo(UPDATED_VOLUME);
        assertThat(testQuote.getTendayaveragevolume()).isEqualTo(UPDATED_TENDAYAVERAGEVOLUME);
        assertThat(testQuote.getLasttradetime()).isEqualTo(UPDATED_LASTTRADETIME);
    }

    @Test
    public void deleteQuote() throws Exception {
        // Initialize the database
        quoteRepository.save(quote);
        int databaseSizeBeforeDelete = quoteRepository.findAll().size();

        // Get the quote
        restQuoteMockMvc.perform(delete("/api/quotes/{id}", quote.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Quote> quotes = quoteRepository.findAll();
        assertThat(quotes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
