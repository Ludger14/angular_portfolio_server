package br.com.portfolio.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;

@Data
@NoArgsConstructor
@Entity
@Table(name = "portfolio")
public class PortfolioModel {

	@JsonIgnore

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
	
	@Column(name = "nome", nullable = false)
    private String nome;
	
	@Column(name = "email", nullable = false)
    private String email;
	
	@Column(name = "assunto", nullable = false)
    private String assunto;
	
	@Column(name = "mensagem", nullable = false)
    private String mensagem;
	
	@Column(name = "dtLimite")
	private LocalDate dtLimite;
}
