package per.ccm.ygmall.database.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.bo.BaseBO;
import per.ccm.ygmall.common.dto.BaseDTO;
import per.ccm.ygmall.common.exception.ServerException;
import per.ccm.ygmall.common.vo.BaseVO;
import per.ccm.ygmall.database.entity.BaseEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConvertUtils {

    /**
     * 将DTO转换为实体类
     *
     * @param dto DTO
     * @param entityClass 实体类的类型
     * @return E 转换后的实体类
     * */
    public static <D extends BaseDTO, E extends BaseEntity> E dtoConvertToEntity(D dto, Class<E> entityClass) {
        return ConvertUtils.convert(dto, entityClass);
    }

    /**
     * 将DTO转换为BO
     *
     * @param dto DTO
     * @param boClass BO的类型
     * @return BO 转换后的BO
     * */
    public static <D extends BaseDTO, B extends BaseBO> B dtoConvertToBO(D dto, Class<B> boClass) {
        return ConvertUtils.convert(dto, boClass);
    }

    /**
     * 将BO转换为DTO
     *
     * @param bo BO
     * @param dtoClass DTO
     * @return E 转换后的实体类
     * */
    public static <B extends BaseBO, D extends BaseDTO> D boToDTO(B bo, Class<D> dtoClass) {
        return ConvertUtils.convert(bo, dtoClass);
    }

    /**
     * 将实体类转换为BO
     *
     * @param entity 实体类
     * @param boClass VO的类型
     * @return B 转换后的BO
     * */
    public static <E extends BaseEntity, B extends BaseBO> B entityConvertToBO(E entity, Class<B> boClass) {
        return ConvertUtils.convert(entity, boClass);
    }

    /**
     * 将实体类转换为VO
     *
     * @param entity 实体类
     * @param voClass VO的类型
     * @return V 转换后的VO
     * */
    public static <E extends BaseEntity, V extends BaseVO> V entityConvertToVO(E entity, Class<V> voClass) {
        return ConvertUtils.convert(entity, voClass);
    }

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
            throw new ServerException("ConvertUtils is Exception -> " + e.getMessage());
        }
    }

    private static <T> T convert(Object source, Class<T> target) {
        try {
            if (ObjectUtils.isEmpty(source)) {
                return null;
            }
            T targetObject = target.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, targetObject);

            return targetObject;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ServerException("ConvertUtils is Exception -> " + e.getMessage());
        }
    }
}
