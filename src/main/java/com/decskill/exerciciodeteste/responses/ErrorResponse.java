package com.decskill.exerciciodeteste.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ErrorResponse {

    @JsonProperty("status")
    private int status;

    @JsonProperty("message")
    private String message;

    @JsonCreator
    public ErrorResponse(@JsonProperty("status") int status, @JsonProperty("message") String message) {
        this.status = status;
        this.message = message;
    }

}
