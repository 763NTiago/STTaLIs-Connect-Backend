package br.com.sttalis.connect.domain.service;

import br.com.sttalis.connect.api.dto.UserRequestDTO;
import br.com.sttalis.connect.api.dto.UserResponseDTO;
import br.com.sttalis.connect.domain.model.User;
import br.com.sttalis.connect.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
    // Injeção de Dependência pelo Contrutor
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

        String encryptedPassword = passwordEncoder.encode(request.password());

        // 2. Conversao DTO -> Entidade
        User userToSave = new User(
                request.name(),
                request.email(),
                encryptedPassword,
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