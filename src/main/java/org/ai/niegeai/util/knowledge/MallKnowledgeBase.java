package org.ai.niegeai.util.knowledge;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.ai.niegeai.util.FileUtil;
import org.ai.niegeai.util.TextSplitter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class MallKnowledgeBase {

    @Getter
    private List<String> productChunks;

    @Resource
    private FileUtil fileUtil;

    @Resource
    private TextSplitter textSplitter;

    @PostConstruct
    public void init() {
        //读取文件
        String content = fileUtil.readText("kb/mall_kb.txt");
        //切块
        productChunks = textSplitter.splitByEmptyLine(content);
        System.out.println("✅ day15 商品知识库加载完成，共 " + productChunks.size() + " 条商品信息");
    }

    /**
     * 简单检索：包含关键词就算匹配
     * @param question
     * @return
     */
    public String search(String question){
        StringBuilder result = new StringBuilder();
        for (String chunk : productChunks) {
            if (match(question, chunk)) {
                result.append(chunk).append("\n\n");
            }
        }
        return result.isEmpty()? "无相关商品资料": result.toString();
    }

    /**
     * 简单匹配
     * @param question
     * @param chunk
     * @return
     */
    private boolean match(String question, String chunk) {
        String q = question.toLowerCase();
        String c = chunk.toLowerCase();
        return c.contains(q) || q.contains("螺栓") || q.contains("规格") || q.contains("等级") || q.contains("商品");
    }
}
