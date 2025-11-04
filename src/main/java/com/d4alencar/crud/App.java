package com.d4alencar.crud;

import com.d4alencar.crud.model.Book;
import com.d4alencar.crud.service.LibraryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends JFrame{

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
    String[] options = {"title", "author", "year"};

    JButton addBookButton = new JButton("Add");
    JButton editBookButton = new JButton("Edit");
    JButton deleteBookButton = new JButton("Delete");
    JTextField searchField = new JTextField();
    JComboBox<String> searchOptions = new JComboBox<>(options);
    JButton searchButton = new JButton("Search");

    menuBarPanel.add(addBookButton);
    menuBarPanel.add(editBookButton);
    menuBarPanel.add(deleteBookButton);
    menuBarPanel.add(searchField);
    menuBarPanel.add(searchOptions);
    menuBarPanel.add(searchButton);

    searchField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == '\n') {
          searchButton.doClick();
        }
      }
    });

    //LIST PANEL
    listBookPanel.add(new JScrollPane(list), BorderLayout.CENTER);
    listBookPanel.add(menuBarPanel, BorderLayout.NORTH);

    mainPanel.add(listBookPanel, "list");

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

        if(selectedIndex != -1) {
          System.out.println("test: "+selectedIndex);
          String[] optionsTitle = {"Yes.", "No."};
          int responseIndex = JOptionPane.showOptionDialog(frame, "Do you really want to delete this book?", "Deleting book", 2, 3, null, optionsTitle, 0);
          System.out.println(responseIndex);
          
          if(responseIndex == 0) {
            if(libraryService.deleteBook(selectedIndex)){
              JOptionPane.showMessageDialog(frame, "book deleted");
            } else {
              JOptionPane.showMessageDialog(frame, "error deleting book");
            }
          }
        } else {
          JOptionPane.showMessageDialog(frame, "Error deleting book: no index selected");
        }
      }

    });

    searchButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        String option = searchOptions.getSelectedItem().toString();
        String input = searchField.getText();
        if(option.equals("year")) {
          try {
            if(!input.isEmpty()) {
              Integer.parseInt(input);
              libraryService.searchBook(input, option);
            } else {
              libraryService.searchBook(input, option);
            }
          } catch (NumberFormatException a) {
            JOptionPane.showMessageDialog(frame, "input invalid for year search");
          }
        } else {
          libraryService.searchBook(input, option);
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
        
        String titleBuff = newBookTitle.getText();
        String authorBuff = newBookAuthor.getText();
        String yearBuff = newBookPublishYear.getText();
        if(!titleBuff.isEmpty() && !authorBuff.isEmpty() && !yearBuff.isEmpty()){
          try {
            Book newBook = new Book(0, titleBuff, Integer.parseInt(yearBuff), authorBuff);
            if(libraryService.addBook(newBook)) {
              JOptionPane.showMessageDialog(frame, "book added");
              dialog.dispose();
            } else {
              JOptionPane.showMessageDialog(frame, "error adding book");
            }
          } catch (NumberFormatException eN) {
            JOptionPane.showMessageDialog(frame, "invalid caracter in year field");
          }
        } else {
          JOptionPane.showMessageDialog(frame, "All fields must be completed.");
        }
      }
    });

    JRootPane rootPane = dialog.getRootPane();
    KeyStroke escapeKey = KeyStroke.getKeyStroke("ESCAPE");

    rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKey, "closeDialog");
    rootPane.getActionMap().put("closeDialog", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dialog.dispose();
      }
    });

    rootPane.setDefaultButton(addNewBookButton);
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

          String titleBuff = titleField.getText();
          String yearBuff = publishYearField.getText();
          String authorBuff = authorField.getText();

          if(!titleBuff.isEmpty() && !yearBuff.isEmpty() && !authorBuff.isEmpty()) {
            
            try {
              Book updatedBook = new Book(selectedBook.getId(), titleBuff, Integer.parseInt(yearBuff), authorBuff);

              if(libraryService.editBook(selectedIndex, updatedBook)) {
                JOptionPane.showMessageDialog(frame, "book edited");
                dialog.dispose();
              } else {
                JOptionPane.showMessageDialog(frame, "Equals");
              }
            } catch (NumberFormatException nf) {
              JOptionPane.showMessageDialog(frame, "Invalid caracter in year field");
            }
          } else {
            JOptionPane.showMessageDialog(frame, "All fields must be filled.");
          }
        }
      });

      JRootPane rootPane = dialog.getRootPane();
      KeyStroke escapeKey = KeyStroke.getKeyStroke("ESCAPE");

      rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKey, "closeDialog");
      rootPane.getActionMap().put("closeDialog", new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
          dialog.dispose();
        }
      });

      rootPane.setDefaultButton(saveModButton);

      dialog.add(panel);
      dialog.setVisible(true);
  }
}
