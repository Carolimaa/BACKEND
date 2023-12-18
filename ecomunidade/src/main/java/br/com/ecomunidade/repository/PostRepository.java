package br.com.ecomunidade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.ecomunidade.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	
	List<Post> findAllbyConteudoContainingIgnoreCase(@Param("conteudo") String conteudo);

}
