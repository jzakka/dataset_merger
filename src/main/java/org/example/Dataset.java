package org.example;

import org.example.converter.Converter;
import org.example.converter.KmhasDataConverter;
import org.example.converter.SmilegateDataConveter;
import org.example.reader.DatasetReader;
import org.example.reader.KmhasDataReader;
import org.example.reader.SmileGateDatasetReader;
import org.example.util.FileUtils;

import java.util.function.Function;
import java.util.function.Supplier;

public enum Dataset {
    SMILEGATE(".tsv", SmileGateDatasetReader::new, SmilegateDataConveter::new),
    KMHAS(".txt", KmhasDataReader::new, KmhasDataConverter::new);

    Dataset(String ext, Function<String, DatasetReader> datasetMaker, Supplier<Converter> converterMaker) {
        this.ext = ext;
        this.datasetMaker = datasetMaker;
        this.converterMaker = converterMaker;
    }

    private final String ext;
    private final Function<String, DatasetReader> datasetMaker;
    private final Supplier<Converter> converterMaker;

    public static DatasetReader getDatasetReader(String filename) {
        String ext = FileUtils.getExt(filename);

        for (Dataset dataset : Dataset.values()) {
            if (dataset.ext.equals(ext)) {
                return dataset.datasetMaker.apply(filename);
            }
        }

        throw new RuntimeException("Cannot find matching tag merger.");
    }

    public static Converter getConverter(String filename) {
        String ext = FileUtils.getExt(filename);

        for (Dataset dataset : Dataset.values()) {
            if (dataset.ext.equals(ext)) {
                return dataset.converterMaker.get();
            }
        }

        throw new RuntimeException("Cannot find matching tag merger.");
    }
}
