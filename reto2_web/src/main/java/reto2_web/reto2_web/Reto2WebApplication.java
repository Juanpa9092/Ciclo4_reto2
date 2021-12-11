package reto2_web.reto2_web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import reto2_web.reto2_web.interfaces.OrderInterface;
import reto2_web.reto2_web.interfaces.UserInterface;
import reto2_web.reto2_web.interfaces.VegetarianInterface;

@SpringBootApplication
public class Reto2WebApplication implements CommandLineRunner {

	@Autowired
	private VegetarianInterface vegetarianCrudRepository;
	@Autowired
	private UserInterface userCrudRepository;
	@Autowired
	private OrderInterface orderCrudRepository;
	public static void main(String[] args) {
		SpringApplication.run(Reto2WebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userCrudRepository.deleteAll();
		vegetarianCrudRepository.deleteAll();
		orderCrudRepository.deleteAll();
	}

}
