package verifyJSON;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

public class Compare2JSON {

    public boolean verifyEqualJSON(String expectedJSON, String actualJSON){
        JsonNode expectedNode=readJsonNode(expectedJSON,"Expected");
        System.out.println("----------------");
        JsonNode actualNode=readJsonNode(actualJSON,"Actual");
        System.out.println("");
        if (expectedNode == null || actualNode == null){
            return false;
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

}