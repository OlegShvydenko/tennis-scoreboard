package util;

// TODO либо называй класс так, чтобы было сразу понятно, что он делает либо хранит, либо параметризуй его, чтобы можно было класть любые типы
// TODO класс простой поэтому можно попробовать использовать record
public class Pair {
    private int first;
    private int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
    public Pair(){
        this.first = 0;
        this.second = 0;
    }

    public int first() {
        return first;
    }

    public void first(int first) {
        this.first = first;
    }

    public int second() {
        return second;
    }

    public void second(int second) {
        this.second = second;
    }
}
