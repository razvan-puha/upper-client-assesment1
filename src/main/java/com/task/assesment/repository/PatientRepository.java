package com.task.assesment.repository;

import com.task.assesment.model.Patient;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer> {

  List<Patient> findByLastVisitDateIsBetween(LocalDate lastVisitDateStart,
      LocalDate lastVisitDateEnd);

  long deleteByLastVisitDateIsBetween(LocalDate lastVisitDateStart, LocalDate lastVisitDateEnd);

}
