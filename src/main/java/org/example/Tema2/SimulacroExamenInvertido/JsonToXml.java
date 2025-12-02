package org.example.Tema2.SimulacroExamenInvertido;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class JsonToXml {
    public static void main(String[] args) {

        try {
            // 1. File
            File jsonFile = new File("productos.json");

            // 2. Crear mapeador de objetos
            ObjectMapper mapper = new ObjectMapper();

            // 3. Crear el JsonNode
            JsonNode jsonNode = mapper.readTree(jsonFile);

            // 4. El mapeador de XML
            XmlMapper xmlMapper = new XmlMapper();

            // 5. Convertir JsonNode a XML
            String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

            // 6. Almacenar en fichero XML
            File xmlFile = new File("productos.xml");
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(xmlFile, jsonNode);

            // Mostrar todo el XML por consola
            System.out.println(xml);

            // Mostrar cada producto con su descripción y precio
            JsonNode productosArray = jsonNode.get("producto");
            for (JsonNode producto : productosArray) {
                String descripcion = producto.get("descripcion").asText();
                double precio = producto.get("precio").asDouble();
                System.out.println(descripcion + " - " + precio);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}