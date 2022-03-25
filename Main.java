import java.util.*;
import java.io.*;

class Main {
  public static int numBins = 20; // ruma not included
  public static int numStates = 10000; // number of states to calculate
  public static int[][] states = new int[200000][numBins + 1]; // stores all states, first number is last played

  public static void main(String[] args) throws IOException {
    readArray();
    for (int i = 1; i <= numStates; i++) {
      unsow(i);
    }
    writeArray();
  }

  public static void unsow(int state) {
    // for (int i = 0; i < states[state].length; i++) {
    // System.out.print(states[state][i] + " ");
    // }
    // System.out.println(" BASE:" + Integer.toBinaryString(state));

    for (int i = 0; i <= 1; i++) {
      int[] bins = new int[numBins + 1];
      for (int j = 0; j < bins.length; j++) {
        bins[j] = states[state][j];
      }
      bins[0] = Integer.MAX_VALUE / 2; // place effectively infinite beans in the ruma

      // binary translation:
      // 1 = sow from ruma
      // 0 = sow from last sowed position
      int pos = 0; // sow from ruma
      if (i == 0) {
        pos = states[state][0]; // sow from last sowed position
      }

      int beansInHand = 0;
      int newState = Integer.parseInt(Integer.toBinaryString(state) + i, 2);
      if (newState < numStates && states[state][0] != -1 && bins[pos] > 1) {
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

        // for (int j = 0; j < bins.length; j++) {
        // System.out.print(bins[j] + " ");
        // }
        // System.out.println(" POS: " + i);

        for (int j = 0; j < bins.length; j++) {
          states[newState][j] = bins[j];
        }
      } else {
        states[newState][0] = -1;
      }
    }
  }

  public static void readArray() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("states.txt"));
    String line;
    while ((line = reader.readLine()) != null) {
      int[] temp = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
      for (int i = 0; i < temp.length - 1; i++) {
        states[temp[0]][i] = temp[i + 1]; // first number of input is the index
      }
    }
    reader.close();
  }

  public static void writeArray() throws IOException {
    FileWriter writer = new FileWriter("states.txt");
    for (int i = 0; i < numStates + 1; i++) {
      if (states[i][0] != -1) {
        writer.write(i + " "); // output index
        for (int j = 0; j < states[i].length; j++) {
          writer.write(states[i][j] + " ");
        }
        writer.write("\n");
      }
    }
    writer.close();
  }
}