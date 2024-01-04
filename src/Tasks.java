import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class Tasks extends JFrame{
    private JPanel tasksPanel;
    private JTextField id;
    private JTextField tasks;
    private JButton adaugaButton;
    private JLabel errorMessage;
    private JLabel succesMessage;

    public Tasks(){
        //run  tasks page
        setContentPane(tasksPanel);
        setSize(350, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        adaugaButton.addActionListener(e -> {
            if(id.getText().isEmpty() || tasks.getText().isEmpty()){
                errorMessage.setText("Toate campurile trebuie completate");
                succesMessage.setText(null);
            }else{
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_universala","root","");
                    PreparedStatement pst = con.prepareStatement("insert into tasks(idMember, task) values(?, ?)");
                    pst.setString(1, id.getText());
                    pst.setString(2, tasks.getText());
                    pst.executeUpdate();
                    succesMessage.setText("Sarcina adaugata cu succes!");
                    id.setText(null);
                    tasks.setText(null);
                    errorMessage.setText(null);
                }catch (Exception ex){
                    errorMessage.setText("Persoana introdusa nu se regaseste in baza de date");
                    succesMessage.setText(null);
                }
            }
        });
    }
}
