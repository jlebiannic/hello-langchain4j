package org.poc.avecConservationContextEntreLesQuestion;

import dev.langchain4j.service.TokenStream;

public interface ModelCommunication {

    TokenStream chatWithModel(String message);
}
