package org.poc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OpenAiTest {

    @Test
    @DisplayName("Doit renvoyer une réponse d'OpenAi")
    void repondA() {
        // When
        String reponse = OpenAi.repondA("Génère le mot 'HELLO' avec des caractères ascii");

        // Then
        System.out.println(reponse);
        Assertions.assertNotNull(reponse);
    }
}