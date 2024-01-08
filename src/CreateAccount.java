import java.security.SecureRandom;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class CreateAccount extends JFrame{
    private JPanel createAccountPanel;
    private JTextField email;
    private JButton generatePassword;
    private JLabel passwordG;
    private JButton addButton;
    private JComboBox rol;
    private JTextField nameJ;
    private JLabel errorMessage;
    private JTextField username;
    private JLabel message;

    public CreateAccount() {
        //run create account page
        setContentPane(createAccountPanel);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Adăugare membru");
        setVisible(true);
        generatePassword.addActionListener(e -> generatePassword());
        //add member
        addButton.addActionListener(e -> {
            String emailR = email.getText();
            boolean isValid = isValidEmailDomain(emailR);
            if(email.getText().isEmpty() || nameJ.getText().isEmpty() || username.getText().isEmpty() || passwordG.getText().isEmpty()){
                errorMessage.setText("Toate câmpurile trebuie completate!");
                message.setText(null);
            }else if(isValid) {
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_universala","root","");
                    PreparedStatement ps = con.prepareStatement("insert into users(name,username, email, password, role) values(?, ?,?, ?, ?)");
                    ps.setString(1, nameJ.getText());
                    ps.setString(2, username.getText());
                    ps.setString(3, email.getText());
                    ps.setString(4, passwordG.getText());
                    ps.setString(5, (String) rol.getSelectedItem());
                    ps.executeUpdate();
                    message.setText("Membru adăugat cu succes!");
                    nameJ.setText(null);
                    username.setText(null);
                    email.setText(null);
                    passwordG.setText(null);
                    errorMessage.setText(null);
                }
                catch(Exception ex){
                    errorMessage.setText("Nume de utilizator sau email deja folosit!");
                    message.setText(null);
                }
            }else {
            errorMessage.setText("Emailul introdus este incorect!");
            message.setText(null);
        }
        });
    }
    private boolean isValidEmailDomain(String email){
        String Pattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        return email.matches(Pattern);
    }
    private void generatePassword(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@-_";
        SecureRandom random = new SecureRandom();
        StringBuilder passwordB = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int randomIndex = random.nextInt(characters.length());
            passwordB.append(characters.charAt(randomIndex));
        }
        passwordG.setText(passwordB.toString());
    }
}
