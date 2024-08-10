package util;

public class Pair<T> {
    private T first;
    private T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T first() {
        return first;
    }

    public void first(T first) {
        this.first = first;
    }

    public T second() {
        return second;
    }

    public void second(T second) {
        this.second = second;
    }
}
