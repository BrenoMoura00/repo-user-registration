package com.example.userregistrationservice.model;

import jakarta.persistence.*;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String logradouro;
    private String localidade;
    private String uf;
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id",name = "user_id")
    private Usuario usuario;

    public Endereco() {}

    public static EnderecoBuilder builder() {
        return new EnderecoBuilder();
    }

    private Endereco(EnderecoBuilder enderecoBuilder) {
        this.id = enderecoBuilder.id;
        this.cep = enderecoBuilder.cep;
        this.logradouro = enderecoBuilder.logradouro;
        this.localidade = enderecoBuilder.localidade;
        this.uf = enderecoBuilder.uf;
        this.estado = enderecoBuilder.estado;
        this.usuario = enderecoBuilder.usuario;
    }

    public static class EnderecoBuilder{
        private Long id;
        private String cep;
        private String logradouro;
        private String localidade;
        private String uf;
        private String estado;
        private Usuario usuario;

        public EnderecoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public EnderecoBuilder cep(String cep) {
            this.cep = cep;
            return this;
        }

        public EnderecoBuilder logradouro(String logradouro) {
            this.logradouro = logradouro;
            return this;
        }

        public EnderecoBuilder localidade(String localidade) {
            this.localidade = localidade;
            return this;
        }

        public EnderecoBuilder uf(String uf) {
            this.uf = uf;
            return this;
        }

        public EnderecoBuilder estado(String estado) {
            this.estado = estado;
            return this;
        }

        public EnderecoBuilder usuario(Usuario usuario) {
            this.usuario = usuario;
            return this;
        }

        public Endereco build() {
            return new Endereco(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
