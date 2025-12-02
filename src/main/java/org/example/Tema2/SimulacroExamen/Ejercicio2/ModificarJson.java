package org.example.Tema2.SimulacroExamen.Ejercicio2;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;

public class ModificarJson {
    public static void main(String[] args) {
        try{
            File fileJson = new File("C:\\Users\\danig\\Downloads\\DAM2\\AD\\AD_Tema2_BBDD_Conectores\\AD_Tema2_BBDD_Conectores\\src\\main\\java\\org\\example\\Tema2\\SimulacroExamen\\Ejercicio2\\persona.json");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(fileJson);
            if (jsonNode.isObject()){
                // Añadir C al array de lenguajes
                JsonNode jsonNodeLenguajes = jsonNode.get("lenguajes");
                if (jsonNodeLenguajes.isArray()){
                     ArrayNode arrayNode = (ArrayNode) jsonNodeLenguajes;
                     arrayNode.add("C");
                }
                // Cambiar dirección.calle por otra
                JsonNode jsonNodeDireccion = jsonNode.get("direccion");
                if (jsonNodeDireccion.isObject()){
                    ObjectNode objectNode = (ObjectNode) jsonNodeDireccion;
                    objectNode.put("calle", "Avenida del Río Henares");
                }
                // Eliminar email entero
                ObjectNode objectNode = (ObjectNode) jsonNode;
                objectNode.remove("email");
            }
            File fileFinal = new File("C:\\Users\\danig\\Downloads\\DAM2\\AD\\AD_Tema2_BBDD_Conectores\\AD_Tema2_BBDD_Conectores\\src\\main\\java\\org\\example\\Tema2\\SimulacroExamen\\Ejercicio2\\persona_mod.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(fileFinal, jsonNode);
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}