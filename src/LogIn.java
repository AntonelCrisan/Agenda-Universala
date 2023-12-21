import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.awt.HeadlessException;
public class LogIn extends JFrame{
    private JButton autentificareButton;
    private JPanel loginPanel;
    private JTextField email;
    private JPasswordField password;
    private JLabel errorMessage;
    public LogIn() {
        autentificareButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                PreparedStatement pst;
                Connection con;
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_universala","root","");
                    pst = con.prepareStatement("select email,password from users where email=? and password=?");
                    pst.setString(1, email.getText());
                    pst.setString(2, password.getText());
                    ResultSet rs = pst.executeQuery();
                    if(rs.next()){
                        AgendaUniversala agenda = new AgendaUniversala();
                        agenda.runAgendaUniversala();
                        dispose();
                    }if(email.getText().equals("") || password.getText().equals("")){
                        errorMessage.setText("Toate campurile trebuie completate!");
                    }else{
                        errorMessage.setText("Emailul sau parola este gresita!");
                    }
                }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                    email.setText("");
                    password.setText("");
                }
            }
        });
    }
    public void runLogInForm(){
        LogIn login = new LogIn();
        login.setContentPane(login.loginPanel);
        login.setSize(500, 500);
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }
}
