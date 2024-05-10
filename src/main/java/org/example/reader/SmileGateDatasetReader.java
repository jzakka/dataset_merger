package org.example.reader;

import org.example.dataline.DataLine;
import org.example.parser.TsvLineParser;

public class SmileGateDatasetReader implements DatasetReader {
    private final TsvReader tsvReader;
    public SmileGateDatasetReader(String inputFilename) {
        tsvReader = new TsvReader(inputFilename);
    }

    @Override
    public DataLine readLine() {
        return tsvReader.readLine();
    }

    @Override
    public void close() {

    }
}
