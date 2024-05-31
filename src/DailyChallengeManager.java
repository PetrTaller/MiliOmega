import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DailyChallengeManager implements Serializable{

    private List<DailyChallenge> challenges;
    private String lastDate;
    private String currentDate;
    private static final String lastDailyPath = "src/LastDaily.txt";
    private static final String challangesPath = "src/Challanges.txt";

    public void refreshChallenges() {
        currentDate = getCurrentDate();
        lastDate = readFile(lastDailyPath);
        if (lastDate == null || !(currentDate.equals(lastDate))) {
            challenges = generateNewChallenges();
        } else if (challenges == null) {
            challenges = readChallengesFromFile(challangesPath);
            if (challenges == null) {
                challenges = generateNewChallenges();
                writeChallengesToFile(challangesPath, challenges);
            }
        }
        writeFile(lastDailyPath, currentDate);
    }

    private List<DailyChallenge> generateNewChallenges() {
        List<DailyChallenge> allChallenges = new ArrayList<>();
        allChallenges.add(new DailyChallenge("Complete the hard challenge 5 times", 5, 200, "hard",0,false));
        allChallenges.add(new DailyChallenge("Complete the normal challenge 5 times", 5, 100, "normal",0,false));
        allChallenges.add(new DailyChallenge("Complete the easy challenge 5 times", 5, 50, "easy",0,false));
        allChallenges.add(new DailyChallenge("Complete the impossible challenge 5 times", 5, 400, "impossible",0,false));
        allChallenges.add(new DailyChallenge("Complete 15 challenges", 15, 400, "all",0,false));
        allChallenges.add(new DailyChallenge("Complete a challenge", 1, 20, "all",0,false));
        allChallenges.add(new DailyChallenge("Level Up", 1, 200, "level",0,false));
        Collections.shuffle(allChallenges);
        return new ArrayList<>(allChallenges.subList(0, 3));
    }

    public List<DailyChallenge> getChallenges() {
        return readChallengesFromFile(challangesPath);
    }

    public void updateChallengeProgress(String difficulty, int amount) {
        if (challenges == null) {
            challenges = readChallengesFromFile(challangesPath);
            if (challenges == null) {
                return;
            }
        }
        for (DailyChallenge challenge : challenges) {
            if (challenge.getDifficulty().equals(difficulty)) {
                challenge.updateProgress(amount);
                writeChallengesToFile(challangesPath, challenges);
                return;
            }
        }
    }

    private static String getCurrentDate() {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(currentDate);
    }

    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return content.toString();
    }

    public static void writeFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//*import lib */
    public String challengeToJson(DailyChallenge challenge) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"description\":\"").append(challenge.getDescription()).append("\",");
        json.append("\"completed\":").append(challenge.getCompleted()).append(",");
        json.append("\"progress\":").append(challenge.getProgress()).append(",");
        json.append("\"requiredProgress\":").append(challenge.getRequiredProgress()).append(",");
        json.append("\"reward\":").append(challenge.getReward()).append(",");
        json.append("\"difficulty\":\"").append(challenge.getDifficulty()).append("\"");
        json.append("}");
        return json.toString();
    }

    public DailyChallenge jsonToChallenge(String json) {
        String[] parts = json.substring(1, json.length() - 1).split(",");
        String description = parts[0].split(":")[1].replace("\"", "").trim();
        boolean completed = Boolean.parseBoolean(parts[1].split(":")[1].trim());
        int progress = Integer.parseInt(parts[2].split(":")[1].trim());
        int requiredProgress = Integer.parseInt(parts[3].split(":")[1].trim());
        int reward = Integer.parseInt(parts[4].split(":")[1].trim());
        String difficulty = parts[5].split(":")[1].replace("\"", "").trim();
        return new DailyChallenge(description, requiredProgress, reward, difficulty,progress,completed);
    }

    public String challengesToJson(List<DailyChallenge> challenges) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        for (int i = 0; i < challenges.size(); i++) {
            json.append(challengeToJson(challenges.get(i)));
            if (i < challenges.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }

    public List<DailyChallenge> jsonToChallenges(String json) {
        List<DailyChallenge> challenges = new ArrayList<>();
        json = json.trim();
        if (json.isEmpty() || json.equals("[]")) {
            return challenges;
        }
        json = json.substring(1, json.length() - 1);
        String[] challengeJsons = json.split("\\},\\{");
        for (String challengeJson : challengeJsons) {
            if (!challengeJson.startsWith("{")) {
                challengeJson = "{" + challengeJson;
            }
            if (!challengeJson.endsWith("}")) {
                challengeJson = challengeJson + "}";
            }
            challenges.add(jsonToChallenge(challengeJson));
        }
        return challenges;
    }

    public void writeChallengesToFile(String filePath, List<DailyChallenge> challenges) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(challengesToJson(challenges));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<DailyChallenge> readChallengesFromFile(String filePath) {
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonToChallenges(json.toString());
    }
}
