package com.task.assesment.controller;

import com.task.assesment.dto.PatientResponseDto;
import com.task.assesment.service.PatientService;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patients")
@AllArgsConstructor
public class PatientController {

  private final PatientService patientService;

  @GetMapping
  public ResponseEntity<PatientResponseDto> getPatients() {
    return ResponseEntity.ok(patientService.getPatients());
  }

  @GetMapping(value = "/{id}/export", produces = "text/csv")
  public ResponseEntity<?> exportPatientDetails(@PathVariable Integer id, HttpServletResponse servletResponse)
      throws IOException {
    patientService.exportPatientDetails(id, servletResponse.getWriter());
    servletResponse.setContentType("text/csv");
    return ResponseEntity.status(HttpStatus.OK)
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"patientDetails.csv\"")
        .build();
  }

  @DeleteMapping
  public ResponseEntity<?> deletePatientProfiles(
      @RequestParam("start") @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
      @RequestParam("end") @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {
    patientService.deletePatientProfiles(startDate, endDate);
    return ResponseEntity.ok().build();
  }
}
