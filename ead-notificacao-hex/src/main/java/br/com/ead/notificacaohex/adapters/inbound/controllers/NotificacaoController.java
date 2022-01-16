package br.com.ead.notificacaohex.adapters.inbound.controllers;

import br.com.ead.notificacaohex.adapters.dtos.NotificacaoDTO;
import br.com.ead.notificacaohex.core.domain.NotificacaoDomain;
import br.com.ead.notificacaohex.core.domain.PageInfo;
import br.com.ead.notificacaohex.core.ports.NotificacaoServicePort;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class NotificacaoController {

    final NotificacaoServicePort notificacaoServicePort;

    public NotificacaoController(NotificacaoServicePort notificacaoServicePort) {
        this.notificacaoServicePort = notificacaoServicePort;
    }

    @GetMapping("/usuarios/{idUsuario}/notificacoes")
    @PreAuthorize("hasAnyRole('ESTUDANTE')")
    public ResponseEntity<Page<NotificacaoDomain>> getAllNotificacoesByUsuario(
            @PathVariable(value = "idUsuario") Long idUsuario,
            @PageableDefault(page = 0, size = 10, sort = "idNotificacao", direction = Sort.Direction.ASC) Pageable pageable) {
        PageInfo pageInfo = new PageInfo();
        BeanUtils.copyProperties(pageable, pageInfo);
        List<NotificacaoDomain> notificacaoDomainList = this.notificacaoServicePort.findAllNotificacoesByUsuario(idUsuario, pageInfo);

        return ResponseEntity.status(HttpStatus.OK).body(new PageImpl<NotificacaoDomain>(notificacaoDomainList, pageable, notificacaoDomainList.size()));
    }

    @PutMapping("/usuarios/{idUsuario}/notificacoes/{idNotificacao}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> updateNotificacao(@PathVariable(value = "idUsuario") Long idUsuario,
                                                    @PathVariable(value = "idNotificacao") Long idNotificacao,
                                                    @RequestBody @Valid NotificacaoDTO notificacaoDTO) {
        Optional<NotificacaoDomain> notificacaoModelOptional =
                this.notificacaoServicePort.findByIdNotificacaoAndIdUsuario(idNotificacao, idUsuario);

        if (notificacaoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notificação não encontrada!");
        }

        notificacaoModelOptional.get().setNotificacaoSituacao(notificacaoDTO.getNotificacaoSituacao());
        this.notificacaoServicePort.salvarNotificacao(notificacaoModelOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body(notificacaoModelOptional.get());
    }
}
