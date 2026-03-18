package in.ashokit.whatsapp;

import lombok.Data;

@Data
public class MessageResponse {

    private String status;
    private String recipient;
    private String messageId;
}
