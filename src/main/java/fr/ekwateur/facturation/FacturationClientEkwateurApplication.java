package fr.ekwateur.facturation;

import fr.ekwateur.facturation.dto.EnergyConsumption;
import fr.ekwateur.facturation.dto.ParticularClientDto;
import fr.ekwateur.facturation.dto.ProfessionalClientDto;
import fr.ekwateur.facturation.service.FacturationClientService;
import fr.ekwateur.facturation.shared.enums.CivilityType;
import fr.ekwateur.facturation.shared.enums.EnergyType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.SequencedCollection;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
@ConfigurationPropertiesScan("fr.ekwateur.facturation")
public class FacturationClientEkwateurApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacturationClientEkwateurApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(FacturationClientService facturationClientService) {
        return (String[] args) -> {
            System.out.println("------------------ Facturation pour le client pro------------------------");
            ProfessionalClientDto proClient = buildProfessionalClient();
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<ProfessionalClientDto>> violations = validator.validate(proClient);
            if (!violations.isEmpty()) {
                for (ConstraintViolation<ProfessionalClientDto> violation : violations) {
                    System.out.println("Erreur de validation du dto :" + violation.getMessage());
                }
            } else {
                System.out.println(facturationClientService.calculeFacturationByClient(proClient));
                System.out.println("------------------ Facturation pour le client particulier------------------------");
                System.out.println(facturationClientService.calculeFacturationByClient(buildParticularClient()));
            }

        };
    }

    private static ProfessionalClientDto buildProfessionalClient() {
        return ProfessionalClientDto.builder()
                .idClient(UUID.randomUUID())
                .referenceClient("EKW123456789")
                .energyConsumptions(buildEnergyConsumption(300L, 250L))
                .numeroSiret(12345L)
                .raisonSociale("startup dans le domaine de RH")
                .chiffreAffaire(1000001)
                .build();
    }

    private static ParticularClientDto buildParticularClient() {
        return ParticularClientDto.builder()
                .idClient(UUID.randomUUID())
                .referenceClient("EKW123456789")
                .energyConsumptions(buildEnergyConsumption(100L, 90L))
                .nom("Labben")
                .prenom("Najd")
                .civilityType(CivilityType.MONSIEUR)
                .build();
    }

    private static SequencedCollection<EnergyConsumption> buildEnergyConsumption(long gazQuantity, long electricityQuantity) {
        EnergyConsumption energyConsumption = new EnergyConsumption(EnergyType.GAZ, gazQuantity);
        EnergyConsumption energyConsumption2 = new EnergyConsumption(EnergyType.ELECTRICITY, electricityQuantity);
        SequencedCollection<EnergyConsumption> energyConsumptions = new ArrayList<>();
        energyConsumptions.add(energyConsumption);
        energyConsumptions.add(energyConsumption2);
        return energyConsumptions;
    }

}
