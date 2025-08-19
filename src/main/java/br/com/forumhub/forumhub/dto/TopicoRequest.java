package br.com.forumhub.forumhub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicoRequest {
    @NotBlank
    private String titulo;

    @NotBlank
    private String mensagem;

    @NotBlank
    private String curso;
}
