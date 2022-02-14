package core;

import java.io.FileReader;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.opencsv.CSVReader;

import core.entities.Person;
import core.repositories.PersonRepository;

@SpringBootApplication
public class Bottomline3Application {

	public static void main(String[] args) {
		SpringApplication.run(Bottomline3Application.class, args);
	}
	
	@Bean
	CommandLineRunner init(PersonRepository personRepository) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				try (CSVReader reader = new CSVReader(new FileReader("C:\\data\\boyNames.csv"))) {
					  List<Person> people = reader.readAll().stream().map(i->i[0]).map(Person::new).toList();
				      personRepository.saveAll(people);
				  }
			}
		};
	}

}
