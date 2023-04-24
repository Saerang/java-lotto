package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinNumbers {
    private final List<Number> numbers;

    public WinNumbers(int... numbers) {
        this.numbers = Arrays.stream(numbers).mapToObj(Number::new).sorted().collect(Collectors.toList());
    }

    public WinType confirmWin(Lotto lotto) {
        long count = lotto.match(this.numbers);
        return WinType.find(count);
    }
}
