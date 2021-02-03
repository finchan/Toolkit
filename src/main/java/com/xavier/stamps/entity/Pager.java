package com.xavier.stamps.entity;

public class Pager<T,U> {
    private Integer page;           // Starting page
    private Integer size;           // Records per page
    private Integer total;          // Total records
    private U searchingCriteria;  // As searching criteria
    private T entities;       // As returned result

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public U getSearchingCriteria() {
        return searchingCriteria;
    }

    public void setSearchingCriteria(U searchingCriteria) {
        this.searchingCriteria = searchingCriteria;
    }

    public T getEntities() {
        return entities;
    }

    public void setEntities(T entities) {
        this.entities = entities;
    }
}
