package br.com.portfolio.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EnviarEmailService {

	@Autowired
	private final JavaMailSender envioEmailJava;
	
	public EnviarEmailService(final JavaMailSender envioEmailJava) {
		this.envioEmailJava = envioEmailJava;
	}
	
	public void enviar(String para, String titulo, String conteudo) {
		
		var message = new SimpleMailMessage();
		
		message.setTo(para);
		message.setSubject(titulo);
		message.setText(conteudo);
		envioEmailJava.send(message);
		
		System.out.println("E-mail enviado com sucesso!");
	}
}
