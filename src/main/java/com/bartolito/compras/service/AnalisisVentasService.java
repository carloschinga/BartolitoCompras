package com.bartolito.compras.service;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bartolito.compras.dto.rotacionProductos.RotacionGeneralSeleccionRequest;
import com.bartolito.compras.dto.rotacionProductos.RotacionProductosRequest;
import com.bartolito.compras.repository.AnalisisVentasRepository;

@Service
public class AnalisisVentasService {

	@Autowired
	private AnalisisVentasRepository analisisVentasRepository;

	private final DataFormatter dataFormatter = new DataFormatter();

	public List<Map<String, Object>> obtenerListadoRotacionProductos() {
		return analisisVentasRepository.obtenerListadoRotacionProductos();
	}
	
	public List<Map<String, Object>> obtenerListadoRotacionGeneralProductosSeleccionados() {
		return analisisVentasRepository.obtenerListadoRotacionGeneralProductosSeleccionados();
	}

	public List<Map<String, Object>> initComboCategvta() {
		return analisisVentasRepository.initComboCategvta();
	}

	public List<Map<String, Object>> initComboGenericos() {
		return analisisVentasRepository.initComboGenericos();
	}

	public List<Map<String, Object>> initComboTipos() {
		return analisisVentasRepository.initComboTipos();
	}

	public List<Map<String, Object>> obtenerDatosProductosLolfarById(String codpro) {
		return analisisVentasRepository.obtenerDatosProductosLolfarById(codpro);
	}

	public List<Map<String, Object>> obtenerVentasUltimos30Dias(String codpro) {
		return analisisVentasRepository.obtenerVentasUltimos30Dias(codpro);
	}
	
	public void deleteRotacionProductoGeneralSeleccion(String codpro) {
		analisisVentasRepository.deleteRotacionProductoGeneralSeleccion(codpro);
	}
	
	
	public void saveRotacionProductoGeneralSeleccion(String json) {
		analisisVentasRepository.saveRotacionProductoGeneralSeleccion(json); 
	}

	public void saveOrUpdate(MultipartFile file) {
		try (InputStream is = file.getInputStream(); Workbook workbook = WorkbookFactory.create(is)) {
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();

			boolean isFirstRow = true;
			Row lastRow = sheet.getRow(sheet.getLastRowNum()); // última fila

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// Saltar la primera fila (encabezado)
				if (isFirstRow) {
					isFirstRow = false;
					continue;
				}

				// Saltar si es la fila "total"
				Cell firstCell = row.getCell(0);
				if (firstCell == null || firstCell.getCellType() == CellType.BLANK)
					continue;

				String prodId = getStringCell(firstCell);
				if (prodId.equalsIgnoreCase("total"))
					continue;
				String normalized = prodId.replaceAll("\\s+", " ").toLowerCase();
				if (normalized.contains("filtros aplicados") || normalized.contains("prodest"))
					continue;

				// Crear request con los valores de las columnas
				RotacionProductosRequest request = new RotacionProductosRequest();
				request.setProdId(getStringCell(row.getCell(0)));
				request.setProdDesc(getStringCell(row.getCell(1)));
				request.setIndiceRotacion(getDoubleCell(row.getCell(2)));
				request.setStockPromedioValorizado(getDoubleCell(row.getCell(3)));
				request.setUltimoStockValorizado(getDoubleCell(row.getCell(4)));
				request.setEnlaceWeb(getStringCell(row.getCell(5)));

				// Guardar o actualizar en BD

				analisisVentasRepository.saveOrUpdateRotacionProducto(request);
			}

		} catch (Exception e) {
			throw new RuntimeException("Error al procesar el archivo Excel de rotación de productos", e);
		}
	}

	private String getStringCell(Cell cell) {
		if (cell == null)
			return "";

		if (cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue().trim();
		} else if (cell.getCellType() == CellType.NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else if (cell.getCellType() == CellType.BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else {
			return "";
		}
	}

	private Integer getIntegerCell(Cell cell) {
		if (cell == null)
			return 0;

		if (cell.getCellType() == CellType.NUMERIC) {
			return (int) cell.getNumericCellValue();
		} else if (cell.getCellType() == CellType.STRING) {
			try {
				return Integer.parseInt(cell.getStringCellValue().trim());
			} catch (NumberFormatException e) {
				return 0;
			}
		} else {
			return 0;
		}
	}

	private boolean isRowEmpty(Row row) {
		if (row == null)
			return true;

		for (int c = 0; c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != CellType.BLANK) {
				return false;
			}
		}
		return true;
	}

	private Double getDoubleCell(Cell cell) {
		if (cell == null)
			return 0.0;

		// Obtiene el valor tal cual lo muestra Excel
		String cellValue = dataFormatter.formatCellValue(cell).trim();

		if (cellValue.isEmpty())
			return 0.0;

		cellValue = cellValue.replace("S/", "").replace("$", "").replace(",", "").replace(" ", "");

		try {
			return Double.parseDouble(cellValue);
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}

	private boolean rowContainsText(Row row, String searchText) {
		if (row == null)
			return false;
		for (Cell cell : row) {
			String value = dataFormatter.formatCellValue(cell).trim();
			if (value.toLowerCase().contains(searchText.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

}
