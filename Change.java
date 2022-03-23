import java.util.Arrays;

/**
 * An interface so we can make two different Fibonacci functions and
 * time them. See SlowFib and FastFib for the two implementations.
 */
interface Change {
  public static final int[] COINS = { 1, 5, 10, 25, 50 };
  public static final Change SLOW = new Slow();
  public static final Change FAST = new Fast();

  /**
   * Compute the number of ways to make change for amount with the
   * given set of coins.
   */
  public long change(int[] coins, int amount);

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

  static class Fast implements Change {

    // Faster than the semi-obvious recursive version because it only
    // computes each required value once, filling out a table from the
    // bottom up. I.e. this is essentially the dynamic programming
    // version.
    public long change(int[] coins, int amount) {
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
  }
}
