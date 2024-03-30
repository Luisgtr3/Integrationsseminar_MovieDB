package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import de.dhbw.ravensburg.wp.mymoviedatabase.dto.AwardDTO;
import de.dhbw.ravensburg.wp.mymoviedatabase.model.Award;
import de.dhbw.ravensburg.wp.mymoviedatabase.service.AwardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@WebMvcTest(AwardControllerImpl.class)
public class AwardControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AwardService awardService;

    private AwardDTO award_1;
    private AwardDTO award_2;

    private List<AwardDTO> awardList;

    @BeforeEach
    public void setUp(){
        award_1 = new AwardDTO(1L, "Oscar", "Best Picture", 2008);  
        award_2 = new AwardDTO(2L, "Oscar", "Best Performance by an Actor in a Supporting Role", 2009);
    
        this.awardList = Arrays.asList(award_1, award_2);
    }

    @Test
    public void testGetAllAwards() throws Exception {
        when(awardService.getAllAwards()).thenReturn(awardList);

        mockMvc.perform(get("/api/v1/awards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(award_1.getId().intValue())))
                .andExpect(jsonPath("$[1].id", is(award_2.getId().intValue())));
    }


    // Add more test methods here
    @Test
    public void ShouldSuccessfullyGetAward() throws Exception {
        when(awardService.getAwardById(1L)).thenReturn(award_1);

        mockMvc.perform(get("/api/v1/awards/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(award_1.getId().intValue())))
                .andExpect(jsonPath("$.academy", is(award_1.getAcademy())))
                .andExpect(jsonPath("$.category", is(award_1.getCategory())))
                .andExpect(jsonPath("$.celebrationYear", is(award_1.getCelebrationYear())));

        verify(awardService, times(1)).getAwardById(1L);
    }

    @Test
    public void ShouldFailUpdateAwardBecauseUnkownId() throws Exception {
        when(awardService.updateAward(any())).thenThrow(new EntityNotFoundException("MovieID does not exist"));

        mockMvc.perform(put("/api/v1/awards/" + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(award_1)))
                .andExpect(status().isNotFound());

        verify(awardService, times(1)).updateAward(any());
    }

    @Test
    public void ShouldFailUpdateAwardBecauseOfConstraintViolation() throws Exception {
        AwardDTO invalidAward = new AwardDTO(1L, "Oscar", "Best Picture", 2025); // Invalid because celebrationYear is 2025
        when(awardService.updateAward(invalidAward)).thenThrow(new ConstraintViolationException("Celebration year must be between 0 and 2024", new HashSet<>()));

        mockMvc.perform(put("/api/v1/awards/" + invalidAward.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(invalidAward)))
                .andExpect(status().isBadRequest());

        verify(awardService, never()).updateAward(invalidAward);
    }

    

    
}