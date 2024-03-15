package fr.ekwateur.facturation.service.impl;

import fr.ekwateur.facturation.dto.BaseClient;
import fr.ekwateur.facturation.dto.EnergyConsumption;
import fr.ekwateur.facturation.dto.ParticularClientDto;
import fr.ekwateur.facturation.dto.ProfessionalClientDto;
import fr.ekwateur.facturation.service.FacturationClientService;
import fr.ekwateur.facturation.shared.configs.HighProClientConfigData;
import fr.ekwateur.facturation.shared.enums.EnergyType;
import fr.ekwateur.facturation.shared.exceptions.InvalidClientTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.SequencedCollection;

@Service
@Slf4j
public class FacturationClientServiceImpl implements FacturationClientService {

    private final HighProClientConfigData highProClientConfigData;

    public FacturationClientServiceImpl(HighProClientConfigData highProClientConfigData) {
        this.highProClientConfigData = highProClientConfigData;
    }

    @Override
    public double calculeFacturationByClient(BaseClient client) {
        return switch (client) {
            case ProfessionalClientDto highProClient when highProClient.getChiffreAffaire() > 1000000 ->
                    calculateTotalPrice(highProClient.getEnergyConsumptions(), highProClientConfigData.getGazPrice(), highProClientConfigData.getElectricityPrice());
            case ProfessionalClientDto lowProClient when lowProClient.getChiffreAffaire() < 1000000 ->
                    calculateTotalPrice(lowProClient.getEnergyConsumptions(), 0.118, 0.113);
            case ParticularClientDto partClient ->
                    calculateTotalPrice(partClient.getEnergyConsumptions(), 0.121, 0.115);
            default -> {
                log.error("cannot calculate facturation of the client id {}", client.getIdClient());
                throw new InvalidClientTypeException("cannot calculate facturation of the client id {}" + client);
            }

        };
    }

    private double calculateTotalPrice(SequencedCollection<EnergyConsumption> energyConsumptions, double gazPrice, double electricityPrice) {
        return energyConsumptions.stream().mapToDouble(energC -> calculatePriceByEnergyType(energC, gazPrice, electricityPrice)).sum();
    }

    private double calculatePriceByEnergyType(EnergyConsumption energyConsumption, double gazPrice, double electricityPrice) {
        EnergyType energyType = energyConsumption.getEnergyType();
        return switch (energyType) {
            case EnergyType.GAZ -> energyConsumption.getQuantityKwh() * gazPrice;
            case EnergyType.ELECTRICITY -> energyConsumption.getQuantityKwh() * electricityPrice;
        };
    }
}
