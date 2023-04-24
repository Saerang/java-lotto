package lotto.domain;

import java.util.Objects;

public class Number implements Comparable<Number> {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;
    private final int number;

    public Number(int number) {
        if (this.validate(number)) {
            throw new IllegalArgumentException("숫자는 1~45사이만 가능합니다.");
        }
        this.number = number;
    }

    private boolean validate(int number) {
        return number < MIN_NUMBER || number > MAX_NUMBER;
    }

    public int number() {
        return number;
    }

    @Override
    public int compareTo(Number o) {
        return this.number - o.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number1 = (Number) o;
        return number == number1.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Number{" +
                "number=" + number +
                '}';
    }
}
