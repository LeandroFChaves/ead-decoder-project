package br.com.ead.auth.services.impl;

import br.com.ead.auth.enums.TipoRole;
import br.com.ead.auth.models.RoleModel;
import br.com.ead.auth.repositories.RoleRepository;
import br.com.ead.auth.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<RoleModel> findByRoleNome(TipoRole nome) {
        return this.roleRepository.findByNome(nome);
    }
}
