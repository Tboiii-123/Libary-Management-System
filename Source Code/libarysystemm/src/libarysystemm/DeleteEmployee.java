package libarysystemm;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteEmployee {

    public static void show() {
        JFrame frame = new JFrame("Delete Employee");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Title label
        JLabel titleLabel = new JLabel("Delete Employee");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(100, 10, 200, 25);
        panel.add(titleLabel);

        JLabel labelId = new JLabel("Employee ID:");
        labelId.setBounds(10, 50, 100, 25);
        panel.add(labelId);

        JTextField textId = new JTextField(20);
        textId.setBounds(150, 50, 165, 25);
        panel.add(textId);

        JButton deleteButton = new JButton("Delete Employee");
        deleteButton.setBounds(10, 90, 150, 25);
        panel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = textId.getText();

                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "yinkus")) {
                    String query = "DELETE FROM employee WHERE idemployee = ?";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1, id);
                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(panel, "Employee deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(panel, "No employee found with the given ID.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Error deleting employee.");
                }
            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(170, 90, 150, 25);
        panel.add(clearButton);

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textId.setText("");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                show();
            }
        });
    }
}
