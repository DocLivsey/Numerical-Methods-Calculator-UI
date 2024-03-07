import java.util.*;

public class Parser {
    /*------------------------------------------------------------------
     * PARSER RULES
     *------------------------------------------------------------------*/
//    function : NAME '(' (expr (',' expr)+)? ')'
//
//    expression : plusminus* EOF ;
//
//    plusminus: multdiv ( ( '+' | '-' ) multdiv )* ;
//
//    multdiv : factor ( ( '*' | '/' ) factor )* ;
//
//    factor : function | unary | NUMBER | '(' expression ')' ;
//
//    unary : '-' factor

    public static HashMap<String, MathFunction> functionMap;

    public static HashMap<String, MathFunction> getFunctionMap()
    {
        HashMap<String, MathFunction> functionTable = new HashMap<>();
        functionTable.put("pow", arguments -> {
            if (arguments.size() != 2)
                throw new RuntimeException(PrettyOutput.ERROR + "ОШИБКА. ожидаемое число аргументов функции: 2\n" +
                        "Получено: " + arguments.size());
            return new Point2D(arguments.get(0), Math.pow(arguments.get(0), arguments.get(1)));
        });
        functionTable.put("sin", arguments -> {
            if (arguments.size() != 1)
                throw new RuntimeException(PrettyOutput.ERROR + "ОШИБКА. ожидаемое число аргументов функции: 1\n" +
                        "Получено: " + arguments.size());
            return new Point2D(arguments.get(0), Math.sin(arguments.get(0)));
        });
        functionTable.put("cos", arguments -> {
            if (arguments.size() != 1)
                throw new RuntimeException(PrettyOutput.ERROR + "ОШИБКА. ожидаемое число аргументов функции: 1\n" +
                        "Получено: " + arguments.size());
            return new Point2D(arguments.get(0), Math.cos(arguments.get(0)));
        });
        functionTable.put("tan", arguments -> {
            if (arguments.size() != 1)
                throw new RuntimeException(PrettyOutput.ERROR + "ОШИБКА. ожидаемое число аргументов функции: 1\n" +
                        "Получено: " + arguments.size());
            return new Point2D(arguments.get(0), Math.tan(arguments.get(0)));
        });
        functionTable.put("exp", arguments -> {
            if (arguments.size() != 1)
                throw new RuntimeException(PrettyOutput.ERROR + "ОШИБКА. ожидаемое число аргументов функции: 1\n" +
                        "Получено: " + arguments.size());
            return new Point2D(arguments.get(0), Math.exp(arguments.get(0)));
        });
        functionTable.put("sqrt", arguments -> {
            if (arguments.size() != 1)
                throw new RuntimeException(PrettyOutput.ERROR + "ОШИБКА. ожидаемое число аргументов функции: 1\n" +
                        "Получено: " + arguments.size());
            return new Point2D(arguments.get(0), Math.sqrt(arguments.get(0)));
        });
        return functionTable;
    }
    public enum LexemeType {
        LEFT_BRACKET, RIGHT_BRACKET,
        OP_PLUS, OP_MINUS, OP_MUL, OP_DIV,
        NUMBER, NAME, COMMA,
        EOF;
    }
    public static class Lexeme {
        LexemeType type;
        String value;
        public Lexeme(LexemeType type, String value) {
            this.type = type;
            this.value = value;
        }
        public Lexeme(LexemeType type, Character value) {
            this.type = type;
            this.value = value.toString();
        }
        @Override
        public String toString() {
            return "Lexeme{" +
                    "type=" + type +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
    public static class LexemeBuffer {
        private int pos;

        public List<Lexeme> lexemes;

        public LexemeBuffer(List<Lexeme> lexemes) {
            this.lexemes = lexemes;
        }

        public Lexeme next() {
            return lexemes.get(pos++);
        }

        public void back() {
            pos--;
        }

        public int getPos() {
            return pos;
        }
    }
    public static List<Lexeme> lexAnalyze(String expText) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        int pos = 0;
        while (pos< expText.length()) {
            char c = expText.charAt(pos);
            switch (c) {
                case '(':
                    lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, c));
                    pos++;
                    continue;
                case ')':
                    lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, c));
                    pos++;
                    continue;
                case '+':
                    lexemes.add(new Lexeme(LexemeType.OP_PLUS, c));
                    pos++;
                    continue;
                case '-':
                    lexemes.add(new Lexeme(LexemeType.OP_MINUS, c));
                    pos++;
                    continue;
                case '*':
                    lexemes.add(new Lexeme(LexemeType.OP_MUL, c));
                    pos++;
                    continue;
                case '/':
                    lexemes.add(new Lexeme(LexemeType.OP_DIV, c));
                    pos++;
                    continue;
                case ',':
                    lexemes.add(new Lexeme(LexemeType.COMMA, c));
                    pos++;
                    continue;
                default:
                    if (c <= '9' && c >= '0') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(c);
                            pos++;
                            if (pos >= expText.length()) {
                                break;
                            }
                            c = expText.charAt(pos);
                        } while (c <= '9' && c >= '0');
                        lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
                    } else {
                        if (c != ' ') {
                            if (c >= 'a' && c <= 'z'
                                    || c >= 'A' && c <= 'Z') {
                                StringBuilder sb = new StringBuilder();
                                do {
                                    sb.append(c);
                                    pos++;
                                    if (pos >= expText.length()) {
                                        break;
                                    }
                                    c = expText.charAt(pos);
                                } while (c >= 'a' && c <= 'z'
                                        || c >= 'A' && c <= 'Z');

                                if (functionMap.containsKey(sb.toString())) {
                                    lexemes.add(new Lexeme(LexemeType.NAME, sb.toString()));
                                } else {
                                    throw new RuntimeException("Unexpected character: " + c);
                                }
                            }
                        } else {
                            pos++;
                        }
                    }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }

    public static double expr(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        if (lexeme.type == LexemeType.EOF) {
            return 0;
        } else {
            lexemes.back();
            return plusminus(lexemes);
        }
    }

    public static double plusminus(LexemeBuffer lexemes) {
        double value = multdiv(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_PLUS:
                    value += multdiv(lexemes);
                    break;
                case OP_MINUS:
                    value -= multdiv(lexemes);
                    break;
                case EOF:
                case RIGHT_BRACKET:
                case COMMA:
                    lexemes.back();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token: " + lexeme.value
                            + " at position: " + lexemes.getPos());
            }
        }
    }

    public static double multdiv(LexemeBuffer lexemes) {
        double value = factor(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_MUL:
                    value *= factor(lexemes);
                    break;
                case OP_DIV:
                    value /= factor(lexemes);
                    break;
                case EOF:
                case RIGHT_BRACKET:
                case COMMA:
                case OP_PLUS:
                case OP_MINUS:
                    lexemes.back();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token: " + lexeme.value
                            + " at position: " + lexemes.getPos());
            }
        }
    }

    public static double factor(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        switch (lexeme.type) {
            case NAME:
                lexemes.back();
                return func(lexemes);
            case OP_MINUS:
                return -factor(lexemes);
            case NUMBER:
                return Integer.parseInt(lexeme.value);
            case LEFT_BRACKET:
                value = plusminus(lexemes);
                lexeme = lexemes.next();
                if (lexeme.type != LexemeType.RIGHT_BRACKET) {
                    throw new RuntimeException("Unexpected token: " + lexeme.value
                            + " at position: " + lexemes.getPos());
                }
                return value;
            default:
                throw new RuntimeException("Unexpected token: " + lexeme.value
                        + " at position: " + lexemes.getPos());
        }
    }

    public static double func(LexemeBuffer lexemeBuffer) {
        String name = lexemeBuffer.next().value;
        Lexeme lexeme = lexemeBuffer.next();

        if (lexeme.type != LexemeType.LEFT_BRACKET) {
            throw new RuntimeException("Wrong function call syntax at " + lexeme.value);
        }

        ArrayList<Double> args = new ArrayList<>();

        lexeme = lexemeBuffer.next();
        if (lexeme.type != LexemeType.RIGHT_BRACKET) {
            lexemeBuffer.back();
            do {
                args.add(expr(lexemeBuffer));
                lexeme = lexemeBuffer.next();

                if (lexeme.type != LexemeType.COMMA && lexeme.type != LexemeType.RIGHT_BRACKET) {
                    throw new RuntimeException("Wrong function call syntax at " + lexeme.value);
                }

            } while (lexeme.type == LexemeType.COMMA);
        }
        return functionMap.get(name).function(args).getY();
    }
}
