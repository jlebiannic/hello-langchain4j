package org.poc;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import java.util.Scanner;

public class Llama2 {
    // Ollama serve locally on port 11434
    private static final String LOCALHOST = "http://localhost:11434";
    public static final String LLAMA_2 = "llama2";

    public static String repondA(String question) {
        Scanner scanner = new Scanner(System.in);
        var model = connectModel(LLAMA_2);
        return model.generate(question);
    }

    private static ChatLanguageModel connectModel(String modelName) {
        return OllamaChatModel.builder()
                .baseUrl(LOCALHOST)
                .modelName(modelName)
                .build();
    }
}
