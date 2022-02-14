package core.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import core.services.PersonService;

@RestController
@RequestMapping(path="/")
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@GetMapping
	public List<String> getSuggestions(@RequestParam String prefix){
		return personService.getSuggestions(prefix);
	}
}
