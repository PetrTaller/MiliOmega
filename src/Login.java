import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login implements ActionListener {

    private UserHandler userHandler = new UserHandler();
    private JFrame frame = new JFrame();
    private JFrame frame2 = new JFrame();
    private JPanel panel = new JPanel();
    private JButton login = new JButton("Login");
    private JButton registration = new JButton("Registration");
    private JButton register = new JButton("Register");
    private JButton loginreg = new JButton("Alredy Registered?");
    private JLabel userLabel, passLabel,userLabel1, passLabel1;
    private JTextField textField1, textField2, textField3, textField4;
    private JFrame message = new JFrame();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JButton loginc = new JButton("Continue");
    private JLabel wel_label = new JLabel("Welcome!");

    public Login() {
        LoginUI();
    }

    private void LoginUI() {
        login.setBackground(Color.GRAY);
        login.setForeground(Color.WHITE);
        login.setFocusable(false);
        login.addActionListener(this);

        registration.setBackground(Color.GRAY);
        registration.setForeground(Color.WHITE);
        registration.setFocusable(false);
        registration.addActionListener(this);

        register.setBackground(Color.GRAY);
        register.setForeground(Color.WHITE);
        register.setFocusable(false);
        register.addActionListener(this);

        loginreg.setBackground(Color.GRAY);
        loginreg.setForeground(Color.WHITE);
        loginreg.setFocusable(false);
        loginreg.addActionListener(this);

        userLabel = new JLabel("Username");
        userLabel1 = new JLabel("Username");
        textField1 = new JTextField(15);
        textField3 = new JTextField(15);
        passLabel = new JLabel("Password");
        passLabel1 = new JLabel("Password");
        textField2 = new JPasswordField(15);
        textField4 = new JPasswordField(15);

        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
        panel.setLayout(new GridLayout(0, 1));
        panel2.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
        panel2.setLayout(new GridLayout(0, 1));

        panel.add(userLabel);
        panel.add(textField1);
        panel.add(passLabel);
        panel.add(textField2);
        panel.add(login);
        panel.add(registration);

        panel2.add(userLabel1);
        panel2.add(textField3);
        panel2.add(passLabel1);
        panel2.add(textField4);
        panel2.add(register);
        panel2.add(loginreg);

        panel1.add(wel_label);
        loginc.addActionListener(this);
        panel1.setBorder(BorderFactory.createEmptyBorder(120, 120, 40, 120));
        panel1.setLayout(new GridLayout(0, 1));
        panel1.add(loginc);
        loginreg.addActionListener(this);

        message.add(panel1, BorderLayout.CENTER);
        message.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        message.setTitle("Login Success");
        message.pack();
        message.setResizable(false);
        message.setLocationRelativeTo(null);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Login");
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        frame2.add(panel2, BorderLayout.CENTER);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setTitle("Register");
        frame2.pack();
        frame2.setResizable(false);
        frame2.setLocationRelativeTo(null);
    }

    private void login() {
        String userValue = textField1.getText();
        String passValue = textField2.getText();
        if (userHandler.Login(userValue, passValue) && !userValue.equals("") && !passValue.equals(""))
        {
            message.setVisible(true);
            frame.setVisible(false);
        } else {
            textField1.setText("");
            textField2.setText("");
        }
    }

    private void end() {
        frame.dispose();
        Menu menu = new Menu();
        menu.MenuUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == login) {
            login();
        } else if (source == registration) {
            registration();
        } else if (source == register) {
            register();
        } else if (source == loginreg) {
            registered();
        } else if (source == loginc) {
            end();
        }
    }

    public void register() {
        String userValue1 = textField3.getText();
        String passValue1 = textField4.getText();
        if (userHandler.Register(userValue1, passValue1) && !userValue1.equals("") && !passValue1.equals("")) {
            frame2.setVisible(false);
            frame.setVisible(true);
        } else {
            textField3.setText("");
            textField4.setText("");
        }
    }

    public void registration() {
        frame.setVisible(false);
        frame2.setVisible(true);
    }

    public void registered() {
        frame.setVisible(true);
        frame2.setVisible(false);
    }
}