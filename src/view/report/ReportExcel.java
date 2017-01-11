package view.report;

import java.awt.Desktop;
import java.io.File;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.*;

import java.util.Map;
import java.util.HashMap;
import java.io.FileOutputStream;
import java.util.List;
import model.domain.ActivoFijo;
import model.domain.Grupo;
import model.domain.Oficina;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jc.JCCalendar;
import static view.report.ReportExcel.headerOrdenadoCodigo;

/**
 * Clase para generar los reportes en Excel
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 */
public class ReportExcel {

    public static String[] headerOrdenadoCodigo = new String[]{"Codigo", "Descripción", "Costo Historico", "Dia", "Mes", "Año",
        "Costo Final Actualizado", "Descripción Gestión", "Des. Acumulada Total", "Valor Neto", "Grupo Contable", "Aux. de Grupo", "Oficina", "Responsable"};
    public static String[] headerOrdenadoGrupo = new String[]{"Codigo", "Descripción", "Costo Historico", "Dia", "Mes", "Año",
        "Indice UFV", "Vida Util", "Descripción Gestión", "Dias Consumidos", "Factor Actualizado", "Costo Total Actualizado", "Depreciacion Acumulada",
        "Valor Neto", "Grupo Contable", "Aux. de Grupo", "Oficina", "Responsable"};
    public static String[] headerOrdenadoOficina = new String[]{"Codigo", "Descripción", "Costo Historico", "Dia", "Mes", "Año",
        "Costo Total Actualizado", "Depreciación Acumulada Total", "Valor Neto", "Grupo Contable", "Aux. de Grupo", "Responsable", "Observaciones"};
    public static String[] headerResumenGrupo = new String[]{"Grupo Contable", "Cantidad", "Vida Util", "Costo Historico", "Costo Actualizado Inicial", "Depreciacion Acumulada Total de Grupo", "Valor Neto Inicial",
        "Actualizacion Gestion", "Costo Total Actualizado", "Depreciacion Gestion", "Actualiazacion Depreciacion Acumulada", "Depreciacion Acumulada", "Valor Neto"};

    public static void reportOrdenadoCodigo(String title, List data) throws Exception {
        Workbook wb = new XSSFWorkbook();

        Map<String, CellStyle> styles = createStyles(wb);

        Sheet sheet = wb.createSheet(title);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);

        //title row
        Row titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(45);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(title);
        titleCell.setCellStyle(styles.get("title"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$N$1"));

        //header row
        Row headerRow = sheet.createRow(1);
        headerRow.setHeightInPoints(40);
        Cell headerCell;
        for (int i = 0; i < headerOrdenadoCodigo.length; i++) {
            headerCell = headerRow.createCell(i);
            headerCell.setCellValue(headerOrdenadoCodigo[i]);
            headerCell.setCellStyle(styles.get("header"));
        }

        int rownum = 2;
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(rownum++);
            for (int j = 0; j < headerOrdenadoCodigo.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellStyle(styles.get("cell"));
            }
            ActivoFijo activoFijo = (ActivoFijo) data.get(i);
            row.getCell(0).setCellValue(activoFijo.getIdActivoFijo());
            row.getCell(1).setCellValue(activoFijo.getDescripcion());
            row.getCell(2).setCellValue(activoFijo.getCostoInicial());
            JCCalendar calendar = new JCCalendar(activoFijo.getIncorporacion());
            row.getCell(3).setCellValue(calendar.getDayOfMonth());
            row.getCell(4).setCellValue(calendar.getMonthInt());
            row.getCell(5).setCellValue(calendar.getYear());
            row.getCell(6).setCellValue(activoFijo.getValor());
            row.getCell(7).setCellValue(activoFijo.getDepreciacionGestion());
            row.getCell(8).setCellValue(activoFijo.getDepreciacionAcumulada());
            row.getCell(9).setCellValue(activoFijo.getValorNeto());
            row.getCell(10).setCellValue(activoFijo.getGrupo().getNombre());
            row.getCell(11).setCellValue(activoFijo.getAuxiliar().getNombre());
            row.getCell(12).setCellValue(activoFijo.getOficina().getNombre());
            row.getCell(13).setCellValue(activoFijo.getResponsable().getNombre());
        }
        sheet.setColumnWidth(0, 256 * 20);
        sheet.setColumnWidth(1, 256 * 70);
        sheet.setColumnWidth(2, 256 * 15);
        sheet.setColumnWidth(3, 256 * 6);
        sheet.setColumnWidth(4, 256 * 6);
        sheet.setColumnWidth(5, 256 * 10);
        sheet.setColumnWidth(6, 256 * 15);
        sheet.setColumnWidth(7, 256 * 15);
        sheet.setColumnWidth(8, 256 * 15);
        sheet.setColumnWidth(9, 256 * 15);
        sheet.setColumnWidth(10, 256 * 30);
        sheet.setColumnWidth(11, 256 * 30);
        sheet.setColumnWidth(12, 256 * 30);
        sheet.setColumnWidth(13, 256 * 30);
        // Write the output to a file
        String file = "report/" + title + ".xls";
        if (wb instanceof XSSFWorkbook) {
            file += "x";
        }
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();
        File path = new File(file);
        Desktop.getDesktop().open(path);
    }

    public static void reportOrdenadoGrupo(String title, List data, List grupos) throws Exception {
        Workbook wb = new XSSFWorkbook();

        Map<String, CellStyle> styles = createStyles(wb);
        for (Object o : grupos) {
            Grupo grupo = (Grupo) o;

            Sheet sheet = wb.createSheet(grupo.getNombre());
            PrintSetup printSetup = sheet.getPrintSetup();
            printSetup.setLandscape(true);
            sheet.setFitToPage(true);
            sheet.setHorizontallyCenter(true);

            //title row
            Row titleRow = sheet.createRow(0);
            titleRow.setHeightInPoints(45);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue(title);
            titleCell.setCellStyle(styles.get("title"));
            sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$M$1"));

            //header row
            Row headerRow = sheet.createRow(1);
            headerRow.setHeightInPoints(40);
            Cell headerCell;
            for (int i = 0; i < headerOrdenadoGrupo.length; i++) {
                headerCell = headerRow.createCell(i);
                headerCell.setCellValue(headerOrdenadoGrupo[i]);
                headerCell.setCellStyle(styles.get("header"));
            }


            int rownum = 2;
            for (int i = 0; i < data.size(); i++) {
                ActivoFijo activoFijo = (ActivoFijo) data.get(i);
                if (grupo.getIdGrupo() == activoFijo.getGrupo().getIdGrupo()) {
                    Row row = sheet.createRow(rownum++);
                    for (int j = 0; j < headerOrdenadoGrupo.length; j++) {
                        Cell cell = row.createCell(j);
                        cell.setCellStyle(styles.get("cell"));
                    }
                    row.getCell(0).setCellValue(activoFijo.getIdActivoFijo());
                    row.getCell(1).setCellValue(activoFijo.getDescripcion());
                    row.getCell(2).setCellValue(activoFijo.getCostoInicial());
                    JCCalendar calendar = new JCCalendar(activoFijo.getIncorporacion());
                    row.getCell(3).setCellValue(calendar.getDayOfMonth());
                    row.getCell(4).setCellValue(calendar.getMonthInt());
                    row.getCell(5).setCellValue(calendar.getYear());
                    row.getCell(6).setCellValue(activoFijo.getIndiceUFV());
                    row.getCell(7).setCellValue(activoFijo.getVidaUtil());
                    row.getCell(8).setCellValue(activoFijo.getDepreciacionGestion());
                    row.getCell(9).setCellValue(activoFijo.getDias());
                    row.getCell(10).setCellValue(activoFijo.getFactorActual());
                    row.getCell(11).setCellValue(activoFijo.getValor());
                    row.getCell(12).setCellValue(activoFijo.getDepreciacionAcumulada());
                    row.getCell(13).setCellValue(activoFijo.getValorNeto());
                    row.getCell(14).setCellValue(activoFijo.getAuxiliar().getNombre());
                    row.getCell(15).setCellValue(activoFijo.getOficina().getNombre());
                    row.getCell(16).setCellValue(activoFijo.getResponsable().getNombre());
                }
            }

            sheet.setColumnWidth(0, 256 * 20);
            sheet.setColumnWidth(1, 256 * 70);
            sheet.setColumnWidth(2, 256 * 15);
            sheet.setColumnWidth(3, 256 * 6);
            sheet.setColumnWidth(4, 256 * 6);
            sheet.setColumnWidth(5, 256 * 10);
            sheet.setColumnWidth(6, 256 * 15);
            sheet.setColumnWidth(7, 256 * 15);
            sheet.setColumnWidth(8, 256 * 15);
            sheet.setColumnWidth(9, 256 * 15);
            sheet.setColumnWidth(10, 256 * 15);
            sheet.setColumnWidth(11, 256 * 15);
            sheet.setColumnWidth(12, 256 * 15);
            sheet.setColumnWidth(13, 256 * 15);
            sheet.setColumnWidth(14, 256 * 30);
            sheet.setColumnWidth(15, 256 * 30);
            sheet.setColumnWidth(16, 256 * 30);
        }
        // Write the output to a file
        String file = "report/" + title + ".xls";
        if (wb instanceof XSSFWorkbook) {
            file += "x";
        }
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();
        File path = new File(file);
        Desktop.getDesktop().open(path);
    }

    public static void reportOrdenadoOficina(String title, List data, List oficinas) throws Exception {
        Workbook wb = new XSSFWorkbook();

        Map<String, CellStyle> styles = createStyles(wb);
        for (Object o : oficinas) {
            Oficina oficina = (Oficina) o;

            Sheet sheet = wb.createSheet(oficina.getNombre());
            PrintSetup printSetup = sheet.getPrintSetup();
            printSetup.setLandscape(true);
            sheet.setFitToPage(true);
            sheet.setHorizontallyCenter(true);

            //title row
            Row titleRow = sheet.createRow(0);
            titleRow.setHeightInPoints(45);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue(title);
            titleCell.setCellStyle(styles.get("title"));
            sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$N$1"));

            //header row
            Row headerRow = sheet.createRow(1);
            headerRow.setHeightInPoints(40);
            Cell headerCell;
            for (int i = 0; i < headerOrdenadoOficina.length; i++) {
                headerCell = headerRow.createCell(i);
                headerCell.setCellValue(headerOrdenadoOficina[i]);
                headerCell.setCellStyle(styles.get("header"));
            }

            int rownum = 2;
            for (int i = 0; i < data.size(); i++) {
                ActivoFijo activoFijo = (ActivoFijo) data.get(i);
                if (oficina.getIdOficina() == activoFijo.getOficina().getIdOficina()) {
                    Row row = sheet.createRow(rownum++);
                    for (int j = 0; j < headerOrdenadoOficina.length; j++) {
                        Cell cell = row.createCell(j);
                        cell.setCellStyle(styles.get("cell"));
                    }
                    row.getCell(0).setCellValue(activoFijo.getIdActivoFijo());
                    row.getCell(1).setCellValue(activoFijo.getDescripcion());
                    row.getCell(2).setCellValue(activoFijo.getCostoInicial());
                    JCCalendar calendar = new JCCalendar(activoFijo.getIncorporacion());
                    row.getCell(3).setCellValue(calendar.getDayOfMonth());
                    row.getCell(4).setCellValue(calendar.getMonthInt());
                    row.getCell(5).setCellValue(calendar.getYear());
                    row.getCell(6).setCellValue(activoFijo.getValor());
                    row.getCell(7).setCellValue(activoFijo.getDepreciacionAcumulada());
                    row.getCell(8).setCellValue(activoFijo.getValorNeto());
                    row.getCell(9).setCellValue(activoFijo.getGrupo().getNombre());
                    row.getCell(10).setCellValue(activoFijo.getAuxiliar().getNombre());
                    row.getCell(11).setCellValue(activoFijo.getResponsable().getNombre());
                    row.getCell(12).setCellValue(activoFijo.getObservaciones());
                }
            }

            sheet.setColumnWidth(0, 256 * 20);
            sheet.setColumnWidth(1, 256 * 70);
            sheet.setColumnWidth(2, 256 * 15);
            sheet.setColumnWidth(3, 256 * 6);
            sheet.setColumnWidth(4, 256 * 6);
            sheet.setColumnWidth(5, 256 * 10);
            sheet.setColumnWidth(6, 256 * 15);
            sheet.setColumnWidth(7, 256 * 15);
            sheet.setColumnWidth(8, 256 * 15);
            sheet.setColumnWidth(9, 256 * 15);
            sheet.setColumnWidth(10, 256 * 15);
            sheet.setColumnWidth(11, 256 * 15);
            sheet.setColumnWidth(12, 256 * 15);
        }
        // Write the output to a file
        String file = "report/" + title + ".xls";
        if (wb instanceof XSSFWorkbook) {
            file += "x";
        }
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();
        File path = new File(file);
        Desktop.getDesktop().open(path);
    }

    public static void reportTranfer(String title, List data) throws Exception {
        Workbook wb = new XSSFWorkbook();

        Map<String, CellStyle> styles = createStyles(wb);

        Sheet sheet = wb.createSheet(title);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);

        //title row
        Row titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(45);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(title);
        titleCell.setCellStyle(styles.get("title"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$N$1"));

        //header row
        Row headerRow = sheet.createRow(1);
        headerRow.setHeightInPoints(40);
        Cell headerCell;
        for (int i = 0; i < headerOrdenadoCodigo.length; i++) {
            headerCell = headerRow.createCell(i);
            headerCell.setCellValue(headerOrdenadoCodigo[i]);
            headerCell.setCellStyle(styles.get("header"));
        }

        int rownum = 2;
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(rownum++);
            for (int j = 0; j < headerOrdenadoCodigo.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellStyle(styles.get("cell"));
                ActivoFijo activoFijo = (ActivoFijo) data.get(i);
                row.getCell(0).setCellValue(activoFijo.getIdActivoFijo());
                row.getCell(1).setCellValue(activoFijo.getDescripcion());
                row.getCell(2).setCellValue(activoFijo.getCostoInicial());
                JCCalendar calendar = new JCCalendar(activoFijo.getIncorporacion());
                row.getCell(3).setCellValue(calendar.getDayOfMonth());
                row.getCell(4).setCellValue(calendar.getMonthInt());
                row.getCell(5).setCellValue(calendar.getYear());
                row.getCell(6).setCellValue(activoFijo.getValor());
                row.getCell(7).setCellValue(activoFijo.getDepreciacionGestion());
                row.getCell(8).setCellValue(activoFijo.getDepreciacionAcumulada());
                row.getCell(9).setCellValue(activoFijo.getValorNeto());
                row.getCell(10).setCellValue(activoFijo.getGrupo().getNombre());
                row.getCell(11).setCellValue(activoFijo.getAuxiliar().getNombre());
                row.getCell(12).setCellValue(activoFijo.getOficina().getNombre());
                row.getCell(13).setCellValue(activoFijo.getResponsable().getNombre());
            }
        }

        sheet.setColumnWidth(0, 256 * 20);
        sheet.setColumnWidth(1, 256 * 70);
        sheet.setColumnWidth(2, 256 * 15);
        sheet.setColumnWidth(3, 256 * 6);
        sheet.setColumnWidth(4, 256 * 6);
        sheet.setColumnWidth(5, 256 * 10);
        sheet.setColumnWidth(6, 256 * 15);
        sheet.setColumnWidth(7, 256 * 15);
        sheet.setColumnWidth(8, 256 * 15);
        sheet.setColumnWidth(9, 256 * 15);
        sheet.setColumnWidth(10, 256 * 30);
        sheet.setColumnWidth(11, 256 * 30);
        sheet.setColumnWidth(12, 256 * 30);
        sheet.setColumnWidth(13, 256 * 30);
        // Write the output to a file
        String file = "report/" + title + ".xls";
        if (wb instanceof XSSFWorkbook) {
            file += "x";
        }
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();
        File path = new File(file);
        Desktop.getDesktop().open(path);
    }

    public static void reportBaja(String title, List data) throws Exception {
        Workbook wb = new XSSFWorkbook();

        Map<String, CellStyle> styles = createStyles(wb);

        Sheet sheet = wb.createSheet(title);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);

        //title row
        Row titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(45);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(title);
        titleCell.setCellStyle(styles.get("title"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$N$1"));

        //header row
        Row headerRow = sheet.createRow(1);
        headerRow.setHeightInPoints(40);
        Cell headerCell;
        for (int i = 0; i < headerOrdenadoCodigo.length; i++) {
            headerCell = headerRow.createCell(i);
            headerCell.setCellValue(headerOrdenadoCodigo[i]);
            headerCell.setCellStyle(styles.get("header"));
        }

        int rownum = 2;
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(rownum++);
            for (int j = 0; j < headerOrdenadoCodigo.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellStyle(styles.get("cell"));
            }
        }

        //set sample data
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.getRow(2 + i);
            ActivoFijo activoFijo = (ActivoFijo) data.get(i);
            row.getCell(0).setCellValue(activoFijo.getIdActivoFijo());
            row.getCell(1).setCellValue(activoFijo.getDescripcion());
            row.getCell(2).setCellValue(activoFijo.getCostoInicial());
            JCCalendar calendar = new JCCalendar(activoFijo.getIncorporacion());
            row.getCell(3).setCellValue(calendar.getDayOfMonth());
            row.getCell(4).setCellValue(calendar.getMonthInt());
            row.getCell(5).setCellValue(calendar.getYear());
            row.getCell(6).setCellValue(activoFijo.getValor());
            row.getCell(7).setCellValue(activoFijo.getDepreciacionGestion());
            row.getCell(8).setCellValue(activoFijo.getDepreciacionAcumulada());
            row.getCell(9).setCellValue(activoFijo.getValorNeto());
            row.getCell(10).setCellValue(activoFijo.getGrupo().getNombre());
            row.getCell(11).setCellValue(activoFijo.getAuxiliar().getNombre());
            row.getCell(12).setCellValue(activoFijo.getOficina().getNombre());
            row.getCell(13).setCellValue(activoFijo.getResponsable().getNombre());
        }
        sheet.setColumnWidth(0, 256 * 20);
        sheet.setColumnWidth(1, 256 * 70);
        sheet.setColumnWidth(2, 256 * 15);
        sheet.setColumnWidth(3, 256 * 6);
        sheet.setColumnWidth(4, 256 * 6);
        sheet.setColumnWidth(5, 256 * 10);
        sheet.setColumnWidth(6, 256 * 15);
        sheet.setColumnWidth(7, 256 * 15);
        sheet.setColumnWidth(8, 256 * 15);
        sheet.setColumnWidth(9, 256 * 15);
        sheet.setColumnWidth(10, 256 * 30);
        sheet.setColumnWidth(11, 256 * 30);
        sheet.setColumnWidth(12, 256 * 30);
        sheet.setColumnWidth(13, 256 * 30);
        // Write the output to a file
        String file = "report/" + title + ".xls";
        if (wb instanceof XSSFWorkbook) {
            file += "x";
        }
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();
        File path = new File(file);
        Desktop.getDesktop().open(path);
    }

    public static void reportRevaluos(String title, List data) throws Exception {
        Workbook wb = new XSSFWorkbook();

        Map<String, CellStyle> styles = createStyles(wb);

        Sheet sheet = wb.createSheet(title);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);

        //title row
        Row titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(45);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(title);
        titleCell.setCellStyle(styles.get("title"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$N$1"));

        //header row
        Row headerRow = sheet.createRow(1);
        headerRow.setHeightInPoints(40);
        Cell headerCell;
        for (int i = 0; i < headerOrdenadoCodigo.length; i++) {
            headerCell = headerRow.createCell(i);
            headerCell.setCellValue(headerOrdenadoCodigo[i]);
            headerCell.setCellStyle(styles.get("header"));
        }

        int rownum = 2;
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(rownum++);
            for (int j = 0; j < headerOrdenadoCodigo.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellStyle(styles.get("cell"));
            }
        }

        //set sample data
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.getRow(2 + i);
            ActivoFijo activoFijo = (ActivoFijo) data.get(i);
            row.getCell(0).setCellValue(activoFijo.getIdActivoFijo());
            row.getCell(1).setCellValue(activoFijo.getDescripcion());
            row.getCell(2).setCellValue(activoFijo.getCostoInicial());
            JCCalendar calendar = new JCCalendar(activoFijo.getIncorporacion());
            row.getCell(3).setCellValue(calendar.getDayOfMonth());
            row.getCell(4).setCellValue(calendar.getMonthInt());
            row.getCell(5).setCellValue(calendar.getYear());
            row.getCell(6).setCellValue(activoFijo.getValor());
            row.getCell(7).setCellValue(activoFijo.getDepreciacionGestion());
            row.getCell(8).setCellValue(activoFijo.getDepreciacionAcumulada());
            row.getCell(9).setCellValue(activoFijo.getValorNeto());
            row.getCell(10).setCellValue(activoFijo.getGrupo().getNombre());
            row.getCell(11).setCellValue(activoFijo.getAuxiliar().getNombre());
            row.getCell(12).setCellValue(activoFijo.getOficina().getNombre());
            row.getCell(13).setCellValue(activoFijo.getResponsable().getNombre());
        }
        sheet.setColumnWidth(0, 256 * 20);
        sheet.setColumnWidth(1, 256 * 70);
        sheet.setColumnWidth(2, 256 * 15);
        sheet.setColumnWidth(3, 256 * 6);
        sheet.setColumnWidth(4, 256 * 6);
        sheet.setColumnWidth(5, 256 * 10);
        sheet.setColumnWidth(6, 256 * 15);
        sheet.setColumnWidth(7, 256 * 15);
        sheet.setColumnWidth(8, 256 * 15);
        sheet.setColumnWidth(9, 256 * 15);
        sheet.setColumnWidth(10, 256 * 30);
        sheet.setColumnWidth(11, 256 * 30);
        sheet.setColumnWidth(12, 256 * 30);
        sheet.setColumnWidth(13, 256 * 30);
        // Write the output to a file
        String file = "report/" + title + ".xls";
        if (wb instanceof XSSFWorkbook) {
            file += "x";
        }
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();
        File path = new File(file);
        Desktop.getDesktop().open(path);
    }

    public static void reportResumenGrupo(String title, List data, List dataAnterior) throws Exception {
        Workbook wb = new XSSFWorkbook();

        Map<String, CellStyle> styles = createStyles(wb);

        Sheet sheet = wb.createSheet(title);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);

        //title row
        Row titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(45);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(title);
        titleCell.setCellStyle(styles.get("title"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$M$1"));

        //header row
        Row headerRow = sheet.createRow(1);
        headerRow.setHeightInPoints(40);
        Cell headerCell;
        for (int i = 0; i < headerResumenGrupo.length; i++) {
            headerCell = headerRow.createCell(i);
            headerCell.setCellValue(headerResumenGrupo[i]);
            headerCell.setCellStyle(styles.get("header"));
        }

        int rownum = 2;
        int k = 0;
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(rownum++);
            for (int j = 0; j < headerResumenGrupo.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellStyle(styles.get("cell"));
            }
            ActivoFijo activoFijo = (ActivoFijo) data.get(i);
            ActivoFijo af;
            try {
                af = (ActivoFijo) dataAnterior.get(k);
            } catch (IndexOutOfBoundsException e) {
                af = new ActivoFijo();
            }
            row.getCell(0).setCellValue(activoFijo.getGrupo().getNombre());
            row.getCell(1).setCellValue(activoFijo.getCantidad());
            row.getCell(2).setCellValue(activoFijo.getGrupo().getVidaUtil());
            row.getCell(3).setCellValue(activoFijo.getCostoInicial());
            if (activoFijo.getGrupo().getIdGrupo() == af.getGrupo().getIdGrupo()) {
                row.getCell(4).setCellValue(af.getValor() + activoFijo.getCostoInicial() - af.getCostoInicial());
                row.getCell(5).setCellValue(af.getDepreciacionAcumulada());
                row.getCell(6).setCellValue(af.getValor() + activoFijo.getCostoInicial() - af.getCostoInicial() - af.getDepreciacionAcumulada());
                row.getCell(7).setCellValue(activoFijo.getValor() - (af.getValor() + activoFijo.getCostoInicial() - af.getCostoInicial()));
                row.getCell(10).setCellValue((activoFijo.getDepreciacionAcumulada() - activoFijo.getDepreciacionGestion()) - af.getDepreciacionAcumulada());
                k++;
            } else {
                row.getCell(4).setCellValue(activoFijo.getCostoInicial());
                row.getCell(5).setCellValue(0);
                row.getCell(6).setCellValue(activoFijo.getCostoInicial());
                row.getCell(7).setCellValue(activoFijo.getValor() - activoFijo.getCostoInicial());
                row.getCell(10).setCellValue(activoFijo.getDepreciacionAcumulada() - activoFijo.getDepreciacionGestion());
            }
            
            row.getCell(8).setCellValue(activoFijo.getValor());
            row.getCell(9).setCellValue(activoFijo.getDepreciacionGestion());
            row.getCell(10).setCellValue(activoFijo.getDepreciacionAcumulada());
            row.getCell(11).setCellValue(activoFijo.getDepreciacionAcumulada());
            row.getCell(12).setCellValue(activoFijo.getValorNeto());
        }
        sheet.setColumnWidth(0, 256 * 40);
        sheet.setColumnWidth(1, 256 * 6);
        sheet.setColumnWidth(2, 256 * 6);
        sheet.setColumnWidth(3, 256 * 25);
        sheet.setColumnWidth(4, 256 * 25);
        sheet.setColumnWidth(5, 256 * 25);
        sheet.setColumnWidth(6, 256 * 25);
        sheet.setColumnWidth(7, 256 * 25);
        sheet.setColumnWidth(8, 256 * 25);
        sheet.setColumnWidth(9, 256 * 25);
        sheet.setColumnWidth(10, 256 * 25);
        sheet.setColumnWidth(11, 256 * 25);
        sheet.setColumnWidth(12, 256 * 25);
        // Write the output to a file
        String file = "report/" + title + ".xls";
        if (wb instanceof XSSFWorkbook) {
            file += "x";
        }
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();
        File path = new File(file);
        Desktop.getDesktop().open(path);
    }

    /**
     * Create a library of cell styles
     */
    private static Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<>();
        CellStyle style;
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 18);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(titleFont);
        styles.put("title", style);

        Font monthFont = wb.createFont();
        monthFont.setFontHeightInPoints((short) 11);
        monthFont.setColor(IndexedColors.WHITE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(monthFont);
        style.setWrapText(true);
        styles.put("header", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setWrapText(true);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("cell", style);

        return styles;
    }
}
