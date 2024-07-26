package libarysystemm;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Employee {
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

        // Add Employee button
        JButton addButton = new JButton("Add Employee");
        addButton.setBounds(10, 20, 200, 25);
        panel.add(addButton);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddEmployee.show();
            }
        });

        // Update Employee button
        JButton updateButton = new JButton("Update Employee");
        updateButton.setBounds(10, 60, 200, 25);
        panel.add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UpdateEmployee.show();
            }
        });

        // Delete Employee button
        JButton deleteButton = new JButton("Delete Employee");
        deleteButton.setBounds(10, 100, 200, 25);
        panel.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteEmployee.show();
            }
        });

        // View Employee button
        JButton viewButton = new JButton("View Employee");
        viewButton.setBounds(10, 140, 200, 25);
        panel.add(viewButton);
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               Viewemployee.show();
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
