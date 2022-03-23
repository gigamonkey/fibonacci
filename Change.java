/**
 * An interface so we can make two different Fibonacci functions and
 * time them. See SlowFib and FastFib for the two implementations.
 */
interface Change {
  public static final int[] COINS = { 1, 5, 10, 25, 50 };

  public long change(int[] coins, int amount);
}
