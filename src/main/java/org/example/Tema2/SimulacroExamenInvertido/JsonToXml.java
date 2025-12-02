package org.example.Tema2.SimulacroExamenInvertido;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonToXml {
    public static void main(String[] args) {

        try {
            // 1. File
            File jsonFile = new File("src/main/java/org/example/Tema2/SimulacroExamenInvertido/productos.json");

            // 2. Crear mapeador de objetos
            ObjectMapper mapper = new ObjectMapper();

            // 3. Crear el JsonNode
            JsonNode jsonNode = mapper.readTree(jsonFile);

            // 4. El mapeador de XML
            XmlMapper xmlMapper = new XmlMapper();

            // 5. Convertir JsonNode a XML
            String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

            // 6. Almacenar en fichero XML
            File xmlFile = new File("src/main/java/org/example/Tema2/SimulacroExamenInvertido/productos.xml");
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(xmlFile, jsonNode);

            // Mostrar todo el XML por consola
            System.out.println(xml);

            // Mostrar cada producto con su descripción y precio leyendo desde el XML generado
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(xmlFile);
            Element root = document.getRootElement();

            List<Element> productos = root.getChildren("producto");

            for (Element producto : productos) {
                String descripcion = producto.getChildText("descripcion");
                String precio = producto.getChildText("precio");
                System.out.println(descripcion + " - " + precio);
            }

        } catch (IOException | JDOMException e) {
            System.out.println(e.getMessage());
        }
    }
}