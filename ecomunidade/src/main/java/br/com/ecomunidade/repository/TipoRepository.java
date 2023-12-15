package br.com.ecomunidade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.ecomunidade.model.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, Long> {
	
	List<Tipo> findAllByNomeIgnoreCase(@Param("nome") String nome);

}
