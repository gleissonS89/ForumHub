package com.forumhub.service;

import com.forumhub.dto.TopicoDTO;
import com.forumhub.dto.TopicoForm;
import com.forumhub.model.Curso;
import com.forumhub.model.Topico;
import com.forumhub.model.Usuario;
import com.forumhub.repository.CursoRepository;
import com.forumhub.repository.TopicoRepository;
import com.forumhub.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicorepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public TopicoDTO criarTopico(TopicoForm dados){
        Curso curso = cursoRepository.findById(dados.cursoId()).orElseThrow();
        Usuario usuario = usuarioRepository.findById(dados.usuarioId()).orElseThrow();

        Topico topico = new Topico();
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setCurso(curso);
        topico.setAutor(autor);

        topicorepository.save(topico);
        return new TopicoDTO(topico);
    }

    public List<TopicoDTO> listar() {
        return null;
    }

    public ResponseEntity<TopicoDTO> cadastrar(@Valid TopicoForm form) {
    }
}
