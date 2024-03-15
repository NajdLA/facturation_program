package fr.ekwateur.facturation.dto;

import fr.ekwateur.facturation.shared.enums.CivilityType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public final class ParticularClientDto extends BaseClient{

    @NotBlank
    private CivilityType civilityType;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;
}
