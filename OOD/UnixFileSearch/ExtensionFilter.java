package com.laioffer.OOD.UnixFileSearch;

public class ExtensionFilter implements Filter{
    private String extension;

    @Override
    public boolean isValid(File file){
        if(extension == null || extension.isEmpty()){
            return true;
        }
        return extension.equals(file.getExtension());
    }
}

