package com.fitness.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "memberships")
public class Membership {

    @Id
    private Long membershipId;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String planName;

    @NotNull
    @NotEmpty
    private String membershipType;

    @Min(1)
    private int monthlyAccessHours;

    private LocalDate launchDate;

    private LocalDate expirationDate;

    @Min(0)
    private double monthlyFee;

    @NotNull
    @NotEmpty
    private String benefits;

    private boolean dietPlanOpted;

    public Membership() {
    }

    public Membership(Long membershipId, String planName, String membershipType, int monthlyAccessHours,
                      LocalDate launchDate, LocalDate expirationDate, double monthlyFee, String benefits, boolean dietPlanOpted) {
        this.membershipId = membershipId;
        this.planName = planName;
        this.membershipType = membershipType;
        this.monthlyAccessHours = monthlyAccessHours;
        this.launchDate = launchDate;
        this.expirationDate = expirationDate;
        this.monthlyFee = monthlyFee;
        this.benefits = benefits;
        this.dietPlanOpted = dietPlanOpted;
    }

    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public int getMonthlyAccessHours() {
        return monthlyAccessHours;
    }

    public void setMonthlyAccessHours(int monthlyAccessHours) {
        this.monthlyAccessHours = monthlyAccessHours;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public boolean isDietPlanOpted() {
        return dietPlanOpted;
    }

    public void setDietPlanOpted(boolean dietPlanOpted) {
        this.dietPlanOpted = dietPlanOpted;
    }
}
