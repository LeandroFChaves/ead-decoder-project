package br.com.ead.curso.specifications;

import br.com.ead.curso.models.AulaModel;
import br.com.ead.curso.models.CursoModel;
import br.com.ead.curso.models.ModuloModel;
import br.com.ead.curso.models.UsuarioModel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
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

    @And({
            @Spec(path = "nomeCompleto", spec = Like.class),
            @Spec(path = "email", spec = Like.class),
            @Spec(path = "tipo", spec = Equal.class),
            @Spec(path = "situacao", spec = Equal.class)})
    public interface UsuarioSpec extends Specification<UsuarioModel> {
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

            return cb.and(cb.equal(curso.get("idCurso"), idCurso), cb.isMember(modulo, cursosModulos));
        };
    }

    public static Specification<AulaModel> aulaModuloId(final Long idModulo) {
        return (root, query, cb) -> {
            query.distinct(true);
            Root<AulaModel> aula = root;
            Root<ModuloModel> module = query.from(ModuloModel.class);
            Expression<Collection<AulaModel>> moduloAulas = module.get("aulas");

            return cb.and(cb.equal(module.get("idModulo"), idModulo), cb.isMember(aula, moduloAulas));
        };
    }

    public static Specification<UsuarioModel> usuarioCursoId(final Long idCurso) {
        return (root, query, cb) -> {
            query.distinct(true);
            Root<UsuarioModel> usuario = root;
            Root<CursoModel> curso = query.from(CursoModel.class);
            Expression<Collection<UsuarioModel>> cursosUsuarios = curso.get("usuarios");

            return cb.and(cb.equal(curso.get("idCurso"), idCurso), cb.isMember(usuario, cursosUsuarios));
        };
    }

    public static Specification<CursoModel> cursoUsuarioId(final Long idUsuario) {
        return (root, query, cb) -> {
            query.distinct(true);
            Root<CursoModel> curso = root;
            Root<UsuarioModel> usuario = query.from(UsuarioModel.class);
            Expression<Collection<CursoModel>> usuariosCursos = usuario.get("cursos");

            return cb.and(cb.equal(usuario.get("idUsuario"), idUsuario), cb.isMember(curso, usuariosCursos));
        };
    }

}
