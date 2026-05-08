package org.ai.niegeai.controller.day13;

import jakarta.annotation.Resource;
import org.ai.niegeai.util.PromptUtil;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiDay13Controller {

    @Resource
    private ChatClient dashScopeChatModel;
//
//    @Resource
//    private QianFanChatModel qianfanChatModel;
//
    @GetMapping("/ai/mall/dashscope")
    public String chat(@RequestParam String msg) {
        try {
            Prompt prompt = PromptUtil.buildMallChatPrompt(msg);
            AssistantMessage reply = dashScopeChatModel.call(prompt).getResult().getOutput();
            return String.valueOf(reply);
        } catch (Exception e) {
            return "通义千问异常：" + e.getMessage();
        }
    }
//
//    /**
//     * 紧固件商城 AI 智能客服（文心一言）
//     */
//    @GetMapping("/ai/mall/qianfan")
//    public String mallQianfan(@RequestParam String msg) {
//        try {
//            Prompt prompt = PromptUtil.buildMallChatPrompt(msg);
//            AssistantMessage reply = qianfanChatModel.call(prompt).getResult().getOutput();
//            return String.valueOf(reply);
//        } catch (Exception e) {
//            return "文心一言异常：" + e.getMessage();
//        }
//    }
//
    /**
     * 统一入口：指定模型 + 商城客服问答
     */
    @GetMapping("/ai/mall/chat")
    public String mallChat(
            @RequestParam String type,
            @RequestParam String msg
    ) {
        Prompt prompt = PromptUtil.buildMallChatPrompt(msg);
        if ("dashscope".equals(type)) {
            AssistantMessage reply = dashScopeChatModel.call(prompt).getResult().getOutput();
            return String.valueOf(reply);
//        } else if ("qianfan".equals(type)) {
//            AssistantMessage reply = qianfanChatModel.call(prompt).getResult().getOutput();
//            return String.valueOf(reply);
        }
        return "暂不支持该模型";
    }
}
