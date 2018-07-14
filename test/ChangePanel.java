import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ChangePanel extends JFrame implements ActionListener{

public static void main(String[] args){
    new ChangePanel();
}

public ChangePanel() {
    setTitle("change pannl");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setSize(300, 100);
    setLocation(300, 300);

    getContentPane().add(getDefPanel());
    setVisible(true);
}

private JPanel getDefPanel(){
    JPanel panel = new JPanel();
    JButton button = new JButton("to new panel");
    button.setActionCommand("2");
    button.addActionListener(this);
    panel.add(new JLabel("first panel"));
    panel.add(button);
    return panel;
}

@Override
public void actionPerformed(ActionEvent e) {

int command = Integer.parseInt(e.getActionCommand());
JPanel panel = new JPanel();
JButton button;

switch (command) {
case 1:

    panel.add(new JLabel("first panel"));
    button = new JButton("to new panel");
    button.setActionCommand("2");
    button.addActionListener(this);
    panel.add(button);

break;

case 2:

    panel.add(new JLabel("new panel"));
    button = new JButton("to first panel");
    button.setActionCommand("1");
    button.addActionListener(this);
    panel.add(button);

break;

default:
break;
}

getContentPane().removeAll();
getContentPane().add(panel);
setVisible(true);

}

}