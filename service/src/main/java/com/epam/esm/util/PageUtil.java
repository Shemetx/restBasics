package com.epam.esm.util;

import org.springframework.stereotype.Component;

/**
 * Helper class to get correct page
 */
@Component
public class PageUtil {

    /**
     * Validate page and size
     *
     * @param page the page
     * @param size the size
     * @return the correct page
     */
    public int getCorrectPage(int page, int size) {
        if (page <= 0 || size <= 0) {
            throw new IllegalArgumentException("Page and size should be positive");
        }
        page -= 1;
        page *= size;
        return page;
    }
}
