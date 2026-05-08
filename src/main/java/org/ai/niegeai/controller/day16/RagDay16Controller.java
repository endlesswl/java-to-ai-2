package org.ai.niegeai.controller.day16;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * http://localhost:8088/ai/rag/day16?question=高强度螺栓有哪些
 * http://localhost:8088/ai/rag/day16?question=普通螺栓用在哪里
 * http://localhost:8088/ai/rag/day16?question=M10有哪些规格
 */
@RestController
public class RagDay16Controller {

    private final ChatClient chatClient;
    @Resource
    private VectorStore vectorStore;

    public RagDay16Controller(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/ai/rag/day16")
    public String search(@RequestParam String question) {

        List<Document> documents = vectorStore.similaritySearch(question);
        String context = documents.stream().map(Document::getContent).reduce("", (a, b) -> a + "\n" + b);

        String prompt = """
                你是商城客服，仅根据资料回答。
                                资料：%s
                                问题：%s
                """.formatted(context, question);

        return chatClient.call(prompt);
    }
}
