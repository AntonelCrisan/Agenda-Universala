import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
public class ShowMembers extends JFrame{
    private JPanel showMembersPanel;
    private JTable table1;
    public ShowMembers(){
        setContentPane(showMembersPanel);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        createTable();
        Connection con;
        PreparedStatement pst;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_universala","root","");
            pst = con.prepareStatement("SELECT id, name, email, role from users");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            while(rs.next()){
                int id = rs.getInt("id");
                String nume = rs.getString("name");
                String email = rs.getString("email");
                String role = rs.getString("role");
                model.addRow(new Object[]{id, nume, email, role});

            }
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    private void createTable(){
        table1.setModel(new DefaultTableModel(null, new String[]{"Id", "Nume", "Email", "Rol"}));
    }
}
