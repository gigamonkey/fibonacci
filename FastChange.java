import java.util.*;

class FastChange implements Change {

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
/*    
    if (amount < 0) {
      for (var row : table) {
        System.out.println(Arrays.toString(row));
      }
    }
*/
