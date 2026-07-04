package br.com.sttalis.connect.domain.repository;

import br.com.sttalis.connect.domain.model.Service;
import br.com.sttalis.connect.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;



public interface ServiceRepository extends JpaRepository<Service, UUID> {



}
