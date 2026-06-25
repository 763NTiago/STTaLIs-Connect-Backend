package br.com.sttalis.connect.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Entity sinaliza ao JPA que essa class é uma tabelas do banco de dados.
 * @Table renomeia a tabela
 * user e uma clase reservada no PostgreSQL por isso usamos "tb_users"
 */

@Entity
@Table(name = "tb_users")

public class User {
    /**
     * @Id define a chave primaria
     * @GeneratedValue define como ela sera gerado
     * Por que UUID e nao BIGINT auto-incremental?
     * UUDI sao mais seguros para APIs. Se usar 1, 2, 3... um atancante pode tentar
     * raspar os dados (Scraping) facilmente mudadno o numero da url. com UUID
     * (ex: 550e8400-e29b-41d4-a716-446655440000), o ID é imprevisivel.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * @Column mapea as regras das colunas do banco.
     * nullable = false grante que o banco rejeite insercoes sem nome (Integridade)
     */

    @Column(nullable = false, length = 150)
    private String name;

    /**
     * unique = true cria restricoes evitando dublicacoes, (dois usuarios com o mesmo email)
     */

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    /**
     * @Enumerated(EnumType.STRING) e critico
     * por padrao o java salva o Enum como numero.
     * Usando STRING ele passa a salvar textualmente.
     */

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    /**
     * Para suportar o contexto binacional
     */

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Country country;

    /**
     * Auditoria basica: saber quando a conta foi criada,
     * updatable = false garante que essa data nuca seja alterada depois do primeiro INSERT.
     */

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Contrutor vazio e obrigatorio pela especificacao do JPA.
     * o HIbernate usa reflection para instanciar a classe ao buscar dados.
     */
    protected User(){}

    /**
     * Construtor pratico para criar instancias no codigo.
     * o id e o createdAt, o sistema gerenciara.
     */

    public User(String name, String email, String password, UserRole role, Country country){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.country = country;
    }

    /**
     * @PrePersist e um gatilho (trigger) do JPA
     * Antes de rodar o comando INSERT no banco, ele executa o este metodo.
     */

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    /**
     * GETTERS (para acessar os dados de fora da classe)
     */

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
