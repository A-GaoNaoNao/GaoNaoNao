package com.example.day02_lxc.Baen;

public class TabList {
    private String name;

    public TabList(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TabList{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
