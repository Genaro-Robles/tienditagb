package com.integrador1.tienditagb.services;

import com.integrador1.tienditagb.Exceptions.SalesNotFoundException;
import com.integrador1.tienditagb.models.Sales;
import com.integrador1.tienditagb.repository.SalesRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service

public class SalesServiceImp implements SalesService{
    @Autowired
    private SalesRepository salesRepository;

    @Override
    public Sales newSales(Sales sales) {
        return salesRepository.save(sales);
    }

    @Override
    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    @Override
    public Sales getSalesbyId(int id) {
        return salesRepository.findById(id).orElseThrow(()->new SalesNotFoundException(id));
    }

    @Override
    public String deleteSales(int id) {
        salesRepository.findById(id)
                .map(sales -> {
                    sales.setStatus(false);
                    return salesRepository.save(sales);
                }).orElseThrow(() -> new SalesNotFoundException(id));
        return "Venta con el "+id+" a sido eliminado";
    }

    @Override
    public String renewSales(int id) {
        salesRepository.findById(id)
                .map(sales -> {
                    sales.setStatus(true);
                    return salesRepository.save(sales);
                }).orElseThrow(() -> new SalesNotFoundException(id));
        return "Venta con el "+id+" a sido restaurado";
    }

    @Override
    public ByteArrayInputStream exportSales() throws Exception {
        String [] columns={"ID", "Fecha Pedido", "Fecha de Entrega", "Subtotal S/.", "IGV S/.", "Total S/.", "Usuario"};
        HSSFWorkbook workBook = new HSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet sheet = workBook.createSheet("Ventas");
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 8000);
        sheet.setColumnWidth(4, 4000);
        sheet.setColumnWidth(5, 4000);
        sheet.setColumnWidth(6, 6000);

        CellStyle titleStyleSales = workBook.createCellStyle();
        Font titleFont = workBook.createFont();
        titleFont.setFontHeightInPoints((short) 30);
        titleFont.setBold(true);
        titleStyleSales.setFont(titleFont);

        CellStyle dateStyle = workBook.createCellStyle();
        dateStyle.setDataFormat(workBook.getCreationHelper().createDataFormat().getFormat("dd/MM/yyyy hh:mm:ss"));

        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Reporte de Ventas");
        titleCell.setCellStyle(titleStyleSales);

        Row dateRow = sheet.createRow(2);
        Cell dateCellLabel = dateRow.createCell(0);
        dateCellLabel.setCellValue("Fecha y hora:");

        Cell dateCellValue = dateRow.createCell(1);
        dateCellValue.setCellValue(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()));
        dateCellValue.setCellStyle(dateStyle);

        Row headerRow = sheet.createRow(4);
        CellStyle headerStyle = workBook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = workBook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        for(int i = 0; i<columns.length; i++){
            Cell cell=headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerStyle);
        }

        List<Sales> listSales = getAllSales();
        int initRow = 5;

        for (Sales sale : listSales){
            Row dataRow = sheet.createRow(initRow++);
            dataRow.createCell(0).setCellValue(sale.getId());
            dataRow.createCell(1).setCellValue(sale.getFechaPedido());
            dataRow.createCell(2).setCellValue(sale.getFechaEntrega());
            dataRow.createCell(3).setCellValue(sale.getSubtotal());
            dataRow.createCell(4).setCellValue(sale.getIgv());
            dataRow.createCell(5).setCellValue(sale.getTotal());
            dataRow.createCell(6).setCellValue(sale.getUsuario());
        }
        workBook.write(stream);
        workBook.close();
        return new ByteArrayInputStream(stream.toByteArray());
    }
}
