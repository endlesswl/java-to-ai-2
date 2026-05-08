package org.ai.niegeai.util.knowledge;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KnowledgeBase {

    private final List<String> docs = new ArrayList<>();

    @PostConstruct
    public void init() {
        docs.add("公司名称：聂哥智能科技有限公司");
        docs.add("主营产品：AI智能客服、RAG知识库系统、SpringAI大模型应用");
        docs.add("技术栈：SpringBoot3、SpringAI、通义千问、Vue3、MySQL、Redis");
        docs.add("部署方式：支持私有化部署、内网隔离、数据不上云");
        docs.add("售后服务：提供7×12小时技术支持，系统免费升级");
        docs.add("核心优势：基于RAG技术，保证回答100%来自企业真实资料，不编造内容");
    }

    /**
     * 知识库搜索 简单匹配（后期项目使用向量库）
     * @param query
     * @return
     */
    public String search(String query) {
        List<String> matched = new ArrayList<>();
        for (String doc : docs) {
            if (doc.contains(query) || query.contains("公司") || query.contains("产品")) {
                matched.add(doc);
            }
        }
        return String.join("\n", matched);
    }
}
