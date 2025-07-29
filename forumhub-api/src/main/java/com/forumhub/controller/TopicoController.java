package com.forumhub.controller;

import com.forumhub.dto.TopicoDTO;
import com.forumhub.dto.TopicoForm;
import com.forumhub.model.Topico;
import com.forumhub.repository.TopicoRepository;
import com.forumhub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid Topico topico) {
        if (repository.existsByTituloAndMensagem(topico.getTitulo(), topico.getMensagem())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tópico duplicado!");
        }
        Topico salvo = repository.save(topico);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public List<Topico> listar(){
        return  repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> detalhar(@PathVariable Long id){
        return repository.findAll(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

@PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid Topico novosDados){
        return repository.findById(id).map(topico -> {
            topico.setTitulo(novosDados.getTitulo());
            topico.setMensagem(novosDados.getMensagem());
            topico.setStatus(novosDados.getStatus());
            repository.save(topico);
            return ResponseEntity.ok(topico);
        }).orElse(ResponseEntity.notFound().build());
}

@DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id){
        if (!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
}

@PostMapping
    public ResponseEntity<TopicoDTO> criar(@RequestBody @Valid TopicoForm form){
        TopicoDTO dto = topicoService.criarTopico(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
}

@GetMapping
    public List<TopicoDTO> lista(){
        return topicoService.listar();
}

@PostMapping
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form){
        return topicoService.cadastrar(form);
}
}
