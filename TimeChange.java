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
      //1000000000, OutOfMemory
    }) {
      if (amt < 101) {
        System.out.println(Change.SLOW.change(Change.COINS, amt));
      }
      System.out.println(Change.FAST.change(Change.COINS, amt));
    }
  }
}
