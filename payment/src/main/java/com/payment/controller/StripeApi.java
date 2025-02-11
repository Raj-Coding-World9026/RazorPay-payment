package com.payment.controller;

import com.payment.payload.PaymentRequest;
import com.payment.payload.StripeTokenDto;
import com.payment.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor
public class StripeApi {
    private StripeService stripeService;
    @PostMapping("/card/token")
    public String createCardToken(@RequestBody StripeTokenDto model){
        return stripeService.createToken(model.getCardNumber(),model.getExpMonth(), model.getExpYear(), model.getCvc());
    }

    @PostMapping("/charge")

    public String charge(@RequestBody PaymentRequest paymentRequest ) throws StripeException{
        PaymentIntentCreateParams params=
                PaymentIntentCreateParams.builder()
                        .setAmount(paymentRequest.getAmount())
                        .setCurrency(paymentRequest.getCurrency())
                        .setPaymentMethod(paymentRequest.getTokenId())
                        .build();
        PaymentIntent paymentIntent= PaymentIntent.create(params);
        System.out.println(paymentIntent);
        return  paymentIntent.getStatus();
    }

}









//@RestController
//@RequestMapping("/api/payment")
//public class PaymentController {
//
//    @Value("${stripe.api.key}")
//    private String stripeSecretKey;
//
//    @PostMapping("/create-payment-intent")
//    public String createPaymentIntent(@RequestParam Integer amount){
//        Stripe.apiKey=stripeSecretKey;
//        try{
//            PaymentIntent intent= PaymentIntent.create(
//                    new PaymentIntentCreateParams.Builder()
//                            .setCurrency("usd")
//                            .setAmount((long) amount*100).build()
//            );
//            return generateResponse(intent.getClientSecret());
//
//        }
//        catch (StripeException e){
//            return generateResponse("Error creating PaymentIntent :"+e.getMessage());
//        }
//    }
//    private String generateResponse(String clientSecret){
//        return "{\"clientSecret\":\""+clientSecret + "\"}";
//    }
//}
