import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame{
    private JPanel signUpPanel;
    private JTextField nume;
    private JTextField email;
    private JPasswordField password;
    private JPasswordField confirmPassword;
    private JButton inregistrareButton;
    private JButton autentificareButton;

    public SignUp() {
        autentificareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogIn login = new LogIn();
                login.runLogInForm();
                dispose();
                setVisible(false);
            }
        });
    }

    public void runSignUpForm(){
        SignUp signup = new SignUp();
        signup.setContentPane(signup.signUpPanel);
        signup.setSize(500, 500);
        signup.setVisible(true);
    }
}
