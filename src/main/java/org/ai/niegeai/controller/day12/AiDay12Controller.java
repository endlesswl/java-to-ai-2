package org.ai.niegeai.controller.day12;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiDay12Controller {

    private final DashScopeChatModel dashScopeChatModel;

    public AiDay12Controller(DashScopeChatModel dashScopeChatModel) {
        this.dashScopeChatModel = dashScopeChatModel;
    }

    @GetMapping("/ai/dashscope")
    public String dashscope(@RequestParam(defaultValue = "你好") String msg) {
        return dashScopeChatModel.call(msg);
    }

    /**
     * 通用智能问答（可自由切换底层模型）
     */
    @GetMapping("/ai/chat")
    public String chat(@RequestParam String type, @RequestParam String msg) {
        if ("dashscope".equals(type)) {
            return dashScopeChatModel.call(msg);
        } else {
            return "不支持的模型类型";
        }
    }

}
