package org.example.reader;

import org.example.dataline.DataLine;
import org.example.parser.TextLineParser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class KmhasDataReader implements DatasetReader {
    private final BufferedReader br;
    private final TextLineParser parser = new TextLineParser();

    public KmhasDataReader(String inputFilePath){
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        }
    }

    @Override
    public DataLine readLine() {
        try {
            String line = br.readLine();
            if (line == null) {
                return null;
            }
            return parser.parse(line);
        } catch (IOException e) {
            throw new RuntimeException("File read error", e);
        }
    }

    @Override
    public void close() {
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException("Error occuer while closing buffered reader.",e);
        }
    }
}
