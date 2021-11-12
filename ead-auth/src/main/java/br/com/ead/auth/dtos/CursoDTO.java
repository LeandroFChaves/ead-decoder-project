package br.com.ead.auth.dtos;

import br.com.ead.auth.enums.CursoNivel;
import br.com.ead.auth.enums.CursoSituacao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CursoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String imagemUrl;
    private Long usuarioDocente;
    private CursoSituacao situacao;
    private CursoNivel nivel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getUsuarioDocente() {
        return usuarioDocente;
    }

    public void setUsuarioDocente(Long usuarioDocente) {
        this.usuarioDocente = usuarioDocente;
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
}
