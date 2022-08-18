package com.laioffer.filesystem;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileSystemTest {

    @Test
    public void cc() {
        FileSystem fs = new FileSystem();
        fs.mkdir("/foo");
        fs.mkdir("/foo/bar");
        fs.createFile("/bar");
        fs.createFile("/foo/foo");
        fs.createFile("/foo/bar/bar");
        assertEquals(fs.count(),5);
        assertEquals(fs.list("/").size(),2);
        fs.delete("/foo/bar");
        assertEquals(fs.count(),3);
        assertEquals(fs.list("/").size(),2);
    }
}