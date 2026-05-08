package org.ai.niegeai.controller.day12;

import org.springframework.ai.chat.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiDay12Controller {

    private final ChatClient chatClient;

    public AiDay12Controller(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/ai/dashscope")
    public String dashscope(@RequestParam(defaultValue = "你好") String msg) {
        return chatClient.call(msg);
    }

    /**
     * 通用智能问答（可自由切换底层模型）
     */
    @GetMapping("/ai/chat")
    public String chat(@RequestParam String type, @RequestParam String msg) {
        if ("dashscope".equals(type)) {
            return chatClient.call(msg);
        } else {
            return "不支持的模型类型";
        }
    }

}
