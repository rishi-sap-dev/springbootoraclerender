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

import com.codingnomad.simplebootrest.dao.BookService;
import com.codingnomad.simplebootrest.models.Book;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping(value = "/books")
	public ResponseEntity<List<Book>> getBooks() {
		try {
			List<Book> books = this.bookService.getBooks();
			return ResponseEntity.of(Optional.of(books));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/book/{bookid}")
	public ResponseEntity<Book> getSingeBook(@PathVariable("bookid") int bId) {

		try {
			Book book = this.bookService.getSingleBook(bId);
			if (book != null) {
				return ResponseEntity.of(Optional.of(book));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping("/books")
	public ResponseEntity<Book> addSingleBook(@RequestBody Book book) {
		Book createdBook = null;
		try {
			createdBook = this.bookService.addBook(book);
			return ResponseEntity.of(Optional.of(createdBook));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@DeleteMapping("/book/{bookid}")
	public ResponseEntity<Void> deleteSingleBook(@PathVariable("bookid") int bId) {
		try {
		    boolean deleted = this.bookService.deleteBook(bId);
		    System.out.println(deleted);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}

	@PutMapping("/book/{bookid}")
	public ResponseEntity<Book>  updSingleBook(@PathVariable("bookid") int bId, @RequestBody Book book) {
		try {
			this.bookService.updBook(bId, book);
			return ResponseEntity.ok().body(book);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
