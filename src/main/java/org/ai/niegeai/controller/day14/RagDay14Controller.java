package org.ai.niegeai.controller.day14;

import jakarta.annotation.Resource;
import org.ai.niegeai.util.knowledge.KnowledgeBase;
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
 * RAG 知识库搜索
 *
 * http://localhost:8088/ai/rag/day14?question=公司是做什么的
 * http://localhost:8088/ai/rag/day14?question=你们用什么技术
 * http://localhost:8088/ai/rag/day14?question=能私有化部署吗
 */
@RestController
public class RagDay14Controller {

    @Resource
    private ChatClient dashScopeChatModel;

    @Resource
    private KnowledgeBase knowledgeBase;

    @GetMapping("/ai/rag/day14")
    public String search(@RequestParam String question) {

        // 1. 知识库搜索
        String context = knowledgeBase.search(question);

        // 2. 构建RAG 提示词
        String systemPrompt = """
                你是公司AI智能助手，必须严格根据提供的【企业知识库】回答问题。
                                    规则：
                                    1. 只回答知识库中有的内容
                                    2. 不知道就说：“抱歉，该问题暂无相关资料”
                                    3. 禁止编造信息
                                    4. 回答简洁、专业、正式
                
                                    企业知识库内容：
                                    {context}
                """.replace("{context}", context);
        // 3. 发送给 AI
        List<Message> messages = List.of(new SystemMessage(systemPrompt),
                new UserMessage(question));

        Prompt prompt = new Prompt(messages);

        return dashScopeChatModel.call(prompt).getResult().getOutput().toString();

    }

}
