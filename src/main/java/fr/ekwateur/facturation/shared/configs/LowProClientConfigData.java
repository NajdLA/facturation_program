package fr.ekwateur.facturation.shared.configs;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "low-pro-client")
@Data
@Validated
public class LowProClientConfigData {

    @NotNull
    private final Double gazPrice;

    @NotNull
    private final Double electricityPrice;
}
