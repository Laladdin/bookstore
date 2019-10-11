package trainingProject.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import trainingProject.bookstore.web.BookController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookstoreApplicationTests {
		
		@Autowired
		 private BookController bookController;
		 
		 @Test
		 public void contextLoads() throws Exception {
		 assertThat(bookController).isNotNull();
		 }

}
