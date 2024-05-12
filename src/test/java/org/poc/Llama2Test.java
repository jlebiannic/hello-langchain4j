package org.poc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}