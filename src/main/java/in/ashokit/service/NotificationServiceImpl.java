package in.ashokit.service;

import in.ashokit.dto.OrderDto;
import in.ashokit.entity.OrderEntity;
import in.ashokit.entity.OrderItemsEntity;
import in.ashokit.entity.UserEntity;
import in.ashokit.repo.OrderItemsRepository;
import in.ashokit.repo.OrderRepository;
import in.ashokit.repo.UserRepository;
import in.ashokit.whatsapp.Content;
import in.ashokit.whatsapp.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private OrderItemsRepository itemsRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private WhatsappService whatsappService;

    @Value("{doubletick.api.key}")
    private String apiKey;

    @Value("{doubletick.api.url}")
    private String apiUrl;

    @Value("{kafka.topic.name}")
    private String topicName;

    @Override
    @KafkaListener(topics = topicName, groupId = "ashokit-order-group")
    public OrderDto sendOrderConfirmation(OrderDto orderDto) {
        Optional<OrderEntity> byId = orderRepo.findById(orderDto.getOrderId());
        if (byId.isPresent()) {
            OrderEntity orderEntity = byId.get();
            List<OrderItemsEntity> orderItems = itemsRepo.findByOrderOrderId(orderEntity.getOrderId());
            UserEntity user = orderEntity.getUser();
            sendOrderConfirmationEmail(orderEntity, orderItems, user);
            sendWhatsAppMsg(user, "order-confirmation-template");
        }
    }

    @Override
    @KafkaListener(topics = topicName, groupId = "ashokit-order-group")
    public List<OrderDto> sendDeliveryNotification() {
        List<OrderEntity> ordersList = orderRepo.findByDeliveryDate(LocalDate.now());

        ordersList.forEach(o -> {
            List<OrderItemsEntity> orderItems = itemsRepo.findByOrderOrderId(orderEntity.getOrderId());
            UserEntity user = o.getUser();
            sendDeliveryNotificationEmail(o, orderItems, user);
            sendWhatsAppMsg(user, "order-delivered-template");
        });
    }

    @Override
    @KafkaListener(topics = topicName, groupId = "ashokit-order-group")
    public OrderDto cancelledOrderNotification(OrderDto orderDto) {
        Optional<OrderEntity> byId = orderRepo.findById(orderDto.getOrderId());
        if (byId.isPresent()) {
            OrderEntity orderEntity = byId.get();
            UserEntity user = orderEntity.getUser();
            sendCancelledOrderNotificationEmail(orderEntity, user);
            sendWhatsAppMsg(user, "order-cancelled-template");
        }
    }


    @Override
    public void sendOfferNotification() {
        List<UserEntity> all = userRepo.findAll();
        all.forEach(user - > {
                sendOfferNotificationEmail(user);
        })
    }

    private void sendOrderConfirmationEmail(OrderEntity orderEntity, OrderItemsEntity itemsEntities, UserEntity userEntity) {
        // Read File data and replace placeholders with dynamic values

        Path path = Paths.get("order-confirmation-mail-template.html");
        String content = Files.lines(path).collect(Collectors.joining("\n"));

        content = content.replace("{{Customer_Name}}", userEntity.getName());
        content = content.replace("{{Order_Tracking_Num}}", orderEntity.getOrderTrackingNum());

        String subject = "Order Confirmation Notification";

        emailService.sendEmail(userEntity.getEmail(), subject, content);
    }


    private void sendDeliveryNotificationEmail(OrderEntity orderEntity, OrderItemsEntity itemsEntities, UserEntity userEntity) {
        // Read File data and replace placeholders with dynamic values

        Path path = Paths.get("delivery-notification-email-template.html");
        String content = Files.lines(path).collect(Collectors.joining("\n"));
        content = content.replace("{{Customer_Name}}", userEntity.getName());
        content = content.replace("{{Order_Tracking_Num}}", orderEntity.getOrderTrackingNum());

        String subject = "Order Delivery Notification";

        emailService.sendEmail(userEntity.getEmail(), subject, content);
    }

    private void sendCancelledOrderNotificationEmail(OrderEntity orderEntity, UserEntity userEntity) {
        // Read File data and replace placeholders with dynamic values

        Path path = Paths.get("order-cancel-mail-template.html");
        String content = Files.lines(path).collect(Collectors.joining("\n"));

        content = content.replace("{{Customer_Name}}", userEntity.getName());
        content = content.replace("{{Order_Tracking_Num}}", orderEntity.getOrderTrackingNum());

        String subject = "Order Cancelled Notification";

        emailService.sendEmail(userEntity.getEmail(), subject, content);
    }

    private void sendOfferNotificationEmail(UserEntity userEntity) {
        // Read File data and replace placeholders with dynamic values

        Path path = Paths.get("offers-email-template.html");
        String content = Files.lines(path).collect(Collectors.joining("\n"));

        content = content.replace("{{Customer_Name}}", userEntity.getName());

        String subject = "Big Day Sale | Offers Available";

        emailService.sendEmail(userEntity.getEmail(), subject, content);
    }

    public void sendWhatsAppMsg(UserEntity user, String templateName) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Content content = new Content();
        content.setLanguage("en");
        content.setTemplateName(templateName);

        MessageRequest requestBody = new MessageRequest();
        requestBody.setTo(user.getPhno());
        requestBody.setFrom("+919985296677");
        requestBody.setContent(content);

        HttpEntity<MessageRequest> reqEntity = new HttpEntity(requestBody, headers);

        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> responseEntity = rt.exchange(apiUrl, HttpMethod.POST, reqEntity, String.class);
        String body = responseEntity.getBody();
        System.out.println(body);

    }

}
























