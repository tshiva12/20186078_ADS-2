import java.util.Random;
/**
 * class StdRandom.
 */
public final class StdRandom {
    /**
     * Random object.
     * pseudo-random number generator
     */
    private static Random random;
    /**
     * pseudo-random number generator seed.
     */
    private static long seed;
    /**
     * static initializer.
     */
    static {
        // this is how the seed was set in Java 1.4
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }
    /**
     * Constructs the object.
     */
    private StdRandom() {
        // default constructor is not used.
    }
    /**
     * Sets the seed of the pseudorandom number generator.
     * This method enables you to produce the same sequence of "random"
     * number for each execution of the program.
     * Ordinarily, you should call this method at most once per program.
     *
     * @param s the seed
     */
    public static void setSeed(final long s) {
        seed   = s;
        random = new Random(seed);
    }
    /**
     * Returns the seed of the pseudorandom number generator.
     *
     * @return the seed
     */
    public static long getSeed() {
        return seed;
    }
    /**
     * Returns a random real number uniformly in [0, 1).
     *
     * @return a random real number uniformly in [0, 1)
     */
    public static double uniform() {
        return random.nextDouble();
    }
    /**
     * Returns a random integer uniformly in [0, n).
     * @param n number of possible integers
     * @return a random integer uniformly between 0  and n
     * @throws IllegalArgumentException if {@code n <= 0}
     */
    public static int uniform(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("argument must be positive");
        }
        return random.nextInt(n);
    }
    /**
     * Returns a random real number uniformly in [0, 1).
     * @return     a random real number uniformly in [0, 1)
     * @deprecated Replaced by {@link #uniform()}.
     */
    @Deprecated
    public static double random() {
        return uniform();
    }
    /**
     * Returns a random integer uniformly in [a, b).
     * @param  a the left endpoint
     * @param  b the right endpoint
     * @return a random integer uniformly in (a, b)
     * @throws IllegalArgumentException if b <= a
     * @throws IllegalArgumentException if b - a >= Integer.MAX_VALUE
     */
    public static int uniform(final int a, final int b) {
        if ((b <= a) || ((long) b - a >= Integer.MAX_VALUE)) {
            throw new IllegalArgumentException(
                "invalid range: [" + a + ", " + b + "]");
        }
        return a + uniform(b - a);
    }
    /**
     * Returns a random real number uniformly in [a, b).
     * @param  a the left endpoint
     * @param  b the right endpoint
     * @return a random real number uniformly in [a, b)
     * @throws IllegalArgumentException unless {@code a < b}
     */
    public static double uniform(final double a, final double b) {
        if (!(a < b)) {
            throw new IllegalArgumentException(
                "invalid range: [" + a + ", " + b + "]");
        }
        return a + uniform() * (b - a);
    }
    /**
     * Returns a random boolean from a Bernoulli distribution with success
     * probability <em>p</em>.
     *
     * @param  p the probability of returning {@code true}
     * @return {@code true} with probability {@code p} and
     *         {@code false} with probability {@code p}
     * @throws IllegalArgumentException
     */
    public static boolean bernoulli(final double p) {
        if (!(p >= 0.0 && p <= 1.0)) {
            throw new IllegalArgumentException(
                "probability p must be between 0.0 and 1.0");
        }
        return uniform() < p;
    }
    /**
     * Returns a random boolean from a Bernoulli distribution with success
     * probability 1/2.
     * @return {@code true} with probability 1/2 and
     *         {@code false} with probability 1/2
     */
    public static boolean bernoulli() {
        final double y = 0.5;
        return bernoulli(y);
    }
    /**
     * Returns a random real number from a standard Gaussian distribution.
     * @return a random real number from a standard Gaussian distribution
     *         (mean 0 and standard deviation 1).
     */
    public static double gaussian() {
        // use the polar form of the Box-Muller transform
        double r, x, y;
        final int b = -2;
        do {
            x = uniform(-1.0, 1.0);
            y = uniform(-1.0, 1.0);
            r = x * x + y * y;
        } while (r >= 1 || r == 0);
        return x * Math.sqrt(b * Math.log(r) / r);

        // Remark:  y * Math.sqrt(-2 * Math.log(r) / r)
        // is an independent random gaussian
    }
    /**
     * Returns a random real number from a Gaussian distribution with mean
     * and standard deviation.
     * @param  mu the mean
     * @param  sigma the standard deviation
     * @return a real number distributed according to the Gaussian distribution
     *         with mean {@code mu} and standard deviation {@code sigma}
     */
    public static double gaussian(final double mu, final double sigma) {
        return mu + sigma * gaussian();
    }
    /**
     * Returns a random integer from a geometric distribution with success
     * probability <em>p</em>.
     * @param  p the parameter of the geometric distribution
     * @return a random integer from a geometric distribution with success
     *         probability {@code p}; or {@code Integer.MAX_VALUE} if
     *         {@code p} is (nearly) equal to {@code 1.0}.
     * @throws IllegalArgumentException
     *  unless {@code p >= 0.0} and {@code p <= 1.0}
     */
    public static int geometric(final double p) {
        if (!(p >= 0.0 && p <= 1.0)) {
            throw new IllegalArgumentException(
                "probability p must be between 0.0 and 1.0");
        }
        // using algorithm given by Knuth
        return (int) Math.ceil(Math.log(uniform()) / Math.log(1.0 - p));
    }
    /**
     * Returns a random integer from a Poisson distribution with mean &lambda;.
     *
     * @param  lambda the mean of the Poisson distribution
     * @return a random integer from a Poisson distribution with mean lambda
     * @throws IllegalArgumentException
     *  unless {@code lambda > 0.0} and not infinite
     */
    public static int poisson(final double lambda) {
        if (!(lambda > 0.0)) {
            throw new IllegalArgumentException("lambda must be positive");
        }
        if (Double.isInfinite(lambda)) {
            throw new IllegalArgumentException("lambda must not be infinite");
        }
        // using algorithm given by Knuth
        // see http://en.wikipedia.org/wiki/Poisson_distribution
        int k = 0;
        double p = 1.0;
        double expLambda = Math.exp(-lambda);
        do {
            k++;
            p *= uniform();
        } while (p >= expLambda);
        return k - 1;
    }
    /**
     * Returns a random real number from the standard Pareto distribution.
     *
     * @return a random real number from the standard Pareto distribution
     */
    public static double pareto() {
        return pareto(1.0);
    }
    /**
     * Returns a random real number from a Pareto distribution with
     * shape parameter &alpha;.
     *
     * @param  alpha shape parameter
     * @return a random real number from a Pareto distribution with shape
     *         parameter {@code alpha}
     * @throws IllegalArgumentException unless {@code alpha > 0.0}
     */
    public static double pareto(final double alpha) {
        if (!(alpha > 0.0)) {
            throw new IllegalArgumentException("alpha must be positive");
        }
        return Math.pow(1 - uniform(), -1.0 / alpha) - 1.0;
    }
    /**
     * Returns a random real number from the Cauchy distribution.
     *
     * @return a random real number from the Cauchy distribution.
     */
    public static double cauchy() {
        final double z = 0.5;
        return Math.tan(Math.PI * (uniform() - z));
    }
    /**
     * Returns a random integer from the specified discrete distribution.
     *
     * @param  probabilities the probability of occurrence of each integer
     * @return a random integer from a discrete distribution:
     *         {@code i} with probability {@code probabilities[i]}
     * @throws IllegalArgumentException if {@code probabilities} is {@code null}
     * @throws IllegalArgumentException
     *  if sum of array entries is not (very nearly) equal to {@code 1.0}
     * @throws IllegalArgumentException
     *  unless {@code probabilities[i] >= 0.0} for each index {@code i}
     */
    public static int discrete(final double[] probabilities) {
        if (probabilities == null) {
            throw new IllegalArgumentException("argument array is null");
        }
        final double epsilon = 1E-14;
        double sum = 0.0;
        for (int i = 0; i < probabilities.length; i++) {
            if (!(probabilities[i] >= 0.0)) {
                throw new IllegalArgumentException(
                    "array entry " + i + " must be nonnegative: "
                     + probabilities[i]);
            }
            sum += probabilities[i];
        }
        if (sum > 1.0 + epsilon || sum < 1.0 - epsilon) {
            throw new IllegalArgumentException(
            "sum of array entries does not approximately equal 1.0: " + sum);
        }
        while (true) {
            double r = uniform();
            sum = 0.0;
            for (int i = 0; i < probabilities.length; i++) {
                sum = sum + probabilities[i];
                if (sum > r) {
                    return i;
                }
            }
        }
    }
    /**
     * Returns a random integer from the specified discrete distribution.
     *
     * @param  frequencies the frequency of occurrence of each integer
     * @return a random integer from a discrete distribution:
     *         {@code i} with probability proportional to {@code frequencies[i]}
     * @throws IllegalArgumentException if {@code frequencies} is {@code null}
     * @throws IllegalArgumentException if all array entries are {@code 0}
     * @throws IllegalArgumentException if {@code frequencies[i]}
     *  is negative for any index {@code i}
     * @throws IllegalArgumentException if sum of frequencies
     *  exceeds Integer.MAX_VALUE} (2<sup>31</sup> - 1)
     */
    public static int discrete(final int[] frequencies) {
        if (frequencies == null) {
            throw new IllegalArgumentException("argument array is null");
        }
        long sum = 0;
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] < 0) {
                throw new IllegalArgumentException(
                    "array entry " + i + " must be nonnegative: "
                     + frequencies[i]);
            }
            sum += frequencies[i];
        }
        if (sum == 0) {
            throw new IllegalArgumentException(
                "at least one array entry must be positive");
        }
        if (sum >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException(
                "sum of frequencies overflows an int");
        }
        // pick index i with probabilitity proportional to frequency
        double r = uniform((int) sum);
        sum = 0;
        for (int i = 0; i < frequencies.length; i++) {
            sum += frequencies[i];
            if (sum > r) {
                return i;
            }
        }
        return -1;
    }
    /**
     * Returns a random real number from an exponential distribution
     * with rate &lambda;.
     * @param  lambda the rate of the exponential distribution
     * @return a random real number from an exponential distribution with
     *         rate {@code lambda}
     * @throws IllegalArgumentException unless {@code lambda > 0.0}
     */
    public static double exp(final double lambda) {
        if (!(lambda > 0.0)) {
            throw new IllegalArgumentException("lambda must be positive");
        }
        return -Math.log(1 - uniform()) / lambda;
    }
    /**
     * Rearranges the elements of the specified array in uniformly random order.
     *
     * @param  a the array to shuffle
     * @throws IllegalArgumentException if {@code a} is {@code null}
     */
    public static void shuffle(final Object[] a) {
        if (a == null) {
            throw new IllegalArgumentException("argument array is null");
        }
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n - i);     // between i and n-1
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
    /**
     * Rearranges the elements of the specified array in uniformly random order.
     *
     * @param  a the array to shuffle
     * @throws IllegalArgumentException if {@code a} is {@code null}
     */
    public static void shuffle(final double[] a) {
        if (a == null) {
            throw new IllegalArgumentException("argument array is null");
        }
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n - i);     // between i and n-1
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
    /**
     * Rearranges the elements of the specified array in uniformly random order.
     *
     * @param  a the array to shuffle
     * @throws IllegalArgumentException if {@code a} is {@code null}
     */
    public static void shuffle(final int[] a) {
        if (a == null) {
            throw new IllegalArgumentException("argument array is null");
        }
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n - i);     // between i and n-1
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
    /**
     * Rearranges the elements of the specified array in uniformly random order.
     *
     * @param  a the array to shuffle
     * @throws IllegalArgumentException if {@code a} is {@code null}
     */
    public static void shuffle(final char[] a) {
        if (a == null) {
            throw new IllegalArgumentException("argument array is null");
        }
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n - i);     // between i and n-1
            char temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
    /**
     * Rearranges the elements of the specified
     *  subarray in uniformly random order.
     *
     * @param  a the array to shuffle
     * @param  lo the left endpoint (inclusive)
     * @param  hi the right endpoint (exclusive)
     * @throws IllegalArgumentException if {@code a} is {@code null}
     * @throws IndexOutOfBoundsException
     * unless (0 <= lo) && (lo < hi) && (hi <= a.length)}
     */
    public static void shuffle(final Object[] a, final int lo, final int hi) {
        if (a == null) {
            throw new IllegalArgumentException("argument array is null");
        }
        if (lo < 0 || lo >= hi || hi > a.length) {
            throw new IndexOutOfBoundsException(
                "invalid subarray range: [" + lo + ", " + hi + ")");
        }
        for (int i = lo; i < hi; i++) {
            int r = i + uniform(hi - i);     // between i and hi-1
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
    /**
     * Rearranges the elements of the specified
     *  subarray in uniformly random order.
     *
     * @param  a the array to shuffle
     * @param  lo the left endpoint (inclusive)
     * @param  hi the right endpoint (exclusive)
     * @throws IllegalArgumentException if {@code a} is {@code null}
     * @throws IndexOutOfBoundsException
     *  unless (0 <= lo) && (lo < hi) && (hi <= a.length)}
     */
    public static void shuffle(final double[] a, final int lo, final int hi) {
        if (a == null) {
            throw new IllegalArgumentException("argument array is null");
        }
        if (lo < 0 || lo >= hi || hi > a.length) {
            throw new IndexOutOfBoundsException(
                "invalid subarray range: [" + lo + ", " + hi + ")");
        }
        for (int i = lo; i < hi; i++) {
            int r = i + uniform(hi - i);     // between i and hi-1
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
    /**
     * Rearranges the elements of the specified
     *  subarray in uniformly random order.
     *
     * @param  a the array to shuffle
     * @param  lo the left endpoint (inclusive)
     * @param  hi the right endpoint (exclusive)
     * @throws IllegalArgumentException if {@code a} is {@code null}
     * @throws IndexOutOfBoundsException
     *  unless (0 <= lo) && (lo < hi) && (hi <= a.length)}
     */
    public static void shuffle(final int[] a, final int lo, final int hi) {
        if (a == null) {
            throw new IllegalArgumentException("argument array is null");
        }
        if (lo < 0 || lo >= hi || hi > a.length) {
            throw new IndexOutOfBoundsException(
                "invalid subarray range: [" + lo + ", " + hi + ")");
        }
        for (int i = lo; i < hi; i++) {
            int r = i + uniform(hi - i);     // between i and hi-1
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
    /**
     * Returns a uniformly random permutation of n elements.
     *
     * @param  n number of elements
     * @throws IllegalArgumentException if {@code n} is negative
     * @return an array of length n that is a uniformly random permutation
     *         of 0,1, ..., n-1
     */
    public static int[] permutation(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("argument is negative");
        }
        int[] perm = new int[n];
        for (int i = 0; i < n; i++) {
            perm[i] = i;
        }
        shuffle(perm);
        return perm;
    }
    /**
     * Returns a uniformly random permutation of k of n elements.
     *
     * @param  n number of elements
     * @param  k number of elements to select
     * @throws IllegalArgumentException if {@code n} is negative
     * @throws IllegalArgumentException unless {@code 0 <= k <= n}
     * @return an array of length k that is a uniformly random permutation
     *         of k of the elements from 0, 1, ...,n-1}
     */
    public static int[] permutation(final int n, final int k) {
        if (n < 0) {
            throw new IllegalArgumentException("argument is negative");
        }
        if (k < 0 || k > n) {
            throw new IllegalArgumentException("k must be between 0 and n");
        }
        int[] perm = new int[k];
        for (int i = 0; i < k; i++) {
            int r = uniform(i + 1);    // between 0 and i
            perm[i] = perm[r];
            perm[r] = i;
        }
        for (int i = k; i < n; i++) {
            int r = uniform(i + 1);    // between 0 and i
            if (r < k) {
                perm[r] = i;
            }
        }
        return perm;
    }
}
