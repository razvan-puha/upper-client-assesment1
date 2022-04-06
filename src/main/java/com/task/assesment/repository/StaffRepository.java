package com.task.assesment.repository;

import com.task.assesment.model.Staff;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Integer> {

  boolean existsByUuidEquals(UUID uuid);

}
