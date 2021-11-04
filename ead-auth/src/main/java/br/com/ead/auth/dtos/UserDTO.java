package br.com.ead.auth.dtos;

import br.com.ead.auth.validations.UsuarioConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    public interface UserView {
        interface CadastroPost {}
        interface CadastroPut {}
        interface SenhaPut {}
        interface ImagemPut {}
    }

    private Long id;
    private UUID idExterno;

    @JsonView({UserView.CadastroPost.class, UserView.CadastroPut.class})
    private String nomeCompleto;

    @NotBlank(groups = UserView.CadastroPost.class)
    @Size(min = 4, max = 50, groups = UserView.CadastroPost.class)
    @UsuarioConstraint(groups = UserView.CadastroPost.class)
    @JsonView(UserView.CadastroPost.class)
    private String usuario;

    @NotBlank(groups = {UserView.CadastroPost.class, UserView.SenhaPut.class})
    @Size(min = 6, max = 20, groups = {UserView.CadastroPost.class, UserView.SenhaPut.class})
    @JsonView({UserView.CadastroPost.class, UserView.SenhaPut.class})
    private String senha;

    @NotBlank(groups = UserView.SenhaPut.class)
    @Size(min = 6, max = 20, groups = UserView.SenhaPut.class)
    @JsonView({UserView.SenhaPut.class})
    private String senhaAnterior;

    @NotBlank(groups = UserView.CadastroPost.class)
    @Email(groups = UserView.CadastroPost.class)
    @JsonView(UserView.CadastroPost.class)
    private String email;

    @JsonView({UserView.CadastroPost.class, UserView.CadastroPut.class})
    private String telefone;

    @CPF(groups = {UserView.CadastroPost.class, UserView.SenhaPut.class})
    @JsonView({UserView.CadastroPost.class, UserView.CadastroPut.class})
    private String cpf;

    @NotBlank(groups = UserView.ImagemPut.class)
    @JsonView({UserView.ImagemPut.class})
    private String imagemUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(UUID idExterno) {
        this.idExterno = idExterno;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaAnterior() {
        return senhaAnterior;
    }

    public void setSenhaAnterior(String senhaAnterior) {
        this.senhaAnterior = senhaAnterior;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }
}
