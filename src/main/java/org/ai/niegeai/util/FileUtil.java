package org.ai.niegeai.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Component
public class FileUtil {

    /**
     * 读取 resources 下文本文件
     * @param path
     * @return
     */
    public String readText(String path) {
        ClassPathResource resource = new ClassPathResource(path);
        try {
            return Files.readString(resource.getFile().toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("文件读取失败：" + e.getMessage());
            return "文件读取失败：" + e.getMessage();
        }

    }
}
