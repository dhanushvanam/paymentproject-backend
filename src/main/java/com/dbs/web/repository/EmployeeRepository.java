package com.dbs.web.repository;

import java.util.Optional;
import com.dbs.web.beans.Employee;

public interface EmployeeRepository {

	Optional<Employee> findById(int eid);

}
