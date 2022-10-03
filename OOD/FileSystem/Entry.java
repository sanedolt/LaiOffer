package com.laioffer.OOD.FileSystem;

public abstract class Entry {
    protected Directory parent;
    protected String name;

    public Entry(String name, Directory parent) {
        this.name=name;
        this.parent=parent;
    }

    public boolean delete() {
        if (parent==null) {
            return false;
        }
        return parent.deleteEntry(this);
    }

    public abstract int size();

    public String getFullPath() {
        if (parent==null) {
            return name;
        } else {
            return parent.getFullPath()+"/"+name;
        }
    }

    public void changeName(String n) {
        this.name=n;
    }

    public String getName() {
        return name;
    }
}
