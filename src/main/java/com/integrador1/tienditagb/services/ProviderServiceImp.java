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
import com.integrador1.tienditagb.Exceptions.ProviderNotFoundException;
import com.integrador1.tienditagb.models.Provider;
import com.integrador1.tienditagb.repository.ProviderRepository;

@Service
public class ProviderServiceImp implements ProviderService{
    
    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Provider newProvider(Provider provider) {
        return this.providerRepository.save(provider);
    }

    @Override
    public List<Provider> getAllProvider() {
        return this.providerRepository.findAll();
    }

    @Override
    public Provider getProviderById(int id) {
        return this.providerRepository.findById(id)
        .orElseThrow(() -> new ProviderNotFoundException(id));
    }

    @Override
    public Provider updateProvider(Provider newProvider, int id) {
        return this.providerRepository.findById(id)
        .map(provider -> {
            provider.setName(newProvider.getName());
            provider.setAddress(newProvider.getAddress());
            provider.setRuc(newProvider.getRuc());
            provider.setPhone(newProvider.getPhone());
            return this.providerRepository.save(provider);
        }).orElseThrow(() -> new ProviderNotFoundException(id));
    }

    @Override
    public String deleteProvider(int id) {
        providerRepository.findById(id)
        .map(provider -> {
            provider.setStatus(false);
            return providerRepository.save(provider);
        }).orElseThrow(() -> new ProviderNotFoundException(id));
            return "Proveedor con el id: " + id + " a sido eliminado.";
    }

    @Override
    public String renewProvider(int id) {
        providerRepository.findById(id)
                .map(provider -> {
                    provider.setStatus(true);
                    return providerRepository.save(provider);
                }).orElseThrow(() -> new ProviderNotFoundException(id));
        return "Proveedor con el id: " + id + " a sido restaurado.";
    }

    @Override
    public ByteArrayInputStream exportProvider() throws Exception {
        String[] columns = {"ID", "RUC", "Proveedor", "Dirección", "Teléfono"};
        HSSFWorkbook workBook = new HSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet sheet = workBook.createSheet("Proveedores");
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 8000);
        sheet.setColumnWidth(4, 4000);

        CellStyle titleStyle = workBook.createCellStyle();
        Font titleFont = workBook.createFont();
        titleFont.setFontHeightInPoints((short) 30);
        titleFont.setBold(true);
        titleStyle.setFont(titleFont);

        CellStyle dateStyle = workBook.createCellStyle();
        dateStyle.setDataFormat(workBook.getCreationHelper().createDataFormat().getFormat("dd/MM/yyyy hh:mm:ss"));

        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Reporte de Proveedores");
        titleCell.setCellStyle(titleStyle);

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

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerStyle);
        }

        List<Provider> providers = getAllProvider();
        int initRow = 5;
        for (Provider provider : providers) {
            Row dataRow = sheet.createRow(initRow++);
            dataRow.createCell(0).setCellValue(provider.getId());
            dataRow.createCell(1).setCellValue(provider.getRuc());
            dataRow.createCell(2).setCellValue(provider.getName());
            dataRow.createCell(3).setCellValue(provider.getAddress());
            dataRow.createCell(4).setCellValue(provider.getPhone());
        }

        workBook.write(stream);
        workBook.close();
        return new ByteArrayInputStream(stream.toByteArray());
    }
}
