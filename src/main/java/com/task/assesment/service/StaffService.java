package com.task.assesment.service;

import com.task.assesment.dto.StaffDto;
import com.task.assesment.dto.StaffDtoMapper;
import com.task.assesment.exceptions.MissingMandatoryFieldsException;
import com.task.assesment.exceptions.NotFoundException;
import com.task.assesment.model.Staff;
import com.task.assesment.repository.StaffRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StaffService {

  private final StaffRepository staffRepository;

  public StaffDto addStaffMember(StaffDto requestBody) {
    Staff entity = new Staff();
    entity.setName(requestBody.getName());
    entity.setUuid(UUID.randomUUID());
    entity.setRegistrationDate(requestBody.getRegistrationDate());

    return StaffDtoMapper.fromObject(staffRepository.save(entity));
  }

  public StaffDto updateStaffMember(Integer id, StaffDto requestBody) {
    if(requestBody.getName() == null || requestBody.getRegistrationDate() == null) {
      throw new MissingMandatoryFieldsException("All staff fields are required!");
    }
    Optional<Staff> optionalStaff = staffRepository.findById(id);
    if(optionalStaff.isPresent()){
      Staff staff = optionalStaff.get();
      staff.setName(requestBody.getName());
      staff.setRegistrationDate(requestBody.getRegistrationDate());

      return StaffDtoMapper.fromObject(staffRepository.save(staff));
    } else {
      throw new NotFoundException(String.format("No Staff with id=%d was found", requestBody.getId()));
    }
  }
}
