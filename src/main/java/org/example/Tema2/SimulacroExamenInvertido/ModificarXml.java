package org.example.Tema2.SimulacroExamenInvertido;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ModificarXml {
    public static void main(String[] args) {
        File file = new File("src/main/java/org/example/Tema2/SimulacroExamenInvertido/empleados.xml");
        File fileF = new File("src/main/java/org/example/Tema2/SimulacroExamenInvertido/empleados_final.xml");

        try {
            // 1. Lectura del fichero XML
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(file);
            Element root = document.getRootElement();

            List<Element> empleados = root.getChildren("Empleado");

            // 2. Modificación: Cambiar salario de 12345678A a 32000
            for (Element empleado : empleados) {
                if (empleado.getAttributeValue("dni").equals("12345678A")) {
                    empleado.getChild("salario").setText("32000");
                    break;
                }
            }

            // 3. Adición: Agregando un nuevo Empleado (55555555X)
            Element nuevoEmpleado = new Element("Empleado");
            nuevoEmpleado.setAttribute("dni", "55555555X");
            nuevoEmpleado.addContent(new Element("nombre").setText("Marta Ruiz"));
            nuevoEmpleado.addContent(new Element("salario").setText("50000"));
            root.addContent(nuevoEmpleado);

            // 4. Eliminación: Eliminar el empleado 98765432B
            Iterator<Element> iterator = empleados.iterator();
            while (iterator.hasNext()) {
                Element empleado = iterator.next();
                if (empleado.getAttributeValue("dni").equals("98765432B")) {
                    iterator.remove();
                    // También se puede con: root.removeContent(empleado);
                    break;
                }
            }

            // 5. Guardado: Escribir el documento modificado con indentación
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            xmlOutputter.output(document, new FileOutputStream(fileF));
            System.out.println(xmlOutputter.outputString(document));

        } catch (JDOMException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}