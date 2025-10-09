package com.d4alencar.crud.service;

import com.d4alencar.crud.dao.BookDAO;
import com.d4alencar.crud.model.Book;

import java.sql.SQLException;
import java.util.List;

public class LibraryService {
  private final BookDAO bookDAO = new BookDAO();

  public void addBook(Book book) {
    try {
      bookDAO.addBook(book);
      System.out.println("book added successfully!");
    } catch (SQLException e) {
      System.out.println("Error adding book: " + e.getMessage());
    }
  }

  public void listBooks() {
    try {
      List<Book> books = bookDAO.getAllBooks();
      for (Book b : books) {
        System.out.println(b.getId() + ": " + b.getTitle() + " (" + b.getYear() + ") by " + b.getAuthor());
      }
    } catch (SQLException e) {
      System.out.println("Error retrieving books: " + e.getMessage());
    }
  }

  public void deleteBook(int id) {
    try {
      bookDAO.deleteBook(id);
      System.out.println("Book deleted!");
    } catch (SQLException e) {
      System.out.println("Error deleting book: " + e.getMessage());
    }
  }

  public void editTitle(int id, String newTitle) {
    try {
      bookDAO.editTitle(id, newTitle);
      System.out.println("Book edited.");
    } catch (SQLException e) {
      System.out.println("Error editing book: " + e.getMessage());
    }
  }
}
