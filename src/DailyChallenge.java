public class DailyChallenge {
    UserHandler userHandler = new UserHandler();
    private String description;
    private boolean completed;
    private int progress;
    private int requiredProgress;
    private int reward;
    private String difficulty;
    CollectReward collectReward = new CollectReward();

    public DailyChallenge(String description, int requiredProgress, int reward,String difficulty,int progress,boolean completed) {
        this.description = description;
        this.completed = completed;
        this.progress = progress;
        this.requiredProgress = requiredProgress;
        this.reward = reward;
        this.difficulty = difficulty;
    }

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


    public void updateProgress(int amount) {
        if (!completed) {
            this.progress += amount;
            if (this.progress >= this.requiredProgress) {
                this.progress = this.requiredProgress;
                this.completed = true;
                Reward();
            }
        }
    }

    private void Reward() {
        userHandler.LevelUp(userHandler.GetSession().getUsername(),reward);
        collectReward.collectRewardUI(reward);
    }

    @Override
    public String toString() {
        String status;
        if(completed){
            status = "Completed";
        }else{
            status = "Not Completed";
        }
        return description + " (" + progress + "/" + requiredProgress + ") " + status + " - Reward: " + reward;
    }
}
