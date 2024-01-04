import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class MembersTasks extends JFrame{
    private JTable table1;
    private JPanel memberstasksPanel;
    public MembersTasks(){
        //run members tasks
        setContentPane(memberstasksPanel);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        createTable();
        //show tasks
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_universala","root","");
            PreparedStatement pst2 = con.prepareStatement("select task from tasks where idMember = ?");
            LogIn login = new LogIn();
            pst2.setInt(1, login.getId());
            ResultSet rs = pst2.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            while(rs.next()){
                String task = rs.getString("task");
                model.addRow(new Object[]{task});
            }
        }catch (Exception ex){
            System.out.println("Error!");
        }
    }
    private void createTable(){
        table1.setModel(new DefaultTableModel(null, new String[]{"Sarcina"}));
    }
}
