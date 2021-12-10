package br.com.ead.notificacao.controllers;

import br.com.ead.notificacao.dtos.NotificacaoDTO;
import br.com.ead.notificacao.models.NotificacaoModel;
import br.com.ead.notificacao.services.NotificacaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class NotificacaoController {

    final NotificacaoService notificacaoService;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @GetMapping("/usuarios/{idUsuario}/notificacoes")
    public ResponseEntity<Page<NotificacaoModel>> getAllNotificacoesByUsuario(
            @PathVariable(value = "idUsuario") Long idUsuario,
            @PageableDefault(page = 0, size = 10, sort = "idNotificacao", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.notificacaoService.findAllNotificacoesByUsuario(idUsuario, pageable));
    }

    @PutMapping("/usuarios/{idUsuario}/notificacoes/{idNotificacao}")
    public ResponseEntity<Object> updateNotificacao(@PathVariable(value = "idUsuario") Long idUsuario,
                                                     @PathVariable(value = "idNotificacao") Long idNotificacao,
                                                     @RequestBody @Valid NotificacaoDTO notificacaoDTO) {
        Optional<NotificacaoModel> notificacaoModelOptional =
                this.notificacaoService.findByIdNotificacaoAndIdUsuario(idNotificacao, idUsuario);

        if (notificacaoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notificação não encontrada!");
        }

        notificacaoModelOptional.get().setNotificacaoSituacao(notificacaoDTO.getNotificacaoSituacao());
        this.notificacaoService.salvarNotificacao(notificacaoModelOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body(notificacaoModelOptional.get());
    }
}
