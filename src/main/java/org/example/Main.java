package org.example;

import com.aspose.cells.Workbook;
import org.example.converter.Converter;
import org.example.dataline.DataLine;
import org.example.reader.DatasetReader;
import org.example.writer.CellWriter;
import org.example.writer.ResultWriter;

import java.util.Arrays;

public class Main {
    private static ResultWriter rw;

    public static void main(String[] args) {
        String[] inputFiles = Arrays.copyOf(args, args.length - 1);
        String outputFileName = args[args.length - 1];

        Workbook workbook = new Workbook();
        CellWriter cellWriter = new CellWriter(workbook);
        rw = new ResultWriter(workbook, cellWriter, outputFileName);

        for (String inputFile : inputFiles) {
            DatasetReader dr = getDatasetReader(inputFile);
            Converter converter = getConverter(inputFile);
            writeMergedTagResult(dr, converter);
        }

        rw.save();
    }

    private static void writeMergedTagResult(DatasetReader dr, Converter converter) {
        DataLine dataLine;
        while ((dataLine = dr.readLine()) != null) {
            MergeResult result = converter.convert(dataLine);
            rw.write(result);
        }
        dr.close();
    }

    private static Converter getConverter(String filepath) {
        return Dataset.getConverter(filepath);
    }


    private static DatasetReader getDatasetReader(String filepath) {
        return Dataset.getDatasetReader(filepath);
    }
}