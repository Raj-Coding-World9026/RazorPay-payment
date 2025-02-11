package com.payment.payload;

import lombok.Data;

@Data
public class PaymentRequest {
    private  Long amount;
    private String currency;
    private String tokenId;
}
