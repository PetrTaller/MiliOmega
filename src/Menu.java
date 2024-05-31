import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
public class Menu implements ActionListener {
    private User session;
    private DailyChallengeManager dcm = new DailyChallengeManager();
    private UserHandler userHandler = new UserHandler();
    public boolean isDarkMode;
    private LevelMenu levelMenu = new LevelMenu();
    private JFrame frame = new JFrame("Maf");
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JButton easyButton = createButton("Easy", Color.GREEN);
    private JButton normalButton = createButton("Normal", Color.YELLOW);
    private JButton hardButton = createButton("Hard", Color.RED);
    private JButton impossibleButton = createButton("Impossible", Color.BLACK, Color.WHITE);
    private JButton settingsButton = createUniformButton("Settings");
    private JButton profileButton = createUniformButton("Profile");
    private JButton leaderboardButton = createUniformButton("Leaderboard");
    private JButton dailyButton = createUniformButton("Daily Challanges");
    private JButton helpButton = createUniformButton("Help");
    private JPanel leaderboardPanel = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(leaderboardPanel);
    private JFrame leaderboardFrame = new JFrame("Leaderboard");
    private JFrame settingsFrame = new JFrame("Settings");
    private JButton toggleThemeButton = new JButton("Switch to Dark Mode");
    private JLabel userLabel,changePasswordLabel;
    private JLabel profileLabel,experienceLabel,EasyLabel,NormalLabel,HardLabel,ImpossibleLabel;
    private JTextField newPasswordField;
    private JButton submitButton,logoutButton;
    private JFrame profileFrame = new JFrame("Profile");
    private JPanel profilePanel = new JPanel();
    private List<JLabel> labels = new ArrayList<>();
    private JFrame dailyFrame = new JFrame("Daily Challanges");
    private JPanel dailyPanel = new JPanel();
    private JLabel dailyText;
    private JFrame helpFrame = new JFrame("Help");
    private JTextArea helpText = new JTextArea();
    String filePath = "src/help.txt";
    String fileContent = readFile(filePath);
    public void MenuUI() {
        dcm.refreshChallenges();
        helpText.setText(fileContent);
        settingsFrame.setSize(300, 200);
        settingsFrame.setLayout(new BorderLayout());
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        toggleThemeButton.addActionListener(this);
        settingsFrame.add(toggleThemeButton, BorderLayout.CENTER);
        settingsFrame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        leftPanel.setLayout(new GridLayout(4, 1));
        leftPanel.add(easyButton);
        leftPanel.add(normalButton);
        leftPanel.add(hardButton);
        leftPanel.add(impossibleButton);
        rightPanel.setLayout(new GridLayout(7, 1));
        rightPanel.add(new JLabel("")); // Spacer
        rightPanel.add(settingsButton);
        rightPanel.add(profileButton);
        rightPanel.add(leaderboardButton);
        rightPanel.add(dailyButton);
        rightPanel.add(helpButton);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        helpFrame.setSize(400, 400);
        helpFrame.setLayout(new BorderLayout());
        helpFrame.setLocationRelativeTo(null);
        helpText.setFocusable(false);
        helpFrame.add(helpText);
    }
    private JButton createButton(String text, Color background) {
        return createButton(text, background, Color.BLACK);
    }
    private JButton createButton(String text, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setFocusPainted(false);
        button.addActionListener(this);
        return button;
    }
    private JButton createUniformButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.addActionListener(this);
        return button;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == easyButton) {
            levelMenu.EasyStart();
        } else if (e.getSource() == normalButton) {
            levelMenu.NormalStart();
        } else if (e.getSource() == hardButton) {
            levelMenu.HardStart();
        } else if (e.getSource() == impossibleButton) {
            levelMenu.ImpossibleStart();
        } else if (e.getSource() == settingsButton) {
            settingsFrame.setVisible(true);
        } else if (e.getSource() == profileButton) {
            profileFrame.dispose();
            profilePanel.removeAll();
            SetProfile();
            profileFrame.setVisible(true);
        } else if (e.getSource() == leaderboardButton) {
            leaderboardFrame.dispose();
            leaderboardPanel.removeAll();
            SetLeaderboard();
            leaderboardFrame.setVisible(true);
        } else if (e.getSource() == toggleThemeButton) {
            toggleTheme();
        } else if (e.getSource() == submitButton) {
            String NewPass = newPasswordField.getText();
            newPasswordField.setText("");
            userHandler.SetNewPassword(session.getUsername(),NewPass);
        } else if (e.getSource() == logoutButton) {
            frame.dispose();
            leaderboardFrame.dispose();
            settingsFrame.dispose();
            profileFrame.dispose();
            userHandler.DeleteSession();
            Login login = new Login();
        } else if (e.getSource() == dailyButton) {
            dailyFrame.dispose();
            dailyPanel.removeAll();
            SetDailyChallanges();
            dailyFrame.setVisible(true);
        } else if (e.getSource() == helpButton) {
            helpFrame.setVisible(true);
        }
    }
    private void toggleTheme() {
        isDarkMode = !isDarkMode;
        if (isDarkMode) {
            frame.getContentPane().setBackground(Color.DARK_GRAY);
            leftPanel.setBackground(Color.DARK_GRAY);
            rightPanel.setBackground(Color.DARK_GRAY);
            toggleThemeButton.setText("Switch to Light Mode");
            leaderboardFrame.setBackground(Color.DARK_GRAY);
            scrollPane.setBackground(Color.DARK_GRAY);
            leaderboardPanel.setBackground(Color.DARK_GRAY);
            profilePanel.setBackground(Color.DARK_GRAY);
            helpText.setBackground(Color.DARK_GRAY);
            helpText.setForeground(Color.WHITE);
            dailyFrame.getContentPane().setBackground(Color.DARK_GRAY);
            for(JLabel label : labels){
                label.setForeground(Color.WHITE);
            }
        } else {
            frame.getContentPane().setBackground(Color.WHITE);
            leftPanel.setBackground(Color.WHITE);
            rightPanel.setBackground(Color.WHITE);
            toggleThemeButton.setText("Switch to Dark Mode");
            leaderboardFrame.setBackground(Color.WHITE);
            scrollPane.setBackground(Color.WHITE);
            leaderboardPanel.setBackground(Color.WHITE);
            profilePanel.setBackground(Color.WHITE);
            helpText.setBackground(Color.WHITE);
            helpText.setForeground(Color.BLACK);
            dailyFrame.getContentPane().setBackground(Color.WHITE);
            for(JLabel label : labels){
                label.setForeground(Color.BLACK);
            }
        }
    }
    private void SetProfile(){
        session = userHandler.GetSession();
        profileLabel = new JLabel(session.getUsername()+":   Level: "+session.getLevel());
        experienceLabel = new JLabel("Experience: " + session.getExperience());
        EasyLabel = new JLabel("Completed Easy Levels: "+session.getEasy());
        NormalLabel = new JLabel("Completed Normal Levels: "+session.getNormal());
        HardLabel = new JLabel("Completed Hard Levels: "+session.getHard());
        ImpossibleLabel = new JLabel("Completed Impossible Levels: "+session.getImpossible());
        changePasswordLabel = new JLabel("New Password: ");
        newPasswordField = new JTextField();
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);
        profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        profileFrame.setSize(800, 500);
        profileFrame.setLayout(new BorderLayout());
        profilePanel.setLayout(new GridLayout(6, 1));
        profilePanel.add(profileLabel);
        profilePanel.add(experienceLabel);
        profilePanel.add(EasyLabel);
        profilePanel.add(NormalLabel);
        profilePanel.add(HardLabel);
        profilePanel.add(ImpossibleLabel);
        profilePanel.add(changePasswordLabel);
        profilePanel.add(newPasswordField);
        profilePanel.add(logoutButton);
        profilePanel.add(submitButton);
        profileFrame.add(profilePanel, BorderLayout.CENTER);
        profileFrame.setLocationRelativeTo(null);
        profileFrame.setVisible(false);
        labels.add(profileLabel);
        labels.add(experienceLabel);
        labels.add(EasyLabel);
        labels.add(NormalLabel);
        labels.add(HardLabel);
        labels.add(ImpossibleLabel);
        labels.add(changePasswordLabel);
        toggleTheme();
        toggleTheme();
    }
    private void SetLeaderboard(){
        session = userHandler.GetSession();
        leaderboardFrame.setSize(400, 600);
        leaderboardFrame.setLayout(new BorderLayout());
        leaderboardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        leaderboardFrame.add(scrollPane, BorderLayout.CENTER);
        leaderboardFrame.setLocationRelativeTo(null);
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));
        List<User> users = userHandler.GetListOfUsers();
        int count = 1;
        for (User user : users) {
            userLabel = new JLabel(count+". "+user.getUsername()+": Level: "+user.getLevel()+" ,Completed Levels: "+user.getLevels());
            leaderboardPanel.add(userLabel);
            labels.add(userLabel);
            count++;
        }
        toggleTheme();
        toggleTheme();
    }
    private void SetDailyChallanges(){
        dcm.refreshChallenges();
        dailyPanel.setLayout(new GridLayout(3, 1));
        List<DailyChallenge> dailyChallenges = dcm.getChallenges();
        int count =1;
        for (DailyChallenge dailyChallenge : dailyChallenges) {
            JLabel dailyText = new JLabel(count+". "+dailyChallenge.toString());
            dailyPanel.add(dailyText);
            labels.add(dailyText);
            count++;
        }
        dailyFrame.add(dailyPanel, BorderLayout.CENTER);
        dailyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dailyFrame.setSize(500, 125);
        dailyFrame.setLayout(new FlowLayout());
        dailyFrame.setLocationRelativeTo(null);
    }
    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}