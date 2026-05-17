package com.fitness.controller;

import com.fitness.entity.Membership;
import com.fitness.service.MembershipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/memberships")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @PostMapping
    public ResponseEntity<Membership> addMembership(@Valid @RequestBody Membership membership) {
        Membership saved = membershipService.addMembership(membership);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{membershipId}")
    public ResponseEntity<Membership> getMembershipById(@PathVariable Long membershipId) {
        Membership membership = membershipService.getMembershipById(membershipId);
        return new ResponseEntity<>(membership, HttpStatus.OK);
    }

    @GetMapping("/benefits")
    public ResponseEntity<List<Membership>> getMembershipsByBenefits(@RequestParam String benefits) {
        List<Membership> memberships = membershipService.getMembershipsByBenefits(benefits);
        return new ResponseEntity<>(memberships, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Membership>> getMembershipsByTypeAndMinHours(
            @RequestParam String membershipType,
            @RequestParam int monthlyAccessHours) {
        List<Membership> memberships = membershipService.getMembershipsByTypeAndMinHours(membershipType, monthlyAccessHours);
        return new ResponseEntity<>(memberships, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getCountByMembershipType() {
        Map<String, Long> countMap = membershipService.getCountByMembershipType();
        return new ResponseEntity<>(countMap, HttpStatus.OK);
    }
}
