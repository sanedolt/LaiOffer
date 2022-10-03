package com.laioffer.OOD.UnixFileSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UnixFind {
    // find files under given directory meet all filters
    public List<File> unixFind(Directory directory, List<Filter> filters){
        List<File> files = new ArrayList<>();
        search(files, directory, filters);
        return files;
    }
    // dfs to search
    private void search(List<File> files, Entry entry, List<Filter> filters){
        if (entry instanceof File) { // base case
            File file = (File) entry;
            for (Filter filter : filters) {
                if (!filter.isValid(entry)) return; // AND logic
            }
            files.add(file);
            return;
        }
        //else if(entry instanceOf Directory){
        for(Map.Entry<String, Entry> e : ((Directory)entry).getChildren().entrySet()){
            search(files, e.getValue(), filters);
        }
    }
}
