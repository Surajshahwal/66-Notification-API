package in.ashokit.rest;

import in.ashokit.dto.ApiResponse;
import in.ashokit.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/notification")
public class NotificationRestController {

    @Autowired
    private NotificationService notificationService;
    //task 321
    public void m2(){
        String s="raju";
        String name="raj";
        int i=10;
    }

    @GetMapping("/offers-notification")
    public ResponseEntity<ApiResponse<String>> sendOfferNotification(){

        notificationService.sendOfferNotification();

        ApiResponse<String> response = new ApiResponse();

        response.setMessage("Notification sent successfully");
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
//task 320
    public  String m1(){
        int a =10;
        int b=20;
        int sum=i+j;
        char='r';
    }
    //325
    public  String m3(){
        //logic
    }

}
