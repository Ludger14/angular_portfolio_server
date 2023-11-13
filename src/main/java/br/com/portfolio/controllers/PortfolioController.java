package br.com.portfolio.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ClassPathResource;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.io.FileOutputStream;

import br.com.portfolio.model.PortfolioModel;
import br.com.portfolio.services.PortfolioService;
import br.com.portfolio.view.PortfolioDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/portfolio")
//@CrossOrigin(origins = { "http://localhost:4200", "http://localhost" }, maxAge = 3600)
@CrossOrigin(origins = {"https://ludger-portfolio.netlify.app/"}, maxAge = 3600)
public class PortfolioController {

	@Autowired
	private PortfolioService portfolioService;
	
	@PostMapping("/saveMessage")
	@Transactional(rollbackFor = Exception.class)
	public PortfolioDto saveMessage(@RequestBody PortfolioModel portfolioModel, HttpServletRequest request) throws ParseException{
		PortfolioDto task = portfolioService.saveMessage(portfolioModel);
		return task;
	}
	
	@GetMapping("/downloadCurriculo/{idioma}")
	public ResponseEntity<ByteArrayResource> downloadCurriculo(
	    @PathVariable("idioma") String idioma,
	    HttpServletResponse response
	) throws IOException {
	    String nomeArquivo = obterNomeArquivo(idioma);
	    String caminhoCurriculo = "curriculo/" + idioma + "/" + nomeArquivo;

	    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(caminhoCurriculo)) {
	        if (inputStream == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }

	        byte[] arquivoBytes = IOUtils.toByteArray(inputStream);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF);
	        headers.setContentDispositionFormData("attachment", nomeArquivo);

	        // Configurando Cache-Control para forçar a revalidação
	        headers.setCacheControl("no-cache, no-store, must-revalidate");
	        headers.setPragma("no-cache");
	        headers.setExpires(0);

	        PortfolioDto downloadCurriculo = portfolioService.baixarCurriculo();

	        return ResponseEntity.ok().headers(headers).body(new ByteArrayResource(arquivoBytes));
	    } catch (IOException e) {
	        // Log de erro ou tratamento adequado
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}



	private String obterNomeArquivo(String idioma) {
	    switch (idioma) {
	        case "pt-br":
	            return "ludger_portuguese.pdf";
	        case "en":
	            return "ludger_english.pdf";
	        default:
	            return "ludger_english.pdf";
	    }
	}

}
