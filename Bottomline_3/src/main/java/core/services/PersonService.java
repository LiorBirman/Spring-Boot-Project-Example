package core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.entities.Person;
import core.repositories.PersonRepository;
import core.utilities.Trie;

@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepository;
	
	public List<String> getSuggestions(String prefix){
		List<Person> people = personRepository.findAll();
		List<String> names = people.stream().map(p -> p.getName()).toList();
		Trie trie = new Trie(names);
		
		return trie.suggest(prefix);
	}
}
