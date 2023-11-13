package br.com.portfolio.services;

import java.util.Objects;
import java.util.Optional;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.portfolio.view.PortfolioDto;
import br.com.portfolio.model.EmailMessage;
import br.com.portfolio.model.PortfolioModel;
import br.com.portfolio.repository.PortfolioRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

@Service
public class PortfolioService {

	@Autowired
	private PortfolioRepository portfolioRepository;
	
	@Autowired
	private EnviarEmailService enviarEmailService;
	
	public PortfolioDto saveMessage(PortfolioModel portfolioModel) throws ParseException {
		
		String meuEmail = "ludgerjeanlouis@gmail.com";
		
		PortfolioDto portfolio = new PortfolioDto();
		if (portfolioModel.getNome() == null || portfolioModel.getEmail() == null || portfolioModel.getAssunto() == null || portfolioModel.getMensagem() == null) {
			portfolio.setSuccess(Boolean.FALSE);
			portfolio.setMessage("Os campos Nome, E-mail, Assunto e Mensagem são obrigatórios.");
            return portfolio;
        }
		
		portfolioModel.setDtLimite(LocalDate.now());
		
		portfolioRepository.save(portfolioModel);
		
		this.enviarEmailService.enviar(meuEmail, EmailMessage.createTitle(), EmailMessage.messageToNewUser(portfolioModel));
		
		portfolio.setId(portfolioModel.getId());
		portfolio.setNome(portfolioModel.getNome());
		
		portfolio.setSuccess(Boolean.TRUE);
		
		return portfolio;
	}
	
	public PortfolioDto baixarCurriculo() throws IOException {
		
		String meuEmail = "ludgerjeanlouis@gmail.com";
		
		PortfolioDto portfolio = new PortfolioDto();
		
		this.enviarEmailService.enviar(meuEmail, EmailMessage.createTitle(), EmailMessage.messageToUser());
		
		portfolio.setSuccess(Boolean.TRUE);
		
		return portfolio;
	}
}
