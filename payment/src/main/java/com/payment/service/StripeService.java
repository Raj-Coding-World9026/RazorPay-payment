package com.payment.service;


import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {
    @Value("${stripe.api.key}")
    private String stripeSecretKey;
    @PostConstruct
    public void init(){
        Stripe.apiKey=stripeSecretKey;
    }
    public String createToken(String cardNumber,String expMonth,String expYear,String cvc){
        return "pm_card_visa";
    }
}
