package com.bookStore;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bookStore.entity.Book;
import com.bookStore.repository.BookRepository;
import com.bookStore.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookStoreApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private BookService bookService;
	
	
	@MockBean
	private BookRepository bookRepository;
	
	
	@Test
	public void saveTest() {
		Book b1 = new Book(1, "springboot", "sp", "1000");
		 when(bookRepository.save(b1)).thenReturn(b1);
		assertEquals(1,bookService.getAllBook().size());
	}
	
//	@Test
//	public void saveTest() {
//		 Book b  = new Book(1, "springboot", "sp", "1000");
//		when(bookRepository.save(b)).thenReturn(b);
//		assertEquals(b, bookService.save(b));
//	}
	
	
	@Test
	public void getAllBookTest() {
		when(bookRepository.findAll()).thenReturn(Stream
				.of(new Book(1, "springboot", "sp", "1000")).collect(Collectors.toList()));
		assertEquals(1, bookService.getAllBook().size());
	}
	
	
	@Test
	public void deleteByIdTest() {
	    int id = 1;
		bookService.deleteById(id);
		verify(bookRepository, times(1)).deleteById(id);
	}
	
	@Test
	public void getBookByIdTest() {
		int id = 1;
		Optional<Book> optionalBook = Optional.of(new Book(1, "springboot", "sp", "1000"));
		when(bookRepository.findById(id)).thenReturn(optionalBook);
		assertEquals(1,bookService.getBookById(id).getId(),"Matched");
	}

}
