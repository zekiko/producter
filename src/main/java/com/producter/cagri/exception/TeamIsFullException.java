package com.producter.cagri.exception;

import com.producter.cagri.model.Player;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamIsFullException extends RuntimeException implements GraphQLError {
    private Map<String, Object> extensions = new HashMap<>();

    public TeamIsFullException(String message, Player player) {
        super(message);
        extensions.put("Team is full!", player);
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
