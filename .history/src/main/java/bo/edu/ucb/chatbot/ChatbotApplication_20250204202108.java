package bo.edu.ucb.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class ChatbotApplication {
	public static void main(String[] args) throws  Exception{
		ApplicationContext ctx = SpringApplication.run(ChatbotApplication.class, args);
	}
}
