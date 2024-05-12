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
    void askRAG() throws ExecutionException, InterruptedException, URISyntaxException {
        // Given
        URL url = ChatServiceRAGTest.class.getResource("/doc");
        // When
        ChatServiceRAG chatService = new ChatServiceRAG(Paths.get(url.toURI()));
        CompletableFuture<Response<AiMessage>> reponse =
                chatService.ask("Explique la catégorie 450 'Déficits antérieurs restant à imputer' dans l'annexe 2044. Comment renseigner les colonnes A et B ?");
        // Then
        Assertions.assertNotNull(reponse.get().content().text());

        /*
        * Exemple de réponse:
        *
        * Category 450: Remaining Deficits to Be Attributed

            In this category, you need to report any remaining deficits from previous years that could not be attributed
             to the current year's income.
            The deficits or portions of deficits from 2011 to 2020 that have not been absorbed by profits can be deducted
             from your 2021 fiscal year income, up to complete compensation on future profits.

            To fill out colonne A, you can use your 2020 Declaration of Fiscal Year Income submitted in 2021.
            The amounts to be reported in colonne A are the same as those in column C of your previous year's declaration.

            Instructions for filling out colonne A:

            1. Identify the deficits from previous years that could not be attributed to the current year's income.
               These may include unabsorbed deficits or portions of deficits from 2011 to 2020.
            2. Calculate the amount of each deficit that has not been absorbed by profits. You can use your previous year's
               declaration to determine these amounts.
            3. Report the deficits in colonne A, exclusively based on their original year of origin.
            4. Ensure that you have fully compensated any remaining deficits by reporting profits or other positive amounts in colonne B.

            Note: If you cease to rent out the property before December 31st of the third year following the imputation,
             except in cases of dismissal, invalidity, or death of the contributor or their spouse or
             co-owners subject to mutual taxation, or in case of an authorized abandonment of the lease,
             the imputation of deficits will be reconsidered.
        * */
    }
}