package org.example.converter;

import org.example.MergeResult;
import org.example.dataline.DataLine;

public interface Converter {
    MergeResult convert(DataLine dataLine);
}
