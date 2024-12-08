package com.example.hotel.dto.request.Base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.pool2.BaseObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Request extends BaseObject {

  private static final long serialVersionUID = -824258042332428833L;
}
