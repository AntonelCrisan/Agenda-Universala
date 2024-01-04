import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditProfile extends JFrame{
    private JPasswordField password;
    private JPasswordField confirmPassword;
    private JButton salveazaButton;
    private JPanel editProfilPanel;
    private JLabel errorMessage;

    public EditProfile() {
        //run edit profile page
        setContentPane(editProfilPanel);
        setSize(350, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        salveazaButton.addActionListener(e -> {
            PreparedStatement pst;
            Connection con;
            if(password.getText().isEmpty() || confirmPassword.getText().isEmpty()){
                errorMessage.setText("Toate campurile trebuie completate!");
            }else if(!password.getText().equals(confirmPassword.getText())){
                errorMessage.setText("Parolele trebuie sa fie egale!");
            }else{
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_universala","root","");
                    pst = con.prepareStatement("UPDATE users SET password=? WHERE email=?");
                    pst.setString(1, password.getText());
                    pst.setString(2, new LogIn().getUserEmail());
                    pst.executeUpdate();
                    dispose();
                    JOptionPane.showMessageDialog(null, "Parola adaugata cu succes!");
                }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
    }
}
