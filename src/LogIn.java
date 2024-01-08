import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.HeadlessException;
    public class LogIn extends JFrame{
    private JButton logInButton;
    private JPanel loginPanel;
    private JPasswordField password;
    private JTextField username;
    private JLabel errorMessage;
    private static String role;
    private static String userName;
    private static int id;
    private static String name;
    public LogIn() {
        //login into account
        logInButton.addActionListener(e -> {
            PreparedStatement pst;
            Connection con;
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_universala","root","");
                pst = con.prepareStatement("select id,password,role, name, username from users where username=? and password=?");
                pst.setString(1, username.getText());
                pst.setString(2, password.getText());
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    String rol = rs.getString("role");
                    setRole(rol);
                    int id = rs.getInt("id");
                    setId(id);
                    setUserName(username.getText());
                    String name = rs.getString("name");
                    setName(name);
                    new AgendaUniversala().setVisible(true);
                    dispose();
                }if(username.getText().isEmpty() || password.getText().isEmpty()){
                    errorMessage.setText("Toate câmpurile trebuie completate!");
                }else{
                    errorMessage.setText("Numele de utilizator sau parola este greșită!");
                }
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
                username.setText("");
                password.setText("");
            }
        });
    }
    public void runLogIn(){
        setContentPane(loginPanel);
        setSize(350, 400);
        setLocationRelativeTo(null);
        setTitle("Autentificare");
        setVisible(true);
    }
    public String getRole(){
        return role;
    }
    public void setRole(String role) {
        LogIn.role = role;
    }
    public  String getUserName() {
        return userName;
    }
    public  void setUserName(String userName) {
        LogIn.userName = userName;
    }
    public int getId(){
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        LogIn.name = name;
    }
    public void setId(int id){
    LogIn.id = id;
}
}
