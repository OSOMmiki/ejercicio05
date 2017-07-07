package es.cic.taller.ejercicio05;

import java.time.LocalDateTime;


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
			return (nombre + " " + apellidos + " " +fecha +" " + pais);
		}
}
