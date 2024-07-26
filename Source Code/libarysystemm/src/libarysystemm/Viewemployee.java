package libarysystemm;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Viewemployee {
    public static void show() {
        JFrame frame = new JFrame("View All Employees");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);

        String[] columnNames = {"ID", "Name", "Address", "Phone Number", "Department"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "yinkus")) {
            String query = "SELECT * FROM employee";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("idemployee");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                String phone = rs.getString("Number");
                String department = rs.getString("Department");

                model.addRow(new Object[]{id, name, address, phone, department});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        frame.setVisible(true);
    }
}
