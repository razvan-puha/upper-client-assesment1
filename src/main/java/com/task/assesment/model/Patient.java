package com.task.assesment.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "patient")
@Getter
@Setter
@NoArgsConstructor
public class Patient {

  @Id
  @GeneratedValue
  private int id;

  private String name;
  private int age;
  private LocalDate lastVisitDate;
}
