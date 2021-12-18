package br.com.ead.auth.services;

import br.com.ead.auth.enums.TipoRole;
import br.com.ead.auth.models.RoleModel;

import java.util.Optional;

public interface RoleService {

    Optional<RoleModel> findByRoleNome(TipoRole nome);
}
