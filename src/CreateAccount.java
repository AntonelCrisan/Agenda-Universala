import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class CreateAccount extends JFrame{
    private JPanel createAccountPanel;
    private JTextField email;
    private JButton genereazaButton;
    private JLabel passwordG;
    private JButton adaugaButton;
    private JComboBox rol;
    private JTextField nameJ;
    private JLabel errorMessage;
    public CreateAccount() {
        genereazaButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@-_";
                SecureRandom random = new SecureRandom();
                StringBuilder passwordB = new StringBuilder();
                for (int i = 0; i < 12; i++) {
                    int randomIndex = random.nextInt(characters.length());
                    passwordB.append(characters.charAt(randomIndex));
                }
                passwordG.setText(passwordB.toString());
            }
        });
        adaugaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailR = email.getText();
                boolean isValid = isValidEmailDomain(emailR);
                if(isValid){
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_universala","root","");
                        PreparedStatement ps=con.prepareStatement("insert into users(name, email, password, role) values(?, ?, ?, ?)");
                        ps.setString(1, nameJ.getText());
                        ps.setString(2, email.getText());
                        ps.setString(3, passwordG.getText());
                        ps.setString(4, (String) rol.getSelectedItem());
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Membru adaugat cu succes!");
                        setVisible(false);
                    }
                    catch(Exception ex){
                        errorMessage.setText("Nume sau email deja folosit!");
                    }
                }if(email.getText().equals("") || nameJ.getText().equals("")){
                    errorMessage.setText("Toate campurile trebuie completate!");
                }else{
                    errorMessage.setText("Emailul introdus este incorect!");
                }
            }
        });
    }
    private boolean isValidEmailDomain(String email){
        String Pattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        return email.matches(Pattern);
    }
    public void runCreateAccount(){
        CreateAccount create = new CreateAccount();
        create.setContentPane(create.createAccountPanel);
        create.setSize(500, 500);
        create.setLocationRelativeTo(null);
        create.setVisible(true);
    }
}
