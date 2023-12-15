import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class LogIn extends JFrame{
    private JButton autentificareButton;
    private JPanel loginPanel;
    private JPasswordField password;
    private JButton inregistrareButton;
    private JTextField textField1;

    public LogIn() {

        inregistrareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp signup = new SignUp();
                signup.runSignUpForm();
                dispose();
                setVisible(false);
            }
        });
        autentificareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PreparedStatement pst;
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_universala","root","");
                    System.out.println("Succes");
                }catch (ClassNotFoundException ex){
                    ex.printStackTrace();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        });
    }
    public void runLogInForm(){
        LogIn login = new LogIn();
        login.setContentPane(login.loginPanel);
        login.setSize(500, 500);
        login.setVisible(true);
    }
}
