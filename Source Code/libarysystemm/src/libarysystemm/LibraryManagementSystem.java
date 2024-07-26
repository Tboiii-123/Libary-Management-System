package libarysystemm;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Title label
        JLabel titleLabel = new JLabel("Library Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(200, 10, 400, 30);
        panel.add(titleLabel);

        JButton employeeButton = new JButton("Manage Employees");
        employeeButton.setBounds(50, 150, 200, 50);
        panel.add(employeeButton);
        employeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Employee.show();
            }
        });

        JButton bookButton = new JButton("Manage Books");
        bookButton.setBounds(50, 250, 200, 50);
        panel.add(bookButton);
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BookManagement.show();
            }
        });
    }
}
