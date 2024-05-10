package org.example.writer;

import com.aspose.cells.Workbook;
import org.example.MergeResult;
import org.example.Tags;

import java.util.Map;

public class ResultWriter {
    private final CellWriter cellWriter;
    private final Workbook workbook;
    private final String outputFileName;
    private int cursorRow;

    public ResultWriter(Workbook workbook, CellWriter cellWriter, String outputFileName) {
        this.workbook = workbook;
        this.cellWriter = cellWriter;
        this.outputFileName = outputFileName;
        writeFieldName();
        cursorRow = 2;
    }

    private void writeFieldName() {
        cellWriter.write("A1", "내용");
        for (Tags tag : Tags.values()) {
            String position = tag.getTsvPosition(1);
            String tagName = tag.getTagName();

            cellWriter.write(position, tagName);
        }
    }

    public void write(MergeResult mergeResult){
        String text = mergeResult.text();
        cellWriter.write(getTextPos(cursorRow), text);

        for (Map.Entry<Tags, Integer> tagAndVal : mergeResult.tags().entrySet()) {
            Tags tags = tagAndVal.getKey();
            Integer value = tagAndVal.getValue();

            String tagPosition = tags.getTsvPosition(cursorRow);
            cellWriter.write(tagPosition, value);
        }

        cursorRow++;
    }

    private String getTextPos(int row) {
        return "A" + row;
    }

    public void save() {
        try {
            workbook.save(outputFileName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save output file.",e);
        }
    }
}
