package com.example.hotel.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang3.StringUtils;


/**
 * Json工具类
 */
public class JSONUtil {

  /**
   * 启动全局Object mapper
   */
  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  // public static void main(String[] args) {
  // System.out.println(createCacheKey(null));
  // }


  /**
   * 转换为Json
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
      return OBJECT_MAPPER.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 解析json成T对象信息
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
      return OBJECT_MAPPER.readValue(json, clazz);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 解析成list T
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
      return (List<T>) OBJECT_MAPPER.readValue(json, javaType);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 获取 get Collection Type
   *
   * @param collectionClass
   * @param elementClasses
   * @return
   */
  private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
    return OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
  }
}
