import java.io.*;
import java.util.Comparator;
import java.util.List;

public class UserHandler {

    private static final String SessionFilePath = "src/currentUser.json";
    private LevelUp levelUp = new LevelUp();
    private DailyChallengeManager dcm = new DailyChallengeManager();

    public boolean Login(String username,String password){
        DB.getInstance();
        User user = DB.getUserByUsername(username);
        DB.closeConnection();
        if (user != null){
        if(user.getPassword().equals(password)){
            SetSession(user);
            return true;
        }else {
            return false;
        }
        }
        return false;
    }

    public boolean Register(String username,String password){
        DB.getInstance();
        if(DB.getUserByUsername(username)==null){
        if(DB.InsertUser(username,password)){
        DB.closeConnection();
        return true;
        }
        }
        DB.closeConnection();
        return false;
    }

    public void SetSession(User user) {
        try (FileWriter fileWriter = new FileWriter(SessionFilePath)) {
            String json = userToJson(user);
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void DeleteSession() {
        try (PrintWriter writer = new PrintWriter(SessionFilePath)){
            writer.print("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User GetSession() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SessionFilePath))) {
            StringBuilder jsonString = new StringBuilder();
            String line;
            int lines = 0;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
                lines++;
            }
            if(lines == 0 ){
                return null;
            }
            DB.getInstance();
            User user = jsonToUser(jsonString.toString());
            User RefreshedUser = DB.getUserByUsername(user.getUsername());
            SetSession(RefreshedUser);
            DB.closeConnection();
            return RefreshedUser;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> GetListOfUsers() {
        DB.getInstance();
        List<User> users = DB.getAllUsers();
        users.sort((Comparator.comparing(User::getLevel).reversed().thenComparing(User::getLevels).reversed()).reversed()); // found a way to sort somewhere on reddit(can be done easier but i am lazy)
        DB.closeConnection();
        return users;
    }

    public void SetNewPassword(String username,String password){
        if(!(password.equals(""))){
        DB.getInstance();
        User UpdatedUser;
        UpdatedUser = DB.getUserByUsername(username);
        UpdatedUser.setPassword(password);
        DB.updateUser(UpdatedUser);
        DB.closeConnection();
        }
    }

    public void LevelUp(String username,int exp){
        DB.getInstance();
        int newExp;
        User updatedUser;
        updatedUser = DB.getUserByUsername(username);
        User user = DB.getUserByUsername(username);
        if(user.getLevel()<15){
            if((user.getExperience()+exp)>=100){
                newExp = (exp+user.getExperience())-100;
                updatedUser.setLevel(user.getLevel()+1);
                updatedUser.setExperience(newExp);
                DB.updateUser(updatedUser);
                levelUp.LevelUpUI(DB.getUserByUsername(username));
                dcm.updateChallengeProgress("level", 1);
            }else{
                newExp = exp+user.getExperience();
                updatedUser.setExperience(newExp);
                DB.updateUser(updatedUser);
            }
        }else if (user.getLevel()>=15 && user.getLevel()< 30) {
            if((user.getExperience()+exp)>=150){
                newExp = (exp+user.getExperience())-150;
                updatedUser.setLevel(user.getLevel()+1);
                updatedUser.setExperience(newExp);
                DB.updateUser(updatedUser);
                levelUp.LevelUpUI(DB.getUserByUsername(username));
                dcm.updateChallengeProgress("level", 1);
            }else{
                newExp = exp+user.getExperience();
                updatedUser.setExperience(newExp);
                DB.updateUser(updatedUser);
            }
            }else if(user.getLevel()>=30){
                if((user.getExperience()+exp)>=300){
                    newExp = (exp+user.getExperience())-300;
                    updatedUser.setLevel(user.getLevel()+1);
                    updatedUser.setExperience(newExp);
                    DB.updateUser(updatedUser);
                    levelUp.LevelUpUI(DB.getUserByUsername(username));
                    dcm.updateChallengeProgress("level", 1);
                }else{
                    newExp = exp+user.getExperience();
                    updatedUser.setExperience(newExp);
                    DB.updateUser(updatedUser);
                }
            }
        DB.closeConnection();
    }

    public void AddCompletion(String difficulty,String username){
        DB.getInstance();
        User newUser = DB.getUserByUsername(username);
        switch (difficulty) {
            case "easy"  -> {
                newUser.setEasy(newUser.getEasy()+1);
                newUser.setLevels(newUser.getLevels()+1);
            }
            case "normal"  -> {
                newUser.setNormal(newUser.getNormal()+1);
                newUser.setLevels(newUser.getLevels()+1);
            }
            case "hard"  -> {
                newUser.setHard(newUser.getHard()+1);
                newUser.setLevels(newUser.getLevels()+1);
            }
            case "impossible"  -> {
                newUser.setImpossible(newUser.getImpossible()+1);
                newUser.setLevels(newUser.getLevels()+1);
            }
            default -> {
                throw new AssertionError();
            }
        }
        DB.updateUser(newUser);
        DB.closeConnection();
    }

    private String userToJson(User user) { // my import from com.google.Gson didnt work and even jsonb didnt work so i did it manually
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"id\":").append(user.getId()).append(",");
        json.append("\"username\":\"").append(user.getUsername()).append("\",");
        json.append("\"password\":\"").append(user.getPassword()).append("\",");
        json.append("\"level\":").append(user.getLevel()).append(",");
        json.append("\"experience\":").append(user.getExperience()).append(",");
        json.append("\"easy\":").append(user.getEasy()).append(",");
        json.append("\"normal\":").append(user.getNormal()).append(",");
        json.append("\"hard\":").append(user.getHard()).append(",");
        json.append("\"impossible\":").append(user.getImpossible()).append(",");
        json.append("\"levels\":").append(user.getLevels());
        json.append("}");
        return json.toString();
    }

    private User jsonToUser(String json) { // my import from com.google.Gson didnt work and even jsonb didnt work so i did it manually
        String[] parts = json.substring(1, json.length() - 1).split(",");
        int id = Integer.parseInt(parts[0].split(":")[1]);
        String username = parts[1].split(":")[1].replace("\"", "");
        String password = parts[2].split(":")[1].replace("\"", "");
        int level = Integer.parseInt(parts[3].split(":")[1]);
        int experience = Integer.parseInt(parts[4].split(":")[1]);
        int easy = Integer.parseInt(parts[5].split(":")[1]);
        int normal = Integer.parseInt(parts[6].split(":")[1]);
        int hard = Integer.parseInt(parts[7].split(":")[1]);
        int impossible = Integer.parseInt(parts[8].split(":")[1]);
        int levels = Integer.parseInt(parts[9].split(":")[1]);
        return new User(id, username, password, level, experience, easy, normal, hard, impossible, levels);
    }

}
