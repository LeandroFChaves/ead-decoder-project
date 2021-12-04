package br.com.ead.curso.repositories;

import br.com.ead.curso.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>, JpaSpecificationExecutor<UsuarioModel> {

}
