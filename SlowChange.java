import java.util.Arrays;

class SlowChange implements Change {

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
