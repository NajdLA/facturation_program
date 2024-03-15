package fr.ekwateur.facturation.dto;

import fr.ekwateur.facturation.shared.enums.EnergyType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnergyConsumption {

    @NotNull
    private EnergyType energyType;

    @NotNull
    private Long quantityKwh;
}
