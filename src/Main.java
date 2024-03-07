import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        String expressionText = "pow(x, y)";
        System.out.print("enter x = ");
        double x = Double.parseDouble(new BufferedReader(new InputStreamReader(System.in)).readLine());
        System.out.print("enter y = ");
        double y = Double.parseDouble(new BufferedReader(new InputStreamReader(System.in)).readLine());
        List<Parser.Lexeme> lexemes = Parser.lexAnalyze(expressionText);
        Parser.LexemeBuffer lexemeBuffer = new Parser.LexemeBuffer(lexemes);
        System.out.println(Parser.analyseExpression(lexemeBuffer, x));

    }
    public static class ConsoleTests {
        public static void main(String[] args) throws IOException {
            System.out.print("enter x = ");
            Double x = Double.parseDouble(new BufferedReader(new InputStreamReader(System.in)).readLine());
            System.out.println(x);
        }
    }
}


// create MathPackage
// make up the UI for actions with math operations include linear programming

// remake structure creation methods in matrix and vector class like methods that calls constructor