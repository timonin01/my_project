package org.javaguru.blacklist.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "black_listed_persons")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BlackListedPersonEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "person_first_name", nullable = false)
    private String personFirstName;

    @Column(name = "person_last_name", nullable = false)
    private String personLastName;

    @Column(name = "person_code", nullable = false)
    private String personCode;

}
