package com.task.assesment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PatientsApiTest {

  private static final String ENDPOINT_PATH = "/api/patients/{id}/export";

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void givenGoodPatientId_WhenExportPatientDetails_thenReturnCsvBody() throws Exception {
    Integer dummyId = 10;

    MvcResult mvcResult = mockMvc.perform(get(ENDPOINT_PATH, dummyId)
            .header("Staff-UUID", "085b3b37-c46b-4a31-84e8-0b6a3e45ba87"))
        .andReturn();

    assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    Assertions.assertEquals("10,Hylda Aita,19,2020-09-22",
        mvcResult.getResponse().getContentAsString().trim());
  }

  @Test
  public void givenWrongPatientId_WhenExportPatientDetails_thenReturnUnauthorizedStatus() throws Exception {
    Integer dummyId = 30;

    MvcResult mvcResult = mockMvc.perform(get(ENDPOINT_PATH, dummyId)
            .header("Staff-UUID", "085b3b37-c46b-4a31-84e8-0b6a3e45ba87"))
        .andReturn();

    assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
    Assertions.assertEquals("Patient with id=30 not found!",
        mvcResult.getResponse().getContentAsString());
  }
}
