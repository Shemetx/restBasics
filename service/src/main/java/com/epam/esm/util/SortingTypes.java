package com.epam.esm.util;

import com.epam.esm.exception.EntityNotFoundException;

import java.util.Arrays;

public enum SortingTypes {
    ASC_NAME,
    ASC_DATE,
    DESC_NAME,
    DESC_DATE;

    public static SortingTypes resolveByName(String type,String param) {
        String sortType = type.toUpperCase();
        String sortParam = param.toUpperCase();
        String result = sortType+ "_" +sortParam;
        SortingTypes[] sortingTypes = SortingTypes.values();
        return Arrays.stream(sortingTypes)
                .filter(tmp -> tmp.name().equals(result))
                .findFirst().orElseThrow(()-> new EntityNotFoundException("This sorting type don't implemented yet"));
    }
}
