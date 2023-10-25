package br.com.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.portfolio.model.PortfolioModel;

public interface PortfolioRepository extends JpaRepository<PortfolioModel, Long>{

	@Query(nativeQuery = true, value = "SELECT * FROM portfolio as por \n" +
            " where por.nome = :nome ")
	PortfolioModel checkNameTask(@Param("nome") String nome);
}
