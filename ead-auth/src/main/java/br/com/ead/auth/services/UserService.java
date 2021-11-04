package br.com.ead.auth.services;

import br.com.ead.auth.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserModel> findAll();

    Page<UserModel> findAll(Pageable pageable);

    Optional<UserModel> findById(Long idUsuario);

    void delete(UserModel userModel);

    void save(UserModel userModel);

    boolean existsByUsuario(String usuario);

    boolean existsByEmail(String email);

}
