package br.com.forumhub.forumhub.dto;

import br.com.forumhub.forumhub.model.Topico;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TopicoResponse {
    private Long id;
    private String titulo;
    private String mensagem;
    private String curso;
    private String autor;
    private LocalDateTime dataCriacao;

    public TopicoResponse(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.curso = topico.getCurso().getNome();
        this.autor = topico.getAutor().getNome();
        this.dataCriacao = topico.getDataCriacao();
    }
}

