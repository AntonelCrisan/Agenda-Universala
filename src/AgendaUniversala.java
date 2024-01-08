import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class AgendaUniversala extends JFrame{
    private JPanel agendaPanel;
    private JLabel logOut;
    private JLabel roleConnected;
    private JLabel name;
    private JPanel editProfilePanel;
    private JPanel taskPanel;
    private JPanel addMemberPanel;
    private JPanel showMembersPanel;
    private JLabel editProfile;
    private JLabel tasks;
    private JLabel addMember;
    private JLabel displayMembers;
    public AgendaUniversala() {
        //run agenda page
        setContentPane(agendaPanel);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setTitle("Agendă universală");
        setVisible(true);
        //role visibility
        String role = new LogIn().getRole();
        roleVisibility(role);
        name.setText(new LogIn().getName());
        roleConnected.setText(role);
        logOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new LogIn().runLogIn();
                dispose();
            }
        });
        editProfilePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                new EditProfile().setVisible(true);
            }
        });
        taskPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if("Admin".equals(role)){
                    new Tasks().setVisible(true);
                }else{
                    new MembersTasks().setVisible(true);
                }
            }
        });
        addMemberPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                new CreateAccount().setVisible(true);
            }
        });
        showMembersPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                new ShowMembers().setVisible(true);
            }
        });
    }
    public void roleVisibility(String role){
        if("Admin".equals(role)){
            addMemberPanel.setVisible(true);
            showMembersPanel.setVisible(true);
            taskPanel.setVisible(true);
            editProfilePanel.setVisible(true);
        }else{
            addMemberPanel.setVisible(false);
            showMembersPanel.setVisible(false);
            taskPanel.setVisible(true);
            editProfilePanel.setVisible(true);
        }
    }
}

