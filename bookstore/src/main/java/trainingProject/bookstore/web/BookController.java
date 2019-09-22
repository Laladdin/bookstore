package trainingProject.bookstore.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import trainingProject.bookstore.domain.Book;
import trainingProject.bookstore.domain.BookRepository;
import trainingProject.bookstore.domain.Category;
import trainingProject.bookstore.domain.CategoryRepository;



@Controller
public class BookController {
	// Spring-alusta luo sovelluksen käynnistyessä BookRepository-rajapintaa toteuttavan luokan olion 
	   // ja kytkee olion BookController-luokasta luodun olion attribuuttiolioksi
	@Autowired
	BookRepository bookRepository; 
	
	@Autowired
	CategoryRepository categoryRepository;
	
	
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
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	
	
	// kirjan tietojen muokkaus
	@RequestMapping(value = "/editbook/{id}", method = RequestMethod.GET)
	public String editBookForm(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookRepository.findById(bookId));
		model.addAttribute("categories", categoryRepository.findAll());
		return "editbook";
	}
	
	/* // muokatun kirjan tiedot kirjalistaan
	 @RequestMapping(value="/editbook/{id}",method=RequestMethod.POST)
	 public String saveEditedBook(@PathVariable("id") Long bookId, Model model) {
		 model.addAttribute("book", bookRepository.findById(bookId));
	     return "redirect:../booklist";
	}
	 
	 
	  * @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
			public String editBook(@PathVariable("id") Long bookId, Model model) {
			
				model.addAttribute("book", bookrepository.findById(bookId));
				
				
			return "EditBook";
	  */

	
}
