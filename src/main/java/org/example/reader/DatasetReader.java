package org.example.reader;

import org.example.dataline.DataLine;

public interface DatasetReader {
    DataLine readLine();

    void close();
}
