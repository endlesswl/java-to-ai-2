package org.ai.niegeai.controller.day15;

import jakarta.annotation.Resource;
import org.ai.niegeai.util.knowledge.MallKnowledgeBase;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * http://localhost:8088/ai/rag/day15?question=8.8级螺栓是什么
 * http://localhost:8088/ai/rag/day15?question=M10规格有哪些
 * http://localhost:8088/ai/rag/day15?question=4.8级和8.8级区别
 */
@RestController
public class RagDay15Controller {

    @Resource
    private ChatClient dashScopeChatModel;

    @Resource
    private MallKnowledgeBase mallKnowledgeBase;

    @GetMapping("/ai/rag/day15")
    public String search(@RequestParam String question) {
        // 1. 检索商品知识库
        String context = mallKnowledgeBase.search(question);
        // 2. 企业级Prompt
        String systemPrompt = """
                你是紧固件B2B商城智能客服，必须严格根据【商品知识库】回答。
                
                规则：
                1. 只回答知识库内容，不编造
                2. 回答简洁、专业、分点清晰
                3. 不知道就说：暂无相关商品资料
                4. 重点说明：等级、规格、材质、适用场景

                商品知识库：
                {context}
                """.replace("{context}", context);

        // 3. 调用 AI
        List<Message> messages = List.of(new SystemMessage(systemPrompt),
                new UserMessage(question));
        Prompt prompt = new Prompt(messages);

        return dashScopeChatModel.call(prompt).getResult().getOutput().toString();
    }

}
