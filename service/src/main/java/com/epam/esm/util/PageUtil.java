package com.epam.esm.util;

import org.springframework.stereotype.Component;

@Component
public class PageUtil {

    public int getCorrectPage(int page, int size) {
        page -= 1;
        page *= size;
        return page;
    }
}
