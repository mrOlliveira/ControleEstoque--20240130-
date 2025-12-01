package com.controleestoque.api_estoque.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Venda> vendas;

    // Construtor vazio (OBRIGATÓRIO)
    public Cliente() {}

    // Construtor com parâmetros
    public Cliente(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // GETTERS E SETTERS (copie EXATAMENTE)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Venda> getVendas() { return vendas; }
    public void setVendas(List<Venda> vendas) { this.vendas = vendas; }
}