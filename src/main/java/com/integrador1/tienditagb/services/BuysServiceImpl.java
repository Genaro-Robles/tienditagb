package com.integrador1.tienditagb.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador1.tienditagb.Exceptions.BuysNotFoundException;
import com.integrador1.tienditagb.models.Buys;
import com.integrador1.tienditagb.repository.BuysRepository;

@Service
public class BuysServiceImpl implements BuysService{
    
    @Autowired
    private BuysRepository buysRepository;

    @Override
    public Buys newBuys(Buys buys) {
        return buysRepository.save(buys);
    }

    @Override
    public List<Buys> getAllBuys() {
        return buysRepository.findAll();
    }

    @Override
    public Buys getBuysById(int id) {
        return buysRepository.findById(id).
        orElseThrow(() -> new BuysNotFoundException(id));
    }

    @Override
    public String deleteBuys(int id) {
        buysRepository.findById(id)
        .map(buys -> {
            buys.setStatus(false);
            return buysRepository.save(buys);
        }).orElseThrow(() -> new BuysNotFoundException(id));
            return "Compra con el id: " + id + " a sido eliminada.";

    }

    @Override
    public String renewBuys(int id) {
        buysRepository.findById(id)
                .map(buys -> {
                    buys.setStatus(true);
                    return buysRepository.save(buys);
                }).orElseThrow(() -> new BuysNotFoundException(id));
        return "Compra con el id: " + id + " a sido restaurada.";

    }

    @Override
    public ByteArrayInputStream exportBuys() throws Exception {
        String[] columns = {"ID", "Producto", "Proveedor", "Usuario", "Precio U. S/." , "Cantidad", "Precio F. S/."};
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
        titleCell.setCellValue("Reporte de Compras");
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

        for (int i = 0; i<columns.length; i++){
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerStyle);
        }

        List<Buys> listBuy = this.getAllBuys();
        int initRow = 5;

        for(Buys buy: listBuy){
            Row dataRow = sheet.createRow(initRow++);
            dataRow.createCell(0).setCellValue(buy.getId());
            dataRow.createCell(1).setCellValue(buy.getProduct());
            dataRow.createCell(2).setCellValue(buy.getProvider());
            dataRow.createCell(3).setCellValue(buy.getUser());
            dataRow.createCell(4).setCellValue((buy.getAmount())/(buy.getPurchasedQuantity()));
            dataRow.createCell(5).setCellValue(buy.getPurchasedQuantity());
            dataRow.createCell(6).setCellValue(buy.getAmount());
        }
        workBook.write(stream);
        workBook.close();
        return new ByteArrayInputStream(stream.toByteArray());
    }
}
