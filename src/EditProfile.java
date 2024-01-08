import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class EditProfile extends JFrame{
    private JPasswordField password;
    private JPasswordField confirmPassword;
    private JButton saveButton;
    private JPanel editProfilPanel;
    private JLabel errorMessage;
    public EditProfile() {
        //run edit profile page
        setContentPane(editProfilPanel);
        setSize(350, 400);
        setLocationRelativeTo(null);
        setTitle("Editare profil");
        setVisible(true);
        saveButton.addActionListener(e -> {
            PreparedStatement pst;
            Connection con;
            if(password.getText().isEmpty() || confirmPassword.getText().isEmpty()){
                errorMessage.setText("Toate câmpurile trebuie completate!");
            }else if(!password.getText().equals(confirmPassword.getText())){
                errorMessage.setText("Parolele trebuie sa fie asemănătoare!");
            }else{
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_universala","root","");
                    pst = con.prepareStatement("update users set password=? where username=?");
                    pst.setString(1, password.getText());
                    pst.setString(2, new LogIn().getUserName());
                    pst.executeUpdate();
                    dispose();
                    JOptionPane.showMessageDialog(null, "Parola adaugată cu succes!");
                }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
    }
}
