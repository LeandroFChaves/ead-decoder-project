package br.com.ead.auth.repositories;

import br.com.ead.auth.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    boolean existsByUsuario(String usuario);

    boolean existsByEmail(String email);
}
