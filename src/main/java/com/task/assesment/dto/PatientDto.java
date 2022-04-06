package com.task.assesment.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class PatientDto {

  private final Integer id;
  private final String name;
  private final Integer age;
  private final LocalDate lastVisitDate;
}
