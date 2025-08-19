package br.com.forumhub.forumhub.repository;

import br.com.forumhub.forumhub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}

