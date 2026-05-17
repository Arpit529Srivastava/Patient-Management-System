package com.fitness.service;

import com.fitness.entity.Membership;
import com.fitness.exception.MembershipNotFoundException;
import com.fitness.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    public Membership addMembership(Membership membership) {
        return membershipRepository.save(membership);
    }

    public Membership getMembershipById(Long membershipId) {
        return membershipRepository.findById(membershipId)
                .orElseThrow(() -> new MembershipNotFoundException("Membership not found with id: " + membershipId));
    }

    public List<Membership> getMembershipsByBenefits(String benefits) {
        return membershipRepository.findByBenefitsContainingIgnoreCase(benefits);
    }

    public List<Membership> getMembershipsByTypeAndMinHours(String membershipType, int monthlyAccessHours) {
        return membershipRepository.findByMembershipTypeAndMonthlyAccessHoursGreaterThan(membershipType, monthlyAccessHours);
    }

    public Map<String, Long> getCountByMembershipType() {
        List<Object[]> results = membershipRepository.countByMembershipType();
        Map<String, Long> countMap = new HashMap<>();
        for (Object[] row : results) {
            countMap.put((String) row[0], (Long) row[1]);
        }
        return countMap;
    }
}
