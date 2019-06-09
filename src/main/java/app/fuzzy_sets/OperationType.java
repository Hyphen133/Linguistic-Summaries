package app.fuzzy_sets;

import lombok.Getter;

@Getter
public enum OperationType {
    INTERSECTION("AND"),
    UNION("OR"),
    NEGATION("NOT");

    String operationName;

    OperationType(String operationName) {
        this.operationName = operationName;
    }
}