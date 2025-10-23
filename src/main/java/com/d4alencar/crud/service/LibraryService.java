package com.d4alencar.crud.service;

import com.d4alencar.crud.dao.BookDAO;
import com.d4alencar.crud.model.Book;

import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;


public class LibraryService {
  private final BookDAO bookDAO = new BookDAO();

  public DefaultListModel<Book> model = new DefaultListModel<>();

  public LibraryService () {
    getAllBooks();
  }

  public boolean addBook(Book book) {
    try {
      int idB = bookDAO.addBook(book);
      Book b = new Book(idB, book.getTitle(), book.getYear(), book.getAuthor());
      model.addElement(b);
      System.out.println("book added successfully!");
    } catch (SQLException e) {
      System.out.println("Error adding book: " + e.getMessage());
      return false;
    }
    return true;
  }

  /*public void listBooks() {
    try {
      List<Book> books = bookDAO.getAllBooks();
      for (Book b : books) {
        System.out.println(b.getId() + ": " + b.getTitle() + " (" + b.getYear() + ") by " + b.getAuthor());
      }
    } catch (SQLException e) {
      System.out.println("Error retrieving books: " + e.getMessage());
    }
  }*/

  public void getAllBooks() {
    try {
      List<Book> booksBuffer = bookDAO.getAllBooks();
      for (Book b : booksBuffer) {
        model.addElement(b);
      }
    } catch (SQLException e) {
      System.out.println("Error retrieving books: " + e.getMessage());
    }
  }

  public boolean deleteBook(int id) {
    try {
      Book selectedBook = model.get(id);
      bookDAO.deleteBook(selectedBook.getId());
      model.remove(id);
      System.out.println("Book deleted!");
    } catch (SQLException e) {
      System.out.println("Error deleting book: " + e.getMessage());
      return false;
    }
    return true;
  }

  public boolean editBook (int id, Book book) {
    try {
      bookDAO.editBook(book);
      model.set(id, book);
    } catch (SQLException e) {
      System.out.println("Error editing book: " + e.getMessage());
      return false;
    }
    return true;
  }
}
