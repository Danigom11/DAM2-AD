package org.example.Tema2.SimulacroExamen.Ejercicio1;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.*;

public class XmlToJson {
    public static void main(String[] args) {
        try {
            // Cargar el archivo XML
            File filexml = new File("C:\\Users\\danig\\Downloads\\DAM2\\AD\\AD_Tema2_BBDD_Conectores\\AD_Tema2_BBDD_Conectores\\src\\main\\java\\org\\example\\Tema2\\SimulacroExamen\\Ejercicio1\\libros.xml");

            // Crear el mapper para leer XML
            XmlMapper xmlMapper = new XmlMapper();
            // Convertir el XML a un mapa
            Map<String, Object> map = xmlMapper.readValue(filexml, Map.class);

            // Crear el mapper para escribir JSON
            ObjectMapper objectMapper = new ObjectMapper();
            // Definir la ruta del archivo JSON de salida
            File filejson = new File("C:\\Users\\danig\\Downloads\\DAM2\\AD\\AD_Tema2_BBDD_Conectores\\AD_Tema2_BBDD_Conectores\\src\\main\\java\\org\\example\\Tema2\\SimulacroExamen\\Ejercicio1\\libros.json");
            // Escribir el mapa en el archivo JSON con formato legible
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(filejson, map);

            // Leer el JSON generado y parsearlo como JsonNode
            JsonNode rootNode = objectMapper.readTree(filejson);
            // Acceder directamente al nodo "libro" que contiene el array
            JsonNode libroNode = rootNode.get("libro");
            // Verificar que existe y es un array
            if (libroNode.isArray()){
                ArrayNode arrayNode = (ArrayNode) libroNode;
                // Recorrer cada libro del array
                for (JsonNode node : arrayNode){
                    // Extraer los datos de cada libro
                    String id = node.get("id").asText();
                    String titulo = node.get("titulo").asText();
                    String stock = node.get("stock").asText();
                    // Mostrar los datos por consola
                    System.out.println("id: " + id + ", título: " + titulo + ", stock: " + stock);
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}