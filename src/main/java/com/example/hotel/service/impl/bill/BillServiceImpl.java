package com.example.hotel.service.impl.bill;

import cn.hutool.core.util.ObjectUtil;
import com.example.hotel.common.base.ResponseCode;
import com.example.hotel.dto.request.PayBillRequestDTO;
import com.example.hotel.dto.response.PaymentResponse;
import com.example.hotel.entity.PaymentInfo;
import com.example.hotel.exception.BizException;
import com.example.hotel.exception.NoRollbackException;
import com.example.hotel.mapper.PaymentInfoMapper;
import com.example.hotel.service.bill.BillService;
import com.example.hotel.util.JSONUtil;
import com.example.hotel.util.JwtUtil;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

  private final JwtUtil jwtUtil;

  @Value("${aib.api-url}")
  private String apiUrl;

  @Value("${aib.merchant-id}")
  private String merchantId;

  @Value("${aib.api-key}")
  private String apiKey;

  @Value("${aib.payment-method}")
  private String paymentMethod;

  private final RestTemplate restTemplate;

  private final PaymentInfoMapper paymentInfoMapper;


  @Override
  @Transactional(rollbackFor = Exception.class, noRollbackFor = NoRollbackException.class)
  public void payBill(String token, PayBillRequestDTO requestDTO) throws BizException {
    if (ObjectUtil.isNull(requestDTO) || requestDTO.getAmount().doubleValue() <= 0) {
      throw new BizException(ResponseCode.param_error);
    }
    String userId = jwtUtil.getUserIdFromToken(token);
    //mock call bank interface
    PaymentResponse callBankResult = callBank(requestDTO);
    if (ObjectUtil.isNull(callBankResult) || callBankResult.getStatus().equals("failed")) {
      throw new BizException(ResponseCode.pay_error);
    }
    //pay successfully, create a payment record in database
    insertNewPayment(requestDTO, userId);
  }

  private void insertNewPayment(PayBillRequestDTO requestDTO, String userId) {
    PaymentInfo paymentInfo = new PaymentInfo();
    BeanUtils.copyProperties(requestDTO, paymentInfo);
    paymentInfo.setOperator(userId);
    paymentInfoMapper.insertSelective(paymentInfo);
  }

  /**
   * mock bank payment
   *
   * @param requestDTO
   * @return
   */
  private PaymentResponse callBank(PayBillRequestDTO requestDTO) {

    // construct payment structure
    HttpHeaders headers = new HttpHeaders();
    headers.set("API-Key", apiKey);  // api key
    headers.set("Content-Type", "application/json");

    // request body
    String requestJson = "{\"merchantId\":\"" + merchantId + "\", " +
        "\"orderId\":\"" + requestDTO.getOrderId() + "\", " +
        "\"amount\":\"" + requestDTO.getAmount() + "\", " +
        "\"currency\":\"" + requestDTO.getCurrency() + "\", " +
        "\"paymentMethod\":\"" + paymentMethod + "\"}";

    HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

    // send POST requirement AIB payment API
    PaymentResponse paymentResponse = new PaymentResponse();
    try {
      ResponseEntity<String> response = restTemplate
          .exchange(apiUrl, HttpMethod.POST, entity, String.class);

      // get return result
      String responseBody = response.getBody();
      paymentResponse = JSONUtil.parseJson(responseBody, PaymentResponse.class);
    } catch (Exception e) {
      //use fake api key, can not get result from aib API
    } finally {
      //if we can call aib api successful, we do not need set value, it's a mock interface
      mockResult(paymentResponse, requestDTO);
    }
    /**
     //     * {
     //     *   "status": "successful",  // payment status，contains "successful", "failed"
     //     *   "transactionId": "TXN123456789",  // trade ID
     //     *   "amount": "100.00",
     //     *   "currency": "EUR",
     //     *   "message": "Payment successful"
     //     * }
     //     */

    return paymentResponse;
  }

  /**
   * //   * can't real call the bank api, need to create mock result //   * @param paymentResponse
   * //   * @param requestDTO //
   */
  private void mockResult(PaymentResponse paymentResponse,
      PayBillRequestDTO requestDTO) {
    paymentResponse.setAmount(requestDTO.getAmount().toString());
    paymentResponse.setCurrency(requestDTO.getCurrency());
    paymentResponse.setTransactionId("TXN" + ThreadLocalRandom.current().nextInt(9));
    //Randomly generate payment results
    int randomValue = ThreadLocalRandom.current().nextInt(2);
    if (randomValue > 0) {
      paymentResponse.setMessage("Payment successful");
      paymentResponse.setStatus("successful");
    } else {
      paymentResponse.setMessage("Insufficient account balance or incorrect information");
      paymentResponse.setStatus("failed");
    }
  }
}
