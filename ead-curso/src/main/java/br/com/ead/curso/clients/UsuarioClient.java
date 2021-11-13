package br.com.ead.curso.clients;

import br.com.ead.curso.dtos.MatriculaCursoDTO;
import br.com.ead.curso.dtos.ResponsePageDTO;
import br.com.ead.curso.dtos.UsuarioDTO;
import br.com.ead.curso.services.UtilsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioClient {

    @Value("${ead.api.url.usuarios}")
    private String REQUEST_URI_USUARIOS;

    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UtilsService utilsService;

    public Page<UsuarioDTO> getAllUsuariosByCurso(Long idCurso, Pageable pageable) {
        List<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
        String url = this.REQUEST_URI_USUARIOS + utilsService.createUrlGetAllUsuariosByCurso(idCurso, pageable);

        log.debug("Request URL: {} ", url);

        try {
            ParameterizedTypeReference<ResponsePageDTO<UsuarioDTO>> responseType = new ParameterizedTypeReference<ResponsePageDTO<UsuarioDTO>>() {
            };
            ResponseEntity<ResponsePageDTO<UsuarioDTO>> result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
            usuarios = result.getBody().getContent();

            log.debug("Response /usuarios - Número de elementos: {} ", usuarios.size());
        } catch (HttpStatusCodeException e) {
            log.error("Error request /usuarios {} ", e);
        }

        log.debug("Ending request /usuarios idCurso {} ", idCurso);

        return new PageImpl<>(usuarios);
    }

    public ResponseEntity<UsuarioDTO> getOneUsuarioById(Long idUsuario) {
        String url = this.REQUEST_URI_USUARIOS + "/usuarios/" + idUsuario;

        return this.restTemplate.exchange(url, HttpMethod.GET, null, UsuarioDTO.class);
    }

    public void postMatricularUsuarioInCurso(Long idCurso, Long idUsuario) {
        String url = this.REQUEST_URI_USUARIOS + "/usuarios/" + idUsuario + "/cursos/matricula";

        MatriculaCursoDTO matriculaCursoDTO = new MatriculaCursoDTO();
        matriculaCursoDTO.setIdUsuario(idUsuario);
        matriculaCursoDTO.setIdCurso(idCurso);

        this.restTemplate.postForObject(url, matriculaCursoDTO, String.class);
    }
}
