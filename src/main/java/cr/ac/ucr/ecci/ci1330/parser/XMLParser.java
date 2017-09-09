package cr.ac.ucr.ecci.ci1330.parser;

import cr.ac.ucr.ecci.ci1330.bean.Bean;

import java.util.Map;

public class XMLParser extends AbstractParser {

    public XMLParser(String path){
        super(path);
    }

    @Override
    public void parseFile(Map<String, Bean> beanMap) {
        
    }

    @Override
    public void createBean(Map<String, Bean> beanMap) {

    }
}


/*public Parser(String filename) {
        File file = new File(filename);
        try {
            InputStream inputStream = new FileInputStream(file);
            Builder builder = new Builder();
            this.configurationFile = builder.build(inputStream); //faltaba el this, Ian parece de progra 1 en el 104....
            this.properties = new HashMap<String, List<Pair<String, String>>>();
            this.parseXML();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


     * Parses a XML configuration file.

@Service(value = "Hola!")
private void parseXML() {
    try {
        Element root = this.configurationFile.getRootElement(); //este saca el raiz, que es el que engloba todoo
        if(root.getFirstChildElement("annotation-config")!=null){ //si es Annotation config
            this.configType = true;
            this.getAnnotationConfigData(root);
        } else { //si es XML config
            this.configType = false;
            this.getXMLconfigData(root);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void getXMLconfigData(Element root){
        Element child = root.getFirstChildElement("beans"); // este saca un hijo que tenga de tag el nombre que le paso
        Elements children = child.getChildElements(); //este saca todos los hijos de ese elemento, que serian todos los messages
        Element e;
        Elements attributes;
        Element attribute;
        List<Pair<String, String>> props;
        Pair<String, String> prop;
        int childrenSize = children.size();
        this.classes = new String[childrenSize];
        this.beans = new String[childrenSize];
        this.scopes = new String[childrenSize];
        for (int i = 0; i < childrenSize; i++) {
            e = children.get(i); //saco el n-esimo hijo
            this.beans[i] = e.getAttributeValue("id"); //meto el id a la lista de ids de beans
            this.classes[i] = e.getAttributeValue("class"); //meto la clase(nombre) a la lista de clases de beans
            this.scopes[i] = e.getAttributeValue("scope");
            //this.autowiring[i] = e.getAttributeValue("autowiring");
            if (this.scopes[i] == null)
                this.scopes[i] = "singleton";//Singleton por default
            //System.out.println(this.scopes[i]);
            //System.out.print(e.getAttribute(0).getQualifiedName()+"="+e.getAttribute(0).getValue()+" "); //imprimo el valor
            //System.out.println(e.getAttribute(1).getQualifiedName()+"="+e.getAttribute(1).getValue()); //imprimo el valor
            attributes = e.getChildElements(); //saco todos los hijos, que seran los properties
            for (int j = 0; j < attributes.size(); j++) {
                attribute = attributes.get(j);
                props = this.properties.get(e.getAttribute(0).getValue());
                if (props == null) { //si el mapa no tenia esa entrada la crea
                    props = new LinkedList<Pair<String, String>>();
                    prop = new Pair<String, String>(attribute.getAttribute(0).getValue(), attribute.getAttribute(1).getValue());
                    props.add(prop);
                    this.properties.put(e.getAttribute(0).getValue(), props);
                } else { //sino debo meter el dato en la lista que ya existe
                    prop = new Pair<String, String>(attribute.getAttribute(0).getValue(), attribute.getAttribute(1).getValue());
                    props.add(prop);
                }
                //System.out.print(attribute.getAttribute(0).getQualifiedName() + "=" + attribute.getAttribute(0).getValue()+" ");
                //System.out.println(attribute.getAttribute(1).getQualifiedName() + "=" + attribute.getAttribute(1).getValue());
            }
        }
    }*/