package com.example.mutimodel;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder builder, ToolCallbackProvider mcpTools) {

        // Groq reasoning model me reasoning band karte hain, taki Spring AI
        // 'reasoning_content' wapas Groq ko na bheje (Spring AI issue #6968).
        // Note: Spring AI 2.0 me defaultOptions() ko builder chahiye, .build() nahi.
        this.chatClient = builder
                .defaultOptions(OpenAiChatOptions.builder()
                        .model("qwen/qwen3.8-27b")
                        .extraBody(Map.of("reasoning_effort", "none")))
                .defaultToolCallbacks(mcpTools)
                .build();
    }

    public String chat(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}