package com.integrador1.tienditagb.services;

import com.integrador1.tienditagb.Exceptions.ProductNotFoundException;
import com.integrador1.tienditagb.models.Product;
import com.integrador1.tienditagb.repository.ProductRepository;
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
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product newProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsActive() {
        return productRepository.findByStatus(true);
    }

    @Override
    public List<Product> getProductsbyCategoryActive(int category) {
        return productRepository.findByStatusAndCategory(true, category);
    }

    @Override
    public Product getProductbyId(int id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product updateProduct(Product newproduct, int id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newproduct.getName());
                    product.setImage(newproduct.getImage());
                    product.setCategory(newproduct.getCategory());
                    product.setStock(newproduct.getStock());
                    product.setPrecioUnitario(newproduct.getPrecioUnitario());
                    return productRepository.save(product);
                }).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public String deleteProduct(int id) {
        productRepository.findById(id)
                .map(product -> {
                    product.setStatus(false);
                    return productRepository.save(product);
                }).orElseThrow(() -> new ProductNotFoundException(id));
        return "Producto con el " + id + " a sido eliminado";
    }

    @Override
    public String renewProduct(int id) {
        productRepository.findById(id)
                .map(product -> {
                    product.setStatus(true);
                    return productRepository.save(product);
                }).orElseThrow(() -> new ProductNotFoundException(id));
        return "Producto con el " + id + " a sido restaurado";
    }

    // true:salida or false:entrada
    @Override
    public void updateStockProduct(int id, int purchasedQuantity, boolean operator) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        int stockActual = product.getStock();
        int newStock = 0;

        if (operator) {
            newStock = stockActual - purchasedQuantity;
        } else {
            newStock = stockActual + purchasedQuantity;
        }

        product.setStock(newStock);

        this.productRepository.save(product);
    }

    @Override
    public ByteArrayInputStream exportProducts() throws Exception {
        String[] columns = {"ID", "Producto", "Categoría", "Stock", "Precio S/."};
        HSSFWorkbook workBook = new HSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet sheet = workBook.createSheet("Ventas");
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 8000);
        sheet.setColumnWidth(4, 4000);

        CellStyle titleStyleSales = workBook.createCellStyle();
        Font titleFont = workBook.createFont();
        titleFont.setFontHeightInPoints((short) 30);
        titleFont.setBold(true);
        titleStyleSales.setFont(titleFont);

        CellStyle dateStyle = workBook.createCellStyle();
        dateStyle.setDataFormat(workBook.getCreationHelper().createDataFormat().getFormat("dd/MM/yyyy hh:mm:ss"));

        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Reporte de Productos");
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

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerStyle);
        }

        List<Product> products = this.getAllProduct();
        int initRow = 5;

        for(Product product : products){
            Row dataRow = sheet.createRow(initRow++);
            dataRow.createCell(0).setCellValue(product.getId());
            dataRow.createCell(1).setCellValue(product.getName());
            dataRow.createCell(2).setCellValue(product.getCategory());
            dataRow.createCell(3).setCellValue(product.getStock());
            dataRow.createCell(4).setCellValue(product.getPrecioUnitario());
        }
        workBook.write(stream);
        workBook.close();
        return new ByteArrayInputStream(stream.toByteArray());
    }
}
