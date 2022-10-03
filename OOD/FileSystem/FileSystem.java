package com.laioffer.OOD.FileSystem;
import java.util.List;
import java.util.ArrayList;

public class FileSystem {
    private final Directory root;

    public FileSystem() {
        root = new Directory("/",null);
    }

    private List<Entry> resolve(String path) {
        assert path.startsWith("/");
        String[] components = path.substring(1).split("/");
        List<Entry> entries = new ArrayList<Entry>(components.length+1);
        entries.add(root);
        Entry entry = root;
        for (String component : components) {
            if (entry==null || !(entry instanceof Directory)) {
                //throw new IllegalArgumentException("invalid path:"+path+entry.getName());
            }
            if (!component.isEmpty()) {
                entry=((Directory) entry).getChild(component);
                entries.add(entry);
            }
        }
        return entries;
    }

    public void mkdir(String path) {
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size()-1)!=null) {
            //throw new IllegalArgumentException("Directory already exists:"+path);
        }
        String[] components = path.split("/");
        final String dirName = components[components.length-1];
        final Directory parent = (Directory) entries.get(entries.size()-2);
        Directory newDir = new Directory(dirName,parent);
        parent.addEntry(newDir);
    }

    public void createFile(String path) {
        assert !path.endsWith("/");
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size()-1)!=null) {
            //throw new IllegalArgumentException("File already exists:"+path);
        }
        final String fileName = path.substring(path.lastIndexOf("/")+1);
        final Directory parent = (Directory) entries.get(entries.size()-2);
        File file = new File(fileName,parent,0);
        parent.addEntry(file);
    }

    public void delete(String path) {
        assert path.startsWith("/");
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size()-2)==null) {
            //throw new IllegalArgumentException("Parent does not exist:"+path);
        }
        String[] components = path.split("/");
        String last = components[components.length-1];
        final Directory parent = (Directory) entries.get(entries.size()-2);
        Entry e = null;
        if (last.length()==0) {
            final String dirName = components[components.length-1];
            e = parent.getChild(dirName);
        } else {
            final String fileName = path.substring(path.lastIndexOf("/")+1);
            e = parent.getChild(fileName);
        }
        parent.deleteEntry(e);
    }

    public List<Entry> list(String path) {
        assert path.endsWith("/");
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size()-1)==null) {
            //throw new IllegalArgumentException("Folder does not exist:"+path);
        }
        final Directory parent = (Directory) entries.get(entries.size()-1);
        return parent.getContents();
    }

    public int count() {
        return root.numberOfFiles();
    }

//    public static void main(String[] args) {
//        FileSystem fs = new FileSystem();
//        fs.mkdir("/foo");
//        fs.mkdir("/foo/bar");
//        fs.createFile("/bar");
//        fs.createFile("/foo/foo");
//        fs.createFile("/foo/bar/bar");
//        check(fs.count() == 5);
//        check(fs.list("/").size() == 2);
//        fs.delete("/foo/bar");
//        check(fs.count() == 3);
//        check(fs.list("/").size() == 2);
//    }
//    public static void check(boolean pass) {
//        System.out.println(pass);
//        //if (!pass) throw new RuntimeException("Test failed");
//    }
}
