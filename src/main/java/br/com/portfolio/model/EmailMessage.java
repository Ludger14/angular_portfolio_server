package br.com.portfolio.model;

public class EmailMessage {

	public static String createTitle() {
		return "Nova mensagem do seu portfolio!";
	}
	
	public static String messageToNewUser(PortfolioModel pessoa) {
		return "Olá Ludger, \n"
		+ "Tem uma nova mensagem do seu portfolio. \n"
		+ "Aqui são os dados do usuário. \n\n"
		+ "========================================== \n"
		+ "Nome: " + pessoa.getNome() + "\n"
		+ "E-mail: " + pessoa.getEmail() + "\n"
		+ "Assunto: " + pessoa.getAssunto() + "\n"
		+ "Mensagem: " + pessoa.getMensagem() + "\n"
		+ "========================================== \n";
	}

	public static String messageToUser(String idioma) {
		return "Olá Ludger, \n"
		+ "Alguém baixou o seu currículo em " + idioma + " pelo seu site. \n";
	}
	
	public static String changeEmail(PortfolioModel pessoa) {
		return pessoa.getNome() + " seu email foi alterado!";
	}
}
