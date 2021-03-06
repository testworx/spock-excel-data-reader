/*
The MIT License (MIT)

Copyright (c) 2015 Nicholas Oppersdorff

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */

package io.testworx.spock.exceldatareader.core

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet

/*
 * This Groovy source file was auto generated by running 'gradle buildInit --type groovy-library'
 * by 'nvonop' at '12/11/15 18:53' with Gradle 2.7
 *
 * @author nvonop, @date 12/11/15 18:53
 */

class RowParser {

    Sheet dataSheet
    CellParser cellParser

    public RowParser(Sheet sheet) {
        dataSheet = sheet
    }

    public int getColumnIndexFromName(String name) {
        int index
        Row headerRow = dataSheet.getRow(0)
        Iterator headerCells = headerRow.cellIterator();
        while (headerCells.hasNext()) {
            Cell cell = (Cell) headerCells.next()
            if(cell.stringCellValue.trim().equalsIgnoreCase(name)) {
                index = cell.getColumnIndex()
            }
        }
        return index
    }

    public List rowDataToList(Row row) {
        cellParser = new CellParser()
        List data = new ArrayList()
        Iterator cells = row.cellIterator();
        while (cells.hasNext()) {
            Cell cell = (Cell) cells.next()
            //int cellIndex = cell.getColumnIndex()
            cellParser.addCellDataToList(cell, data)
        }
        return data
    }

    //Added to address issue #1
    public List rowDataToList(Row row, int columnStart) {
        int currentCellIndex = 0
        cellParser = new CellParser()
        List data = new ArrayList()
        Iterator cells = row.cellIterator();
        while (cells.hasNext()) {
            Cell cell = (Cell) cells.next()
            currentCellIndex = cell.getColumnIndex()

            if (currentCellIndex >= columnStart)  {
                cellParser.addCellDataToList(cell, data)
            }
        }
        return data
    }
}
