package per.ccm.ygmall.common.basic.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.basic.exception.YougouException;
import per.ccm.ygmall.common.basic.response.ResponseCodeEnum;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
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
            log.error("ConvertUtils is Exception -> " + e.getMessage());
            throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00001);
        }
    }

    /**
     * 复制属性值
     * */
    public static <T> T convertProperties(Object source, Class<T> target) {
        try {
            if (ObjectUtils.isEmpty(source)) {
                log.error("ConvertUtils is Exception -> source is empty");
                throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00001);
            }
            T targetObject = target.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, targetObject);

            return targetObject;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error("ConvertUtils is Exception -> " + e.getMessage());
            throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00001);
        }
    }
}
