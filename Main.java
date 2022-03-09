import java.util.*;
import java.io.*;

class Main {
  public static int numBins = 10; // ruma not included
  public static int numStates = 100000; // number of states to calculate
  public static int[][] states = new int[200000][numBins + 1]; // stores all states, first number is last played

  public static void main(String[] args) throws IOException {
    readArray();
    for (int i = 1; i <= numStates; i++) {
      unsow(i);
    }
    writeArray();
  }

  public static void unsow(int state) {
    int[] bins = new int[numBins + 1];
    for (int i = 0; i < bins.length; i++) {
      bins[i] = states[state][i];
    }
    bins[0] = Integer.MAX_VALUE / 2; // place effectively infinite beans in the ruma

    System.out.print("X ");
    for (int j = 1; j < bins.length; j++) {
      System.out.print(bins[j] + " ");
    }
    System.out.println(" BASE:" + Integer.toBinaryString(state));

    for (int i = 0; i <= 1; i++) {
      // binary translation:
      // 1 = sow from ruma
      // 0 = sow from last sowed position
      int pos = 0; // sow from ruma
      if (i == 0) {
        pos = states[state][0]; // sow from last sowed position
      }

      int beansInHand = 0;
      while (bins[pos] > 0) {
        bins[pos]--;
        pos++;
        // wrapping
        // if(pos > numBins) {
        // pos = 0;
        // }
        beansInHand++;
      }
      bins[pos] = beansInHand;
      bins[0] = pos;

      for (int j = 0; j < bins.length; j++) {
        System.out.print(bins[j] + " ");
      }
      System.out.println(" POS: " + i);

      int newState = Integer.parseInt(Integer.toBinaryString(state) + i, 2);
      for (int j = 0; j < bins.length; j++) {
        states[newState][j] = bins[j];
      }
    }
  }

  public static void readArray() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("states.txt"));
    int index = 0;
    String line;
    while ((line = reader.readLine()) != null) {
      states[index] = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
      index++;
    }
    reader.close();
  }

  public static void writeArray() throws IOException {
    FileWriter writer = new FileWriter("states.txt");
    for (int i = 0; i < numStates + 1; i++) {
      for (int j = 0; j < states[i].length; j++) {
        writer.write(states[i][j] + " ");
      }
      writer.write("\n");
    }
    writer.close();
  }
}