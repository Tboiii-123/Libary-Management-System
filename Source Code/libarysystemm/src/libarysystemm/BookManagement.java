package libarysystemm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BookManagement {
    public static void show() {
        JFrame frame = new JFrame("Book Management");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton addButton = new JButton("Add Book");
        addButton.setBounds(50, 50, 200, 50);
        panel.add(addButton);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook(panel);
            }
        });

        JButton updateButton = new JButton("Update Book");
        updateButton.setBounds(50, 150, 200, 50);
        panel.add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateBook(panel);
            }
        });

        JButton viewButton = new JButton("View Books");
        viewButton.setBounds(50, 250, 200, 50);
        panel.add(viewButton);
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewBooks(panel);
            }
        });

        JButton deleteButton = new JButton("Delete Book");
        deleteButton.setBounds(50, 350, 200, 50);
        panel.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteBook(panel);
            }
        });
    }

    private static void addBook(JPanel panel) {
        JFrame frame = new JFrame("Add Book");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel addPanel = new JPanel();
        frame.add(addPanel);
        addPanel.setLayout(null);

        JLabel labelCallNumber = new JLabel("Call Number:");
        labelCallNumber.setBounds(10, 20, 100, 25);
        addPanel.add(labelCallNumber);

        JTextField textCallNumber = new JTextField(20);
        textCallNumber.setBounds(150, 20, 165, 25);
        addPanel.add(textCallNumber);

        JLabel labelISBN = new JLabel("ISBN:");
        labelISBN.setBounds(10, 50, 100, 25);
        addPanel.add(labelISBN);

        JTextField textISBN = new JTextField(20);
        textISBN.setBounds(150, 50, 165, 25);
        addPanel.add(textISBN);

        JLabel labelTitle = new JLabel("Title:");
        labelTitle.setBounds(10, 80, 100, 25);
        addPanel.add(labelTitle);

        JTextField textTitle = new JTextField(20);
        textTitle.setBounds(150, 80, 165, 25);
        addPanel.add(textTitle);

        JLabel labelAuthor = new JLabel("Author:");
        labelAuthor.setBounds(10, 110, 100, 25);
        addPanel.add(labelAuthor);

        JTextField textAuthor = new JTextField(20);
        textAuthor.setBounds(150, 110, 165, 25);
        addPanel.add(textAuthor);

        JButton saveButton = new JButton("Add Book");
        saveButton.setBounds(10, 140, 150, 25);
        addPanel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String callNumber = textCallNumber.getText();
                String isbn = textISBN.getText();
                String title = textTitle.getText();
                String author = textAuthor.getText();

                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "yinkus")) {
                    String query = "INSERT INTO books (CallNumber, ISBN, Title, Author) VALUES (?, ?, ?, ?)";
                    PreparedStatement pstmt = con.prepareStatement(query);

                    pstmt.setString(1, callNumber);
                    pstmt.setString(2, isbn);
                    pstmt.setString(3, title);
                    pstmt.setString(4, author);
                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(panel, "Book added successfully.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Error adding book.");
                }
            }
        });

        frame.setVisible(true);
    }

    private static void updateBook(JPanel panel) {
        JFrame frame = new JFrame("Update Book");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel updatePanel = new JPanel();
        frame.add(updatePanel);
        updatePanel.setLayout(null);

        JLabel labelCallNumber = new JLabel("Call Number:");
        labelCallNumber.setBounds(10, 20, 100, 25);
        updatePanel.add(labelCallNumber);

        JTextField textCallNumber = new JTextField(20);
        textCallNumber.setBounds(150, 20, 165, 25);
        updatePanel.add(textCallNumber);

        JLabel labelISBN = new JLabel("New ISBN:");
        labelISBN.setBounds(10, 50, 100, 25);
        updatePanel.add(labelISBN);

        JTextField textISBN = new JTextField(20);
        textISBN.setBounds(150, 50, 165, 25);
        updatePanel.add(textISBN);

        JLabel labelTitle = new JLabel("New Title:");
        labelTitle.setBounds(10, 80, 100, 25);
        updatePanel.add(labelTitle);

        JTextField textTitle = new JTextField(20);
        textTitle.setBounds(150, 80, 165, 25);
        updatePanel.add(textTitle);

        JLabel labelAuthor = new JLabel("New Author:");
        labelAuthor.setBounds(10, 110, 100, 25);
        updatePanel.add(labelAuthor);

        JTextField textAuthor = new JTextField(20);
        textAuthor.setBounds(150, 110, 165, 25);
        updatePanel.add(textAuthor);

        JButton saveButton = new JButton("Update Book");
        saveButton.setBounds(10, 140, 150, 25);
        updatePanel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String callNumber = textCallNumber.getText();
                String newISBN = textISBN.getText();
                String newTitle = textTitle.getText();
                String newAuthor = textAuthor.getText();

                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "yinkus")) {
                    String query = "UPDATE books SET ISBN = ?, Title = ?, Author = ? WHERE CallNumber = ?";
                    PreparedStatement pstmt = con.prepareStatement(query);

                    pstmt.setString(1, newISBN);
                    pstmt.setString(2, newTitle);
                    pstmt.setString(3, newAuthor);
                    pstmt.setString(4, callNumber);
                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(panel, "Book updated successfully.");
                    } else {
                        JOptionPane.showMessageDialog(panel, "No book found with the given Call Number.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Error updating book.");
                }
            }
        });

        frame.setVisible(true);
    }

    private static void viewBooks(JPanel panel) {
        JFrame frame = new JFrame("View Books");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400);

        JPanel viewPanel = new JPanel();
        frame.add(viewPanel);
        viewPanel.setLayout(null);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(10, 10, 760, 350);
        viewPanel.add(textArea);

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "yinkus")) {
            String query = "SELECT CallNumber, ISBN, Title, Author FROM books";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String callNumber = rs.getString("CallNumber");
                String isbn = rs.getString("ISBN");
                String title = rs.getString("Title");
                String author = rs.getString("Author");

                textArea.append("Call Number: " + callNumber + ", ISBN: " + isbn + ", Title: " + title + ", Author: " + author + "\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Error viewing books.");
        }

        frame.setVisible(true);
    }

    private static void deleteBook(JPanel panel) {
        JFrame frame = new JFrame("Delete Book");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel deletePanel = new JPanel();
        frame.add(deletePanel);
        deletePanel.setLayout(null);

        JLabel labelCallNumber = new JLabel("Call Number:");
        labelCallNumber.setBounds(10, 20, 100, 25);
        deletePanel.add(labelCallNumber);

        JTextField textCallNumber = new JTextField(20);
        textCallNumber.setBounds(150, 20, 165, 25);
        deletePanel.add(textCallNumber);

        JButton deleteButton = new JButton("Delete Book");
        deleteButton.setBounds(10, 60, 150, 25);
        deletePanel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String callNumber = textCallNumber.getText();


                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "yinkus")) {
                	  String query = "DELETE FROM books WHERE CallNumber = ?";
                       
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1, callNumber);
                    int rowsAffected = pstmt.executeUpdate();


                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(panel, "Book deleted successfully.");
                        } else {
                            JOptionPane.showMessageDialog(panel, "No book found with the given Call Number.");
                        }
                    }
                 catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Error deleting book.");
                }
            
        

            }
        });

        frame.setVisible(true);
    }
}
