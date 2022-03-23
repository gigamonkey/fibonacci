/**
 * Times our two implementations of the Fibonacci function. Run this and see
 * the difference in timing between SlowFib and FastFib and think about why
 * SlowFib is so much slower.
 */
class Main {

  public static void main(String[] args) {
    compareChange();
  }

  static void compareFibs() {
    var slow = Fibonacci.SLOW;
    var fast = Fibonacci.FAST;

    System.out.format(
      "%2s | %12s | slow seconds | fast seconds\n",
      "n",
      "fib(n)"
    );
    System.out.println("---+--------------+--------------+--------------");
    for (long i = 0; i < 51; i++) {
      final long n = i;
      var s = time(() -> slow.fib(n));
      var f = time(() -> fast.fib(n));
      assert s.answer() == f.answer();
      System.out.format(
        "%2d | %12d | %12f | %12f\n",
        i,
        s.answer(),
        s.millis() / 1000.0,
        f.millis() / 1000.0
      );
    }
  }

  static void compareChange() {
    var slow = Change.SLOW;
    var fast = Change.FAST_SMALL;

    System.out.format(
      "%8s | %12s | slow seconds | fast seconds\n",
      "a",
      "change(a)"
    );
    System.out.println(
      "---------+--------------+--------------+--------------"
    );
    for (int i = 0; i < 20; i++) {
      final int amount = (int) Math.pow(2, i);
      var s = time(() -> slow.change(Change.COINS, amount));
      var f = time(() -> fast.change(Change.COINS, amount));
      assert s.answer() == f.answer();
      System.out.format(
        "%8d | %12d | %12f | %12f\n",
        amount,
        s.answer(),
        s.millis() / 1000.0,
        f.millis() / 1000.0
      );
    }
  }

  static Timing time(Timeable t) {
    var start = System.currentTimeMillis();
    var answer = t.run();
    var millis = System.currentTimeMillis() - start;
    return new Timing(answer, millis);
  }

  static interface Timeable {
    public long run();
  }

  static record Timing(long answer, long millis) {}
}
