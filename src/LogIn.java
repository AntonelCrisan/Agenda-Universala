import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.HeadlessException;
    public class LogIn extends JFrame{
    private JButton autentificareButton;
    private JPanel loginPanel;
    private JTextField email;
    private JPasswordField password;
    private JLabel errorMessage;
    private static String role;
    private static String userEmail;
    private static int id;
    public LogIn() {
        //login into account
        autentificareButton.addActionListener(e -> {
            PreparedStatement pst;
            Connection con;
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_universala","root","");
                pst = con.prepareStatement("select id,email,password,role from users where email=? and password=?");
                pst.setString(1, email.getText());
                pst.setString(2, password.getText());
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    String rol = rs.getString("role");
                    setRole(rol);
                    int id = rs.getInt("id");
                    setId(id);
                    setUserEmail(email.getText());
                    new AgendaUniversala().setVisible(true);
                    dispose();
                }if(email.getText().isEmpty() || password.getText().isEmpty()){
                    errorMessage.setText("Toate campurile trebuie completate!");
                }else{
                    errorMessage.setText("Emailul sau parola este gresita!");
                }
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
                email.setText("");
                password.setText("");
            }
        });
    }
    public void runLogIn(){
        setContentPane(loginPanel);
        setSize(350, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public String getRole(){
        return role;
    }
    public void setRole(String role) {
        LogIn.role = role;
    }
    public  String getUserEmail() {
        return userEmail;
    }
    public  void setUserEmail(String userEmail) {
        LogIn.userEmail = userEmail;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        LogIn.id = id;
    }
}
