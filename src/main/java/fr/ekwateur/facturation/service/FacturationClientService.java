package fr.ekwateur.facturation.service;

import fr.ekwateur.facturation.dto.BaseClient;

public interface FacturationClientService {
    double calculeFacturationByClient(BaseClient client);
}
