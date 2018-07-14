import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class login {

  private JLabel namelabel, passlabel;

  JTextField nametext, passtext;

  class loginButtonAction implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String name = nametext.getText();
      String pass = passtext.getText();

      logincommand s = new logincommand();
      
      if (s.sqlcommand(name, pass) == true) {
        //if logged in
        System.out.println("logged in!");
      } else {
        //if not logged in
        System.out.println("not a member.");
      }
      
    }
  }

  class cancelButtonAction implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      nametext.setText("");
      passtext.setText(""); 
    }
  }

  public Component createComponents() {
    namelabel = new JLabel("Name");
    passlabel = new JLabel("Password");
    nametext = new JTextField();
    passtext = new JTextField();

    JButton loginbutton = new JButton("Login");
    loginButtonAction loginbuttonListener = new loginButtonAction();
    loginbutton.addActionListener(loginbuttonListener);

    JButton cancelbutton = new JButton("Cancel");
    cancelButtonAction cancelbuttonListener = new cancelButtonAction();
    cancelbutton.addActionListener(cancelbuttonListener);

    //panel
    JPanel pane = new JPanel();
    pane.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
    pane.setLayout(new GridLayout(3, 2));
    pane.add(namelabel);
    pane.add(nametext);
    pane.add(passlabel);
    pane.add(passtext);
    pane.add(loginbutton);
    pane.add(cancelbutton);
    return pane;
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Login");
    login app = new login();
    Component contents = app.createComponents();
    frame.getContentPane().add(contents, BorderLayout.CENTER);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
