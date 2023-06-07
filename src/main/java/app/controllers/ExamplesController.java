package app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.entities.User;

@RestController
@RequestMapping("/examples")
public class ExamplesController {

	@GetMapping("/index")
	public String index() {
		return "Ciao";
	}

	@GetMapping("/index2")
	public int index2() {
		return 7777;
	}

	@GetMapping("/about")
	public String about() {
		return "<h1>pagina about</h1>";
	}

	@GetMapping("/queryParamsExample1")
	public String queryParams1(@RequestParam("name") String name) {
		return "";
	}

	@GetMapping("/queryParamsExample2")
	public String queryParams2(@RequestParam(value = "name", required = false) String name) {
		return "";
	}

	@GetMapping("/queryParamsExample3")
	public String queryParams3(@RequestParam(required = false) String name,
			@RequestParam(required = false) String surname) {
		return "Hello " + name + " " + surname;
	}

	@GetMapping("/pathParamsExample")
	public String noPathParams() {
		return "Ciao sei sull'endpoint /pathParamsExample/";
	}

	@GetMapping("/pathParamsExample/{parameter}")
	public String pathParams(@PathVariable String parameter) {
		return "Ciao sei sull'endpoint /pathParamsExample/" + parameter;
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		users.add(new User(1, "Aldo", "Baglio"));
		users.add(new User(2, "Giovanni", "Storti"));
		users.add(new User(3, "Giacomo", "Poretti"));
		return users;
	}

	@GetMapping("/users/{id}")
	public Optional<User> getSingleUser(@PathVariable String id) {
		List<User> users = new ArrayList<>();
		users.add(new User(1, "Aldo", "Baglio"));
		users.add(new User(2, "Giovanni", "Storti"));
		users.add(new User(3, "Giacomo", "Poretti"));

		User u = null;
		for (User user : users) {
			if (Integer.toString(user.getId()).equals(id)) {
				u = user;
			}
		}
		return Optional.ofNullable(u);
	}

	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {
		user.setId(4);
		System.out.println("User: " + user + " è stato aggiunto al DB");
		return user;
	}

	// RESPONSE ENTITY
	@GetMapping("/additionalHeadersExample")
	public ResponseEntity<User> additionalHeadersExample() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("customHeader", "customvalue");

		User body = new User(5, "Claudio", "Bisio");
		System.out.println("User: " + body + " è stato aggiunto al DB");

		return new ResponseEntity<User>(body, headers, HttpStatus.OK);
	}
}
