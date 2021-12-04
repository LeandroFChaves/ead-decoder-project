package br.com.ead.auth.services.impl;

import br.com.ead.auth.enums.TipoOperacao;
import br.com.ead.auth.models.UserModel;
import br.com.ead.auth.publishers.UsuarioEventPublisher;
import br.com.ead.auth.repositories.UserRepository;
import br.com.ead.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsuarioEventPublisher usuarioEventPublisher;

    @Override
    public List<UserModel> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Page<UserModel> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Override
    public Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable) {
        return this.userRepository.findAll(spec, pageable);
    }

    @Override
    public Optional<UserModel> findById(Long idUsuario) {
        return this.userRepository.findById(idUsuario);
    }

    @Override
    @Transactional
    public void delete(UserModel userModel) {
        this.userRepository.delete(userModel);
    }

    @Override
    public UserModel save(UserModel userModel) {
        return this.userRepository.save(userModel);
    }

    @Override
    @Transactional
    public UserModel saveUsuarioAndPublishRabbitMQ(UserModel usuarioModel) {
        usuarioModel = save(usuarioModel);
        usuarioEventPublisher.publishUsuarioEvent(usuarioModel.convertToUsuarioEventDTO(), TipoOperacao.CREATE);

        return usuarioModel;
    }

    @Override
    public boolean existsByUsuario(String usuario) {
        return this.userRepository.existsByUsuario(usuario);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }
}
