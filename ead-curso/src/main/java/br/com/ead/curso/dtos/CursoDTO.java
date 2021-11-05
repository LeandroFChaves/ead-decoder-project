package br.com.ead.curso.dtos;

import br.com.ead.curso.enums.CursoNivel;
import br.com.ead.curso.enums.CursoSituacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CursoDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    private String imagemUrl;

    @NotNull
    private CursoSituacao situacao;

    @NotNull
    private CursoNivel nivel;

    @NotNull
    private Long usuarioDocente;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public CursoSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(CursoSituacao situacao) {
        this.situacao = situacao;
    }

    public CursoNivel getNivel() {
        return nivel;
    }

    public void setNivel(CursoNivel nivel) {
        this.nivel = nivel;
    }

    public Long getUsuarioDocente() {
        return usuarioDocente;
    }

    public void setUsuarioDocente(Long usuarioDocente) {
        this.usuarioDocente = usuarioDocente;
    }
}
