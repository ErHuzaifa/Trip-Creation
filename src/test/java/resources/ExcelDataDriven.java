package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataDriven {

	/**
	 * This method fetches data from excel sheet and returns them in an array list.
	 * 
	 * @param TestCaseSheetName Name of the sheet where your data is stored in
	 *                          TestData.xlsx
	 * @param testcaseName      Name of the test case you want to fetch data from
	 * @return Array list of data fetched from TestData.xlsx file.
	 * @throws IOException
	 * 
	 */
	public ArrayList<String> getData(String TestCaseSheetName, String testcaseName) throws IOException {
		// fileInputStream argument
		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fis = new FileInputStream("TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		/*
		 * The following code iterates through the sheets and fetches the sheet where
		 * your data is stored based on the TestCaseSheetName passed.
		 */
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(TestCaseSheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				/*
				 * Following code identifies the test case from the TestCase column and fetches
				 * the row value of the test case and then gets all the data of that row in an
				 * array list.
				 * 
				 * Note:- Always keep your test cases in the first column and give the name of
				 * the test case logically so that its easy for you and others to understand
				 * which test case it belongs to.
				 */
				Iterator<Row> rows = sheet.iterator();
				int column = 0;
				/*Iterator<Cell> ce=firstrow.cellIterator();//row is collection of cells
				int k=0;
				int column = 0;
			while(ce.hasNext())
			{
				Cell value=ce.next();
				
				if(value.getStringCellValue().equalsIgnoreCase("TestCases"))
				{
					column=k;
					
				}
				
				k++;
			}*/
				while (rows.hasNext()) {

					Row r = rows.next();

					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {

						/*
						 * After you grab test case row = pull all the data of that row and add it into
						 * array list.
						 */

						Iterator<Cell> cv = r.cellIterator();
						cv.next();
						while (cv.hasNext()) {
							Cell c = cv.next();

							if (c.getCellType() == CellType.STRING) {

								a.add(c.getStringCellValue());
							} else {

								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}
						}
					}

				}
			}
			workbook.close();
		}

		return a;

	}

}