package org.example.util;

public class FileUtils {

    public static String getExt(String filename) {
        int extIdx = filename.lastIndexOf('.');
        return filename.substring(extIdx);
    }
}
