package org.ai.niegeai.controller.day8;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {

    private final DashScopeChatModel chatModel;

    public AIController(DashScopeChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/ai")
    public String ai(@RequestParam String question) {
        String answer = chatModel.call(question);

        return "Day8 JAVA+AI 跑通！ \n" +
                "你：" + question + "\n" +
                "AI:" + answer;
    }

}
