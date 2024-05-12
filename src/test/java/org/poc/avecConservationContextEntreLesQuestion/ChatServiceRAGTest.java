package org.poc.avecConservationContextEntreLesQuestion;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class ChatServiceRAGTest {

    @Test
    @DisplayName("Doit répondre à une question en mode RAG, c a d utiliser des documents précis")
    void ask() throws ExecutionException, InterruptedException, URISyntaxException {
        // Given
        URL url = ChatServiceRAGTest.class.getResource("/doc");
        // When
        ChatServiceRAG chatService = new ChatServiceRAG(Paths.get(url.toURI()));
        CompletableFuture<Response<AiMessage>> reponse =
                chatService.ask("Explique la catégorie 450 'Déficits antérieurs restant à imputer' dans l'annexe 2044. Comment renseigner les colonnes A et B ?");
        // Then
        Assertions.assertNotNull(reponse.get().content().text());
    }
}