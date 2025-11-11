package com.bartolito.compras.dto;

import java.util.UUID;

public class Constantes {

	public static final String BLANK_SPACE = " ";
	public static final String GUION = "-";
	public static final String SLASH = "/";

	public static final UUID UUID_NULL = null;
	public static final String TRUE = "true";
	public static final Boolean HABILITADO = true;
	public static final Boolean INHABILITADO = false;
	public static final String CERO = "0";
	public static final Integer PAGINATION_START = 0;
	public static final Integer PAGINATION_SIZE = 20;
	public static final Integer MAXIMO_CARGA_INSPECCIONES = 200;
	public static final Integer MINIMO_IMAGEN_SEGUIMIENTO = 1;
	public static final String EMPTY = "";
	public static final String CSC = "CSC-PERU";
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String SCHEMA = "public";
	public static final String CATALOG = "bartolito";
	public static final Boolean SI = true;
	public static final Boolean NO = false;
	public static final String FILE_NAME_BLOB = "blob";
	public static final Integer MAX_CANT_OBSERVACIONES = 3;
	public static final Integer LONGUITUD_CODIGO_CATALOGO = 3;
	public static final String ACCESS = "ACCESS";
	public static final Integer SMS_INICIO = 100000;
	public static final Integer SMS_FIN = 999999;
	public static final String TDC_SOLICITUD = "VARIOS";
	public static final Long NUMERO_SIN_MEDIDOR = 0L;
	public static final String SI_TXT = "SI";
	public static final String NO_TXT = "NO";
	public static final String NA_TXT = "NO APLICA";
	public static final Long MAX_FILE_SIZE = 10L * 1024 * 1024;
	public static final String MAX_FILE_SIZE_TEXT = "10MB";
	
	public static final String ZONA_CODIGO = "Z";
	public static final String NIVEL_CODIGO = "N";
	
	private Constantes() {
	}
	
	public static class Secciones{
		public static final String LUCES_EMERGENCIA = "LUCES DE EMERGENCIA";
		public static final String DETECTOR_HUMO = "DETECTOR DE HUMO";
		public static final String CASETA_EXTINTOR = "CASETA DE EXTINTOR";
		public static final String TAPA_CIEGA = "TAPA CIEGA DE TOMACORRIENTE";
		public static final String SENALIZACION = "SEÑALIZACION";
	}

	public static class Rol{
		public static final String ROL_OWNER = "ROL_OWNER";
		public static final String ROL_INSPECTOR = "ROL_INSPECTOR";
	}
	
	public static class Habilitado{
		public static final String HABILITADO = "H001";
		public static final String HABILITADO_VALOR = "HABILITADO";
		public static final String INHABILITADO = "H002";
		public static final String INHABILITADO_VALOR = "INHABILITADO";
	}
	
	public static class PathUrl {
		public static final String ACTUALIZAR_PASSWORD = "cambiar-clave?token=";
		public static final String REGRESAR = "dashboard";
	}
	
	
	public static class Catalogo {
		public static final String VALORES_CONSTANTES = "VALORES_CONSTANTES";
		public static final String ESTADO_USUARIO = "ESTADO_USUARIO";
		public static final String TIPO_DOCUMENTO = "TIPO_DOCUMENTO";
		public static final String SEXO = "SEXO";
		public static final String PRIORIDAD = "PRIORIDAD";
		public static final String MEDICO = "MEDICO";
		public static final String TIPO_INSPECCION = "TIPO_INSPECCION";
		public static final String CONECTADO = "CONECTADO";
		public static final String ESTADO = "ESTADO_EQUIPO";
		public static final String FUNCIONA = "FUNCIONA";
		public static final String TIPO_SENALIZACION = "TIPO_SENALIZACION";
		public static final String ESTADO_CAPACITACION = "ESTADO_CAPACITACION";
		public static final String TIPO_CAPACITACION = "TIPO_CAPACITACION";
		private Catalogo() {
		}
	}
	
	public static class TipoInspeccion {
		public static final String LOCAL = "TPIS002";
		public static final String INTERNA = "TPIS001";
		private TipoInspeccion() {
		}
	}
	
	public static class ValoresConstantes {

		public static final String CANTIDAD_DIAS_RESET_PASSWORD = "VCO001";
		


		private ValoresConstantes() {
		}
	}
	
	public static class TipoDocumento {

		public static final String DNI = "TD001";
		public static final String RUC = "TD002";


		private TipoDocumento() {
		}
	}
	
	public static class EstadoCapacitacion {
		public static final String PUBLICADO = "ESCAP001";
		public static final String CONCLUIDO = "ESCAP002";
		public static final String CANCELADO = "ESCAP003";
		public static final String ACTIVO = "ESCAP004";
		
		private EstadoCapacitacion() {
		}
	}
	
	public static class TipoCapacitacion {
		public static final String PRESENCIAL = "TPCAP001";
		public static final String VIRTUAL = "TPCAP002";
		public static final String MIXTO = "TPCAP003";
	
		
		private TipoCapacitacion() {
		}
	}
	
	

	public static class ContentType {

		public static final String WORD = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		public static final String PDF = "application/pdf";
		public static final String XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		public static final String XLS = "application/vnd.ms-excel";
		public static final String ZIP = "application/zip";

		private ContentType() {
		}
	}
	

	

	public static class EstadoUsuario {

		public static final String ACTIVO = "ESUS001";
		public static final String INACTIVO = "ESUS002";

		private EstadoUsuario() {
		}
	}

	public static class Folder {

		public static final String USUARIO = "usuario";
		public static final String CAPACITACION = "capacitacion";

		private Folder() {
		}
	}

	public static class Extension {

		public static final String PNG = ".png";
		public static final String PDF = ".pdf";
		public static final String JPG = ".jpg";
		public static final String JSON = ".json";
		public static final String XLXS = ".xlsx";

		private Extension() {
		}
	}





	public static class Email {

		public static final String TEMPLATE_EMAIL = "TEMPLATE";
		public static final String SUBJECT_EMAIL = "SUBJECT";
		public static final String TO_EMAIL = "TO";
		public static final String MESSAGE = "MESSAGE";
		public static final String CC = "CC";
		public static final String ICS = "ICS";
		public static final String EVENT_NAME = "calendar.ics";

		private Email() {
		}
	}

	

	

	public static class ConfiguracionMarcaAgua{
		public static final String ANCHO_ALTO = "CONFM001";
		public static final String OPACIDAD = "CONFM002";
		
		private ConfiguracionMarcaAgua() {
		}
	}
	
	public static class ConfiguracionSistema{
		public static final String COLOR_REPETIDO = "CONFS001";
		
		private ConfiguracionSistema() {
		}
	}

}
