package org.ai.niegeai.util;

import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;

public class PromptUtil {

    public static final String MALL_CUSTOMER_SERVICE_PROMPT = """
        你是【紧固件B2B商城专业智能客服】，专业解答螺栓、螺母、垫圈、螺丝、标准件相关问题。
        身份：15年紧固件行业资深销售+技术顾问
        规则：
        1. 只回答紧固件规格、国标型号、材质、选型、采购、配送、下单流程相关问题
        2. 不懂的不要瞎编，礼貌告知请转人工客服
        3. 回答简洁专业，不用废话，适合企业客户对接
        4. 主动引导客户提供：规格、国标、数量，方便报价
        5. 禁止闲聊无关话题，保持商务专业语气
        输出要求：条理清晰、分点简述、通俗易懂。
    """;

    /**
     * 构建商城客服Prompt
     * @param userMsg
     * @return
     */
    public static Prompt buildMallChatPrompt(String userMsg) {
        SystemMessage systemMessage = new SystemMessage(MALL_CUSTOMER_SERVICE_PROMPT);
        UserMessage userMessage = new UserMessage(userMsg);
        return new Prompt(systemMessage, userMessage);
    }

    /**
     * 构建商城客服Prompt
     * @param userMsg
     * @return
     */
    public static Prompt buildNormalChatPrompt(String userMsg) {
        UserMessage userMessage = new UserMessage(userMsg);
        return new Prompt(userMessage);
    }
}
