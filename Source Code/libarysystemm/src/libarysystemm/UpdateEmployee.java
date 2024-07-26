package libarysystemm;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateEmployee {

    public static void show() {
        JFrame frame = new JFrame("Employee Management");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Title label
        JLabel titleLabel = new JLabel("Update  Employee");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(200, 10, 400, 30);
        panel.add(titleLabel);

        JLabel labelId = new JLabel("Employee ID:");
        labelId.setBounds(10, 50, 100, 25);
        panel.add(labelId);

        JTextField textId = new JTextField(20);
        textId.setBounds(150, 50, 165, 25);
        panel.add(textId);

        JLabel labelName = new JLabel("Name:");
        labelName.setBounds(10, 80, 80, 25);
        panel.add(labelName);

        JTextField textName = new JTextField(20);
        textName.setBounds(150, 80, 165, 25);
        panel.add(textName);

        JLabel labelAddress = new JLabel("Address:");
        labelAddress.setBounds(10, 110, 80, 25);
        panel.add(labelAddress);

        JTextField textAddress = new JTextField(20);
        textAddress.setBounds(150, 110, 165, 25);
        panel.add(textAddress);

        JLabel labelPhone = new JLabel("Phone Number:");
        labelPhone.setBounds(10, 140, 100, 25);
        panel.add(labelPhone);

        JTextField textPhone = new JTextField(20);
        textPhone.setBounds(150, 140, 165, 25);
        panel.add(textPhone);

        JLabel labelDepartment = new JLabel("Department:");
        labelDepartment.setBounds(10, 170, 80, 25);
        panel.add(labelDepartment);

        JTextField textDepartment = new JTextField(20);
        textDepartment.setBounds(150, 170, 165, 25);
        panel.add(textDepartment);

        JButton updateButton = new JButton("Update Employee");
        updateButton.setBounds(10, 200, 150, 25);
        panel.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = textId.getText();
                String name = textName.getText();
                String address = textAddress.getText();
                String phone = textPhone.getText();
                String department = textDepartment.getText();

                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "yinkus")) {
                    String query = "UPDATE employee SET Name = ?, Address = ?, Number = ?, Department = ? WHERE idemployee = ?";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1, name);
                    pstmt.setString(2, address);
                    pstmt.setString(3, phone);
                    pstmt.setString(4, department);
                    pstmt.setString(5, id);
                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(panel, "Employee updated successfully.");
                    } else {
                        JOptionPane.showMessageDialog(panel, "No employee found with the given ID.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Error updating employee.");
                }
            }
        });

//        JButton deleteButton = new JButton("Delete Employee");
//        deleteButton.setBounds(170, 200, 150, 25);
//        panel.add(deleteButton);
//
//        deleteButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String id = textId.getText();
//
//                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "yinkus")) {
//                    String query = "DELETE FROM employee WHERE idemployee = ?";
//                    PreparedStatement pstmt = con.prepareStatement(query);
//                    pstmt.setString(1, id);
//                    int rowsAffected = pstmt.executeUpdate();
//
//                    if (rowsAffected > 0) {
//                        JOptionPane.showMessageDialog(panel, "Employee deleted successfully.");
//                    } else {
//                        JOptionPane.showMessageDialog(panel, "No employee found with the given ID.");
//                    }
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(panel, "Error deleting employee.");
//                }
//            }
//        });
//        
    }
}
