public class User {
    private int id;
    private String username;
    private String password;
    private int level;
    private int experience;
    private int easy;
    private int normal;
    private int hard;
    private int impossible;
    private int levels;

    public User(int id, String username, String password, int level, int experience, int easy, int normal, int hard, int impossible, int levels) { // constructor
        this.id = id;
        this.username = username;
        this.password = password;
        this.level = level;
        this.experience = experience;
        this.easy = easy;
        this.normal = normal;
        this.hard = hard;
        this.impossible = impossible;
        this.levels = levels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getEasy() {
        return easy;
    }

    public void setEasy(int easy) {
        this.easy = easy;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public int getHard() {
        return hard;
    }

    public void setHard(int hard) {
        this.hard = hard;
    }

    public int getImpossible() {
        return impossible;
    }

    public void setImpossible(int impossible) {
        this.impossible = impossible;
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    @Override
    public String toString() { // Returns a string with values
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", level=" + level +
                ", experience=" + experience +
                ", easy=" + easy +
                ", normal=" + normal +
                ", hard=" + hard +
                ", impossible=" + impossible +
                ", levels=" + levels +
                '}';
    }
}