import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }
    public void runLogInForm(){
        LogIn login = new LogIn();
        login.setContentPane(login.loginPanel);
        login.setSize(500, 500);
        login.setVisible(true);
    }
}
