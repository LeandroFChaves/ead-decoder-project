package br.com.ead.auth.services;

import br.com.ead.auth.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserModel> findAll();

    Page<UserModel> findAll(Pageable pageable);

    Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable);

    Optional<UserModel> findById(Long idUsuario);

    boolean existsByUsuario(String usuario);

    boolean existsByEmail(String email);

    void delete(UserModel userModel);

    void deleteUsuarioAndPublishRabbitMQ(UserModel usuarioModel);

    UserModel save(UserModel userModel);

    UserModel saveUsuarioAndPublishRabbitMQ(UserModel userModel);

    UserModel updateUsuarioAndPublishRabbitMQ(UserModel usuarioModel);

}
