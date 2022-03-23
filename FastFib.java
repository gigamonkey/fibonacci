/**
 * Slightly less obvious but still recursive definition of Fibonacci
 * function. But much faster than SlowFib. Why is that?
 */
class FastFib implements Fibonacci {

  public long fib(long n) {
    return n < 2 ? n : helper(n - 2, 0, 1);
  }

  private long helper(long n, long a, long b) {
    return n == 0 ? a + b : helper(n - 1, b, a + b);
  }
}
