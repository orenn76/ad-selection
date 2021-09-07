package com.ninyo.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@JsonPropertyOrder({"timestamp", "status", "error", "exception", "message", "path"})
@Builder(toBuilder = true)
@Data
@JsonDeserialize(builder = ErrorMessage.ErrorMessageBuilder.class)
public class ErrorMessage implements Serializable {

    private static final long serialVersionUID = -8042087345155849600L;

    private String timestamp;
    private int status;
    private String error;
    private String exception;
    private String message;
    private Map<String, String> errors;
    private String path;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class ErrorMessageBuilder {
    }
}

