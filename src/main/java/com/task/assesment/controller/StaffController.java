package com.task.assesment.controller;

import com.task.assesment.dto.StaffDto;
import com.task.assesment.service.StaffService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/staff")
@AllArgsConstructor
public class StaffController {

  private final StaffService staffService;


  @PostMapping
  public ResponseEntity<StaffDto> addStaffMember(@RequestBody StaffDto requestBody) {
    return ResponseEntity.status(HttpStatus.CREATED).body(staffService.addStaffMember(requestBody));
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<StaffDto> updateStaffMember(@PathVariable Integer id, @RequestBody StaffDto requestBody) {
    return ResponseEntity.ok(staffService.updateStaffMember(id, requestBody));
  }
}
