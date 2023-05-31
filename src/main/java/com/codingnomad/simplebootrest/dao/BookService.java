package com.codingnomad.simplebootrest.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.codingnomad.simplebootrest.models.Book;

@Service
public class BookService {

	private static List<Book> books = new ArrayList<Book>();
	static {
	    books.add(new Book(100, "Learn Java", "Rishabh"));
		books.add(new Book(200, "Learn Javascript", "Rishi"));
		books.add(new Book(300, "Learn Docker", "Sunny"));
	    books.add(new Book(400, "Learn React", "Rish"));
	}

	public List<Book> getBooks() {
		return books;
	}

	public Book getSingleBook(int bookId) {
		return books.stream().filter(p -> p.getId() == bookId).findFirst().get();
	}

	public Book addBook(Book book) {
		books.add(book);
		return book;
	}

	public boolean deleteBook(int bookId) {
		Book removeBook = books.stream().filter(p -> p.getId() == bookId).findFirst().get();
		return books.remove(removeBook);
//		System.out.println(didRemove);
//		return removeBook;
	}

	public Book updBook(int bookId,Book book) {
		Book updBook = books.stream().filter(p -> p.getId() == bookId).findFirst().get();
		updBook.setTitle(book.getTitle());
		updBook.setAuthor(book.getAuthor());
		return updBook;

	}
}
