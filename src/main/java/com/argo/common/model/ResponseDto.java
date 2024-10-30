package com.argo.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record ResponseDto<T>(String message, T data) {

    @JsonProperty
    public String dataType() {
        if (data == null) {
            return null;
        }

        var dataClassName = data.getClass().getCanonicalName();
        if (data instanceof Iterable<?> iterable) {
            String iterableClassName;
            if (data instanceof List<?>) {
                iterableClassName = List.class.getName();
            } else if (data instanceof Set<?>) {
                iterableClassName = Set.class.getName();
            } else {
                iterableClassName = dataClassName;
            }

            var iterator = iterable.iterator();
            if (iterator.hasNext()) {
                var anyItem = iterator.next();
                // assuming all of them are of the same type 😏
                return String.format("%s<%s>", iterableClassName, anyItem.getClass().getCanonicalName());
            }
        }

        return dataClassName;
    }

    @JsonProperty
    public long dataSize() {
        if (data == null) {
            return 0;
        }

        if (data instanceof Iterable<?> iterable) {
            return iterable.spliterator().getExactSizeIfKnown();
        }

        if (data.getClass().isArray()) {
            return ((Object[]) data).length;
        }

        return 1;
    }
}
