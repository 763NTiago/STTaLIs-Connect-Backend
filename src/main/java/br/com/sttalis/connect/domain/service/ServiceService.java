package br.com.sttalis.connect.domain.service;


import br.com.sttalis.connect.api.dto.ServiceResponseDTO;
import br.com.sttalis.connect.domain.repository.ServiceRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ServiceService {

    private final ServiceRepository repository;

    public ServiceService (ServiceRepository repository) {
        this.repository = repository;
    }

    public List<ServiceResponseDTO> listarTodos() {
        // 1. Busque a lista bruta (Entidades que vêm do banco de dados)
        List<br.com.sttalis.connect.domain.model.Service> listaBruta = repository.findAll();

        // 2. Crie uma lista nova vazia (que vai guardar os DTOs)
        List<ServiceResponseDTO> listaPronta = new ArrayList<>();

        // 3. Faça um for na lista bruta, extraia os dados, instancie um DTO novo e adicione na lista pronta
        for (br.com.sttalis.connect.domain.model.Service entidade : listaBruta) {
            ServiceResponseDTO dto = new ServiceResponseDTO(
                    entidade.getId(),
                    entidade.getTitle(),
                    entidade.getDescription(),
                    entidade.getPrice(),
                    entidade.getCurrency(),
                    entidade.getCategory(),
                    entidade.getProvider().getName()
            );
            listaPronta.add(dto);
        }

        // 4. Retorne a lista pronta preenchida com os DTOs
        return listaPronta;
    }
}
