/**
 * Times our two implementations of the Fibonacci function. Run this and see
 * the difference in timing between SlowFib and FastFib and think about why
 * SlowFib is so much slower.
 */
class Main {

  public static void main(String[] args) {

    var slow = new SlowFib();
    var fast = new FastFib();

    System.out.format("%2s | %12s | slow seconds | fast seconds\n", "n", "fib(n)");
    System.out.println("---+--------------+--------------+--------------");
    for (long i = 0; i < 51; i++) {
      var s = time(slow, i);
      var f = time(fast, i);
      assert s.answer == f.answer;
      System.out.format("%2d | %12d | %12f | %12f\n", i, s.answer, s.millis/1000.0, f.millis/1000.0);
    }
  }

  static Timing time(Fibonacci f, long n) {
    var start = System.currentTimeMillis();
    var answer = f.fib(n);
    var millis = System.currentTimeMillis() - start;
    return new Timing(answer, millis);
  }
}
