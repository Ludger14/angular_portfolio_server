package br.com.portfolio.view;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude
public class PortfolioDto {

	Long id;
	String nome;
	String email;
	String assunto;
	String mensagem;
	
	String message;	
	LocalDate dtLimite;
	Boolean success;
}
