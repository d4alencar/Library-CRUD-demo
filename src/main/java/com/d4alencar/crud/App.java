package com.d4alencar.crud;

import com.d4alencar.crud.model.Book;
import com.d4alencar.crud.service.LibraryService;

public class App {
  public static void main( String[] args ){

    LibraryService libraryService = new LibraryService();
    
    //libraryService.addBook(new Book(0, "Sapiens", 2011, "Yuval Harari"));
    libraryService.editTitle(27, "Sapiensia");
    libraryService.listBooks();

    /*JFrame frame = new JFrame("CRUD by d4alencar");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);

    JPanel panel = new JPanel();
    panel.setBackground(Color.WHITE);
    panel.setLayout(new FlowLayout());

    panel.add(new JButton("Button 1"));
    panel.add(new JButton("Button 2"));

    frame.add(panel);

    /frame.setVisible(true);*/
  }
}
