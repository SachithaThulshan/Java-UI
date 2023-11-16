import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel MainPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JButton closeButton;
    private JCheckBox checkBox;

    public Login(){
        setContentPane(MainPanel);
        setTitle("Login form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,300);
        //setLocationRelativeTo(null);
        setVisible(true);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals("amal")) {
                    if (passwordField1.getText().equals("123")) {
                        JOptionPane.showMessageDialog(rootPane, "You are Longed");
                    } else
                        JOptionPane.showMessageDialog(MainPanel, "Invalid Password");
                }else
                    JOptionPane.showMessageDialog(MainPanel,"Incorrect Name");
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox.isSelected())
                    passwordField1.setEchoChar((char) 0);
                else
                    passwordField1.setEchoChar('*');
            }
        });
    }
    public static void main(String[] args) {
        Login login = new Login();
    }
}
