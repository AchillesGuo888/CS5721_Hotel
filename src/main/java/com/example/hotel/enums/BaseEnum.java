package com.example.hotel.enums;


public interface BaseEnum<K> {

  /**
   * @return
   */
  K getCode();

  /**
   * @return
   */
  String getDesc();

  /**
   * @return
   */
  String name();


}
