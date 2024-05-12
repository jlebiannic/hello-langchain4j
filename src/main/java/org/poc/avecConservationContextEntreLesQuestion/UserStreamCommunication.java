package org.poc.avecConservationContextEntreLesQuestion;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.output.Response;

import java.util.concurrent.CompletableFuture;

public interface UserStreamCommunication {

    CompletableFuture<Response<AiMessage>> ask(String prompt);

}