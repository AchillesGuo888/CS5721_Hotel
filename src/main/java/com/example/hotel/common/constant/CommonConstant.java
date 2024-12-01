package com.example.hotel.common.constant;

import java.math.BigDecimal;

/**
 * 常量
 *
 * @author 言曌
 */
public interface CommonConstant {

  /**
   * 正常状态
   */
  Integer STATUS_NORMAL = 0;

  /**
   * 用户密码加盐的盐
   */
  String PASSWORD_SALT = "sens";

  /**
   * none
   */
  String NONE = "none";

  /**
   * email signal
   */
  public static final String EMAIL = "@";

  public static final byte COPPER = 0;
  public static final byte SILVER = 1;
  public static final byte GOLD = 2;
  public static final byte DIAMOND = 3;

  public final static BigDecimal COPPER_DISCOUNT = new BigDecimal(0.9);

  public final static BigDecimal SILVER_DISCOUNT = new BigDecimal(0.88);

  public final static BigDecimal GOLD_DISCOUNT = new BigDecimal(0.85);

  public final static BigDecimal DIAMOND_DISCOUNT = new BigDecimal(0.8);

  public final static BigDecimal COPPER_POINTS_RULE = new BigDecimal(0.15);

  public final static BigDecimal SILVER_POINTS_RULE = new BigDecimal(0.20);

  public final static BigDecimal GOLD_POINTS_RULE = new BigDecimal(0.25);

  public final static BigDecimal DIAMOND_POINTS_RULE = new BigDecimal(0.30);

  public final static Integer SILVER_MEMBER_POINTS = 1000;

  public final static Integer GOLD_MEMBER_POINTS = 5000;

  public final static Integer DIAMOND_MEMBER_POINTS = 10000;

//    public final static BigDecimal EXPENSE_POINTS_RULE = new BigDecimal(0.01);

//    public final static BigDecimal EXPENSE_POINTS_LIMIT = new BigDecimal(0.1);
}
