package fr.ekwateur.facturation.shared.configs;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "particular-client")
@Data
@Validated
public class ParticularClientConfigData {

    @NotNull
    private final Double gazPrice;

    @NotNull
    private final Double electricityPrice;
}
