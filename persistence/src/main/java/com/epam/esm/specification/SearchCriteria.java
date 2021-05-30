package com.epam.esm.specification;

/**
 * Search criteria.
 */
public class SearchCriteria {

    private String key;
    private Object value;
    private SearchOperation operation;

    /**
     * Instantiates a new Search criteria.
     */
    public SearchCriteria() {
    }

    /**
     * Instantiates a new Search criteria.
     *
     * @param key       the key
     * @param value     the value
     * @param operation the operation
     */
    public SearchCriteria(String key, Object value, SearchOperation operation) {
        this.key = key;
        this.value = value;
        this.operation = operation;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets key.
     *
     * @param key the key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Gets operation.
     *
     * @return the operation
     */
    public SearchOperation getOperation() {
        return operation;
    }

    /**
     * Sets operation.
     *
     * @param operation the operation
     */
    public void setOperation(SearchOperation operation) {
        this.operation = operation;
    }
}
