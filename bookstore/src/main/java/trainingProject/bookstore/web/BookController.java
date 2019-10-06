package trainingProject.bookstore.web;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import trainingProject.bookstore.domain.Book;
import trainingProject.bookstore.domain.BookRepository;
import trainingProject.bookstore.domain.CategoryRepository;



@Controller
public class BookController {
	// Spring-alusta luo sovelluksen käynnistyessä BookRepository-rajapintaa toteuttavan luokan olion 
	   // ja kytkee olion BookController-luokasta luodun olion attribuuttiolioksi
	@Autowired
	BookRepository bookRepository; 
	
	@Autowired
	CategoryRepository categoryRepository;
	
	
	// Login page
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
	// kirjojen listaus tietokannasta
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String getBooks(Model model) {
		List<Book> books = (List<Book>) bookRepository.findAll();
		// välitetään kirjalista templatelle model-olion avulla
		model.addAttribute("books", books);
		// DispatherServlet saa tämän template-nimen ja kutsuu seuraavaksi booklist.html-templatea,
		// joka prosessoidaan palvelimella
		return "booklist";
	}
	
	// Show all books in Thymeleaf template
	/*@RequestMapping(value="/booklist")
	public String bookList(Model model) {
	model.addAttribute("books", bookRepository.findAll());
	return "booklist";
	}
	*/
	
	// RESTful service to get all books
    @RequestMapping(value="/Apibooks", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) bookRepository.findAll();
    } 
    
 // RESTful service to get book by id
    @RequestMapping(value="/Apibooks/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return bookRepository.findById(bookId);
    }       
    
 // RESTful service to save new book 
    @RequestMapping(value="/books", method = RequestMethod.POST)
    public @ResponseBody Book saveBookRest(@RequestBody Book car) {	
    	return bookRepository.save(car);
    }
	
	// tyhjän kirjalomakkeen muodostaminen
	@RequestMapping(value = "/newbook", method = RequestMethod.GET)
	public String getNewBookForm(Model model) {
		model.addAttribute("book", new Book()); // "tyhjä" kirjaolio
		model.addAttribute("categories", categoryRepository.findAll());
		return "bookform";
	}

	// kirjalomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value = "/newbook", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book) {
		// talletetaan yhden kirjan tiedot tietokantaan
		bookRepository.save(book);
		return "redirect:/booklist";
	}

	// kirjan poisto
	@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	
	// kirjan tietojen muokkaus
	@RequestMapping(value = "/editbook/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editBookForm(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookRepository.findById(bookId));
		model.addAttribute("categories", categoryRepository.findAll());
		return "editbook";
	}

	
}
