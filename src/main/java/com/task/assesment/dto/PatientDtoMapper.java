package com.task.assesment.dto;

import com.task.assesment.model.Patient;
import java.util.List;
import java.util.stream.Collectors;

public class PatientDtoMapper {

  private PatientDtoMapper() {
  }

  public static PatientResponseDto toResponseDto(List<Patient> pacients) {
    return PatientResponseDto.builder()
        .response(pacients.stream().map(PatientDtoMapper::toDto).collect(
            Collectors.toList())).build();
  }

  private static PatientDto toDto(Patient it) {
    return PatientDto.builder().id(it.getId()).age(it.getAge())
        .lastVisitDate(it.getLastVisitDate()).build();
  }
}
