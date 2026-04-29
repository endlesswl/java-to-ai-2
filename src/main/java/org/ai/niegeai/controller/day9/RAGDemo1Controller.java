package org.ai.niegeai.controller.day9;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RAGDemo1Controller {

    private final DashScopeChatModel chatModel;

    private final List<String> knowledgeBase = new ArrayList<>();

    public RAGDemo1Controller(DashScopeChatModel chatModel) {
        this.chatModel = chatModel;
        initKnowledge();
    }

    private void initKnowledge() {
        knowledgeBase.add("公司商城基于 RuoYi-Vue-Pro 开发");
        knowledgeBase.add("后端技术栈：Java、SpringBoot、MySQL、Redis、Docker");
        knowledgeBase.add("未来方向：AI大模型、RAG知识库、智能问答");
        knowledgeBase.add("私有化部署：内网离线、数据不上云");
    }

    @GetMapping("/rag/chat/demo1")
    public String ragDemo1(@RequestParam String question) {
        String context = String.join("\n", knowledgeBase);

        String prompt = "你是一个专业的AI助手，可以帮助用户回答关于公司的各种问题。\n" +
                "你需要根据用户的问题，从知识库中检索相关信息，并给出回答。\n" +
                "知识库内容：\n" +
                context + "\n" +
                "用户的问题："+question+"\n" ;

        return chatModel.call(prompt);
    }
}
