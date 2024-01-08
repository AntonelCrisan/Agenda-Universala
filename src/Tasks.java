import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
public class Tasks extends JFrame{
    private JPanel tasksPanel;
    private JTextField id;
    private JTextField tasks;
    private JButton adaugaButton;
    private JLabel errorMessage;
    private JLabel succesMessage;
    private JTable table1;
    Connection con;
    PreparedStatement pst;
    public Tasks(){
        //run  tasks page
        setContentPane(tasksPanel);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setTitle("Sarcini");
        setVisible(true);
        connectDB();
        loadTable();
        adaugaButton.addActionListener(e -> {
            if(id.getText().isEmpty() || tasks.getText().isEmpty()){
                errorMessage.setText("Toate câmpurile trebuie completate!");
                succesMessage.setText(null);
            }else{
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_universala","root","");
                    pst = con.prepareStatement("insert into tasks(idMember, task) values(?, ?)");
                    pst.setString(1, id.getText());
                    pst.setString(2, tasks.getText());
                    pst.executeUpdate();
                    succesMessage.setText("Sarcină adăugata cu succes!");
                    id.setText(null);
                    tasks.setText(null);
                    errorMessage.setText(null);
                }catch (Exception ex){
                    errorMessage.setText("Persoana introdusă nu se regasește in baza de date");
                    succesMessage.setText(null);
                }
            }
        });
    }
    private void createTable(){
        table1.setModel(new DefaultTableModel(null, new String[]{"Id", "Nume de utilizator", "Nume"}));
    }
    public void loadTable(){
        createTable();
        try{
            pst = con.prepareStatement("select id, username, name from users");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            while(rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String name = rs.getString("name");
                model.addRow(new Object[]{id, username, name});
            }
        }catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void connectDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_universala","root","");
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
