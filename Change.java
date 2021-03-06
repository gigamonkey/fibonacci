import java.util.Arrays;

/**
 * An interface so we can make different change counting functions and
 * time them.
 */
interface Change {
  public static final int[] COINS = { 1, 5, 10, 25, 50 };
  public static final Change SLOW = new Slow();
  public static final Change FAST = new Fast();
  public static final Change FAST_SMALL = new FastAndSmall();

  /**
   * Compute the number of ways to make change for amount with the
   * given set of coins.
   */
  public long change(int[] coins, int amount);

  /**
   * Direct recursive solution. Works great but is slow for larger
   * amounts because of all the duplicated work.
   */
  static class Slow implements Change {

    public long change(int[] coins, int amount) {
      if (coins.length == 0 || amount < 0) {
        return 0;
      } else if (coins[0] == amount) {
        return 1;
      } else {
        var newCoins = Arrays.copyOfRange(coins, 1, coins.length);
        return change(newCoins, amount) + change(coins, amount - coins[0]);
      }
    }
  }

  /**
   * Faster than the semi-obvious recursive version because it only
   * computes each required value once, filling out a table from the
   * bottom up. I.e. this is essentially the dynamic programming
   * version.
   */
  static class Fast implements Change {

    public long change(int[] coins, int amount) {
      var table = table(coins, amount);

      // Now fill in the table from the bottom up.
      for (int i = 1; i <= amount; i++) {
        for (int j = 0; j < coins.length; j++) {
          var d = coins[j];
          table[j][i] =
            (j == 0 ? 0 : table[j - 1][i]) + (i < d ? 0 : table[j][i - d]);
        }
      }
      return table[coins.length - 1][amount];
    }

    private long[][] table(int[] coins, int amount) {
      // Make the table to be filled in. For each coin there's a row
      // containing a slot for every amount from 0 to amount, inclusive.
      // Each row will contain the number af ways to make change for up
      // to that many coins.
      var table = new long[coins.length][amount + 1];

      // Initialize the number of ways to make change for amount 0 to 1
      // for all numbers of coins.
      for (int i = 0; i < table.length; i++) {
        table[i][0] = 1;
      }
      return table;
    }
  }

  /**
   * Basically the same as Fast but uses an amount of memory that
   * depends only on the number and denominations of the coins.
   * Possibly very slightly slower than Fast but can compute larger
   * amounts without running out of memory.
   */
  static class FastAndSmall implements Change {

    // Faster than the semi-obvious recursive version because it only
    // computes each required value once, filling out a table from the
    // bottom up. I.e. this is essentially the dynamic programming
    // version.
    public long change(int[] coins, int amount) {
      var table = table(coins);
      var offsets = offsets(coins);

      for (int amt = 0; amt <= amount; amt++) {
        for (int c = 0; c < coins.length; c++) {
          var p = c - 1;
          table[offsets[c] + (amt % coins[c])] +=
            c == 0 ? 0 : table[offsets[p] + (amt % coins[p])];
        }
      }
      var last = coins.length - 1;
      return table[offsets[last] + (amount % coins[last])];
    }

    private long[] table(int[] coins) {
      // Build a table of essentially a circular buffer for each coin.
      // Kick things off with 1 way to make change for amount 0 using
      // just the first coin. Everything else will follow from there.
      var table = new long[Arrays.stream(coins).sum()];
      table[0] = 1;
      return table;
    }

    private int[] offsets(int[] coins) {
      var offsets = new int[coins.length];
      var o = 0;
      for (int i = 0; i < coins.length; i++) {
        offsets[i] = o;
        o += coins[i];
      }
      return offsets;
    }
  }
}
