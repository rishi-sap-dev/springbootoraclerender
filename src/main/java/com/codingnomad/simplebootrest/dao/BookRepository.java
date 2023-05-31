package com.codingnomad.simplebootrest.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.codingnomad.simplebootrest.models.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
 public Book findById(int id);
 }
