package org.poc.avecConservationContextEntreLesQuestion;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class ChatService implements UserStreamCommunication, ModelCommunication {
    private static final String OLLAMA_HOST = "http://localhost:11434";
    public static final String LLAMA_2 = "llama2";

    private final StreamingChatLanguageModel languageModel;
    private final ModelCommunication assistant;

    public ChatService() {
        this.languageModel = connectModel(OLLAMA_HOST, LLAMA_2);
        // Memorize for 10 messages continuously
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        this.assistant = AiServices.builder(ModelCommunication.class)
                // Alternative of .chatLanguageModel() which support streaming response
                .streamingChatLanguageModel(this.languageModel)
                .chatMemory(chatMemory)
                .build();
    }

    // Could you give me the way to exercise for office worker, please?
    // Based on previous answer, What if I don't have much place outside?
    @Override
    public CompletableFuture<Response<AiMessage>> ask(String userPrompt) {
        TokenStream tokenStream = chatWithModel(userPrompt);
        CompletableFuture<Response<AiMessage>> future = new CompletableFuture<>();
        tokenStream.onNext(System.out::print)
                .onComplete(content -> {
                    System.out.println();
                    future.complete(content);
                })
                .onError(Throwable::printStackTrace)
                .start();
        return future;
    }

    @Override
    public TokenStream chatWithModel(String message) {
        return assistant.chatWithModel(message);
    }

    private static StreamingChatLanguageModel connectModel(String url, String modelName) {
        return OllamaStreamingChatModel.builder()
                .baseUrl(url)
                .modelName(modelName)
                .timeout(Duration.ofHours(1))
                .build();
    }
}