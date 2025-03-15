package org.javaguru.travel.insurance.core.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "agreements_xml_export")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgreementXmlExportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "agreement_uuid")
    private String agreementUuid;

    @Column(name = "already_exported")
    private Boolean alreadyExported;

}
