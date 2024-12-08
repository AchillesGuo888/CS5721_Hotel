package com.example.hotel.service.impl.factory;

import com.example.hotel.common.constant.CommonConstant;
import com.example.hotel.service.impl.price.CopperPriceServiceImpl;
import com.example.hotel.service.impl.price.DiamondPriceServiceImpl;
import com.example.hotel.service.impl.price.GoldPriceServiceImpl;
import com.example.hotel.service.impl.price.SilverPriceServiceImpl;
import com.example.hotel.service.price.PriceCalculationService;

public class PriceFactory {

  public static PriceCalculationService getService(Byte membership) {
    switch (membership) {
      case CommonConstant.COPPER:
        return new CopperPriceServiceImpl();
      case CommonConstant.SILVER:
        return new SilverPriceServiceImpl();
      case CommonConstant.GOLD:
        return new GoldPriceServiceImpl();
      case CommonConstant.DIAMOND:
        return new DiamondPriceServiceImpl();
      default:
        return new CopperPriceServiceImpl();
    }
  }
}
