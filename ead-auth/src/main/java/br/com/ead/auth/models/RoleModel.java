package br.com.ead.auth.models;

import br.com.ead.auth.enums.TipoRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ROLES", uniqueConstraints = {
        @UniqueConstraint(name = "UK_ROLES_NOME", columnNames = {"nome"})
})
public class RoleModel implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRole;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private TipoRole nome;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return this.nome.toString();
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public TipoRole getNome() {
        return nome;
    }

    public void setNome(TipoRole nome) {
        this.nome = nome;
    }
}
