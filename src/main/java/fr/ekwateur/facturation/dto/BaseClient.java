package fr.ekwateur.facturation.dto;

import fr.ekwateur.facturation.shared.validators.ClientRefConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.SequencedCollection;
import java.util.UUID;

@Data
@SuperBuilder
public abstract sealed class BaseClient permits ParticularClientDto, ProfessionalClientDto {

    @NotNull
    protected UUID idClient;

    @ClientRefConstraint
    protected String referenceClient;

    @NotEmpty
    protected SequencedCollection<@Valid EnergyConsumption> energyConsumptions;

}
