/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reports;


import DataBase.CarCare_DB;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;


public class Supplier_Report {

    private ResultSet resultSet;

    public void GetDataJTable() {
        try (PreparedStatement preparedStatement = CarCare_DB.MyCon().prepareStatement("SELECT * FROM supplier")) {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void closeResultSet() {
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void openFile(String file) {

        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void exportExcel(JTable S_table) {
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(S_table);
            File saveFile = jFileChooser.getSelectedFile();
            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("Supplier");
                Row rowCol = sheet.createRow(0);
                
                
                Font font = wb.createFont();
                font.setBold(true);
                font.setFontHeightInPoints((short) 14);
                font.setColor(IndexedColors.BLUE.getIndex());

                CellStyle style = wb.createCellStyle();
                style.setFont(font);
                int[] columnWidths = {5000,7000,13000,5000,5000,5000};


                for (int i = 0; i < S_table.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(S_table.getColumnName(i));
                    cell.setCellStyle(style);
                    
                    if (i < columnWidths.length) {
                        sheet.setColumnWidth(i, columnWidths[i]);
                    } else {
                        sheet.autoSizeColumn(i); // Auto-size if no specific width is set
                    }
                }

                int rowCount = 1;
                // Create a new ResultSet to avoid closing the original one
                try (ResultSet resultSetCopy = CarCare_DB.MyCon().prepareStatement("SELECT * FROM supplier").executeQuery()) {
                    while (resultSetCopy.next()) {
                        Row row = sheet.createRow(rowCount++);
                        for (int k = 0; k < S_table.getColumnCount(); k++) {
                            Cell cell = row.createCell(k);
                            if (S_table.getValueAt(rowCount - 2, k) != null) {
                                cell.setCellValue(S_table.getValueAt(rowCount - 2, k).toString());
                            }
                        }
                    }
                }

                FileOutputStream out = new FileOutputStream(saveFile);
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}



