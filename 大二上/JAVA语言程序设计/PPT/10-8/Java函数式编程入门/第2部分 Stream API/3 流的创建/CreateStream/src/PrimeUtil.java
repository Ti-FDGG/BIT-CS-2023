import java.util.stream.Stream;

/**
 * 生成素数
 *
 * @author JinXuLiang
 *
 */
public class PrimeUtil {
	// Used for a stateful PrimeUtil
	private long lastPrime = 0L;

	// Computes the prime number after the last generated prime
	public long next() {
		lastPrime = next(lastPrime);
		return lastPrime;
	}

	// Computes the prime number after the specified number
	public static long next(long after) {
		long counter = after;
		// Keep looping until you find the next prime number
		while (!isPrime(++counter))
			;
		return counter;
	}

	// Checks if the specified nubmer is a prime number
	public static boolean isPrime(long number) {
		// <= 1 is not a prime number
		if (number <= 1) {
			return false;
		}
		// 2 is a prime number
		if (number == 2) {
			return true;
		}
		// Even numbers > 2 are not prime numbers
		if (number % 2 == 0) {
			return false;
		}
		long maxDivisor = (long) Math.sqrt(number);
		for (int counter = 3; counter <= maxDivisor; counter += 2) {
			if (number % counter == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println("前5个素数：");
		Stream.iterate(2L, PrimeUtil::next)
				.limit(5)
				.forEach(System.out::println);

//      另一种方法：Print the first 5 prime numbers
//		Stream.iterate(2L, n -> n + 1)
//		.filter(PrimeUtil::isPrime)
//		.limit(5)
//		.forEach(System.out::println);

		System.out.println("跳过前100个素数");
		Stream.iterate(2L, PrimeUtil::next)
				.skip(100)
				.limit(5)
				.forEach(System.out::println);
	}
}
