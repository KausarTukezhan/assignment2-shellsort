package util;

import java.util.Random;

public class ArrayGenerator {
    public enum Pattern { RANDOM, SORTED, REVERSED, NEARLY_SORTED }

    private final Random rnd;
    public ArrayGenerator() { this(System.nanoTime()); }
    public ArrayGenerator(long seed) { rnd = new Random(seed); }

    public int[] generate(int n, Pattern p) {
        int[] a = new int[n];
        switch (p) {
            case SORTED -> { for (int i = 0; i < n; i++) a[i] = i; }
            case REVERSED -> { for (int i = 0; i < n; i++) a[i] = n - i; }
            case NEARLY_SORTED -> {
                for (int i = 0; i < n; i++) a[i] = i;
                if (n > 1) { int i = n/2, j = Math.min(n-1, i+1); int t=a[i]; a[i]=a[j]; a[j]=t; }
            }
            default -> { for (int i = 0; i < n; i++) a[i] = rnd.nextInt(1_000_000); }
        }
        return a;
    }
}
