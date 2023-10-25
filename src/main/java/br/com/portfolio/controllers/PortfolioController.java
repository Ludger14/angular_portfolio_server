package br.com.portfolio.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.portfolio.model.PortfolioModel;
import br.com.portfolio.services.PortfolioService;
import br.com.portfolio.view.PortfolioDto;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/portfolio")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost" }, maxAge = 3600)
//@CrossOrigin(origins = {"https://fattotarefa.netlify.app"}, maxAge = 3600)
public class PortfolioController {

	@Autowired
	private PortfolioService portfolioService;
	
	@PostMapping("/saveMessage")
	@Transactional(rollbackFor = Exception.class)
	public PortfolioDto saveMessage(@RequestBody PortfolioModel portfolioModel, HttpServletRequest request) throws ParseException{
		PortfolioDto task = portfolioService.saveMessage(portfolioModel);
		return task;
	}
}
