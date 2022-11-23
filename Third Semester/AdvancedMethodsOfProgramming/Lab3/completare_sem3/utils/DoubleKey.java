package socialnetwork.utils;

import java.util.Objects;

public class DoubleKey<KEY> {

    private final KEY firstKey;

    private final KEY secondKey;

    public DoubleKey(KEY firstKey, KEY secondKey) {
        this.firstKey = firstKey;
        this.secondKey = secondKey;
    }

    public KEY getFirstKey() {
        return firstKey;
    }

    public KEY getSecondKey() {
        return secondKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleKey<?> doubleKey = (DoubleKey<?>) o;
        return Objects.equals(firstKey, doubleKey.firstKey) && Objects.equals(secondKey, doubleKey.secondKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstKey, secondKey);
    }
}
