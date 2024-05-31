import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LevelMenu implements ActionListener{

    private DailyChallengeManager dcm = new DailyChallengeManager();
    private LevelGenerator levelGenerator = new LevelGenerator();
    private UserHandler userHandler = new UserHandler();

    private JFrame frame = new JFrame();

    private JPanel easyPanel = new JPanel();
    private JLabel easyLabel;
    private JTextField easyAnswer;
    private JButton easySubmitButton;

    private JPanel normalPanel = new JPanel();
    private JLabel normalLabel;
    private JTextField normalAnswer;
    private JButton normalSubmitButton;

    private JPanel hardPanel = new JPanel();
    private JLabel hardLabel;
    private JTextField hardAnswer;
    private JButton hardSubmitButton;

    private JPanel impossiblePanel = new JPanel();
    private JLabel impossibleLabel;
    private JTextField impossibleAnswer;
    private JButton impossibleSubmitButton;

    public LevelMenu(){
        LevelUI();
    }

    private void LevelUI() {

        easyLabel = new JLabel();
        normalLabel = new JLabel();
        hardLabel = new JLabel();
        impossibleLabel = new JLabel();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);

        easyPanel.setLayout(new GridLayout(3, 1));
        normalPanel.setLayout(new GridLayout(3, 1));
        hardPanel.setLayout(new GridLayout(3, 1));
        impossiblePanel.setLayout(new GridLayout(3, 1));

        easySubmitButton = new JButton("Submit");
        easySubmitButton.addActionListener(this);
        normalSubmitButton = new JButton("Submit");
        normalSubmitButton.addActionListener(this);
        hardSubmitButton = new JButton("Submit");
        hardSubmitButton.addActionListener(this);
        impossibleSubmitButton = new JButton("Submit");
        impossibleSubmitButton.addActionListener(this);

        easyAnswer = new JTextField();
        normalAnswer = new JTextField();
        hardAnswer = new JTextField();
        impossibleAnswer = new JTextField();

        easyPanel.add(easyLabel);
        easyPanel.add(easyAnswer);
        easyPanel.add(easySubmitButton);

        normalPanel.add(normalLabel);
        normalPanel.add(normalAnswer);
        normalPanel.add(normalSubmitButton);

        hardPanel.add(hardLabel);
        hardPanel.add(hardAnswer);
        hardPanel.add(hardSubmitButton);

        impossiblePanel.add(impossibleLabel);
        impossiblePanel.add(impossibleAnswer);
        impossiblePanel.add(impossibleSubmitButton);
    }

    public void EasyStart(){
        frame.remove(impossiblePanel);
        frame.remove(normalPanel);
        frame.remove(hardPanel);
        frame.dispose();
        easyLabel.setText(levelGenerator.EasyGenerate());
        frame.setTitle("Easy");
        frame.add(easyPanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    public void NormalStart(){
        frame.remove(impossiblePanel);
        frame.remove(easyPanel);
        frame.remove(hardPanel);
        frame.dispose();
        normalLabel.setText(levelGenerator.NormalGenerate());
        frame.setTitle("Normal");
        frame.add(normalPanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    public void HardStart(){
        frame.remove(impossiblePanel);
        frame.remove(normalPanel);
        frame.remove(easyPanel);
        frame.dispose();
        hardLabel.setText(levelGenerator.HardGenerate());
        frame.setTitle("Hard");
        frame.add(hardPanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    public void ImpossibleStart(){
        frame.remove(easyPanel);
        frame.remove(hardPanel);
        frame.remove(normalPanel);
        impossibleLabel.setText(levelGenerator.ImpossibleGenerate());
        frame.setTitle("Impossible");
        frame.add(impossiblePanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == easySubmitButton) {
            String correctAnswer = deleteZero(levelGenerator.getAnswer());
            if(correctAnswer.equals((easyAnswer.getText())) && !easyAnswer.getText().equals("")){
                userHandler.LevelUp(userHandler.GetSession().getUsername(),5);
                userHandler.AddCompletion("easy",userHandler.GetSession().getUsername());
                EasyStart();
                easyAnswer.setText("");
                dcm.updateChallengeProgress("easy", 1);
                dcm.updateChallengeProgress("all", 1);
            }else{
                easyAnswer.setText("");
            } 
        } else if (e.getSource() == normalSubmitButton) {
            String correctAnswer = deleteZero(levelGenerator.getAnswer());
            if(correctAnswer.equals((normalAnswer.getText())) && !normalAnswer.getText().equals("")){
                userHandler.LevelUp(userHandler.GetSession().getUsername(),10);
                userHandler.AddCompletion("normal",userHandler.GetSession().getUsername());
                NormalStart();
                normalAnswer.setText("");
                dcm.updateChallengeProgress("normal", 1);
                dcm.updateChallengeProgress("all", 1);
            }else{
                normalAnswer.setText("");
            } 
        } else if (e.getSource() == hardSubmitButton) {
            String correctAnswer = deleteZero(levelGenerator.getAnswer());
            if(correctAnswer.equals((hardAnswer.getText())) && !hardAnswer.getText().equals("")){
                userHandler.LevelUp(userHandler.GetSession().getUsername(),20);
                userHandler.AddCompletion("hard",userHandler.GetSession().getUsername());
                HardStart();
                hardAnswer.setText("");
                dcm.updateChallengeProgress("hard", 1);
                dcm.updateChallengeProgress("all", 1);
            }else{
                hardAnswer.setText("");
            } 
        } else if (e.getSource() == impossibleSubmitButton) {
            String correctAnswer = deleteZero(levelGenerator.getAnswer());
            if(correctAnswer.equals((impossibleAnswer.getText())) && !impossibleAnswer.getText().equals("")){
                userHandler.LevelUp(userHandler.GetSession().getUsername(),20);
                userHandler.AddCompletion("impossible",userHandler.GetSession().getUsername());
                ImpossibleStart();
                impossibleAnswer.setText("");
                dcm.updateChallengeProgress("impossible", 1);
                dcm.updateChallengeProgress("all", 1);
            }else{
                impossibleAnswer.setText("");
            }
        }
    }

    public String deleteZero(double x){
        String stringResult = String.valueOf(x);
        char lastchar1 = stringResult.charAt(stringResult.length()-1);
        char seclastchar1 = stringResult.charAt(stringResult.length()-2);
        String last2 = ""+seclastchar1+lastchar1;
        if(last2.equals(".0")){
            int z = (int)Math.round(x);
            String y = Integer.toString(z);
            return y;
        }else{
            String y = Double.toString(x);
            return y;
        }
    }
}
