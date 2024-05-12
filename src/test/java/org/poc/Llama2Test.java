package org.poc;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.poc.avecConservationContextEntreLesQuestion.ChatService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class Llama2Test {

    @Test
    @DisplayName("Doit renvoyer une réponse fournit par Ollama qui encapsule le modèle Llama2")
    void repondA() {
        // When
        String reponse = Llama2.repondA("Give me hello world program in java");

        // Then
        System.out.println(reponse);
        Assertions.assertNotNull(reponse);
    }

    @Test
    @DisplayName("Doit renvoyer une réponse en streaming à une question plus complexe")
    void repondAEnStreaming() {
        // When
        String reponse = Llama2.repondA("Give me instructions to create Spring Boot Microservice project");

        // Then
        System.out.println(reponse);
        Assertions.assertNotNull(reponse);
    }

    @Test
    @DisplayName("Doit renvoyer des réponses en gardant le contexte entre chaque question")
    void repondAExerecicesBureau() throws ExecutionException, InterruptedException {
        // When
        //String reponse = Llama2.repondA("Peux tu me donner deux exercices physiques à faire au bureau ?");
        //reponse = Llama2.repondA("Basé sur la réponse précédente, que dois je faire si je ne peux pas me lever da ma chaise ?");
        ChatService chatService = new ChatService();
        CompletableFuture<Response<AiMessage>> reponse = chatService.ask("Peux tu me donner deux exercices physiques à faire au bureau ?");
        reponse.join();
        reponse = chatService.ask("Basé sur la réponse précédente, que dois je faire si je ne peux pas me lever da ma chaise ?");
        // Then
        Assertions.assertNotNull(reponse.get().content().text());
    }
}