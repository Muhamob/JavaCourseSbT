package ai.muhamob.week4.src;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CollectionUtils {
    public static<T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static<T> List<T> newArrayLit() {
        return new ArrayList<T>();
    }

    public static<T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static<T> List<? extends T> limit(List<? extends T> source, int size) {
        return source.subList(0, size);
    }

    public static<T> void add(List<? super T> destination, T o) {
        destination.add(o);
    }

    public static<T> void removeAll(List<? super T> removeFrom, List<T> c2) {
        removeFrom.removeAll(c2);
    }

    public static<T> boolean containsAny(List<? super T> c1, List<T> c2) {
        for (T t : c2) {
            if (c1.contains(t)) {
                return true;
            }
        }
        return false;
    }

    public static<T> boolean containsAll(List<? extends T> c1, List<T> c2) {
        for (T t : c2) {
            if (!c1.contains(t)) {
                return false;
            }
        }
        return true;
    }

    public static<T extends Comparable<T>> List range(List<? extends T> list,
                                           T min, T max) {
        List<? super T> dest = new ArrayList<>();
        for (T t : list) {
            if (t.compareTo(min) > 0 && t.compareTo(max) < 0) {
                dest.add(t);
            }
        }
        return dest;
    }

    public static<T> List<? super T> range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        List<? super T> dest = new ArrayList<>();
        for (T t : list) {
            if (comparator.compare(t, min) > 0 && comparator.compare(t, max) < 0) {
                dest.add(t);
            }
        }
        return dest;
    }
 }
