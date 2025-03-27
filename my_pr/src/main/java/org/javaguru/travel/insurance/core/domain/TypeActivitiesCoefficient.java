package org.javaguru.travel.insurance.core.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "type_sport_activities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeActivitiesCoefficient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Size(max = 200)
    @Column(name = "sport_activity",nullable = false)
    private String sportActivity;

    @Column(name = "coefficient", precision = 10, scale = 2, nullable = false)
    private BigDecimal coefficient;

}
