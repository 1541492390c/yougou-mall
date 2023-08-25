package per.ccm.ygmall.common.basic.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConvertUtils {

    /**
     * 复制列表
     * */
    public static <T> List<T> converList(Collection<?> sourceList, Class<T> target) {
        try {
            if (ObjectUtils.isEmpty(sourceList)) {
                return new ArrayList<>();
            }
            List<T> targetList = new ArrayList<>(sourceList.size());

            for (Object source : sourceList) {
                T targetObject = target.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(source, targetObject);
                targetList.add(targetObject);
            }
            return targetList;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("ConvertUtils is Exception -> " + e.getMessage());
        }
    }

    /**
     * 复制属性值
     * */
    public static <T> T convertProperties(Object source, Class<T> target) {
        try {
            if (ObjectUtils.isEmpty(source)) {
                throw new RuntimeException("ConvertUtils is Exception -> source is empty");
            }
            T targetObject = target.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, targetObject);

            return targetObject;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("ConvertUtils is Exception -> " + e.getMessage());
        }
    }
}
