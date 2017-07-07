package es.cic.taller.ejercicio05;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


;



/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	  private GridLayout sample;
	  private Persona perso;
		 
	    @Override
	    protected void init(VaadinRequest vaadinRequest) {
	    	final VerticalLayout layout = new VerticalLayout();
	        final TextField nombre = new TextField();
	        final TextField apellidos = new TextField();
	   	 	final DateTimeField fecha = new DateTimeField();
	   	 	fecha.setValue(LocalDateTime.now());
	   	 	
	   	 ArrayList<String> paises = new ArrayList<String>();
	        paises.add("España");
	        paises.add("Francia");
	        paises.add("Inglaterra");

	        final ComboBox<String> pais = new ComboBox<String>();
	        pais.setItems(paises);
	   	 	
	   	 	Component[] col2= {nombre,apellidos,fecha,pais};
	   	 	pintaTextField(nombre,"Introduzca nombre",30);
	        
	        pintaTextField(apellidos,"Introduzca apedillos",30);
	        
		     
	        pais.setPlaceholder("Pais no seleccionado");
	        pais.setEmptySelectionAllowed(false);
	        pais.setNewItemHandler(inputString -> {
	        	
	        	String nuevoPais = inputString;
	            paises.add(nuevoPais);
	           
	            pais.setItems(paises);

	            pais.setSelectedItem(nuevoPais);
	        });
	        Button button = new Button("Guarde los datos");
	        button.addClickListener( e -> {
	        	 perso = new Persona(nombre.getValue(),apellidos.getValue(), fecha.getValue(),pais.getValue());
	        });
	        
	        Button datos = new Button("Datos");
	        datos.addClickListener(event ->{
	        	Notification.show(perso.datos(),Type.TRAY_NOTIFICATION);
	        });
	        
	       
	        Label[] etiquetas= {new Label("Nombre:"),new Label("Apellidos:"),new Label("Fecha:"),new Label("Pais:")};
	        
	        sample = new GridLayout();
	        sample.addStyleName("outlined");
	        sample.setSpacing(true);
	 
	        generateMatrixGrid(4, 2,etiquetas,col2);
	        layout.addComponents(sample,button,datos);
	        setContent(layout);
	 

	        
	    }
	    private void pintaTextField(TextField texto,String placeholder,int maxLength) {
	          texto.setPlaceholder(placeholder);
	          texto.setMaxLength(maxLength);
	    }

		

		@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	    public static class MyUIServlet extends VaadinServlet {
	    }
	    private void generateMatrixGrid(final int rows, final int columns,Label[] etiquetas,Component[] cuadrosTexto) {
	        sample.removeAllComponents();
	        sample.setRows(rows);
	        sample.setColumns(columns);
	 
	        for (int row = 0; row < sample.getRows(); row++) {
	            for (int col = 0; col < sample.getColumns(); col++) {
	                if (col==0) {
	                	Label etiqueta = etiquetas[row];
	                	sample.addComponent(etiqueta);
	                }else {
	                	Component cuadroTexto = cuadrosTexto[row];
	                	sample.addComponent(cuadroTexto);
	                	
	                }
	                sample.setRowExpandRatio(row, 4.0f);
	                sample.setColumnExpandRatio(col, 4.0f);
	            }
	        }
	    }
}
