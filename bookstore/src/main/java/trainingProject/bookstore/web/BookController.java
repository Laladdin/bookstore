package trainingProject.bookstore.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import trainingProject.bookstore.domain.Book;



@Controller

public class BookController {
	
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String bookForm(Model model) {
		List<Book> books = new ArrayList<Book>();
		books.add(new Book("Java", "Silander Simo ym.", 2010, "951-846-237-2", 50));
		model.addAttribute("books", books); 
		return "booklist"; 
	}
	

	//"Java", "Silander Simo ym.", 2010, "951-846-237-2", 50)
}
