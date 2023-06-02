package verifyJSON;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.util.Comparator;

public class IgnoreComparator implements Comparator<JsonNode> {
    @Override
    public int compare(JsonNode o1, JsonNode o2) {
        if (o1.equals(o2)) {
            return 0;
        }
        //Si se encuentra con el $IGNORE y no es igual al expected
        if ((o1 instanceof TextNode)){
            String s1 = ((TextNode) o1).asText();
            if (s1.equals("$IGNORE")){
                return 0;
            }
        }
        return 1;
    }
}
