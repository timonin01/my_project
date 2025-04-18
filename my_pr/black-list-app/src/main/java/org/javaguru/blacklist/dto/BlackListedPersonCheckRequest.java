package org.javaguru.blacklist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlackListedPersonCheckRequest {

    private String personFirstName;

    private String personLastName;

    private String personCode;

}
