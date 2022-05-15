package com.producter.cagri.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoSuchPlayerException extends RuntimeException implements GraphQLError {

    private Map<String, Object> extensions = new HashMap<>();

    public NoSuchPlayerException(String message, Long id) {
        super(message);
        extensions.put("No such player!!", id);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
