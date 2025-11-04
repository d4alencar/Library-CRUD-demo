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
      if(!book.toString().equals(model.getElementAt(id).toString())) {
        bookDAO.editBook(book);
        model.set(id, book);
      } else {
        return false;
      }
    } catch (SQLException e) {
      System.out.println("Error editing book: " + e.getMessage());
      return false;
    }
    return true;
  }

  public void searchBook (String key, String option) {
    try {
      if(!key.isEmpty()) {
        model.clear();
        List<Book> booksBuffer = bookDAO.searchBook(key, option);
        for (Book b : booksBuffer) {
          model.addElement(b);
        }
      } else {
        model.clear();
        getAllBooks();
      }
    } catch (SQLException e) {
      System.out.println("Error searching book: " + e.getMessage());
    }
  }
}
