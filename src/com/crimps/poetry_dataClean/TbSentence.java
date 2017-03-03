/**
 * @(#)com.crimps.poetry_dataClean.TbSentence.java Copyright (c) 2014-2018 crimps
 */
package com.crimps.poetry_dataClean;

/**
 *
 * @author crimps
 * @version 1.0  17/3/3
 * @modified crimps  17/3/3  <创建>
 */
public class TbSentence {
    private String id;
    private String rollId;
    private String articlesId;
    private String beforId;
    private String afterId;
    private String poertyId;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRollId() {
        return rollId;
    }

    public void setRollId(String rollId) {
        this.rollId = rollId;
    }

    public String getArticlesId() {
        return articlesId;
    }

    public void setArticlesId(String articlesId) {
        this.articlesId = articlesId;
    }

    public String getBeforId() {
        return beforId;
    }

    public void setBeforId(String beforId) {
        this.beforId = beforId;
    }

    public String getAfterId() {
        return afterId;
    }

    public void setAfterId(String afterId) {
        this.afterId = afterId;
    }

    public String getPoertyId() {
        return poertyId;
    }

    public void setPoertyId(String poertyId) {
        this.poertyId = poertyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}