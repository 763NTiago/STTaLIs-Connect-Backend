package br.com.sttalis.connect.api.controller;


import br.com.sttalis.connect.domain.service.ServiceService;
import br.com.sttalis.connect.api.dto.ServiceResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }


    @GetMapping
    public List<ServiceResponseDTO> listarTodos(){
        return serviceService.listarTodos();
    }

}
