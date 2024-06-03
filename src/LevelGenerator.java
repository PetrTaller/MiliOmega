import java.util.Random;

public class LevelGenerator {

    private Random random = new Random();
    private Calc calc = new Calc();
    private double answer;  

    public String EasyGenerate() { // Generates a easy level math example
        double a = GenerateRandomNum(100, 1);
        double b = GenerateRandomNum(100, 1);
        double c = GenerateRandomNum(100, a);
        int choice = (int) GenerateRandomNum(4, 1);
        String example = "";
        switch (choice) {
            case 1 -> {
                setAnswer(calc.Addition(a, b));
                example = a + " + " + b;
            }
            case 2 -> {
                setAnswer(calc.Subtraction(a, b));
                example = a + " - " + b;
            }
            case 3 -> {
                setAnswer(calc.Multiplication(a, b));
                example = a + " * " + b;
            }
            case 4 -> {
                setAnswer(calc.Division(c, a));
                example = c + " / " + a;
            }
        }
        return example;
    }

    public String NormalGenerate(){ // Generates a normal level math example
        double a = GenerateRandomNum(100, 1);
        double b = GenerateRandomNum(100, 1);
        double c = GenerateRandomNum(100, a);
        double d = GenerateRandomNum(100, 10);
        double e = GenerateRandomNum(10, 1);
        int choice = (int) GenerateRandomNum(4, 1);
        String example = "";
        switch (choice) {
            case 1 -> {
                setAnswer(calc.Addition((calc.Multiplication(e,b)),c));
                example = e + " * " + b + " + " + c;
            }
            case 2 -> {
                setAnswer(calc.Addition(calc.Power(e,2),a));
                example = "("+ e + "^2) + " + a;
            }
            case 3 -> {
                setAnswer(e);
                example = "√" + calc.Power(e,2);
            }
            case 4 -> {
                setAnswer(calc.Multiplication(d, c));
                example = d + " * " + c;
            }
        }
        return example;
    }

    public String HardGenerate(){ // Generates a Hard level math example
        double a = GenerateRandomNum(100, 1);
        double b = GenerateRandomNum(100, 1);
        double c = GenerateRandomNum(100, a);
        double d = GenerateRandomNum(100, 10);
        double e = GenerateRandomNum(10, 1);
        int choice = (int) GenerateRandomNum(4, 1);
        String example = "";
        switch (choice) {
            case 1 -> {
                setAnswer(calc.Addition((calc.Multiplication(e,b)),(calc.Subtraction(c, d))));
                example = e + " * " + b + " + " + c + " - " + d;
            }
            case 2 -> {
                setAnswer(calc.Addition(calc.Subtraction(calc.Power(e,2),calc.Power(e,2)),a));
                example = "("+ e + "^2) - (" + e + "^2)" + " + " + a;
            }
            case 3 -> {
                setAnswer(calc.Multiplication(e, b));
                example = "√" + calc.Power(e,2) + " * " + b;
            }
            case 4 -> {
                setAnswer(calc.Division(calc.Multiplication(e, c), a));
                example = e + " * " + c + " / " + a;
            }
        }
        return example;
    }
    
    public String ImpossibleGenerate(){ // Generates an impossible level math example
        double a = GenerateRandomNum(100, 2);
        double b = GenerateRandomNum(100, 1);
        double c = GenerateRandomNum(50, 2);
        double c1 = GenerateRandomNum(50, 2);
        double d = GenerateRandomNum(100, 10);
        double e = GenerateRandomNum(10, 1);
        double f = GenerateRandomNum(5, 1);
        double g = GenerateRandomNum(5, 1);
        int choice = (int) GenerateRandomNum(5, 1);
        String example = "";
        switch (choice) {
            case 1 -> {
                setAnswer(calc.Multiplication(e,(calc.Subtraction(calc.Multiplication(e, c), d))));
                example = e + " * (" + e + " * " + c + " - " + d + ")";
            }
            case 2 -> {
                setAnswer(calc.Addition(calc.Subtraction(calc.Power(a,f),calc.Addition(calc.Power(e,g), a)), c));
                example = "("+ e + "^2) - ((" + e + "^2)" + " + " + a + ")) +" + c;
            }
            case 3 -> {
                setAnswer(calc.Addition(e,calc.Multiplication(b, c)));
                example = "√" + calc.Power(e,f) + " + (" + b + " * " + c +")";
            }
            case 4 -> {
                setAnswer(calc.Division(calc.Multiplication(c, c1),2));
                example = "(" + c + " * " + c1 + ") / " + 2;
            }
            case 5 -> {
                setAnswer(calc.Addition(f, e));
                example = "log2(" + calc.Power(2,f) +") + " + e;
            }
            default -> {
                throw new AssertionError();
            }
        }
        return example;
    }

    public double GenerateRandomNum(int range, double divisible) { // Generates a random number within a range that is divisible by a number
        if (range <= 0) {
            throw new IllegalArgumentException("Range must be greater than 0");
        }
        int num;
        if (divisible == 0) {
            throw new IllegalArgumentException("Divisible must be greater than 0");
        } else {
            do {
                num = random.nextInt(range) + 1;
            } while (num % divisible != 0);
        }
        return num;
    }

    public double getAnswer() {  // Returns the answer to the generated example
        return answer;
    }

    public void setAnswer(double answer) { // Sets the answer to the generated example
        this.answer = answer;
    }
}
