package com.d4alencar.crud;

import com.d4alencar.crud.model.Book;
import com.d4alencar.crud.service.LibraryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends JFrame {

  static JFrame frame;
  static String title = "CRUD BY d4alencar";

  public static void main( String[] args ){
    SwingUtilities.invokeLater(App::new);
  }

  public App () {

    LibraryService libraryService = new LibraryService();
    JList<Book> list = new JList<>(libraryService.model);
    list.setSelectedIndex(0);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);
    
    //PANELS
    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel(cardLayout);
    
    JPanel listBookPanel = new JPanel(new BorderLayout());
    listBookPanel.setBackground(Color.WHITE);

    JPanel menuBarPanel = new JPanel();
    menuBarPanel.setLayout(new BoxLayout(menuBarPanel, BoxLayout.X_AXIS));


    //MENU BAR PANEL
    JButton addBookButton = new JButton("Add");
    JButton editBookButton = new JButton("Edit");
    JButton deleteBookButton = new JButton("Delete");

    menuBarPanel.add(addBookButton);
    menuBarPanel.add(editBookButton);
    menuBarPanel.add(deleteBookButton);


    //LIST PANEL
    listBookPanel.add(new JScrollPane(list), BorderLayout.CENTER);
    listBookPanel.add(menuBarPanel, BorderLayout.NORTH);


    mainPanel.add(listBookPanel, "list");
    

    //Buttons Actions
    addBookButton.addActionListener(e -> openAddDialog(frame, libraryService));
    
    editBookButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          int selectedIndex = list.getSelectedIndex();
          openEditDialog(frame, libraryService, list, selectedIndex);      
        } catch (ArrayIndexOutOfBoundsException a) {
          JOptionPane.showMessageDialog(frame, "Error editing book: no index selected");
        }
      }
    });

    deleteBookButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed (ActionEvent e) {
        int selectedIndex = list.getSelectedIndex();
        
        if(libraryService.deleteBook(selectedIndex)){
          JOptionPane.showMessageDialog(frame, "book deleted");
        } else {
          JOptionPane.showMessageDialog(frame, "error deleting book");
        }
      }

    });
    frame.add(mainPanel);
    frame.setVisible(true);
  }

  private void openAddDialog(JFrame parent, LibraryService libraryService) {
 
    JDialog dialog = new JDialog(parent, "Add Book", true);
    dialog.setSize(300, 150);
    dialog.setLocationRelativeTo(parent);

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints cons = new GridBagConstraints();

    cons.insets = new Insets(3, 3, 3, 3);
    
    cons.gridy = 0;
    cons.gridx = 0;
    cons.weightx = 0;
    cons.fill = GridBagConstraints.NONE;
    cons.anchor = GridBagConstraints.LINE_START;
    panel.add(new JLabel("Title"), cons);

    JTextField newBookTitle = new JTextField();
    cons.gridy = 0;
    cons.gridx = 1;
    cons.weightx = 1.0f;
    cons.fill = GridBagConstraints.HORIZONTAL;
    cons.anchor = GridBagConstraints.CENTER;
    panel.add(newBookTitle, cons);

    cons.gridy = 1;
    cons.gridx = 0;
    cons.weightx = 0;
    cons.fill = GridBagConstraints.NONE;
    cons.anchor = GridBagConstraints.LINE_START;
    panel.add(new JLabel("Author"), cons);

    JTextField newBookAuthor = new JTextField();
    cons.gridy = 1;
    cons.gridx = 1;
    cons.weightx = 1.0f;
    cons.fill = GridBagConstraints.HORIZONTAL;
    cons.anchor = GridBagConstraints.CENTER;
    panel.add(newBookAuthor, cons);

    cons.gridy = 2;
    cons.gridx = 0;
    cons.weightx = 0;
    cons.fill = GridBagConstraints.NONE;
    cons.anchor = GridBagConstraints.LINE_START;
    panel.add(new JLabel("Publish year"), cons);

    JTextField newBookPublishYear = new JTextField();
    cons.gridy = 2;
    cons.gridx = 1;
    cons.weightx = 1.0f;
    cons.fill = GridBagConstraints.HORIZONTAL;
    cons.anchor = GridBagConstraints.CENTER;
    panel.add(newBookPublishYear, cons);

    //cons.insets = new Insets (3, 3, 3, 3);
    JButton closeDialogButton = new JButton("Close");
    cons.gridy = 3;
    cons.gridx = 0;
    cons.weightx = 0.5f;
    cons.fill = GridBagConstraints.HORIZONTAL;
    cons.anchor = GridBagConstraints.LINE_END;
    panel.add(closeDialogButton, cons);
    
    JButton addNewBookButton = new JButton("Add");
    cons.gridy = 3;
    cons.gridx = 1;
    cons.weightx = 0.5f;
    cons.fill = GridBagConstraints.HORIZONTAL;
    cons.anchor = GridBagConstraints.LINE_START;
    panel.add(addNewBookButton, cons);

    closeDialogButton.addActionListener(e -> dialog.dispose());
    
    addNewBookButton.addActionListener (new ActionListener(){
      @Override
      public void actionPerformed (ActionEvent e) {
        Book newBook = new Book(0, newBookTitle.getText(), Integer.parseInt(newBookPublishYear.getText()), newBookAuthor.getText());
        if(libraryService.addBook(newBook)) {
          JOptionPane.showMessageDialog(frame, "book added");
        } else {
          JOptionPane.showMessageDialog(frame, "error adding book");
        }
        dialog.dispose();
      }
    });

    dialog.add(panel);
    dialog.setVisible(true);
  }

    private void openEditDialog(JFrame parent, LibraryService libraryService, JList<Book> list, int id) {


      Book selectedBook = libraryService.model.get(id);
      
      JDialog dialog = new JDialog(parent, "Edit book", true);
      dialog.setSize(300, 150);
      dialog.setLocationRelativeTo(frame);

      JPanel panel = new JPanel(new GridBagLayout());
      GridBagConstraints cons = new GridBagConstraints();
 
      cons.insets = new Insets(3, 3, 3, 3);
      
      cons.gridy = 0;
      cons.gridx = 0;
      cons.weightx = 0;
      cons.fill = GridBagConstraints.NONE;
      cons.anchor = GridBagConstraints.LINE_START;
      panel.add(new JLabel("Title"), cons);

      JTextField titleField = new JTextField();
      cons.gridy = 0;
      cons.gridx = 1;
      cons.weightx = 1.0;
      cons.fill = GridBagConstraints.HORIZONTAL;
      cons.anchor = GridBagConstraints.CENTER;
      panel.add(titleField, cons);

      cons.gridy = 1;
      cons.gridx = 0;
      cons.weightx = 0;
      cons.fill = GridBagConstraints.NONE;
      cons.anchor = GridBagConstraints.LINE_START;
      panel.add(new JLabel("Author"), cons);
      
      JTextField authorField = new JTextField();
      cons.gridy = 1;
      cons.gridx = 1;
      cons.weightx = 1.0;
      cons.fill = GridBagConstraints.HORIZONTAL;
      cons.anchor = GridBagConstraints.CENTER;
      panel.add(authorField, cons);

      cons.gridy = 2;
      cons.gridx = 0;
      cons.weightx = 0;
      cons.fill = GridBagConstraints.NONE;
      cons.anchor = GridBagConstraints.LINE_START;
      panel.add(new JLabel("Publish year"), cons);

      JTextField publishYearField = new JTextField();
      cons.gridy = 2;
      cons.gridx = 1;
      cons.weightx = 1.0;
      cons.fill = GridBagConstraints.HORIZONTAL;
      cons.anchor = GridBagConstraints.CENTER;
      panel.add(publishYearField, cons);

      JButton closeDialogButton = new JButton("Close");
      cons.gridy = 3;
      cons.gridx = 0;
      cons.weightx = 0.5;
      cons.fill = GridBagConstraints.HORIZONTAL;
      cons.anchor = GridBagConstraints.LINE_START;
      panel.add(closeDialogButton, cons);

      JButton saveModButton = new JButton("Save");
      cons.gridy = 3;
      cons.gridx = 1;
      cons.weightx = 0.5;
      cons.fill = GridBagConstraints.HORIZONTAL;
      cons.anchor = GridBagConstraints.LINE_END;
      panel.add(saveModButton, cons);

      titleField.setText(selectedBook.getTitle());
      authorField.setText(selectedBook.getAuthor());
      publishYearField.setText(String.valueOf(selectedBook.getYear()));

      closeDialogButton.addActionListener(e -> dialog.dispose());

      saveModButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int selectedIndex = list.getSelectedIndex();
          Book selectedBook = libraryService.model.get(selectedIndex);

          Book updatedBook = new Book(selectedBook.getId(), titleField.getText(), Integer.parseInt(publishYearField.getText()), authorField.getText());

          if(libraryService.editBook(selectedIndex, updatedBook)) {
            JOptionPane.showMessageDialog(frame, "book edited");
          } else {
            JOptionPane.showMessageDialog(frame, "error editing book");
          }
          dialog.dispose();
        }
      });

      dialog.add(panel);
      dialog.setVisible(true);
    }
}
