package com.laioffer.OOD.UnixFileSearch;

public class NameFilter implements Filter {
    private String name;

    @Override
    public boolean isValid(File file){
        if (name == null || name.length()<=0){
            return true;
        }
        return String.indexOf(name)>=0;
    }
}
