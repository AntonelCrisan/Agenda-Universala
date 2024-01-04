import javax.swing.*;
public class AgendaUniversala extends JFrame{
    private JPanel agendaPanel;
    public JButton adaugaMembruButton;
    private JButton editeazaProfilButton;
    private JButton sarciniButton;
    private JButton afiseazaPersonalButton;
    private JButton button2;
    public AgendaUniversala() {
        //run agenda page
        setContentPane(agendaPanel);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        //role visibility
        String role = new LogIn().getRole();
        roleVisibility(role);
        adaugaMembruButton.addActionListener(e -> {
            new CreateAccount().setVisible(true);
        });
        editeazaProfilButton.addActionListener(e -> {
            new EditProfile().setVisible(true);
        });
        afiseazaPersonalButton.addActionListener(e -> {
            new ShowMembers().setVisible(true);
        });
        sarciniButton.addActionListener(e ->{
            if("admin".equals(role)){
                new Tasks().setVisible(true);
            }else{
                new MembersTasks().setVisible(true);
            }
        });
    }
    public void roleVisibility(String role){
        if("admin".equals(role)){
            adaugaMembruButton.setVisible(true);
            editeazaProfilButton.setVisible(true);
            afiseazaPersonalButton.setVisible(true);
        }else if("profesor".equals(role)){
            adaugaMembruButton.setVisible(false);
            editeazaProfilButton.setVisible(true);
            afiseazaPersonalButton.setVisible(false);
        }else{
            adaugaMembruButton.setVisible(false);
            editeazaProfilButton.setVisible(false);
            afiseazaPersonalButton.setVisible(false);
        }
    }
}

