package org.poc.avecConservationContextEntreLesQuestion;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class ChatServiceTest {
    @Test
    @DisplayName("Doit renvoyer des réponses en gardant le contexte entre chaque question")
    void repondAExerecicesBureau() throws ExecutionException, InterruptedException {
        // When
        ChatService chatService = new ChatService();
        CompletableFuture<Response<AiMessage>> reponse = chatService.ask("Peux tu me donner deux exercices physiques à faire au bureau ?");
        reponse.join();
        reponse = chatService.ask("Basé sur la réponse précédente, que dois je faire si je ne peux pas me lever da ma chaise ?");
        // Then
        Assertions.assertNotNull(reponse.get().content().text());
    }

    @Test
    @DisplayName("Doit répondre à une question SANS le mode RAG")
    void askNoRAG() throws ExecutionException, InterruptedException {
        // When
        ChatService chatService = new ChatService();
        CompletableFuture<Response<AiMessage>> reponse =
                chatService.ask("Explique la catégorie 450 'Déficits antérieurs restant à imputer' dans l'annexe 2044. Comment renseigner les colonnes A et B ?");
        // Then
        Assertions.assertNotNull(reponse.get().content().text());
    }
}