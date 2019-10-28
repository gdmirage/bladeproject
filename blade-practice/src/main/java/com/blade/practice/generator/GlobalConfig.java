package com.blade.practice.generator;

import com.blade.practice.util.DateUtil;

/**
 * @author blade
 * 2019/10/28 14:16
 */
public class GlobalConfig {

    private String author;
    private String createDate;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreateDate() {
        return DateUtil.getDateTimeString(DateUtil.getCurrentDateTime());
    }
}
