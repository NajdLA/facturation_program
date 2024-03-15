package fr.ekwateur.facturation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public final class ProfessionalClientDto extends BaseClient {

    @NotNull(message = "le numéro de siret ne doit pas etre null !")
    private Long numeroSiret;
    @NotBlank
    private String raisonSociale;
    @NotNull
    private double chiffreAffaire;
}
