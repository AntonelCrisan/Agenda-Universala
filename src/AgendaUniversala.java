import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AgendaUniversala extends JFrame{
    private JPanel agendaPanel;
    private JButton adaugaMembruButton;

    public AgendaUniversala() {
        adaugaMembruButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateAccount create = new CreateAccount();
                create.runCreateAccount();
            }
        });
    }
    public void runAgendaUniversala(){
        AgendaUniversala agenda = new AgendaUniversala();
        agenda.setContentPane(agenda.agendaPanel);
        agenda.setSize(1200, 800);
        agenda.setLocationRelativeTo(null);
        agenda.setVisible(true);
    }
}

