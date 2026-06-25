package br.com.sttalis.connect.domain.service;

import br.com.sttalis.fronteirapp.api.dto.UserRequestDTO;
import br.com.sttalis.fronteirapp.api.dto.UserResponseDTO;
import br.com.sttalis.fronteirapp.domain.model.User;
import br.com.sttalis.fronteirapp.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    // Injeção de Dependência pelo Contrutor
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Transactional garate que toda a operacao ocorra na mesma transacao de banco.
     * Se houver falha no meio do processo, ele executa um ROLLBACK automatico.
     */

    @Transactional
    public UserResponseDTO registerUser(UserRequestDTO request){
        //1. Validacao da regra de negocio
        if (userRepository.existsByEmail(request.email())){
            throw new RuntimeException("E-mail já está em uso na plataforma.");
        }

        // 2. Conversao DTO -> Entidade
        User userToSave = new User(
                request.name(),
                request.email(),
                request.password(), //Na Fase de Segurity aplicaremos PasswordEncoder
                request.role(),
                request.country()
        );

        //3. Persistenia via JPA
        User savedUser = userRepository.save(userToSave);

        //4. Conversao de Entidade -> DTO seguro pra retorno
        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.getCountry(),
                savedUser.getCreatedAt()
        );

    }

}