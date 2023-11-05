package top.zhangdashuai.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhangdashuai
 */
public class CompareUtil {
    public static class CompareSameTypeUtil {

        public static <T> GroupListData<T> compareAndGroup(List<T> sourceList, List<T> targetList, Function<T, Object> hashGenerator) {
            return compareAndGroup(sourceList, targetList, hashGenerator, hashGenerator, (o1, o2) -> 0);
        }

        /**
         * 比较并分组
         *
         * @param sourceList          源List
         * @param targetList          目标List
         * @param sourceHashGenerator 用作hash比较的hash生成器，他会为每个元素生成hash值
         * @param targetHashGenerator 用作hash比较的hash生成器，他会为每个元素生成hash值
         * @param comparator          用作对象是否修改比较的对象比较器
         * @param <T>                 元素类型
         * @return 分组信息
         */
        public static <T> GroupListData<T> compareAndGroup(List<T> sourceList, List<T> targetList, Function<T, Object> sourceHashGenerator, Function<T, Object> targetHashGenerator, Comparator<T> comparator) {
            Assert.notNull(sourceHashGenerator, "hash生成器不可为null");
            Assert.notNull(targetHashGenerator, "hash生成器不可为null");
            Assert.notNull(comparator, "比较器不可为null");

            GroupListData<T> res = new GroupListData<>();
            if (CollectionUtils.isEmpty(sourceList) && CollectionUtils.isEmpty(targetList)) {
                return res;
            }

            // 默认值
            sourceList = Optional.ofNullable(sourceList).orElse(Collections.emptyList());
            targetList = Optional.ofNullable(targetList).orElse(Collections.emptyList());

            // 清空null元素
            sourceList.removeIf(Objects::isNull);
            targetList.removeIf(Objects::isNull);

            // 构建sourceList以及targetList的 HashMap，如果存在Hash相同的数据，将他们放在同一个key中，value List 的元素是多个
            Map<Object, List<T>> sourceMap = sourceList.stream().collect(Collectors.groupingBy(sourceHashGenerator));
            Map<Object, List<T>> targetMap = targetList.stream().collect(Collectors.groupingBy(targetHashGenerator));
            sourceMap.forEach((sk, svList) -> {
                List<T> tvList = targetMap.getOrDefault(sk, Collections.emptyList());
                int sourceExtraCount = Math.max(svList.size() - tvList.size(), 0);
                int targetExtraCount = Math.max(tvList.size() - svList.size(), 0);

                // 设置源list多的数据
                res.getSourceExtra().addAll(svList.subList(svList.size() - sourceExtraCount, svList.size()));

                // 设置目标list多的数据
                res.getTargetExtra().addAll(tvList.subList(tvList.size() - targetExtraCount, tvList.size()));

                // 源list和目标list共有的几条数据，比较他们是否发生变化
                for (int i = 0; i < Math.min(svList.size(), tvList.size()); i++) {
                    int compare = comparator.compare(svList.get(i), tvList.get(i));
                    if (compare != 0) {
                        res.getTargetChanged().add(new Both<>(svList.get(i), tvList.remove(i)));
                    }
                }
                targetMap.remove(sk);
            });
            targetMap.forEach((tk, tv) -> res.getTargetExtra().addAll(tv));
            return res;
        }
    }

    public static class CompareDifferentTypeUtil {

        public static <S, T> EqualGroupListData<S, T> equalsAndGroup(List<S> sourceList, List<T> targetList, Function<S, Object> sourceHashGenerator, Function<T, Object> targetHashGenerator) {
            return equalsAndGroup(sourceList, targetList, sourceHashGenerator, targetHashGenerator, (o1, o2) -> true);
        }

        /**
         * 比较并分组
         *
         * @param sourceList          源List
         * @param targetList          目标List
         * @param sourceHashGenerator 用作hash比较的hash生成器，他会为每个元素生成hash值，注意，hash值不能是null值
         * @param targetHashGenerator 用作hash比较的hash生成器，他会为每个元素生成hash值，注意，hash值不能是null值
         * @param equator             用作对象是否修改比较的对象比较器
         * @param <T>                 元素类型
         * @return 分组信息
         */
        public static <S, T> EqualGroupListData<S, T> equalsAndGroup(List<S> sourceList, List<T> targetList, Function<S, Object> sourceHashGenerator, Function<T, Object> targetHashGenerator, Equator<S, T> equator) {
            Assert.notNull(sourceHashGenerator, "hash生成器不可为null");
            Assert.notNull(targetHashGenerator, "hash生成器不可为null");
            Assert.notNull(equator, "比较器不可为null");

            EqualGroupListData<S, T> res = new EqualGroupListData<>();
            if (CollectionUtils.isEmpty(sourceList) && CollectionUtils.isEmpty(targetList)) {
                return res;
            }

            // 默认值
            sourceList = Optional.ofNullable(sourceList).orElse(Collections.emptyList());
            targetList = Optional.ofNullable(targetList).orElse(Collections.emptyList());

            // 清空null元素
            sourceList.removeIf(Objects::isNull);
            targetList.removeIf(Objects::isNull);

            // 构建sourceList以及targetList的 HashMap，如果存在Hash相同的数据，将他们放在同一个key中，value List 的元素是多个
            Map<Object, List<S>> sourceMap = sourceList.stream().collect(Collectors.groupingBy(sourceHashGenerator));
            Map<Object, List<T>> targetMap = targetList.stream().collect(Collectors.groupingBy(targetHashGenerator));
            sourceMap.forEach((sk, svList) -> {
                List<T> tvList = targetMap.getOrDefault(sk, Collections.emptyList());
                int sourceExtraCount = Math.max(svList.size() - tvList.size(), 0);
                int targetExtraCount = Math.max(tvList.size() - svList.size(), 0);

                // 设置源list多的数据
                res.getSourceExtra().addAll(svList.subList(svList.size() - sourceExtraCount, svList.size()));

                // 设置目标list多的数据
                res.getTargetExtra().addAll(tvList.subList(tvList.size() - targetExtraCount, tvList.size()));

                // 源list和目标list共有的几条数据，比较他们是否发生变化
                for (int i = 0; i < Math.min(svList.size(), tvList.size()); i++) {
                    boolean isEqual = equator.equals(svList.get(i), tvList.get(i));
                    if (!isEqual) {
                        res.getTargetChanged().add(new Both<>(svList.get(i), tvList.remove(i)));
                    }
                }
                targetMap.remove(sk);
            });
            targetMap.forEach((tk, tv) -> res.getTargetExtra().addAll(tv));
            return res;
        }
    }

    @Data
    @AllArgsConstructor
    public static class Both<S, T> {

        private S source;

        private T target;
    }

    @Data
    public static class EqualGroupListData<S, T> {
        /**
         * source多出的数据
         */
        private List<S> sourceExtra = new ArrayList<>();

        /**
         * target多出的数据
         */
        private List<T> targetExtra = new ArrayList<>();

        /**
         * source和target都有，但是target中有变动的数据
         */
        private List<Both<S,T>> targetChanged = new ArrayList<>();
    }

    @Data
    public static class GroupListData<T> {
        /**
         * source多出的数据
         */
        private List<T> sourceExtra = new ArrayList<>();

        /**
         * target多出的数据
         */
        private List<T> targetExtra = new ArrayList<>();

        /**
         * source和target都有，但是target中有变动的数据
         */
        private List<Both<T, T>> targetChanged = new ArrayList<>();

    }
}

