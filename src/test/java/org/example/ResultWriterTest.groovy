package org.example

import com.aspose.cells.Cells
import com.aspose.cells.Workbook
import com.aspose.cells.Worksheet
import com.aspose.cells.WorksheetCollection
import org.example.writer.CellWriter
import org.example.writer.ResultWriter
import spock.lang.Specification

class ResultWriterTest extends Specification {
    def workBook = Mock(Workbook)
    def cellWriter = Mock(CellWriter)
    def writer = new ResultWriter(workBook, cellWriter, "test")

    def "결과 작성 테스트"() {
        given:
        MergeResult result = new MergeResult("This is text", [
                (Tags.GENDER) : 0,
                (Tags.RACE_COUNTRY) : 0,
                (Tags.YEAR) : 0,
                (Tags.LOCALE) : 1,
                (Tags.RELIGION) : 1,
                (Tags.ABUSE) : 1,
                (Tags.ETC) : 1,
                (Tags.CLEAN) : 0,
        ])
        when:
        writer.write(result)
        then:
        1 * cellWriter.write("B2", 0)
        1 * cellWriter.write("C2", 0)
        1 * cellWriter.write("D2", 0)
        1 * cellWriter.write("E2", 1)
        1 * cellWriter.write("F2", 1)
        1 * cellWriter.write("G2", 1)
        1 * cellWriter.write("H2", 1)
        1 * cellWriter.write("I2", 0)
    }
}
