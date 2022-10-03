package com.laioffer.OOD.UnixFileSearch;

import java.util.HashMap;
import java.util.Map;

public class Directory extends Entry{
    private Map<String,Entry> children;
    public Directory(String name) {
        super(name);
        children = new HashMap<>();
    }

    public Map<String, Entry> getChildren() {
        return children;
    }

    public void setChildren(Map<String, Entry> children) {
        this.children = children;
    }
}
