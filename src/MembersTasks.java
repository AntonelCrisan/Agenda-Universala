import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
public class MembersTasks extends JFrame{
    private JTable table1;
    private JPanel memberstasksPanel;
    private JButton completedButton;
    private JLabel errorMessage;
    private JLabel message;
    private JButton addTaskButton;
    private JTextField newTask;
    Connection con;
    PreparedStatement pst;
    public MembersTasks(){
        //run members tasks
        setContentPane(memberstasksPanel);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setTitle("Sarcini");
        setVisible(true);
        connectDB();
        //show tasks
        loadTable();
        completedButton.addActionListener(e -> {
            int selectedRow = table1.getSelectedRow();
            if(selectedRow >= 0){
                try{
                    String selected = table1.getValueAt(selectedRow, 0).toString();
                    pst = con.prepareStatement("delete from tasks where task = ?");
                    pst.setString(1, selected);
                    pst.executeUpdate();
                    errorMessage.setText(null);
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.removeRow(selectedRow);
                    message.setText("Sarcină completată!");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }else{
                errorMessage.setText("Selectează o sarcină!");
                message.setText(null);
            }
        });
        addTaskButton.addActionListener(e -> {
            if(newTask.getText().isEmpty()){
                errorMessage.setText("Adaugă o sarcină");
                message.setText(null);
            }else{
                try{
                    pst = con.prepareStatement("insert into tasks(task, idMember) values(?, ?)");
                    pst.setString(1, newTask.getText());
                    pst.setInt(2, new LogIn().getId());
                    pst.executeUpdate();
                    errorMessage.setText(null);
                    message.setText(null);
                    newTask.setText(null);
                    loadTable();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
    }
    private void createTable(){
        table1.setModel(new DefaultTableModel(null, new String[]{"Sarcini"}));
    }
    public void loadTable(){
        createTable();
        try{
            pst = con.prepareStatement("select task from tasks where idMember = ?");
            pst.setInt(1, new LogIn().getId());
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            while(rs.next()){
                String task = rs.getString("task");
                model.addRow(new Object[]{task});
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void connectDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_universala","root","");
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
