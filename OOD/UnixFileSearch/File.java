package com.laioffer.OOD.UnixFileSearch;

import java.util.List;

public class File extends Entry{
    private List<Byte> content;
    private int size;

    public File(String name, List<Byte> content) {
        super(name);
        this.content = content;
        this.size = content.size();
    }

    public List<Byte> getContent() {
        return content;
    }

    public void setContent(List<Byte> content) {
        this.content = content;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getExtension() {
        return name.substring(name.lastIndexOf(".")+1);
    }
}
