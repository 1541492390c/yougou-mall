package per.ccm.ygmall.common.basic.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import per.ccm.ygmall.common.basic.exception.YougouException;
import per.ccm.ygmall.common.basic.response.ResponseCodeEnum;

@Slf4j
public class JSONUtils {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("JSONUtils readValue Exception -> " + e.getMessage());
            throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00001);
        }
    }

    public static <T> T readValue(String json, TypeReference<T> valueTypeRef) {
        try {
            return objectMapper.readValue(json, valueTypeRef);
        } catch (JsonProcessingException e) {
            log.error("JSONUtils readValue Exception -> " + e.getMessage());
            throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00001);
        }
    }

    public static <T> String writeValueAsString(T value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("JSONUtils writeValueAsString Exception -> " + e.getMessage());
            throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00001);
        }
    }
}
