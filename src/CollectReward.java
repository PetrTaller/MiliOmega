import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CollectReward implements ActionListener{

    private JFrame frame = new JFrame("Level UP!");
    private JPanel panel = new JPanel();
    private JLabel label;
    private JButton button = new JButton("Continue");

    public void collectRewardUI(int amount){ // Constructor to set up the GUI
        
        label = new JLabel("Congratulations on completing a daily challange, reward: "+amount);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.addActionListener(this);
        panel.setLayout(new GridLayout(2, 1));
        panel.add(label);
        panel.add(button);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) { // Action event handler for the button
        frame.dispose();
    }

}