package com.fitness.exception;

public class MembershipNotFoundException extends RuntimeException {

    public MembershipNotFoundException(String message) {
        super(message);
    }
}
