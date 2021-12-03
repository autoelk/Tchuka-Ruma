class Main {
  public static int[] bins = new int[100];
  public static int numBins; // ruma not included

  public static void main(String[] args) {
    bins[0] = Integer.MAX_VALUE / 2;
    numBins = 3;
    for(int i = 0; i < 10; i++) {
      unsow();
    }
  }

  public static void unsow() {
    int beansInHand = 0;
    int pos = 0; // always start at ruma

    while(bins[pos] > 0) {
      bins[pos]--;
      pos++;
      if(pos > numBins) {
        pos = 0;
      }
      beansInHand++;
    }
    bins[pos] = beansInHand;
    for(int i = 1; i <= numBins; i++){
      System.out.print(bins[i] + " ");
    }
    System.out.println();
  }
}