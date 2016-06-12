package com.dmallcott.library;

import java.io.Serializable;
import java.util.List;

/**
 * @author dmallcott
 */
public class PageList implements Serializable {

    private List<Page> pages;

    public PageList(List<Page> pages) {
        this.pages = pages;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
}
