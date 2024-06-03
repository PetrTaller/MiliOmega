public class DailyChallenge {
    UserHandler userHandler = new UserHandler();
    private String description;
    private boolean completed;
    private int progress;
    private int requiredProgress;
    private int reward;
    private String difficulty;
    CollectReward collectReward = new CollectReward();

    public DailyChallenge(String description, int requiredProgress, int reward,String difficulty,int progress,boolean completed) {  // Constructor to initialize a daily challenge
        this.description = description;
        this.completed = completed;
        this.progress = progress;
        this.requiredProgress = requiredProgress;
        this.reward = reward;
        this.difficulty = difficulty;
    }

// Getters and setters
    public String getDescription() {
        return description;
    }

    public boolean getCompleted() {
        return completed;
    }

    public int getProgress() {
        return progress;
    }

    public int getRequiredProgress() {
        return requiredProgress;
    }

    public int getReward() {
        return reward;
    }

    public String getDifficulty() {
        return difficulty;
    }
//

    public void updateProgress(int amount) { // Updates the progress of the challenge
        if (!completed) {
            this.progress += amount;
            if (this.progress >= this.requiredProgress) {
                this.progress = this.requiredProgress;
                this.completed = true;
                Reward();
            }
        }
    }

    private void Reward() { // Reward the player
        userHandler.LevelUp(userHandler.GetSession().getUsername(),reward);
        collectReward.collectRewardUI(reward);
    }

    @Override
    public String toString() { // Return String
        String status;
        if(completed){
            status = "Completed";
        }else{
            status = "Not Completed";
        }
        return description + " (" + progress + "/" + requiredProgress + ") " + status + " - Reward: " + reward;
    }
}
