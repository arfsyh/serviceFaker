package com.faker.servicefaker.controller;

import com.faker.servicefaker.dto.*;
import com.faker.servicefaker.service.NotificationService;
import com.faker.servicefaker.service.SubscriberService;
import com.faker.servicefaker.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
    private RestTemplate restTemplate;

    private SubscriptionService subscriptionService;
    private NotificationService notificationService;
    private SubscriberService subscriberService;

    @PostMapping
    public ResponseEntity<PurchaseDto> purchase(@RequestHeader("env") String env,
                                                @RequestParam("msisdn") String msisdn,
                                                @RequestParam("serviceId") String serviceId){
        HttpHeaders headers = new HttpHeaders();
        headers.set("msisdn", msisdn);
        headers.set("env", env);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<SubscriberStatusDto> ss = restTemplate.exchange(
                    "http://localhost:8080/api/subscriber/status",
                    HttpMethod.GET,
                    entity,
                    SubscriberStatusDto.class
            );

            // Memeriksa status subscriber
            if (ss.getStatusCode() == HttpStatus.OK && ss.getBody().getStatus().equals("ACTIVE")) {
                try{

                    ResponseEntity<SubscriberBalanceDto> sb = restTemplate.exchange(
                            "http://localhost:8080/api/subscriber/balance",
                            HttpMethod.GET,
                            entity,
                            SubscriberBalanceDto.class
                    );
                    HttpHeaders header = new HttpHeaders();
                    header.set("env", env);
                    HttpEntity<String> e = new HttpEntity<>(header);

                    String packageUrl = "http://localhost:8080/api/package/" + serviceId;

                    ResponseEntity<PackageDto> response = restTemplate.exchange(packageUrl, HttpMethod.GET, e, PackageDto.class);

                    PackageDto pd = response.getBody();

                    if (sb.getStatusCode() == HttpStatus.OK && sb.getBody().getBalance() > pd.getPrice() && sb.getBody().getSubscriberType().equals(pd.getPackageType())){
                        LocalDateTime currentDate = LocalDateTime.now();

                        SubscriptionDto subs = new SubscriptionDto();
                        subs.setMsisdn(msisdn);
                        subs.setServiceId(serviceId);
                        subs.setPackageName(pd.getPackageName());
                        subs.setBuyDate(currentDate.toString().substring(0,19));
                        subscriptionService.createSubs(subs, env);

                        NotificationDto n = new NotificationDto();
                        n.setMsisdn(msisdn);
                        n.setStatus("SUCCESS");
                        n.setMessage("Success Purchase Package " + pd.getPackageName());
                        notificationService.createNotification(n, env);

                        Long balance = sb.getBody().getBalance() - pd.getPrice();
                        SubscriberBalanceDto newB = new SubscriberBalanceDto();
                        newB.setMsisdn(msisdn);
                        newB.setBalance(balance);
                        subscriberService.updateBalance(msisdn,newB,env );

                    }else if(sb.getBody().getBalance() < pd.getPrice()){
                        NotificationDto n = new NotificationDto();
                        n.setMsisdn(msisdn);
                        n.setStatus("Failed");
                        n.setMessage("cannot purchase package because insufficient balance");
                        notificationService.createNotification(n, env);
                        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                .body(new PurchaseDto("failed", "cannot purchase package because insufficient balance "));
                    }
                    else {
                        NotificationDto n = new NotificationDto();
                        n.setMsisdn(msisdn);
                        n.setStatus("Failed");
                        n.setMessage("The Subscriber type "+ sb.getBody().getSubscriberType() + " cannot purchase a package of type " + pd.getPackageType() +".");
                        notificationService.createNotification(n, env);
                        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                .body(new PurchaseDto("failed", "The Subscriber type "+ sb.getBody().getSubscriberType() + " cannot purchase a package of type " + pd.getPackageType() +"."));
                    }

                } catch (Exception e){

                    NotificationDto n = new NotificationDto();
                    n.setMsisdn(msisdn);
                    n.setStatus("Failed");
                    n.setMessage("An error occurred while calling the endpoint.");
                    notificationService.createNotification(n, env);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new PurchaseDto("failed", "An error occurred while calling the endpoint."));
                }

            }else {

                NotificationDto n = new NotificationDto();
                n.setMsisdn(msisdn);
                n.setStatus("Failed");
                n.setMessage("cannot purchase package because subscriber not active");
                notificationService.createNotification(n, env);
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new PurchaseDto("failed", "cannot purchase package because subscriber not active"));
            }
        } catch (Exception e){

            NotificationDto n = new NotificationDto();
            n.setMsisdn(msisdn);
            n.setStatus("Failed");
            n.setMessage("An error occurred while calling the endpoint.");
            notificationService.createNotification(n, env);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PurchaseDto("failed", "An error occurred while calling the endpoint."));
        }


        PurchaseDto purchase = new PurchaseDto();
        purchase.setStatus("SUCCESS");
        purchase.setMessage("SUCCESS PURCHASE PACKAGE");

        return ResponseEntity.ok(purchase);
    }


}
