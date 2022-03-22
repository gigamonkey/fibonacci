/**
 * Obvious recursive definition of Fibonacci function.
 */
class SlowFib implements Fibonacci {
  public long fib(long n) {
    return n < 2 ? n : fib(n - 2) + fib(n - 1);
  }
}