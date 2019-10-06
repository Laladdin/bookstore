package trainingProject.bookstore;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import trainingProject.bookstore.domain.Book;
import trainingProject.bookstore.domain.BookRepository;
import trainingProject.bookstore.domain.Category;
import trainingProject.bookstore.domain.CategoryRepository;
import trainingProject.bookstore.domain.User;
import trainingProject.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);  // uusi loggeriattribuutti

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) { 
		return (args) -> {
			log.info("save a couple of books");
			Category categoryHistory = new Category("history");
			categoryRepository.save(categoryHistory);
			
			//Category categoryIT = new Category("IT");
			//categoryRepository.save(categoryIT);
			
			//Category categoryFinnishLaw = new Category("finnishLaw");
			//categoryRepository.save(categoryFinnishLaw);
			
			
			 categoryRepository.save(new Category("IT"));
			 categoryRepository.save(new Category("finnishLaw"));
			
			bookRepository.save(new Book("Learning Python", "Lutz, Mark & Asher, David", 1999, "1-56592-464-9", 19.90, categoryRepository.findByName("IT").get(0)));
			bookRepository.save(new Book("Oracle - Tietokannan tehokas hallinta", "Hakkarainen, Anssi", 2011, "978-952-220-485", 29.95, categoryRepository.findByName("IT").get(0)));	
			
			// Create users: user/helpposalasana admin/hankalasalasana 
			User user1 = new User("user", "$2a$10$jdQvkXo532GgZ/QvZO/n7egR.IxXnPS/1g7UNTbfhHDJ4azhb/jOG", "juusojuuseri@gmail.com", "USER");
			User user2 = new User("admin", "$2a$10$2Tg2vSL1fw57bPAou53aD./YKvZXHzx87QJT2.6Eg66C3FcD8b6oy", "adamadmini@gmail.com", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}

