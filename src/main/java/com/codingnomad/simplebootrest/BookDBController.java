package com.codingnomad.simplebootrest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codingnomad.simplebootrest.dao.BookRepository;
import com.codingnomad.simplebootrest.models.Book;


@RestController
public class BookDBController {
	
	@Autowired
	private BookRepository BookRepository;
	
	@GetMapping(value = "/dbbooks")
	public ResponseEntity<List<Book>> getBooks() {
		System.out.println(BookRepository);
		try {
			List<Book> books = (List<Book>) BookRepository.findAll();
			return ResponseEntity.of(Optional.of(books));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	@GetMapping("/dbbook/{bookid}")
	public ResponseEntity<Book> getSingeBook(@PathVariable("bookid") int bId) {
  System.out.println("db book");
		try {
			//Book book = this.bookService.getSingleBook(bId);
			Book findBook = BookRepository.findById(bId);
			System.out.println(findBook);
			if (findBook != null) {
				return ResponseEntity.ok(findBook);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping("/dbbooks")
	public ResponseEntity<Book> addSingleBook(@RequestBody Book book) {
		Book createdBook = null;
		try {
			createdBook = BookRepository.save(book);
			return ResponseEntity.of(Optional.of(createdBook));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}	
	@DeleteMapping("/dbbook/{bookid}")
	public ResponseEntity<Void> deleteSingleBook(@PathVariable("bookid") int bId) {
		try {
		    BookRepository.deleteById(bId);		 
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@PutMapping("/dbbook/{bookid}")
	public ResponseEntity<Book>  updSingleBook(@PathVariable("bookid") int bId, @RequestBody Book book) {
		try {
			if(bId > 0 ) {
				book.setId(bId);
			}
			Book createdBook = BookRepository.save(book);
			return ResponseEntity.ok().body(createdBook);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}	
}
