package org.ai.niegeai.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TextSplitter {

    /**
     * 按空行分割文档
     *
     * @param text
     * @return
     */
    public List<String> splitByEmptyLine(String text) {
        List<String> chunks = new ArrayList<>();
        String[] split = text.split("\\n\\s*\\n");

        for (String s: split) {
            String trim = s.trim();
            if (!trim.isEmpty()) {
                chunks.add(trim);
            }
        }
        return chunks;
    }

}
