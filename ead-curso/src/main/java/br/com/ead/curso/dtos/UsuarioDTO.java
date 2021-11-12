package br.com.ead.curso.dtos;

import br.com.ead.curso.enums.UsuarioSituacao;
import br.com.ead.curso.enums.UsuarioTipo;

public class UsuarioDTO {

    private Long id;
    private String usuario;
    private String email;
    private String nomeCompleto;
    private String cpf;
    private String telefone;
    private String imagemUrl;
    private UsuarioTipo tipo;
    private UsuarioSituacao situacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public UsuarioTipo getTipo() {
        return tipo;
    }

    public void setTipo(UsuarioTipo tipo) {
        this.tipo = tipo;
    }

    public UsuarioSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(UsuarioSituacao situacao) {
        this.situacao = situacao;
    }
}
