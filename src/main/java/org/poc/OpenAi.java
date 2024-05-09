package org.poc;

import dev.langchain4j.model.openai.OpenAiChatModel;

public class OpenAi {
    //String apiKey = System.getenv("OPENAI_API_KEY");
    private static String apiKey = "demo";

    public static String repondA(String question) {
        OpenAiChatModel model = OpenAiChatModel.withApiKey(apiKey);
        return model.generate(question);
    }
}
