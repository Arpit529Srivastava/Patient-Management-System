package com.employee.repository;

import com.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Retrieve all employees sorted by empname ascending
    List<Employee> findAllByOrderByEmpnameAsc();

    // Update salary by empid using JPQL
    @Modifying
    @Query("UPDATE Employee e SET e.empsalary = :salary WHERE e.empid = :empid")
    int updateSalaryByEmpid(@Param("empid") int empid, @Param("salary") double salary);

    // Update designation by empid using JPQL (@Query approach)
    @Modifying
    @Query("UPDATE Employee e SET e.empdesignation = :empdesignation WHERE e.empid = :empid")
    int updateDesignationByEmpid(@Param("empid") int empid, @Param("empdesignation") String empdesignation);
}
