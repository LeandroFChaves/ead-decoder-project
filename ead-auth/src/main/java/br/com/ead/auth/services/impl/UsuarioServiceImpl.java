package br.com.ead.auth.services.impl;

import br.com.ead.auth.enums.TipoOperacao;
import br.com.ead.auth.models.UsuarioModel;
import br.com.ead.auth.publishers.UsuarioEventPublisher;
import br.com.ead.auth.repositories.UsuarioRepository;
import br.com.ead.auth.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private UsuarioEventPublisher usuarioEventPublisher;

    @Override
    public List<UsuarioModel> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Page<UsuarioModel> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Override
    public Page<UsuarioModel> findAll(Specification<UsuarioModel> spec, Pageable pageable) {
        return this.userRepository.findAll(spec, pageable);
    }

    @Override
    public Optional<UsuarioModel> findById(Long idUsuario) {
        return this.userRepository.findById(idUsuario);
    }

    @Override
    public boolean existsByUsuario(String usuario) {
        return this.userRepository.existsByUsuario(usuario);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public void delete(UsuarioModel usuarioModel) {
        this.userRepository.delete(usuarioModel);
    }

    @Override
    @Transactional
    public void deleteUsuarioAndPublishRabbitMQ(UsuarioModel usuarioModel) {
        delete(usuarioModel);
        usuarioEventPublisher.publishUsuarioEvent(usuarioModel.convertToUsuarioEventDTO(), TipoOperacao.DELETE);
    }

    @Override
    public UsuarioModel save(UsuarioModel usuarioModel) {
        return this.userRepository.save(usuarioModel);
    }

    @Override
    @Transactional
    public UsuarioModel saveUsuarioAndPublishRabbitMQ(UsuarioModel usuarioModel) {
        usuarioModel = save(usuarioModel);
        usuarioEventPublisher.publishUsuarioEvent(usuarioModel.convertToUsuarioEventDTO(), TipoOperacao.CREATE);

        return usuarioModel;
    }

    @Override
    @Transactional
    public UsuarioModel updateUsuarioAndPublishRabbitMQ(UsuarioModel usuarioModel) {
        usuarioModel = save(usuarioModel);
        usuarioEventPublisher.publishUsuarioEvent(usuarioModel.convertToUsuarioEventDTO(), TipoOperacao.UPDATE);

        return usuarioModel;
    }

}
