package Semestrovka_1;

public class Counter {
    private long count = 0;
    public void inc() { count++; }
    public void inc(long delta) { count += delta; }
    public long get() { return count; }
    public void reset() { count = 0; }
}