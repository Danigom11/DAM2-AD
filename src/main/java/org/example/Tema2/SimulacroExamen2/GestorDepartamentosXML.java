package org.example.Tema2.SimulacroExamen2;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.*;
import org.jdom2.xpath.*;

import java.io.*;
import java.util.List;

public class GestorDepartamentosXML {
    public static void main(String[] args) {
        try{
            // Leer el archivo XML
            File fileXml = new File("departamentos.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(fileXml);
            Element root = document.getRootElement();
            System.out.println("Documento leído correctamente.");

            // Insertar nuevo departamento con id=30
            Element nuevoDep = new Element("Departamento");
            nuevoDep.setAttribute("id", "30");
            nuevoDep.addContent(new Element("nombre").setText("Ventas"));
            nuevoDep.addContent(new Element("localidad").setText("Barcelona"));
            root.addContent(nuevoDep);
            System.out.println("Departamento 30 insertado.");

            // Modificar localidad del departamento con id="10"
            List<Element> departamentos = root.getChildren("Departamento");
            for (Element dep : departamentos){
                if (dep.getAttributeValue("id").equals("10")){
                    Element localidad = dep.getChild("localidad");
                    if (localidad != null){
                        localidad.setText("Málaga");
                        System.out.println("Localidad del departamento 10 modificada a Málaga.");
                    }
                    break;
                }
            }

            // Guardar el documento modificado
            File fileFinal = new File("departamentos_mod.xml");
            XMLOutputter xmlOutputter = new XMLOutputter();
            xmlOutputter.setFormat(Format.getPrettyFormat());
            try (FileOutputStream fos = new FileOutputStream(fileFinal)){
                xmlOutputter.output(document, fos);
            }
            System.out.println("Documento guardado en: departamentos_mod.xml");

        } catch (JDOMException | IOException e){
            System.out.println(e.getMessage());
        }
    }
}