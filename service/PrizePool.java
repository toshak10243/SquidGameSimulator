package service;

public class PrizePool {
    private static long totalPrize = 0;
    private static final long PRIZE_PER_PLAYER = 10_000_000; // ₹1 Crore

    public static void reset() {
        totalPrize = 0;
    }

    public static void addEliminations(int count) {
        totalPrize += (PRIZE_PER_PLAYER * count);
    }

    public static long getTotalPrize() {
        return totalPrize;
    }

    public static String getFormattedPrize() {
        return "₹" + String.format("%,d", totalPrize);
    }
}
