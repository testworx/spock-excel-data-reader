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

package io.testworx.spock.exceldatareader.client.tests

import io.testworx.spock.exceldatareader.client.ExcelDataReader
import spock.lang.Specification
/*
 * This Groovy source file was auto generated by running 'gradle buildInit --type groovy-library'
 * by 'nvonop' at '12/11/15 18:53' with Gradle 2.7
 *
 * @author nvonop, @date 12/11/15 18:53
 */

class ExcelDataReaderSpec extends Specification {

    static final String DATA_FILE_LOCATION = "/src/test/resources/"
    static final String DATA_FILE_NAME = "TestData.xlsx"
    static final String DATA_SHEET_1_NAME = "Datasheet1"
    static final String DATA_SHEET_2_NAME ="Datasheet2"

    public correctWorksheetOpenedWhenSpecifiedTest() {
        given: "I open a spreadsheet and specify a worksheet"
        System.setProperty('test.data.dir', System.getProperty("user.dir")+ DATA_FILE_LOCATION)
        ExcelDataReader excelDataReader = new ExcelDataReader(System.getProperty("test.data.dir"), DATA_FILE_NAME , DATA_SHEET_2_NAME)
        
        when: "I check which worksheet I am on"
        String name = excelDataReader.getCurrentWorksheetName()

        then: "The name of the worksheet is as expected"
        name == DATA_SHEET_2_NAME
    }

    public firstWorksheetIsOpenedByDefaultWhenNoneSpecifiedTest() {
        given: "I open a spreadsheet and dont specify a worksheet"
        System.setProperty('test.data.dir', System.getProperty("user.dir")+DATA_FILE_LOCATION)
        ExcelDataReader excelDataReader = new ExcelDataReader(System.getProperty("test.data.dir"), DATA_FILE_NAME)

        when: "I check which worksheet I am on"
        String name = excelDataReader.getCurrentWorksheetName()

        then: "The name of the worksheet is as expected"
        name == DATA_SHEET_1_NAME
    }

    public dataSetReturnedFromNamedColumnTest() {
        given: "I open a spreadsheet and specify a worksheet"
        System.setProperty('test.data.dir', System.getProperty("user.dir")+ DATA_FILE_LOCATION)
        ExcelDataReader excelDataReader = new ExcelDataReader(System.getProperty("test.data.dir"), DATA_FILE_NAME , DATA_SHEET_2_NAME)

        when: "I retrieve data from the column named Keyword"
        List columnData = excelDataReader.getDataFromColumn("Keyword")

        then: "The column data is as expected"
        columnData.size() == 22
    }

    public dataSetReturnedFromNamedColumnWithoutHeaderTest() {
        given: "I open a spreadsheet and specify a worksheet"
        System.setProperty('test.data.dir', System.getProperty("user.dir")+ DATA_FILE_LOCATION)
        ExcelDataReader excelDataReader = new ExcelDataReader(System.getProperty("test.data.dir"), DATA_FILE_NAME , DATA_SHEET_2_NAME)

        when: "I retrieve data from the column named Keyword"
        List columnData = excelDataReader.getDataFromColumn("TestCase", 1)

        then: "The column data is as expected"
        columnData.size() == 21
        columnData.get(0) == "Test 1 - Click Button 1"
    }

    public dataSetReturnedFromIndexedColumnTest() {
        given: "I open a spreadsheet on the default worksheet"
        System.setProperty('test.data.dir', System.getProperty("user.dir")+DATA_FILE_LOCATION)
        ExcelDataReader excelDataReader = new ExcelDataReader(System.getProperty("test.data.dir"), DATA_FILE_NAME)

        when: "I retrieve data from a column by its index"
        List columnData = excelDataReader.getDataFromColumn(1)

        then: "The column data is as expected"
        columnData.size() == 22

    }

    public dataSetReturnedFromIndexedColumnWithoutHeaderTest() {
        given: "I open a spreadsheet on the default worksheet"
        System.setProperty('test.data.dir', System.getProperty("user.dir")+DATA_FILE_LOCATION)
        ExcelDataReader excelDataReader = new ExcelDataReader(System.getProperty("test.data.dir"), DATA_FILE_NAME)

        when: "I retrieve data from a column by its index"
        List columnData = excelDataReader.getDataFromColumn(0, 1)

        then: "The column data is as expected"
        columnData.size() == 21
        columnData.get(0) == "Test 1 - Click Button 1"

    }

    public dataSetReturnedFromNamedRowTest() {
        given: "I open a spreadsheet on the default worksheet"
        System.setProperty('test.data.dir', System.getProperty("user.dir")+DATA_FILE_LOCATION)
        ExcelDataReader excelDataReader = new ExcelDataReader(System.getProperty("test.data.dir"), DATA_FILE_NAME)

        when: "I retrieve data from a column by its index"
        List rowData = excelDataReader.getDataFromRow("Test 2 - Click Button 2")

        then: "The row data is as expected"
        rowData.size() == 4
        rowData.get(0) == "Test 2 - Click Button 2"
        rowData.get(1) == "test"
        rowData.get(2) == "TEST"
        rowData.get(3) == new Date("Wed Jan 16 00:00:00 GMT 1980")
    }

    public dataSetReturnedFromNamedRowWithoutFirstParameterTest() {
        given: "I open a spreadsheet on the default worksheet"
        System.setProperty('test.data.dir', System.getProperty("user.dir")+DATA_FILE_LOCATION)
        ExcelDataReader excelDataReader = new ExcelDataReader(System.getProperty("test.data.dir"), DATA_FILE_NAME)

        when: "I retrieve data from a column by its index"
        List rowData = excelDataReader.getDataFromRow("Test 2 - Click Button 2", 1)

        then: "The row data is as expected"
        rowData.size() == 3
        rowData.get(0) == "test"
        rowData.get(1) == "TEST"
        rowData.get(2) == new Date("Wed Jan 16 00:00:00 GMT 1980")
    }

    public dataSetReturnedFromIndexedRowTest() {
        given: "I open a spreadsheet on the default worksheet"
        System.setProperty('test.data.dir', System.getProperty("user.dir")+DATA_FILE_LOCATION)
        ExcelDataReader excelDataReader = new ExcelDataReader(System.getProperty("test.data.dir"), DATA_FILE_NAME)

        when: "I retrieve data from a column by its index"
        List rowData = excelDataReader.getDataFromRow(5)

        then: "The row data is as expected"
        rowData.size() == 4
        rowData.get(0) == "Test 2 - Click Button 2"
        rowData.get(1) == "test"
        rowData.get(2) == "TEST"
        rowData.get(3) == new Date("Wed Jan 16 00:00:00 GMT 1980")
    }

    public dataSetReturnedFromIndexedRowWithoutFirstParameterTest() {
        given: "I open a spreadsheet on the default worksheet"
        System.setProperty('test.data.dir', System.getProperty("user.dir")+DATA_FILE_LOCATION)
        ExcelDataReader excelDataReader = new ExcelDataReader(System.getProperty("test.data.dir"), DATA_FILE_NAME)

        when: "I retrieve data from a column by its index"
        List rowData = excelDataReader.getDataFromRow(5, 1)

        then: "The row data is as expected"
        rowData.size() == 3
        rowData.get(0) == "test"
        rowData.get(1) == "TEST"
        rowData.get(2) == new Date("Wed Jan 16 00:00:00 GMT 1980")
    }

    public columnValueReturnedFromNamedRowTest() {

        given: "I open a spreadsheet and specify a worksheet"
        System.setProperty('test.data.dir', System.getProperty("user.dir")+ DATA_FILE_LOCATION)
        ExcelDataReader excelDataReader = new ExcelDataReader(System.getProperty("test.data.dir"), DATA_FILE_NAME , DATA_SHEET_2_NAME)

        when: "I retrieve single cell data by specifying a row as a string and the column as a number"
        String cellData = excelDataReader.getDataFromCell("Test 1 - Click Button 1", 0)

        then: "The column data is as expected"
        cellData == "Test 1 - Click Button 1"
    }
}
