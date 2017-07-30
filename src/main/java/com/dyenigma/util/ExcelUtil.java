package com.dyenigma.util;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel导入导出类
 * <p>
 * author Dyenigma
 * create 2016/4/2 18:44
 */
public class ExcelUtil<T> {
    Class<T> clazz;

    public ExcelUtil(Class<T> clazz) {
        this.clazz = clazz;
    }

    //excel导入
    public List<T> importExcel(String sheetName, InputStream input) {
        List<T> list = new ArrayList<>();
        try {
            Workbook book = Workbook.getWorkbook(input);
            Sheet sheet = null;
            if (!sheetName.trim().equals("")) {
                sheet = book.getSheet(sheetName);// 根据名称获取sheet
            }
            if (sheet == null) {
                sheet = book.getSheet(0);// 根据索引获取sheet
            }
            int rows = sheet.getRows();// 获取行数
            if (rows > 0) {
                Field[] allFields = clazz.getDeclaredFields();//获取类的字段属性
                Map<Integer, Field> fieldsMap = new HashMap<>();
                for (Field field : allFields) {
                    if (field.isAnnotationPresent(ExcelVOAttribute.class)) {
                        ExcelVOAttribute attr = field
                                .getAnnotation(ExcelVOAttribute.class);
                        int col = getExcelCol(attr.column());
                        field.setAccessible(true);
                        fieldsMap.put(col, field);
                    }
                }
                for (int i = 1; i < rows; i++) {
                    Cell[] cells = sheet.getRow(i);
                    T entity = null;
                    for (int j = 0; j < cells.length; j++) {
                        String c = cells[j].getContents();
                        if (c.equals("")) {
                            continue;
                        }
                        entity = (entity == null ? clazz.newInstance() : entity);
                        Field field = fieldsMap.get(j);
                        Class<?> fieldType = field.getType();
                        if ((Integer.TYPE == fieldType)
                                || (Integer.class == fieldType)) {
                            field.set(entity, Integer.parseInt(c));
                        } else if (String.class == fieldType) {
                            field.set(entity, String.valueOf(c));
                        } else if ((Long.TYPE == fieldType)
                                || (Long.class == fieldType)) {
                            field.set(entity, Long.valueOf(c));
                        } else if ((Float.TYPE == fieldType)
                                || (Float.class == fieldType)) {
                            field.set(entity, Float.valueOf(c));
                        } else if ((Short.TYPE == fieldType)
                                || (Short.class == fieldType)) {
                            field.set(entity, Short.valueOf(c));
                        } else if ((Double.TYPE == fieldType)
                                || (Double.class == fieldType)) {
                            field.set(entity, Double.valueOf(c));
                        } else if (Character.TYPE == fieldType) {
                            if ((c != null) && (c.length() > 0)) {
                                field.set(entity, Character.valueOf(c.charAt(0)));
                            }
                        }

                    }
                    if (entity != null) {
                        list.add(entity);
                    }
                }
            }

        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return list;
    }

    //导出excel
    public boolean exportExcel(List<T> list, String sheetName, int sheetSize, OutputStream output) {

        Field[] allFields = clazz.getDeclaredFields();
        List<Field> fields = new ArrayList<>();
        for (Field field : allFields) {
            if (field.isAnnotationPresent(ExcelVOAttribute.class)) {
                fields.add(field);
            }
        }

        HSSFWorkbook workbook = new HSSFWorkbook();

        // excel2003最大行数65536
        if (sheetSize > 65536 || sheetSize < 1) {
            sheetSize = 65536;
        }
        double sheetNo = Math.ceil(list.size() / sheetSize);
        for (int index = 0; index <= sheetNo; index++) {
            HSSFSheet sheet = workbook.createSheet();
            workbook.setSheetName(index, sheetName + index);
            HSSFRow row;
            HSSFCell cell;

            row = sheet.createRow(0);
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
                int col = getExcelCol(attr.column());
                cell = row.createCell(col);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(attr.name());
                if (!attr.prompt().trim().equals("")) {
                    setHSSFPrompt(sheet, "", attr.prompt(), 1, 100, col, col);
                }
                if (attr.combo().length > 0) {
                    setHSSFValidation(sheet, attr.combo(), 1, 100, col, col);
                }
            }

            int startNo = index * sheetSize;
            int endNo = Math.min(startNo + sheetSize, list.size());
            for (int i = startNo; i < endNo; i++) {
                row = sheet.createRow(i + 1 - startNo);
                T vo = list.get(i);
                for (int j = 0; j < fields.size(); j++) {
                    Field field = fields.get(j);
                    field.setAccessible(true);
                    ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
                    try {
                        if (attr.isExport()) {
                            cell = row.createCell(getExcelCol(attr.column()));
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellValue(field.get(vo) == null ? "" : String.valueOf(field.get(vo)));
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        try {
            output.flush();
            workbook.write(output);
            output.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    //获取excel转化到数字 A->1 B->2
    public static int getExcelCol(String col) {
        col = col.toUpperCase();
        int count = -1;
        char[] cs = col.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            count += (cs[i] - 64) * Math.pow(26, cs.length - 1 - i);
        }
        return count;
    }

    //设置提示信息
    public static HSSFSheet setHSSFPrompt(HSSFSheet sheet, String promptTitle, String promptContent,
                                          int firstRow, int endRow, int firstCol, int endCol) {
        DVConstraint constraint = DVConstraint.createCustomFormulaConstraint("DD1");
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        HSSFDataValidation data_validation_view = new HSSFDataValidation(regions, constraint);
        data_validation_view.createPromptBox(promptTitle, promptContent);
        sheet.addValidationData(data_validation_view);
        return sheet;
    }

    //设置验证行数据
    public static HSSFSheet setHSSFValidation(HSSFSheet sheet, String[] textlist,
                                              int firstRow, int endRow, int firstCol, int endCol) {
        DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        HSSFDataValidation data_validation_list = new HSSFDataValidation(regions, constraint);
        sheet.addValidationData(data_validation_list);
        return sheet;
    }
}
