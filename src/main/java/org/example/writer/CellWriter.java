package org.example.writer;

import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;

public class CellWriter {
    private final Cells cells;

    public CellWriter() {
        this.cells = null;
    }

    public CellWriter(Workbook workbook) {
        this.cells = workbook.getWorksheets().get(0).getCells();
    }

    public void write(String position, Object value) {
        cells.get(position).putValue(value);
    }
}
