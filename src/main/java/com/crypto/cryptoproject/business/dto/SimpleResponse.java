package com.crypto.cryptoproject.business.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(Include.NON_NULL)
public class SimpleResponse
{
    @Schema(description = "result is available, when there are business context messages, like INFO, WARNING or ERROR.")
    public Result result;

    public SimpleResponse info(String message)
    {
        initializeMessage();
        result.messages.add(Result.ResponseMessage.info(message));
        return this;
    }

    public SimpleResponse warning(String warning)
    {
        initializeMessage();
        result.messages.add(Result.ResponseMessage.warning(warning));
        return this;
    }

    public SimpleResponse error(String error)
    {
        initializeMessage();
        result.messages.add(Result.ResponseMessage.error(error));
        return this;
    }

    public void fieldMessage(String field, List<Result.ResponseMessage> message)
    {
        initializeFieldMessage();
        result.fieldMessages.put(field, message);
    }

    public void initializeMessage()
    {
        if (result == null)
        {
            result = new Result();
        }
        if (result.messages == null)
        {
            result.messages = new ArrayList<>();
        }
    }

    public void initializeFieldMessage()
    {
        if (result == null)
        {
            result = new Result();
        }
        if (result.fieldMessages == null)
        {
            result.fieldMessages = new HashMap<>();
        }
    }

    @JsonInclude(Include.NON_NULL)
    public static class Result
    {
        @Schema(description = "A list of messages, supposed to be presented to the user")
        public List<ResponseMessage> messages;
        @Schema(description = "Field messages must be displayed at the field, represented by the key of this map")
        public Map<String, List<ResponseMessage>> fieldMessages;
        @Schema(description = "A logid is supplied, when an error was logged. A logid helps to find the corresponding excpetion in the logfile and should be presented to the user")
        public String logid;
        @Schema(description = "Timestamp when the result was generated")
        public Instant timestamp = Instant.now();

        public static class ResponseMessage implements Serializable
        {
            public enum MessageType
            {
                INFO, WARNING, ERROR
            }

            public MessageType type;
            public String message;

            public ResponseMessage(String message)
            {
                this(MessageType.ERROR, message);
            }

            public ResponseMessage(MessageType type, String message)
            {
                this.type = type;
                this.message = message;
            }

            public static ResponseMessage info(String message)
            {
                return new ResponseMessage(message);
            }

            public static ResponseMessage warning(String warning)
            {
                return new ResponseMessage(MessageType.WARNING, warning);
            }

            public static ResponseMessage error(String error)
            {
                return new ResponseMessage(MessageType.ERROR, error);
            }
        }
    }
}
