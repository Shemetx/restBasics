package com.epam.esm.util;

import com.epam.esm.exception.EntityNotFoundException;

import java.util.Arrays;

/**
 * Enum to help recognise sorting type
 */
public enum SortingTypes {
    /**
     * Ascending name sorting type.
     */
    ASC_NAME,
    /**
     * Ascending date sorting type.
     */
    ASC_DATE,
    /**
     * Descending name sorting type.
     */
    DESC_NAME,
    /**
     * Descending date sorting type.
     */
    DESC_DATE;

    /**
     * Resolve by name sorting types.
     *
     * @param type  the type of sort
     * @param param the param of sort
     * @return the sorting type
     */
    public static SortingTypes resolveByName(String type, String param) {
        String sortType = type.toUpperCase();
        String sortParam = param.toUpperCase();
        String result = sortType + "_" + sortParam;
        SortingTypes[] sortingTypes = SortingTypes.values();
        return Arrays.stream(sortingTypes)
                .filter(tmp -> tmp.name().equals(result))
                .findFirst().orElseThrow(() -> new EntityNotFoundException("This sorting type don't implement yet"));
    }
}
