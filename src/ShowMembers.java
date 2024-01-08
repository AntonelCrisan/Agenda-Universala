import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
public class ShowMembers extends JFrame{
    private JPanel showMembersPanel;
    private JTable table1;
    private JTextField usernameF;
    private JTextField nameF;
    private JTextField emailF;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField searchUsername;
    private JLabel errorMessage;
    private JLabel message;
    private JComboBox role;
    private static int id;
    Connection con;
    PreparedStatement pst, pst2;
    public ShowMembers(){
        setContentPane(showMembersPanel);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setTitle("Afișare membri");
        setVisible(true);
        connectDB();
        loadTable();
        searchButton.addActionListener(e -> {
            try{
                pst = con.prepareStatement("select id, username, name, email, role from users where username = ?");
                pst.setString(1, searchUsername.getText());
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    int id = rs.getInt("id");
                    setId(id);
                    String username = rs.getString("username");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String rol = rs.getString("role");
                    usernameF.setText(username);
                    nameF.setText(name);
                    emailF.setText(email);
                    errorMessage.setText(null);
                    message.setText(null);
                }else{
                    errorMessage.setText("Membrul căutat nu se gasește în baza de date!");
                    usernameF.setText(null);
                    nameF.setText(null);
                    emailF.setText(null);
                    message.setText(null);
                }
            }catch(HeadlessException | SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        });
        updateButton.addActionListener(e -> {
            if(searchUsername.getText().isEmpty()){
                errorMessage.setText("Introduceți numele de utilizator!");
            }else if(usernameF.getText().isEmpty() || nameF.getText().isEmpty() || emailF.getText().isEmpty()){
                errorMessage.setText("Toate câmpurile trebuie completate!");
            }else{
                try{
                    pst = con.prepareStatement("update users set username = ?, name = ?, email = ?, role = ? where username = ?");
                    pst.setString(1, usernameF.getText());
                    pst.setString(2, nameF.getText());
                    pst.setString(3, emailF.getText());
                    pst.setString(4,  (String) role.getSelectedItem());
                    pst.setString(5, searchUsername.getText());
                    pst.executeUpdate();
                    usernameF.setText(null);
                    nameF.setText(null);
                    emailF.setText(null);
                    message.setText("Persoană actualizată cu succes!");
                    errorMessage.setText(null);
                    searchUsername.setText(null);
                    loadTable();
                }catch(HeadlessException | SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }

        });
        deleteButton.addActionListener(e ->{
            if(searchUsername.getText().isEmpty()){
                errorMessage.setText("Introduceți numele de utilizator!");
            }else{
                try{
                    pst = con.prepareStatement("delete from tasks where idMember = ?");
                    pst.setInt(1, getId());
                    pst.executeUpdate();
                }
                catch(HeadlessException | SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
                try{
                    pst = con.prepareStatement("delete from users where username = ?");
                    pst.setString(1, searchUsername.getText());
                    pst.executeUpdate();
                    usernameF.setText(null);
                    nameF.setText(null);
                    emailF.setText(null);
                    message.setText("Persoana ștearsă cu succes!");
                    errorMessage.setText(null);
                    searchUsername.setText(null);
                    loadTable();
                }catch(HeadlessException | SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
    }
    private void createTable(){
        table1.setModel(new DefaultTableModel(null, new String[]{"Id", "Nume de utilizator", "Nume", "Email", "Rol"}));
    }
    public void loadTable(){
        createTable();
        try{
            pst = con.prepareStatement("select id, username, name, email, role from users");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            while(rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String role = rs.getString("role");
                model.addRow(new Object[]{id, username, name, email, role});
            }
        }catch(HeadlessException| SQLException ex){
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
    public int getId(){
        return id;
    }
    public void setId(int id){
        ShowMembers.id = id;
    }
}
