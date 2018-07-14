import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class login extends JFrame implements ActionListener {
    public static void main(String[] args) {
        login frame = new login("login");
        frame.setVisible(true);
    }

    
    login (String title) {
        setTitle(title);
        setBounds(100,100,400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p = new JPanel();
        JButton btn = new JButton("Push");
        btn.addActionListener(this);

        p.add(btn);
        getContentPane().add(p, BorderLayout.CENTER);
    }
}
