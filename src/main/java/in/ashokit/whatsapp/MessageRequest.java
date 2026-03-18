package in.ashokit.whatsapp;

import lombok.Data;

@Data
public class MessageRequest {

    private String from;
    private String to;
    private Content content;
}
