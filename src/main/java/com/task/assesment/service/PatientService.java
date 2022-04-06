package com.task.assesment.service;

import com.task.assesment.dto.PatientDtoMapper;
import com.task.assesment.dto.PatientResponseDto;
import com.task.assesment.exceptions.NotFoundException;
import com.task.assesment.model.Patient;
import com.task.assesment.repository.PatientRepository;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService {

  private final PatientRepository patientRepository;

  public PatientService(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }

  public PatientResponseDto getPatients() {
    return PatientDtoMapper.toResponseDto(
        patientRepository.findByLastVisitDateIsBetween(LocalDate.now().minus(2, ChronoUnit.YEARS),
            LocalDate.now()));
  }

  public void exportPatientDetails(Integer id, Writer writer) throws IOException {
    Optional<Patient> optionalPatient = patientRepository.findById(id);
    if(optionalPatient.isEmpty()){
      throw new NotFoundException(String.format("Patient with id=%d not found!", id));
    }

    try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
      csvPrinter.printRecord(optionalPatient.get().getId(), optionalPatient.get().getName(),
          optionalPatient.get().getAge(), optionalPatient.get().getLastVisitDate());
    }
  }

  @Transactional
  public void deletePatientProfiles(LocalDate startDate, LocalDate endDate) {
    patientRepository.deleteByLastVisitDateIsBetween(startDate, endDate);
  }
}
