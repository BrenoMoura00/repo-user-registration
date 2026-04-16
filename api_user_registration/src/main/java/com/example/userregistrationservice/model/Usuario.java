package com.example.userregistrationservice.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();
    
    public Usuario() {
    }

    private Usuario(UsuarioBuilder usuarioBuilder) {
        this.id = usuarioBuilder.id;
        this.nome = usuarioBuilder.nome;
        this.email = usuarioBuilder.email;
    }

    public static UsuarioBuilder builder() {
        return new UsuarioBuilder();
    }

    public static class UsuarioBuilder {
        private Long id;
        private String nome;
        private String email;

        public UsuarioBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UsuarioBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public UsuarioBuilder email(String email) {
            this.email = email;
            return this;
        }

        public Usuario build() {
            return new Usuario(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
