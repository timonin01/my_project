package org.javaguru.travel.insurance.dto;

import lombok.*;

@Value
public class ValidationError {

    private String errorCode;
    private String description;

}
