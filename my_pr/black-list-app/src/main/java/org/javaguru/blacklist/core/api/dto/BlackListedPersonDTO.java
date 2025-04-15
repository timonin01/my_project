package org.javaguru.blacklist.core.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlackListedPersonDTO {

    private String firstName;

    private String lastName;

    private String personCode;

    private Boolean blackListed;

}
