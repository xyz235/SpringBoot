package top.zhangdashuai.utils;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author zhangdashuai
 */
@FunctionalInterface
public interface Equator<S, T> {

    boolean equals(S o1, T o2);

    static <S, T, SU, TU> Equator<S, T> equaling(Function<? super S, ? extends SU> sKeyExtractor, Function<? super T, ? extends TU> tKeyExtractor) {
        Objects.requireNonNull(sKeyExtractor);
        Objects.requireNonNull(tKeyExtractor);
        return (o1, o2) -> Objects.equals(sKeyExtractor.apply(o1), tKeyExtractor.apply(o2));
    }

    static <S, T, SU, TU> Equator<S, T> equaling(Function<? super S, ? extends SU> sKeyExtractor, Function<? super T, ? extends TU> tKeyExtractor, Equator<? super SU, ? super TU> keyComparator) {
        Objects.requireNonNull(sKeyExtractor);
        Objects.requireNonNull(tKeyExtractor);
        Objects.requireNonNull(keyComparator);
        return (o1, o2) -> keyComparator.equals(sKeyExtractor.apply(o1), tKeyExtractor.apply(o2));
    }

    default Equator<S, T> thenEqualing(Equator<? super S, ? super T> other) {
        return (c1, c2) -> !equals(c1, c2) && other.equals(c1, c2);
    }
}
