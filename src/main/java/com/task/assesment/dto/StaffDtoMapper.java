package com.task.assesment.dto;

import com.task.assesment.model.Staff;

public final class StaffDtoMapper {

  private StaffDtoMapper() {}

  public static StaffDto fromObject(Staff staff) {
    return StaffDto.builder()
        .id(staff.getId())
        .name(staff.getName())
        .uuid(staff.getUuid())
        .registrationDate(staff.getRegistrationDate())
        .build();
  }
}
