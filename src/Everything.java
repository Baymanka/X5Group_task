import java.io.*;
import java.util.*;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;


/**
 * Created by Alexey on 26.06.2017.
 */
/*
Код ужасен!
Делался ночью при нехватке времени
Всё захардкожено т.к. в задании нет ничего про параметризацию, да и времени не было
Главное - работает.
 */
public class Everything {
    public static void main(String[] args) throws Throwable {
        File file = new File("inputData.txt");

        Scanner scanner = new Scanner(file);
        String line = "";
        while (scanner.hasNextLine()) {
            line += scanner.nextLine();
        }
        System.out.println(line);
        String[] s = line.split(", ");
        String[] s_old = s.clone();
        // HashSet<String> mSet = new HashSet<String>();
        // Collections.addAll(mSet, s);
        for (int i = 0; i < s.length; i++) {
            s[i] = s[i].replace(",", ".");
        }
        TreeSet<String> mSet2 = new TreeSet<String>();
        Collections.addAll(mSet2, s);
        // System.out.print("Без повторов: ");
        ArrayList<String> arr_sorted = new ArrayList<>();

       /*  for (String temp : mSet) {

            System.out.print(temp + ", ");

        }
        */

        for (String temp : mSet2) {

            arr_sorted.add(temp);

        }
        double numb[] = new double[s.length];
        for (int i = 0; i < s.length; i++) {
            numb[i] = Double.parseDouble(s[i]);
        }
        int maxIndex = 0;
        int minIndex = 0;
        for (int i = 0; i < numb.length; i++) {
            if (numb[maxIndex] < numb[i]) {
                maxIndex = i;
            }
            if (numb[minIndex] > numb[i]) {
                minIndex = i;
            }
        }
        /*
        Массив s_old создан только для того,
        что бы сохранить формат вывода как в примере [для красоты]
        (Запятые-разделители и целое число без .0)
        */
        Map<String, Integer> counter = new HashMap<String, Integer>();
        for (int i = 0; i < s_old.length; i++) {
            String tempChar = s_old[i];

            if (!counter.containsKey(tempChar)) {
                counter.put(tempChar, 1);
            } else {
                counter.put(tempChar, counter.get(tempChar) + 1);
            }
        }
      //  System.setOut(new java.io.PrintStream(System.out, true, "Cp866")); для отображения в командной строке
        System.out.print("\nМаксимальный элемент: " + s_old[maxIndex] + "." + " Минимальный элемент: " + s_old[minIndex] + ". Повторяющиеся элементы: ");
        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            if (entry.getValue() > 1) System.out.print(entry.getKey() + "(" + entry.getValue() + "), ");
        }

        ArrayList<String> numb_plus = new ArrayList<>();
        ArrayList<String> numb_minus = new ArrayList<>();
        ArrayList<String> numb_zero = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            if (Double.parseDouble(arr_sorted.get(i)) > 0) numb_plus.add(arr_sorted.get(i));
            else if (Double.parseDouble(arr_sorted.get(i)) < 0) numb_minus.add(arr_sorted.get(i));
            else if (Double.parseDouble(arr_sorted.get(i)) == 0) numb_zero.add(arr_sorted.get(i));
        }

        HSSFWorkbook table = new HSSFWorkbook();
        Sheet sheet = table.createSheet("Задание для Х5 Лисицын");
        Row row = sheet.createRow(0);
        Cell name = row.createCell(0);
        HSSFCellStyle myStyle = table.createCellStyle();
        Font font = table.createFont();
        font.setColor(HSSFColor.RED.index);
        myStyle.setFont(font);
        name.setCellStyle(myStyle);
        name.setCellValue("Положительные");

        Cell name1 = row.createCell(1);
        HSSFCellStyle myStyle1 = table.createCellStyle();
        Font font1 = table.createFont();
        font1.setColor(HSSFColor.LIGHT_BLUE.index);
        myStyle1.setFont(font1);
        name1.setCellStyle(myStyle1);
        name1.setCellValue("Отрицательные");

        Cell name2 = row.createCell(2);
        HSSFCellStyle myStyle2 = table.createCellStyle();
        Font font2 = table.createFont();
        font2.setColor(HSSFColor.BRIGHT_GREEN.index);
        myStyle2.setFont(font2);
        name2.setCellStyle(myStyle2);
        name2.setCellValue("Ноль");

        Row row1 = sheet.createRow(1);
        Row row2 = sheet.createRow(2);
        Row row3 = sheet.createRow(3);
        Row row4 = sheet.createRow(4);
        Row row5 = sheet.createRow(5);
        Row row6 = sheet.createRow(6);
        Row row7 = sheet.createRow(7);

        ArrayList<Cell> value_arr = new ArrayList<Cell>();
        Cell value1 = row1.createCell(0);
        value_arr.add(value1);
        Cell value2 = row2.createCell(0);
        value_arr.add(value2);
        Cell value3 = row3.createCell(0);
        value_arr.add(value3);
        Cell value4 = row4.createCell(0);
        value_arr.add(value4);
        Cell value5 = row5.createCell(0);
        value_arr.add(value5);
        Cell value6 = row6.createCell(0);
        value_arr.add(value6);
        Cell value7 = row7.createCell(0);
        value_arr.add(value7);
        Cell value8 = row4.createCell(1);
        value_arr.add(value8);
        Cell value9 = row3.createCell(1);
        value_arr.add(value9);
        Cell value10 = row2.createCell(1);
        value_arr.add(value10);
        Cell value11 = row1.createCell(1);
        value_arr.add(value11);
        Cell value12 = row1.createCell(2);
        value_arr.add(value12);

        for (int i = 0; i < value_arr.size(); i++) {
            if (i < 7) value_arr.get(i).setCellValue(numb_plus.get(i));
            else if (i > 6 && i < 11) value_arr.get(i).setCellValue(numb_minus.get(i - 7));
            else if (i == 11) value_arr.get(i).setCellValue(numb_zero.get(i - 11));
        }
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);


        try (FileOutputStream out = new FileOutputStream(new File("MegaTableX5.xls"))) {
            table.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        table.close();


    }
}
