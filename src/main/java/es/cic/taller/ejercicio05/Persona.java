package es.cic.taller.ejercicio05;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Persona {
		private String nombre;
		private String apellidos;
		private LocalDateTime fecha;
		private String pais;
		
		public Persona(String nombre, String apellidos,LocalDateTime fecha,String pais) {
			this.nombre= nombre;
			this.apellidos = apellidos;
			this.fecha = fecha;
			this.pais= pais;
		}
	
		public String datos() {
			String sFecha = fecha.format(DateTimeFormatter.ofPattern( "dd MM yyyy"));
			return String.format(" %s %n %s %n estamos a %s %n en %s", nombre, apellidos, sFecha, pais);
		}
}