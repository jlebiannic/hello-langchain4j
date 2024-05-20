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

        // Ou

        /*
        * The category "Déficits antérieurs restant à imputer" in Annex 2044 refers to the portion of previous year's deficits that have not been yet allocated to imputeable income. The information provided in columns A and B of this annex are used to calculate this amount.

To determine the part of the deficits that are not yet imputed, you need to refer to your previous year's revenues. The formula for calculating this amount is:

Part of deficits not yet imputed = Deficits at end of year - Total imputable income at end of year

Where:

* Deficits at end of year refers to the amount of deficits that were outstanding as of the end of the previous year.
* Total imputable income at end of year refers to the total imputable income that was declared in your tax return for the previous year.

Once you have calculated the part of the deficits that are not yet imputed, you can report this amount in column A of Annex 2044.

For example, let's say that as of December 31, 2022, you had a deficit of $10,000 and your total imputable income for the previous year was $50,000. To calculate the part of the deficits that are not yet imputed, you would use the following formula:

Part of deficits not yet imputed = $10,000 - $50,000 = $40,000

Therefore, you would report $40,000 in column A of Annex 2044.

As for column B, it refers to the total amount of déficits antérieurs non encore imputés au 31 décembre 2022. This amount is calculated by adding up all the parts of deficits not yet imputed for each year since 2017, as reported in columns A of Annex 2044.

For example, let's say that as of December 31, 2022, you had reported the following amounts in columns A of Annex 2044 for each year since 2017:

* 2017: $20,000
* 2018: $30,000
* 2019: $40,000
* 2020: $50,000

To calculate the total amount of déficits antérieurs non encore imputés au 31 décembre 2022, you would add up all these amounts:

Total amount of déficits antérieurs non encore imputés = $20,000 + $30,000 + $40,000 + $50,000 = $170,000

Therefore, you would report $170,000 in column B of Annex 2044.
        * */
    }
}