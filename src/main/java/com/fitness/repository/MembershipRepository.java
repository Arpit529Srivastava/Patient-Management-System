package com.fitness.repository;

import com.fitness.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {

    List<Membership> findByBenefitsContainingIgnoreCase(String benefits);

    List<Membership> findByMembershipTypeAndMonthlyAccessHoursGreaterThan(String membershipType, int monthlyAccessHours);

    @Query("SELECT m.membershipType, COUNT(m) FROM Membership m GROUP BY m.membershipType")
    List<Object[]> countByMembershipType();
}
