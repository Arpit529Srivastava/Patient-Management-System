package com.repository;

import com.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAllByOrderByEmpnameAsc();

    @Modifying
    @Query("UPDATE Employee e SET e.empsalary = :salary WHERE e.empid = :empid")
    int updateSalaryByEmpid(@Param("empid") int empid, @Param("salary") double salary);

    @Modifying
    @Query("UPDATE Employee e SET e.empdesignation = :empdesignation WHERE e.empid = :empid")
    int updateDesignationByEmpid(@Param("empid") int empid, @Param("empdesignation") String empdesignation);
}
