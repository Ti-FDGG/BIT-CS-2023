package com.jinxuliang;

import com.google.gson.Gson;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ChatMessage {
    private String userName;
    private String message;

    private static Gson gson = new Gson();

    public static String toJson(ChatMessage message) {
        return gson.toJson(message);
    }

    public static ChatMessage fromJson(String json) {
        ChatMessage message = gson.fromJson(json, ChatMessage.class);
        return message;
    }
}
