package com.superbank.credit.controller;


import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.superbank.credit.dto.CreditDto;
import com.superbank.credit.dto.DurationDto;
import com.superbank.credit.dto.UserCreditDto;
import com.superbank.credit.model.Credit;
import com.superbank.credit.service.CreditService;
import com.superbank.credit.service.CreditServiceTest;
import com.superbank.credit.service.period.PeriodType;
import com.superbank.credit.service.period.RateType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

@ActiveProfiles("dev")
@SpringBootTest
@AutoConfigureMockMvc
public class CreditControllerTest {

    public static final String ROOT = "$";
    public static final String FIRST_ARRAY_ELEMENT = ROOT + "[0]";
    @MockBean
    private CreditService creditService;
    @Autowired
    private MockMvc       mockMvc;

    @Test
    public void userCredits() throws Exception {
//        Given
        final Long userId = 101L;
        final Credit credit = new Credit("creditDto.title", "creditDto.description",
                                         userId, emptyList());
        final UserCreditDto userCreditDto = UserCreditDto.createUserCreditDto(credit, 1000d);
        when(creditService.creditByUserId(userId)).thenReturn(singletonList(userCreditDto));
//        When
        final ResultActions result = mockMvc.perform(get("/api/v1/credit/user/{userId}", userId)
                                                             .contentType(MediaType.APPLICATION_JSON));
//        Then
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(ROOT, hasSize(1)))
                .andExpect(jsonPath(FIRST_ARRAY_ELEMENT + "title", is(userCreditDto.title)))
                .andExpect(jsonPath(FIRST_ARRAY_ELEMENT + "description", is(userCreditDto.description)))
                .andExpect(jsonPath(FIRST_ARRAY_ELEMENT + "userId", is(userCreditDto.userId.intValue())))
                .andExpect(jsonPath(FIRST_ARRAY_ELEMENT + "remainingSum", is(userCreditDto.remainingSum)));

        verify(creditService, times(1)).creditByUserId(userId);
        verifyNoMoreInteractions(creditService);
    }

    @Test
    public void payNextPeriod() throws Exception {
//        Given
        final Long creditId = 101L;
        doNothing().when(creditService).payNextPeriod(creditId);
//        When
        final ResultActions result = mockMvc.perform(put("/api/v1/credit/{creditId}", creditId)
                                                             .contentType(MediaType.APPLICATION_JSON));
//        Then
        result.andExpect(status().isOk());
        verify(creditService, times(1)).payNextPeriod(creditId);
        verifyNoMoreInteractions(creditService);
    }

    @Test
    public void addCredit() throws Exception {
//        Given
        final DurationDto durationDto = new DurationDto(1, 1, 1);
        final CreditDto creditDto = new CreditDto("CreditTitle", "CreditDescription",
                                                  101L, durationDto, 100d, RateType.FIXED,
                                                  PeriodType.MONTHLY, LocalDate.now());
        final ObjectMapper objectMapper = JsonMapper.builder()
                                                    .addModule(new JavaTimeModule())
                                                    .build();
        final String content = objectMapper.writeValueAsString(creditDto);
        doNothing().when(creditService).addCredit(any(CreditDto.class));
//        When

        final ResultActions result = mockMvc.perform(post("/api/v1/credit")
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(content));
//        Then
        result.andExpect(status().isOk());
        verify(creditService, times(1)).addCredit(any(CreditDto.class));
        verifyNoMoreInteractions(creditService);

    }
}
