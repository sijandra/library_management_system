package com.library.library_management_system.modules.book.domain.models;

public class Section implements IBookSubdomain {
    private Long id;
    private String name;

    protected Section(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Section create(Long id, String name) {
        return new Section(id, name);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}