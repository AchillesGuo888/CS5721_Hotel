package com.example.hotel.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;


@Component
public class FreeMarkerTemplateUtil {

  @Autowired
  private FreeMarkerConfigurer freeMarkerConfigurer;

  public String getEmailHtml(Map map, String templateName) {

    String htmlText = "";
    Configuration configuration = new Configuration(Configuration.getVersion());
    try {
      configuration.setDefaultEncoding("utf-8");
      Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
      //render template as html
      htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return htmlText;
  }
}
