package com.laioffer.OOD.UnixFileSearch;

public class MinimumSizeFilter implements Filter {
    private Integer size;

    @Override
    public boolean isValid(File file){
        if (size == null || size<=0) {
            return true;
        }
        return size.compareTo(file.getSize())<0;
    }
}