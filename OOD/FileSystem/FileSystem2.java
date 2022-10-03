package com.laioffer.OOD.FileSystem;

import java.util.*;

public class FileSystem2 {
    static class Entry {
        protected String name;
        public Entry(String name) {
            this.name = name;
        }
    }
    static class File extends Entry {
        String content;
        public File(String name) {
            super(name);
            content="";
        }
    }
    static class Directory extends Entry {
        Map<String,Entry> children;
        public Directory(String name) {
            super(name);
            children = new HashMap<>();
        }
    }
    private Directory root;
    public FileSystem2() {
        root = new Directory("");
    }

    public List<String> ls (String path) {
        String[] strs = path.split("/");
        List<String> list = new ArrayList<>();
        if (strs.length==0) {
            list.addAll(root.children.keySet());
            Collections.sort(list);
            return list;
        }
        Directory parent = getToDir(strs,1,strs.length-2);
        Entry e = parent.children.get(strs[strs.length-1]);
        if (e instanceof Directory) {
            Directory d = (Directory) e;
            System.out.println(d.children.keySet());;
            list.addAll(d.children.keySet());
            System.out.println(list);
            Collections.sort(list);
            System.out.println(list);
        } else {
            File f = (File) e;
            list.add(f.name);
        }
        return list;
    }
    private Directory getToDir(String[] strs, int start, int end) {
        Directory cur = root;
        for (int i=start; i<=end; i++) {
            Directory child = (Directory) cur.children.get(strs[i]);
            if (child == null) {
                child = new Directory(strs[i]);
                cur.children.put(strs[i],child);
            }
            cur=child;
        }
        return cur;
    }

    public void mkdir(String path) {
        String[] strs = path.split("/");
        Directory d = (Directory) getToDir(strs, 1, strs.length - 1);
    }

    public void addContentToFile (String filePath, String content) {
        String[] strs = filePath.split("/");
        Directory dir = getToDir(strs, 1, strs.length - 2);
        String fileName = strs[strs.length - 1];
        File file = (File) dir.children.get(fileName);
        if (file == null) {
            file = new File(fileName);
            dir.children.put(fileName, file);
        }
        file.content += content;
    }

    public String readContentFromFile (String filePath) {
        String[] strs = filePath.split("/");
        Directory dir = getToDir(strs,1,strs.length-2);
        String fileName = strs[strs.length - 1];
        File file = (File) dir.children.get(fileName);
        return file.content;
    }

}
