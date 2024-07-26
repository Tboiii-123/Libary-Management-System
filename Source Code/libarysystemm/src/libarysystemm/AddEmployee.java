package libarysystemm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddEmployee {
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
        JLabel titleLabel = new JLabel("Add New Employee");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(250, 10, 300, 30);
        panel.add(titleLabel);

        JLabel labelName = new JLabel("Name:");
        labelName.setBounds(10, 50, 80, 25);
        panel.add(labelName);

        JTextField textName = new JTextField(20);
        textName.setBounds(150, 50, 165, 25);
        panel.add(textName);

        JLabel labelAddress = new JLabel("Address:");
        labelAddress.setBounds(10, 80, 80, 25);
        panel.add(labelAddress);

        JTextField textAddress = new JTextField(20);
        textAddress.setBounds(150, 80, 165, 25);
        panel.add(textAddress);

        JLabel labelPhone = new JLabel("Phone Number:");
        labelPhone.setBounds(10, 110, 100, 25);
        panel.add(labelPhone);

        JTextField textPhone = new JTextField(20);
        textPhone.setBounds(150, 110, 165, 25);
        panel.add(textPhone);

        JLabel labelDepartment = new JLabel("Department:");
        labelDepartment.setBounds(10, 140, 80, 25);
        panel.add(labelDepartment);

        JTextField textDepartment = new JTextField(20);
        textDepartment.setBounds(150, 140, 165, 25);
        panel.add(textDepartment);

        JButton addButton = new JButton("Add Employee");
        addButton.setBounds(10, 170, 150, 25);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textName.getText();
                String address = textAddress.getText();
                String phone = textPhone.getText();
                String department = textDepartment.getText();

                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "yinkus")) {
                    String query = "INSERT INTO employee (Name, Address, Number, Department) VALUES (?, ?, ?, ?)";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1, name);
                    pstmt.setString(2, address);
                    pstmt.setString(3, phone);
                    pstmt.setString(4, department);
                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(panel, "Employee added successfully.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Error adding employee.");
                }
            }
        });

        // Add other components and action listeners for update/delete as needed
    }
}
