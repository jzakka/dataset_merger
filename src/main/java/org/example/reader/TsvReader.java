package org.example.reader;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Row;
import com.aspose.cells.RowCollection;
import com.aspose.cells.Workbook;
import org.example.dataline.DataLine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TsvReader {
    private final Workbook workbook;
    private final Row labels;
    private final Iterator<Row> rowIterator;

    public TsvReader(String inputFilename) {
        try {
            workbook = new Workbook(inputFilename);
            RowCollection rows = workbook.getWorksheets().get(0).getCells().getRows();
            labels = workbook.getWorksheets().get(0).getCells().getRows().get(1);
            rowIterator = rows.iterator();
        } catch (Exception e) {
            throw new RuntimeException("Cannot open tsv file.", e);
        }
    }

    public DataLine readLine() {
        if (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            return readRow(row);
        }
        return null;
    }

    private DataLine readRow(Row row) {
        Iterator<Cell> cellIterator = row.iterator();
        String text = cellIterator.next().getStringValue();

        Map<String, Integer> readTags = new HashMap<>();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            int columnIdx = cell.getColumn();
            String label = labels.get(columnIdx).getStringValue();
            int intValue = cell.getIntValue();

            readTags.put(label, intValue);
        }

        return new DataLine(text, readTags);
    }
}
