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
    var slow = new SlowFib();
    var fast = new FastFib();

    System.out.format(
      "%2s | %12s | slow seconds | fast seconds\n",
      "n",
      "fib(n)"
    );
    System.out.println("---+--------------+--------------+--------------");
    for (long i = 0; i < 51; i++) {
      var s = timeFib(slow, i);
      var f = timeFib(fast, i);
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
    var slow = new SlowChange();
    var fast = new FastChange();

    System.out.format(
      "%8s | %12s | slow seconds | fast seconds\n",
      "a",
      "change(a)"
    );
    System.out.println(
      "---------+--------------+--------------+--------------"
    );
    for (int i = 0; i < 20; i++) {
      int amount = (int) Math.pow(2, i);
      var s = timeChange(slow, amount);
      var f = timeChange(fast, amount);
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

  static Timing timeFib(Fibonacci f, long n) {
    var start = System.currentTimeMillis();
    var answer = f.fib(n);
    var millis = System.currentTimeMillis() - start;
    return new Timing(answer, millis);
  }

  static Timing timeChange(Change c, int n) {
    var start = System.currentTimeMillis();
    var answer = c.change(Change.COINS, n);
    var millis = System.currentTimeMillis() - start;
    return new Timing(answer, millis);
  }
}
