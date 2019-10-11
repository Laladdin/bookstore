package trainingProject.bookstore;

import static org.assertj.core.api.Assertions.assertThat; 

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import trainingProject.bookstore.domain.Book;
import trainingProject.bookstore.domain.BookRepository;
import trainingProject.bookstore.domain.CategoryRepository;
import trainingProject.bookstore.domain.User;
import trainingProject.bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreRepositoryTest {
	
	 @Autowired
	 private BookRepository bookRepository;
	 
	 @Autowired
	 private CategoryRepository categoryRepository;
	 
	 @Autowired
	 private UserRepository userRepository;
	 
	 @Test
	    public void findByTitleReturnBook() {
	        List<Book> books = bookRepository.findByTitle("Oracle - Tietokannan tehokas hallinta");
	        
	        assertThat(books).hasSize(1);
	        assertThat(books.get(0).getAuthor()).isEqualTo("Hakkarainen, Anssi");
	    }
	 
	 @Test
	    public void createNewBook() {
	    	Book book = new Book("Uusi kirja", "Kirjailija", 1999, "1-5903-555-8", 19.90, categoryRepository.findByName("IT").get(0));
	    	bookRepository.save(book);
	    	assertThat(book.getId()).isNotNull();
	    }
	 
	 @Test
	    public void findByUsernameReturnUser() {
		 // salasana = melkovaikeasalasana
		User user = new User("testaaja", "$2a$10$.mIH0qCToObPIwRvBTebGeodob9W/Hm13k.D.kefYGpK8dfnU1xlm", "terhitestaaja@gmail.com", "USER");
		userRepository.save(user);
		assertThat(user.getId()).isNotNull();
	 }

	 
 }
	 	
	 
	 /*
	  *  // Login page
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
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	
	// kirjan tietojen muokkaus
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editbook/{id}", method = RequestMethod.GET)
	public String editBookForm(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookRepository.findById(bookId));
		model.addAttribute("categories", categoryRepository.findAll());
		return "editbook";
	}
	  */

