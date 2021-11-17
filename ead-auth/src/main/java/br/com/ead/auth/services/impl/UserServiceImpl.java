package br.com.ead.auth.services.impl;

import br.com.ead.auth.clients.CursoClient;
import br.com.ead.auth.models.UserModel;
import br.com.ead.auth.models.UsuarioCursoModel;
import br.com.ead.auth.repositories.UserRepository;
import br.com.ead.auth.repositories.UsuarioCursoRepository;
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
    private UsuarioCursoRepository usuarioCursoRepository;

    @Autowired
    private CursoClient cursoClient;

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
        boolean deleteUsuarioCursoInCurso = false;

        List<UsuarioCursoModel> userCourseModelList = this.usuarioCursoRepository.findAllUserCourseIntoUser(userModel.getId());
        if (!userCourseModelList.isEmpty()) {
            this.usuarioCursoRepository.deleteAll(userCourseModelList);
            deleteUsuarioCursoInCurso = true;
        }

        this.userRepository.delete(userModel);

        if (deleteUsuarioCursoInCurso) {
            this.cursoClient.deleteUsuarioInCurso(userModel.getId());
        }
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
