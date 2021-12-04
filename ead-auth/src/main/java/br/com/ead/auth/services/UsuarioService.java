package br.com.ead.auth.services;

import br.com.ead.auth.models.UsuarioModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioModel> findAll();

    Page<UsuarioModel> findAll(Pageable pageable);

    Page<UsuarioModel> findAll(Specification<UsuarioModel> spec, Pageable pageable);

    Optional<UsuarioModel> findById(Long idUsuario);

    boolean existsByUsuario(String usuario);

    boolean existsByEmail(String email);

    void delete(UsuarioModel usuarioModel);

    void deleteUsuarioAndPublishRabbitMQ(UsuarioModel usuarioModel);

    UsuarioModel save(UsuarioModel usuarioModel);

    UsuarioModel saveUsuarioAndPublishRabbitMQ(UsuarioModel usuarioModel);

    UsuarioModel updateUsuarioAndPublishRabbitMQ(UsuarioModel usuarioModel);

}
