public class Calc {
    
    public double Addition(double a, double b){ // Adds two numbers
        return (a+b);
    }

    public double Subtraction(double a, double b){ // Subtracts the second number from the first
        return (a-b);
    }

    public double Multiplication(double a, double b){ // Multiplies two numbers
        return (a*b);
    }

    public double Division(double a, double b) {  // Divides the first number by the second
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }
    
    public double Power(double a,double b){  
        return Math.pow(a,b);
    }

    public double Root(double a){  // Calculates the root of the number
        return Math.sqrt(a);
    }

    public double Log(double a){
        return Math.log10(a);
    }

}
