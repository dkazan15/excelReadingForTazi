import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class main {


    public static void main(String[] args) throws Exception {

        try (
                InputStream is = new FileInputStream(new File("/Users/dogakazan/Downloads/excel-streaming-reader-master/src/test/resources/SAG_BULK_DATA.xlsx"));
                Workbook wb = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is);
        ) {

            Iterator<Row> rowIterator = wb.getSheetAt(0).rowIterator();

            Row header = rowIterator.next();

            List<Cell> listOfKeys = new ArrayList<>();

            List<Cell> listOfKeys2 = new ArrayList<>();

            List<Cell> listOfValues = new ArrayList<>();

            Map<String, String> mapOfKeysAndValues = new HashMap<>();


            int counter = 0;

            for (Cell c : header) {
                listOfKeys.add(c);
                //System.out.print(c.getStringCellValue() + " ");
            }

            System.out.println();

            for (Row r : wb.getSheetAt(0)) {
                counter++;
                if (counter == 100) {
                    break;
                }
                for (Cell c : r) {
                    listOfValues.add(c);
                    //System.out.print(c.getStringCellValue()+"," );
                }
                System.out.println();
            }
            for(int i=0; i< listOfValues.size()/listOfKeys.size();i++){
                for(Cell c: header){
                    listOfKeys2.add(c);
                }
            }
            for(int j = 0; j<listOfValues.size();j++){
                mapOfKeysAndValues.put(listOfKeys2.get(j).getStringCellValue(),listOfValues.get(j).getStringCellValue());
            }
        }
    }
}
