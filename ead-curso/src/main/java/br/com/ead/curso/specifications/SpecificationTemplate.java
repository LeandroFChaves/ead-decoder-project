package br.com.ead.curso.specifications;

import br.com.ead.curso.models.AulaModel;
import br.com.ead.curso.models.CursoModel;
import br.com.ead.curso.models.CursoUsuarioModel;
import br.com.ead.curso.models.ModuloModel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class SpecificationTemplate {

    @And({
            @Spec(path = "nivel", spec = Equal.class),
            @Spec(path = "situacao", spec = Equal.class),
            @Spec(path = "nome", spec = LikeIgnoreCase.class)
    })
    public interface CursoSpec extends Specification<CursoModel> {
    }

    @Spec(path = "titulo", spec = LikeIgnoreCase.class)
    public interface ModuloSpec extends Specification<ModuloModel> {
    }

    @Spec(path = "titulo", spec = LikeIgnoreCase.class)
    public interface AulaSpec extends Specification<AulaModel> {
    }

    public static Specification<ModuloModel> moduloCursoId(final Long idCurso) {
        return (root, query, cb) -> {
            query.distinct(true);
            Root<ModuloModel> modulo = root;
            Root<CursoModel> curso = query.from(CursoModel.class);
            Expression<Collection<ModuloModel>> cursosModulos = curso.get("modulos");

            return cb.and(cb.equal(curso.get("id"), idCurso), cb.isMember(modulo, cursosModulos));
        };
    }

    public static Specification<AulaModel> aulaModuloId(final Long idModulo) {
        return (root, query, cb) -> {
            query.distinct(true);
            Root<AulaModel> aula = root;
            Root<ModuloModel> module = query.from(ModuloModel.class);
            Expression<Collection<AulaModel>> moduloAulas = module.get("aulas");

            return cb.and(cb.equal(module.get("id"), idModulo), cb.isMember(aula, moduloAulas));
        };
    }

    public static Specification<CursoModel> cursosByUsuario(final Long idUsuario) {
        return (root, query, cb) -> {
            query.distinct(true);
            Join<CursoModel, CursoUsuarioModel> cursosUsuario = root.join("cursosUsuarios");

            return cb.equal(cursosUsuario.get("idUsuario"), idUsuario);
        };
    }
}
