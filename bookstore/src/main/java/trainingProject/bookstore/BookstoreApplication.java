package trainingProject.bookstore;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import trainingProject.bookstore.domain.Book;
import trainingProject.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);  // uusi loggeriattribuutti

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository) { 
		return (args) -> {
			log.info("save a couple of books");
			bookRepository.save(new Book("Learning Python", "Lutz, Mark & Asher, David", 1999, "1-56592-464-9", 19.90));
			bookRepository.save(new Book("Oracle - Tietokannan tehokas hallinta", "Hakkarainen, Anssi", 2011, "978-952-220-485", 29.95));	
			
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
