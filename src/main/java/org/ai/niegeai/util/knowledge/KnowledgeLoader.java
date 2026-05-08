package org.ai.niegeai.util.knowledge;

import jakarta.annotation.PostConstruct;

import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识库加载类
 */
@Component
public class KnowledgeLoader {

    @Resource
    private VectorStore vectorStore;

    @Value("classpath:kb/mall_kb.txt")
    private org.springframework.core.io.Resource resource;

    /**
     * 项目启动 → 自动把商品库向量化
     */
    @PostConstruct
    public void init() {
//        TextReader reader = new TextReader(resource);
//        List<Document> documents = reader.get();
//
//        TokenTextSplitter splitter = new TokenTextSplitter();
//        List<Document> chunks = new ArrayList<>();
//
//        for (Document doc: documents) {
//            List<String> splitTexts = splitter.split(doc.getContent(), 512);
//            for (String text: splitTexts) {
//                chunks.add(new Document(text));
//            }
//        }
//
//        vectorStore.add(chunks);
//        System.out.println("✅ Day16 向量库初始化完成，共 " + chunks.size() + " 条商品信息");;
    }
}
