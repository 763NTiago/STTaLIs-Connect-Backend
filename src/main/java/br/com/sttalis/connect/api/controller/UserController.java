package br.com.sttalis.connect.api.controller;

import br.com.sttalis.connect.api.dto.UserRequestDTO;
import br.com.sttalis.connect.api.dto.UserResponseDTO;
import br.com.sttalis.connect.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("" +
        "" +
        "" +
        "" +
        "" +
        "")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    /**
     * PostMapping mapeia requisicoes POST para rota /api/users
     * @Valid exige que o Spring execute as anotacoes @NotBlank do DTO
     * Se os dados forem invalido lanca um erro 400Bad Request
     */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //Boas praticas: Retorna 201 e nao 200 ao criar

    public UserResponseDTO createUser(@RequestBody @Valid UserRequestDTO request){
        return userService.registerUser(request);
    }
}