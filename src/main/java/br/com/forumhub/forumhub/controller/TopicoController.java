package br.com.forumhub.forumhub.controller;


import br.com.forumhub.forumhub.dto.TopicoRequest;
import br.com.forumhub.forumhub.dto.TopicoResponse;
import br.com.forumhub.forumhub.model.Curso;
import br.com.forumhub.forumhub.model.Topico;
import br.com.forumhub.forumhub.model.Usuario;
import br.com.forumhub.forumhub.repository.CursoRepository;
import br.com.forumhub.forumhub.repository.TopicoRepository;
import br.com.forumhub.forumhub.repository.UsuarioRepository;
import br.com.forumhub.forumhub.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;
    private final AuthService authService;

    public TopicoController(TopicoRepository topicoRepository,
                            CursoRepository cursoRepository,
                            UsuarioRepository usuarioRepository,
                            AuthService authService) {
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
        this.authService = authService;
    }


    // Listar todos os tópicos
    @GetMapping
    public List<TopicoResponse> listar() {
        return topicoRepository.findAll()
                .stream()
                .map(TopicoResponse::new)
                .collect(Collectors.toList());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponse> buscarPorId(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .map(topico -> ResponseEntity.ok(new TopicoResponse(topico)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TopicoResponse> criar(@RequestBody @Valid TopicoRequest request) {
        Curso curso = cursoRepository.findByNome(request.getCurso());
        Usuario usuario = authService.getUsuarioLogado();

        Topico topico = new Topico();
        topico.setTitulo(request.getTitulo());
        topico.setMensagem(request.getMensagem());
        topico.setCurso(curso);
        topico.setAutor(usuario);

        topicoRepository.save(topico);

        return ResponseEntity.ok(new TopicoResponse(topico));
    }



    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponse> atualizar(@PathVariable Long id,
                                                    @RequestBody @Valid TopicoRequest request) {
        return topicoRepository.findById(id)
                .map(topico -> {
                    topico.setTitulo(request.getTitulo());
                    topico.setMensagem(request.getMensagem());
                    topico.setCurso(cursoRepository.findByNome(request.getCurso()));
                    topicoRepository.save(topico);
                    return ResponseEntity.ok(new TopicoResponse(topico));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar tópico
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .map(topico -> {
                    topicoRepository.delete(topico);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

