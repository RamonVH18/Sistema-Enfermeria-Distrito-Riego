/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import AtributosFisicos.AgudezaVisual;
import AtributosFisicos.AtributoBase;
import entidades.DetalleExtra;
import enums.AtributoFisico;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;
import response.AtributoFisicoResponse;

/**
 *
 * @author Ramon Valencia
 */
public class AtributosFisicosMapper {

    private static final Map<AtributoFisico, Function<DetalleExtra, AtributoFisicoResponse>> xmlParser = Map.of(
            AtributoFisico.AGUDEZA_VISUAL,
            AtributosFisicosMapper::obtenerAgudezaVisual
    );

    public static Map<String, AtributoFisicoResponse> toDetalleExtraResponse(List<DetalleExtra> atributos) {
        Map<String, AtributoFisicoResponse> map = new HashMap<>();
        AtributoFisico atributo;
        for (DetalleExtra a : atributos) {
            atributo = AtributoFisico.valueOf(a.getDetalle().getNombreDetalle());
            Function<DetalleExtra, AtributoFisicoResponse> parser
                    = xmlParser.getOrDefault(
                            atributo,
                            AtributosFisicosMapper::obtenerAtributo
                    );
            map.put(
                    atributo.toString(),
                    parser.apply(a)
            );

        }
        return map;
    }
    
    public static String convertirPropiedadesAXML(Map<String, Object> props, AtributoFisico tipoAtributo) {
        if (tipoAtributo.equals(AtributoFisico.AGUDEZA_VISUAL)) {
            AgudezaVisual agudezaVisual = AgudezaVisual.fromMap(props);
            return generarXMLAgudezaVisual(agudezaVisual);
        }
        AtributoBase atributoBase = AtributoBase.fromMap(props, tipoAtributo);
        return generarXmlAtributo(atributoBase);
    }
    
    private static String generarXMLAgudezaVisual(AgudezaVisual agudeza) {
        try {
            JAXBContext context = JAXBContext.newInstance(AgudezaVisual.class);
            Marshaller marshaller = context.createMarshaller();

//            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//            Schema schema = sf.newSchema(new File("src/main/resources/xsd/AgudezaVisual.xsd"));
//            marshaller.setSchema(schema);

            // No necesitamos que sea "bonito" (indentado) para la base de datos, 
            // así ahorramos espacio en el disco.
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);

            StringWriter sw = new StringWriter();
            marshaller.marshal(agudeza, sw);

            return sw.toString(); // Este es el String que irá al SQL
        } catch (JAXBException e) {
            throw new RuntimeException("Error al mapear el objeto: " + e.getMessage());
        } /*catch (SAXException ex) {
            Logger.getLogger(AtributosFisicosMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        */
    }

    private static AtributoFisicoResponse obtenerAgudezaVisual(DetalleExtra d) {
        try {
            // 1. Instanciar el contexto con la clase que creamos antes
            JAXBContext context = JAXBContext.newInstance(AgudezaVisual.class);

            // 2. Crear el Unmarshaller (el que traduce de XML a Java)
            Unmarshaller unmarshaller = context.createUnmarshaller();

            StringReader reader = new StringReader(d.getValor());

            AgudezaVisual agudeza = (AgudezaVisual) unmarshaller.unmarshal(reader);
            return new AtributoFisicoResponse(agudeza.toMap());

        } catch (JAXBException e) {
            System.err.println("Error: El XML de la base está corrupto o no coincide con la clase.");
            e.printStackTrace();
            return null;
        }
    }

    public static AtributoFisicoResponse obtenerAtributo(DetalleExtra d) {
        try {
            JAXBContext context = JAXBContext.newInstance(AtributoBase.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            StringReader reader = new StringReader(d.getValor());

            AtributoBase atributoBase = (AtributoBase) unmarshaller.unmarshal(reader);
            return new AtributoFisicoResponse(atributoBase.toMap());
        } catch (JAXBException e) {
            System.err.println("Error: El XML de la base está corrupto o no coincide con la clase.");
            e.printStackTrace();
            return null;
        }
    }
    
    public static String generarXmlAtributo(AtributoBase atributo) {
    try {
        // 1. Crear el contexto con la clase base
        JAXBContext context = JAXBContext.newInstance(AtributoBase.class);

        // 2. Crear el Marshaller (el que "ordena" los datos hacia afuera)
        Marshaller marshaller = context.createMarshaller();

        // 3. Configuración (Opcional pero recomendada para que el XML sea legible)
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);

        // 4. El "destino" de los datos (un StringWriter en este caso)
        StringWriter writer = new StringWriter();

        // 5. El acto final: Convertir el objeto a XML
        marshaller.marshal(atributo, writer);

        return writer.toString();

    } catch (JAXBException e) {
        System.err.println("Error al serializar el objeto a XML");
        e.printStackTrace();
        return null;
    }
}
}
