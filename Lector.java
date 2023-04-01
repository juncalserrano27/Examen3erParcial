package examenParcial3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class Lector {
	List<String[]> renglones;
	List<String> palabras;
	String[][] correos; 
	HashMap<String, Integer> emails =  new HashMap<>();
	int inicio; 
	int fin;
	String id;
	
	public Lector() { }
	
	public void ejecutar(String id) throws IOException {
		this.id = id;
		limite(id);
		CrearMatriz();
		CrearHashMap();
		CrearArchivo();
	}
	
	public void limite(String Id) {
		int ultimosDigitos = Integer.parseInt(Id.substring(Id.length() - 3));
		int inicio;
		inicio = ultimosDigitos;
		int fin = inicio + 50;
		this.inicio = inicio; this.fin = fin;
	}
	
	public void CrearMatriz() {
		renglones = new ArrayList<String[]>();
		try (BufferedReader bReader = new BufferedReader(new FileReader("\\Users\\junca\\OneDrive\\Documentos\\UDLAP\\Programacion orientada a objetos\\emails.csv.zip"))) {
			String line;
		    while ((line = bReader.readLine()) != null) {
		        String[] emails = line.split(",");
		        renglones.add(emails);
		    }
		    bReader.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		correos = new String[renglones.size()][];
		for (int i = 0; i < renglones.size(); i++) {
		    String[] columna = renglones.get(i);
		    correos[i] = columna;
		}
	}
	
	public void CrearHashMap() {
		List<String> palabras = new ArrayList<>();
		this.palabras = palabras;
		for(int j = 1; j < 3001; j++) {
			String temporal = correos[0][j];
			palabras.add(temporal);
			emails.put(correos[0][j], 0);	
		}
		for(int i= 1; i < 3001; i++) {
			int valor = 0;
			for(int j = inicio; j < fin; j++) {
				valor = valor + Integer.parseInt(correos[j][i]);
			}	
			emails.replace(correos[0][i], valor);
		}
	}
	
	public void CrearArchivo() throws IOException {
		 try {
	            FileWriter writer = new FileWriter("178054.txt");
	            BufferedWriter bufWriter = new BufferedWriter(writer);
	            for(int i = 1; i < 3001; i++) {
	            	for(int j = 0; j < 3000; j++) {
	            	bufWriter.write(palabras.get(j)+", " + emails.get(palabras.get(j)) + "\n");
	            	}
	            }
	            bufWriter.close();
	            writer.close();
	            System.out.println("Se escribio correctamente el documento " +id+ ".txt");
	            System.out.println("Se logro imprimir correctamente de la fila "+inicio+" a la "+fin);
	        } catch (IOException e) {
	            System.out.println("OCURRIO UN ERROR EN: " + e.getMessage());
	        }
	}
}
