/**
 * Times our two implementations of the Fibonacci function. Run this and see
 * the difference in timing between SlowFib and FastFib and think about why
 * SlowFib is so much slower.
 */
class TimeChange {

  public static void main(String[] args) {
    var s = new SlowChange();
    var f = new FastChange();

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
    }) {
      if (amt < 101) {
        System.out.println(s.change(Change.COINS, amt));
      }
      System.out.println(f.change(Change.COINS, amt));
    }
  }
}
