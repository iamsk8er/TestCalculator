        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.Scanner;
    public class Calculator {
    public static void main(String[] args) throws Exceptions {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input");
        String str  = scanner.nextLine();
        String result = Main.calc(str);
        System.out.println("Output: " + result);
    }
    }
    class Exceptions extends Exception {
            public Exceptions(String message) {
                super(message);
            }
        }
        enum RomanNum {
            I(1), IV(4), V(5), IX(9), X(10), XL(40), L(50),
            XC(90), C(100);
            private final int number;
            RomanNum(int number) {
                this.number = number;
            }
            public int getNumber() {
                return number;
            }
        }
        class Main {
    public static String calc(String input) throws Exceptions {
        String expr = input;
        String[] romN = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        List<String> romList = new ArrayList<>(Arrays.asList(romN));
        expr = expr.replaceAll("\\s+", ""); // if any spaces or tabs
        String [] arrExpr = expr.split("(?=[+-/*])|(?<=[+-/*])");
        if (arrExpr.length != 3) {
            throw new Exceptions("т.к. только 1 оператор и 2 операнда/целые числа");
        }
        int leftNum;
        int rightNum;
        int leftRom;
        int rightRom;
        int resRom = 0;
        if ((romList.contains(arrExpr[0])) && (romList.contains(arrExpr[2]))) {
            leftRom = romList.indexOf(arrExpr[0])+1;
            rightRom = romList.indexOf(arrExpr[2])+1;
            switch (arrExpr[1]) {
                case ("+") -> resRom = leftRom + rightRom;
                case ("-") -> resRom = leftRom - rightRom;
                case ("*") -> resRom = leftRom * rightRom;
                case ("/") -> resRom = leftRom / rightRom;
            }
            if (resRom <= 0) {
                throw new Exceptions("т.к. в римской системе нет отрицательных чисел");
            }
            return romanConverter(resRom);
        } else {
            leftNum = Integer.parseInt(arrExpr[0]);
            rightNum = Integer.parseInt(arrExpr[2]);
            if ((leftNum <= 0) || (leftNum > 10)) {
                throw new Exceptions("Первый операнд - от 1 до 10");
            } else if ((rightNum <= 0) || (rightNum > 10)) {
                throw new Exceptions("Второй операнд - от 1 до 10");
            }
        }
        int res = switch (arrExpr[1]) {
            case ("+") -> leftNum + rightNum;
            case ("-") -> leftNum - rightNum;
            case ("*") -> leftNum * rightNum;
            case ("/") -> leftNum / rightNum;
            default -> 0;
        };
        return Integer.toString(res);
    }
    public static String romanConverter(int number) {
        StringBuilder resRom = new StringBuilder();
        int arabic = number;
        int j = RomanNum.values().length - 1;
        while (j>=0) {
            if (RomanNum.values()[j].getNumber() == arabic) {
                resRom.append(RomanNum.values()[j]);
                break;
            }
            if (RomanNum.values()[j].getNumber() < arabic) {
                resRom.append(RomanNum.values()[j]);
                arabic = arabic - RomanNum.values()[j].getNumber();
                if (RomanNum.values()[j].getNumber() == arabic) {
                    resRom.append(RomanNum.values()[j]);
                    break;
                }
            }
            else j--;
        }
        return resRom.toString();
    }
    }