package io.github.agramar.model.request;

import lombok.Builder;

@Builder
public record TeslaGraphQLRequest<T>(
    String query,
    String operationName,
    T variables
) {
    public TeslaGraphQLRequest {
        if (query == null || query.isBlank())
            throw new IllegalArgumentException("query can not be blank");

        if (operationName == null || operationName.isBlank())
            throw new IllegalArgumentException("operationName can not be blank");

        if (variables == null)
            throw new IllegalArgumentException("variables can not be null");
    }
}
