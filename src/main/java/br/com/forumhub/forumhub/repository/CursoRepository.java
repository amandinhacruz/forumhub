package br.com.forumhub.forumhub.repository;


import br.com.forumhub.forumhub.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByNome(String nome);
}

