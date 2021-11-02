package br.com.ead.auth.services.impl;

import br.com.ead.auth.models.UserModel;
import br.com.ead.auth.repositories.UserRepository;
import br.com.ead.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserModel> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<UserModel> findById(Long idUsuario) {
        return this.userRepository.findById(idUsuario);
    }

    @Override
    public void delete(UserModel userModel) {
        this.userRepository.delete(userModel);
    }

    @Override
    public void save(UserModel userModel) {
        this.userRepository.save(userModel);
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
