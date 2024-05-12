package org.poc;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.model.output.Response;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Llama2 {
    // Ollama serve locally on port 11434
    private static final String OLLAMA_HOST = "http://localhost:11434";
    public static final String LLAMA_2 = "llama2";

    public static String repondABasique(String question) {
        var model = connectModel(LLAMA_2);
        return model.generate(question);
    }

    public static String repondA(String question) {
        var model = connectSreamingModel(LLAMA_2);
        return repondEnStreaming(model, question);
    }

    private static ChatLanguageModel connectModel(String modelName) {
        return OllamaChatModel.builder()
                .baseUrl(OLLAMA_HOST)
                .modelName(modelName)
                .build();
    }

    private static StreamingChatLanguageModel connectSreamingModel(String modelName) {
        return OllamaStreamingChatModel.builder()
                .baseUrl(OLLAMA_HOST)
                .modelName(modelName)
                .timeout(Duration.ofHours(1))
                .build();
    }

    private static String repondEnStreaming(StreamingChatLanguageModel model, String question) {
        String reponse;
        CompletableFuture<Response<AiMessage>> futureResponse = new CompletableFuture<>();
        model.generate(question, new StreamingResponseHandler<>() {
            @Override
            public void onNext(String token) {
                System.out.print(token);
            }

            @Override
            public void onComplete(Response<AiMessage> response) {
                System.out.println();
                System.out.println("=====> FIN");
                futureResponse.complete(response);
            }

            @Override
            public void onError(Throwable error) {
                System.out.println();
                System.out.println("=====> ERROR: " + error);
                futureResponse.completeExceptionally(error);
            }
        });
        try {
            reponse = futureResponse.get().content().text();
        } catch (InterruptedException | ExecutionException e) {
            reponse = null;
        }
        System.out.println();
        System.out.println("=============================================================");
        System.out.println("========================= FIN ===============================");
        System.out.println("=============================================================");
        return reponse;
    }
}
