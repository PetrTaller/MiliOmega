import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LevelUp implements ActionListener{

    private User user;
    private JFrame frame = new JFrame("Level UP!");
    private JPanel panel = new JPanel();
    private JLabel label;
    private JButton button = new JButton("Continue");

    public void LevelUpUI(User user){

        label =  new JLabel("Congratulations on leveling up to level "+user.getLevel());
        panel.setLayout(new GridLayout(2, 1));
        panel.add(label);
        panel.add(button);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
    }

}