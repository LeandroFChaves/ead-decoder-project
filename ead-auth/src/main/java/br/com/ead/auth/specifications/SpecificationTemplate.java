package br.com.ead.auth.specifications;

import br.com.ead.auth.models.UserModel;
import br.com.ead.auth.models.UsuarioCursoModel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class SpecificationTemplate {

    @And({
            @Spec(path = "nomeCompleto", spec = Like.class),
            @Spec(path = "email", spec = Like.class),
            @Spec(path = "cpf", spec = Equal.class),
            @Spec(path = "tipo", spec = Equal.class),
            @Spec(path = "situacao", spec = Equal.class)
    })
    public interface UserSpec extends Specification<UserModel> {
    }

    public static Specification<UserModel> usuariosByCurso(final Long idCurso) {
        return (root, query, cb) -> {
            query.distinct(true);
            Join<UserModel, UsuarioCursoModel> usuariosCurso = root.join("usuariosCursos");

            return cb.equal(usuariosCurso.get("idCurso"), idCurso);
        };
    }
}
