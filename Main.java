import java.util.Scanner;

class Main {
  static Scanner scan = new Scanner(System.in);
  static String gameState = "playing";
  static int pos = 0;
  // create array of bins
  static int[] bins = new int[100];

  public static void main(String[] args) {
    bins[0] = 0; // bin 0 is the ruma
    System.out.println("How many bins do you want to play with? ");
    int size = scan.nextInt();
    for (int i = 0; i < size; i++) {
      bins[i + 1] = 1;
    }

    // play game
    while (bins[0] != size && gameState == "playing") {
      for (int i = 0; i < size + 1; i++) {
        System.out.print(bins[i] + " ");
      }
      System.out.println();
      if (pos == 0 || bins[pos] > 1) {
        choose();
      } else {
        gameState = "lost";
      }
    }

    // game ends
    if (gameState == "lost") {
      System.out.println("YOU LOST");
    } else {
      System.out.println("YOU WON!");
    }
  }

  public static void sow(int x) {
    int inHand = bins[x];
    bins[x] = 0;

    // loop from x to x - inHand and place one bean in each
    for (int i = 0; i < inHand; i++) {
      if (x - i - 1 < 0) {
        gameState = "lost";
      } else {
        bins[x - i - 1]++;
      }
    }
    pos = x - inHand;
  }

  public static void choose() {
    System.out.println("Which bin do you want to sow? ");
    int x = scan.nextInt();
    if (bins[x] <= 0) {
      System.out.println("You cannot choose that bin please choose a different one.");
      choose();
    } else {
      sow(x);
    }
  }
}