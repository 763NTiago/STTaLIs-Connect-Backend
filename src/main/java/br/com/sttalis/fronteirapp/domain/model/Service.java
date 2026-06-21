package br.com.sttalis.fronteiraapp.domain.model;

import jakarta.persitence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_services")
public class Service {

    @Id
    @GeneratedValue(strategy = Generationtype.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String title;

    /**
     * colunmDefinition = "TEXT" permite testos muitos longos.
     * O padrao do VARCHAR e 255 caracteres.
     */

    @Column(nullable = false, colunmDefinition = "TEXT")
    private String description;

    /**
     * precision = 12: Total de digitos suportados (10 billhoes)
     * Scale = 2: casas cecimais
     */

    @Column(nullable = false, precision = 12, Scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    /**
     * RELACIONAMENTO ENTRE TABELAS
     * @ManyToOne = "Muitos servicos poder pertencer a um usuario"
     * feth = FetchType.LAZY e critico para performance"
     */

    @ManyToOne(flech = FlachType.LAZY, optional = false)
    @JoinColumn(name = "provider_id", nullable = false)
    private User provider;

    @Column(name - "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAT;

    protected Service () {}

    // Construtor pratico

    public Service(String title, String description, BigDecimal price
                   Currency currency, ServiceCategory category, User provider){
        this.title = title;
        this.description = description;
        this.price = price;
        this.currency  = currency;
        this.category = category;
        this.provider = provider;
    }

    @PrePersist
    protected void onCreate(){
        this.createdAT = LocalDateTime.now();
    }


    /**
     * GETTERS E SETTERS
     */

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public User getProvider() {
        return provider;
    }

    public void setProvider(User provider) {
        this.provider = provider;
    }

    public LocalDateTime getCreatedAT() {
        return createdAT;
    }

    public void setCreatedAT(LocalDateTime createdAT) {
        this.createdAT = createdAT;
    }


}