package verifyJSON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Equivalence;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class Compare2JSON {

    public boolean verifyEqualJSON(String expectedJSON, String actualJSON){
        JsonNode expectedNode=readJsonNode(expectedJSON,"Expected");
        System.out.println("----------------");
        JsonNode actualNode=readJsonNode(actualJSON,"Actual");
        System.out.println("");
        if (expectedNode == null || actualNode == null){
            return false;
        }
        try{
            JSONObject exJson= new JSONObject(expectedJSON);
            JSONObject acJson= new JSONObject(actualJSON);
            getDifferingVal(exJson,acJson);
        } catch (Exception e){

        }

        //El comparator custom de Jackson-Databind permite asumir que si el expected contiene
        //"$IGNORE" en alguno de los values, no revisara para esa key del actual siempre y cuando
        //esta exista en la actual y expected
        IgnoreComparator cmp=new IgnoreComparator();
        return expectedNode.equals(cmp,actualNode);
    }

    //Para que nos de los JSONnode e imprima los key-value de los String a analizar (si es que la
    // sintaxis es correcta o no es un string vacio)
    private JsonNode readJsonNode(String JSONAnalyze,String type){
        JSONObject strJson;
        JsonNode strNode;
        ObjectMapper mapper= new ObjectMapper();
        try {
            strJson= new JSONObject(JSONAnalyze);
            strNode=mapper.readTree(String.valueOf(strJson));
            System.out.println(type+" JSON: ");
            System.out.println(strNode);
            return strNode;
        } catch (Exception e){
            System.out.println("* "+type+" JSON is not correctly formatted");
            return null;
        }
    }

    //Gracias a la librería Guava, se puede identificar las diferencias de manera automática y tomando en
    //cuenta que existen los JSON (y solo se ejecuta si es que no hay errores de parseo JSON en los dos)
    //* Cómo se lee:
    // key/{key si esta anidada}/.../{key si esta anidada}: (value1,value2)
    //* En el caso de los que existen solo en un lado:
    // key/{key si esta anidada}.../{key si esta anidada}: value
    private void getDifferingVal(JSONObject expected,JSONObject actual) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> type = new TypeReference<>() {};
        Map<String, Object> leftMap;
        Map<String, Object> rightMap;
        try{
            leftMap = mapper.readValue(expected.toString(), type);
            rightMap = mapper.readValue(actual.toString(), type);

            //Usando una clase para ajustar el mapa en leer los keys y values de diferencia
            Map<String, Object> leftFlatMap = FlatMapUtil.flatten(leftMap);
            Map<String, Object> rightFlatMap = FlatMapUtil.flatten(rightMap);

            MapDifference<String, Object> difference = Maps.difference(leftFlatMap, rightFlatMap, new Equivalence<Object>() {
                //Con un override de datos podemos hacer que tome en cuenta el "$IGNORE" como keyword
                //para que lo considere positivo/equivalente
                @Override public boolean doEquivalent(Object a, Object b) {

                    return "$IGNORE".equals(a) || "$IGNORE".equals(b) || Objects.equals(a, b);
                }
                @Override public int doHash(Object s) { return 0; }
            });

            System.out.println("Solo existe en la izquierda\n--------------------------");
            difference.entriesOnlyOnLeft()
                    .forEach((key, value) -> System.out.println(key + ": " + value));

            System.out.println("\nSolo existe en la derecha\n--------------------------");
            difference.entriesOnlyOnRight()
                    .forEach((key, value) -> System.out.println(key + ": " + value));

            System.out.println("\nEntradas variantes\n--------------------------");
            difference.entriesDiffering()
                    .forEach((key, value) -> System.out.println(key + ": " + value));
        } catch (Exception e){
            return;
        }

    }

}
