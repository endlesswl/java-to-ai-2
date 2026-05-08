package org.ai.niegeai.controller.day8;

import org.springframework.ai.chat.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {

    private final ChatClient chatClient;

    public AIController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/ai")
    public String ai(@RequestParam String question) {
        String answer = chatClient.call(question);

        return "Day8 JAVA+AI 跑通！ \n" +
                "你：" + question + "\n" +
                "AI:" + answer;
    }

}
