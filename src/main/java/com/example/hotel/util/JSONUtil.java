package com.example.hotel.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.key.Jsr310NullKeySerializer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;


/**
 * Json tool util
 */
public class JSONUtil {
   * global Object mapper
    private static volatile ObjectMapper OBJECT_MAPPER;

    private JSONUtil() {
    }

    public static ObjectMapper getInstance() {
        if (OBJECT_MAPPER == null) {
            synchronized (JSONUtil.class) {
                if (OBJECT_MAPPER == null) {
                    OBJECT_MAPPER = new ObjectMapper()
                            .registerModule(new JavaTimeModule())
                            .enable(JsonGenerator.Feature.IGNORE_UNKNOWN)
                            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                            .enable(JsonParser.Feature.IGNORE_UNDEFINED)
                            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    OBJECT_MAPPER.getSerializerProvider().setNullKeySerializer(new Jsr310NullKeySerializer());
                }
            }
        }
        return OBJECT_MAPPER;
    }

    /**
   * transfer to Json
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }

        if (object instanceof Collection) {
            Collection collection = (Collection) object;
            if (collection == null || collection.size() <= 0) {
                return null;
            }
        }

        try {
            return getInstance().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
   * analyse json and transfer to T object
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T extends Object> T parseJson(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return getInstance().readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
   * transfer to list T
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<T> parseListJson(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            JavaType javaType = getCollectionType(ArrayList.class, clazz);
            return (List<T>) getInstance().readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
   *  get Collection Type
     *
     * @param collectionClass
     * @param elementClasses
     * @return
     */
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return getInstance().getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
