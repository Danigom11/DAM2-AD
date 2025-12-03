package org.example.Tema2.SimulacroExamenInvertido;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.util.Iterator;

public class ModificarJsonBiblioteca {
    public static void main(String[] args) {
        try {
            // 1. Lectura del JSON
            File fileJson = new File("src/main/java/org/example/Tema2/SimulacroExamenInvertido/biblioteca.json");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(fileJson);

            // Obtener el array de biblioteca
            JsonNode bibliotecaNode = jsonNode.get("biblioteca");
            if (bibliotecaNode.isArray()) {
                ArrayNode bibliotecaArray = (ArrayNode) bibliotecaNode;

                // 2. Modificación: Localizar libro con id=2 y modificarlo
                for (JsonNode categoria : bibliotecaArray) {
                    JsonNode librosNode = categoria.get("libros");
                    if (librosNode.isArray()) {
                        for (JsonNode libro : librosNode) {
                            if (libro.get("id").asInt() == 2) {
                                // Cambiar disponibilidad a "Disponible"
                                ((ObjectNode) libro).put("disponibilidad", "Disponible");
                                // Actualizar precio a 28.0
                                JsonNode precioNode = libro.get("precio");
                                if (precioNode.isObject()) {
                                    ((ObjectNode) precioNode).put("valor", 28.0);
                                }
                            }
                        }
                    }
                }

                // 3. Adición: Agregar nuevo libro en la categoría "Ficción"
                for (JsonNode categoria : bibliotecaArray) {
                    if (categoria.get("categoria").asText().equals("Ficción")) {
                        JsonNode librosNode = categoria.get("libros");
                        if (librosNode.isArray()) {
                            ArrayNode librosArray = (ArrayNode) librosNode;

                            // Crear nuevo libro
                            ObjectNode nuevoLibro = mapper.createObjectNode();
                            nuevoLibro.put("id", 6);
                            nuevoLibro.put("titulo", "1984");
                            nuevoLibro.put("autor", "George Orwell");
                            nuevoLibro.put("editorial", "Secker & Warburg");
                            nuevoLibro.put("anio", 1949);

                            // Crear objeto precio
                            ObjectNode precio = mapper.createObjectNode();
                            precio.put("valor", 18.5);
                            precio.put("moneda", "EUR");
                            nuevoLibro.set("precio", precio);

                            nuevoLibro.put("disponibilidad", "Disponible");

                            // Añadir el nuevo libro al array
                            librosArray.add(nuevoLibro);
                        }
                        break;
                    }
                }

                // 4. Eliminación: Eliminar libro con id=5 y la categoría si queda vacía
                Iterator<JsonNode> categoriaIterator = bibliotecaArray.iterator();
                while (categoriaIterator.hasNext()) {
                    JsonNode categoria = categoriaIterator.next();
                    JsonNode librosNode = categoria.get("libros");

                    if (librosNode.isArray()) {
                        ArrayNode librosArray = (ArrayNode) librosNode;
                        Iterator<JsonNode> libroIterator = librosArray.iterator();

                        while (libroIterator.hasNext()) {
                            JsonNode libro = libroIterator.next();
                            if (libro.get("id").asInt() == 5) {
                                libroIterator.remove();
                                break;
                            }
                        }

                        // Si la categoría queda sin libros, eliminarla
                        if (librosArray.size() == 0) {
                            categoriaIterator.remove();
                        }
                    }
                }
            }

            // 5. Guardado: Escribir el JSON modificado con formato legible
            File fileFinal = new File("src/main/java/org/example/Tema2/SimulacroExamenInvertido/biblioteca_final.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(fileFinal, jsonNode);

            // Mostrar por consola
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode));

        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Error: datos no encontrados - " + e.getMessage());
        }
    }
}

