package com.task.assesment.dto;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class StaffDto {

  private final Integer id;
  private final String name;
  private final UUID uuid;
  private final LocalDate registrationDate;
}
