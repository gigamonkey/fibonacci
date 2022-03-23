/**
 * An interface so we can make two different Fibonacci functions and
 * time them. See SlowFib and FastFib for the two implementations.
 */
interface Fibonacci {
  public static final Fibonacci SLOW = new Slow();
  public static final Fibonacci FAST = new Fast();

  public long fib(long n);

  /**
   * Obvious recursive definition of Fibonacci function.
   */
  static class Slow implements Fibonacci {

    public long fib(long n) {
      return n < 2 ? n : fib(n - 2) + fib(n - 1);
    }
  }

  /**
   * Slightly less obvious but still recursive definition of Fibonacci
   * function. But much faster than SlowFib. Why is that?
   */
  static class Fast implements Fibonacci {

    public long fib(long n) {
      return n < 2 ? n : helper(n - 2, 0, 1);
    }

    private long helper(long n, long a, long b) {
      return n == 0 ? a + b : helper(n - 1, b, a + b);
    }
  }
}
