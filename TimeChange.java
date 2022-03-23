/**
 * Times our two implementations of the Fibonacci function. Run this and see
 * the difference in timing between SlowFib and FastFib and think about why
 * SlowFib is so much slower.
 */
class TimeChange {

  public static void main(String[] args) {
    for (var amt : new int[] {
      1,
      5,
      10,
      100,
      2000,
      5000,
      10000,
      100000,
      1000000,
      10000000,
      100000000,
      1000000000,
    }) {
      System.out.format(
        "%20d %20d %20d\n",
        (amt < 101 ? Change.SLOW.change(Change.COINS, amt) : -1),
        (amt <= 100000000 ? Change.FAST.change(Change.COINS, amt) : -1),
        Change.FAST_SMALL.change(Change.COINS, amt)
      );
    }
  }
}
