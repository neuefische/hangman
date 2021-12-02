import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

  public static String[] words = {"Menschen ", "Teil", "Bremen", "Rolle"};

  public static Scanner scanner = new Scanner(System.in);
  public static Random rand = new Random();

  public static String word;
  public static String target;
  public static char[] solution;
  public static int lives;
  public static boolean gameRunning;

  public static void print(String what) {
    System.out.println(what);
  }

  public static void initHangman() {
    word = words[rand.nextInt(words.length)];
    target = word.toLowerCase(Locale.GERMAN);
    solution = new char[target.length()];
    Arrays.fill(solution, '-');
    lives = 12;
    gameRunning = true;
  }

  public static String readUserInput() {
    return scanner.nextLine().toLowerCase(Locale.GERMAN);
  }

  public static void trySolveWith(String input) {
    int pos = target.indexOf(input);
    if (pos >= 0) {
      while (pos >= 0) {
        solution[pos] = word.charAt(pos);
        pos = target.indexOf(input, pos + 1);
      }
    } else {
      --lives;
      print("Falsch");
    }
  }

  public static void checkGameOver() {
    if (word.equals(String.valueOf(solution))) {
      print("Gewonnen!");
      gameRunning = false;
    }
    if (lives == 0) {
      print("Verloren!");
      gameRunning = false;
    }
  }

  public static void stepGame() {
    print(String.valueOf(solution));
    String input = readUserInput();
    if (input.isEmpty()) {
      gameRunning = false;
    } else if (input.length() == 1) {
      trySolveWith(input);
    } else {
      print("Ungültige Eingabe");
    }
  }

  public static void main(String[] args) {
    initHangman();

    while (gameRunning) {
      stepGame();
      checkGameOver();
    }
  }

}
