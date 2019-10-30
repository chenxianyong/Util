package com.cxy.util;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * @author: ChenXianyong
 * @description: excel表格工具类
 * @date: 2019/10/29 15:17
 */
public class ExcelUtil {

  /**
   * @author: ChenXianyong
   * @description: TODO
   * @date: 2019/10/29 15:19
   * @param: []
   * @return: void
   */
  public void exportExcel(HttpServletResponse response, String fileName,
      List<Map<String, Object>> excelDate, Map<String, String> columns) {

    int rowNum = 0;
    int collIndex = 0;

    if (CollectionUtils.isNotEmpty(excelDate)) {
      rowNum = excelDate.get(0).size();
    }
    if (StringUtils.isBlank(fileName)) {
      fileName = String.valueOf(System.currentTimeMillis()) + ".xlsx";
    }
    OutputStream outputStream = null;
    Workbook workbook = new SXSSFWorkbook();

    try {
      response.reset();
      response.getOutputStream();
      response.setContentType("application/vnd.ms-excel; charset=utf-8");
      response.setCharacterEncoding("UTF-8");
      response.setHeader("Content-Disposition",
          "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
      Sheet sheet = workbook.createSheet();
      Row row = sheet.createRow(0);
      for (int i = 0; i < rowNum; i++) {
        row.createCell(i);
      }
      CellStyle titleStyle = createCellTitleStyle(workbook);

      List<String> dataKeys = new LinkedList();
      //表头放进  dataKeys
      dataKeys.addAll(columns.keySet());
      Iterator<String> titles = columns.values().iterator();
      while (titles.hasNext()) {
        Cell cell = row.getCell(collIndex);
        cell.setCellValue(titles.next());
        cell.setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, collIndex, collIndex));
        collIndex++;
      }
      exportFundsToExcel(sheet, null, 1, dataKeys, excelDate);
      workbook.write(outputStream);
      outputStream.flush();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (outputStream != null) {
          outputStream.close();
        }
        workbook.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

  public CellStyle createCellTitleStyle(Workbook workbook) {
    CellStyle cellStyle = workbook.createCellStyle();
    cellStyle.setDataFormat(workbook.createDataFormat().getFormat("@"));
    cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
    cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    Font font = workbook.createFont();
    font.setBold(true);
    cellStyle.setFont(font);
    return cellStyle;
  }

  public CellStyle createCellStyle(Workbook workbook) {
    CellStyle cellStyle = workbook.createCellStyle();
    cellStyle.setDataFormat(workbook.createDataFormat().getFormat("@"));
    return cellStyle;
  }

  public void exportFundsToExcel(Sheet sheet, List<String> titles, int rowIndex,
      List<String> dataKeys,
      List<Map<String, Object>> dataList) {
    CellStyle cellStyle = createCellStyle(sheet.getWorkbook());
    if (titles != null && titles.size() > 0) {
      CellStyle titleStyle = createCellTitleStyle(sheet.getWorkbook());
      Row row = sheet.createRow(rowIndex++);
      for (int i = 0; i < titles.size(); i++) {
        Cell cell = row.createCell(i, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(titleStyle);
        cell.setCellValue(titles.get(i));
      }
    }
    if (dataList != null) {
      Iterator<Map<String, Object>> datas = dataList.iterator();
      while (datas.hasNext()) {
        Map<String, Object> item = datas.next();
        Row row = sheet.createRow(rowIndex++);
        for (int i = 0; i < dataKeys.size(); i++) {
          String key = dataKeys.get(i);
          Object value = item.get(key);
          Cell cell = row.createCell(i);
          cell.setCellStyle(cellStyle);

          cell.setCellValue(value == null ? "" : value.toString());
        }
      }
    }

  }

}
