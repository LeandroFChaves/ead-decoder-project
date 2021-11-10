package br.com.ead.curso.dtos;

import javax.validation.constraints.NotBlank;

public class AulaDTO {

    @NotBlank
    private String titulo;

    private String descricao;

    @NotBlank
    private String videoUrl;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "AulaDTO{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }
}
