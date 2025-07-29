package com.forumhub.dto;

import com.forumhub.model.Status;
import com.forumhub.model.Topico;

import java.time.LocalDateTime;

public record TopicoDTO(Long id, String titulo, String mensagem, LocalDateTime dataCriacao, String nomeAutor, Status status, String curso) {
    public TopicoDTO(Topico topico){
        this.(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getStatus(), topico.getCurso().getNome());
    }
}
