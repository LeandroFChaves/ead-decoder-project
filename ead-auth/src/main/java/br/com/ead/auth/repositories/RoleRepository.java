package br.com.ead.auth.repositories;

import br.com.ead.auth.enums.TipoRole;
import br.com.ead.auth.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByNome(TipoRole nome);
}
