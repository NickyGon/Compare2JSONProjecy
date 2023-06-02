package testCompare;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import verifyJSON.Compare2JSON;

public class CompareTwoJSONSTest {
    static Compare2JSON compare2JSON;
    @BeforeAll
    public static void setup(){
        compare2JSON=new Compare2JSON();
    }

    //Test positivo Todo igual
    @Test
    public void verifyComparer(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "}";
        boolean expectedResult=true;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test positivo Un ignore
    @Test
    public void verifyComparer2(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":\"$IGNORE\"\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=true;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test positivo Dos ignore
    @Test
    public void verifyComparer3(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":\"$IGNORE\",\n" +
                "\"date\":\"$IGNORE\"\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=true;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test positivo Todos ignore
    @Test
    public void verifyComparer4(){
        String expected="{\n" +
                "\"Content\":\"$IGNORE\",\n" +
                "\"Icon\":\"$IGNORE\",\n" +
                "\"date\":\"$IGNORE\"\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=true;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo sin igualdad completa o ignore
    @Test
    public void verifyComparer5(){
        String expected="{\n" +
                "\"Content\":\"Christmas\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":\"123231321\"\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":99,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo un ignore pero desigualdad
    @Test
    public void verifyComparer6(){
        String expected="{\n" +
                "\"Content\":\"Christmas\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":\"$IGNORE\"\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":99,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo dos ignore pero desigualdad
    @Test
    public void verifyComparer7(){
        String expected="{\n" +
                "\"Content\":\"Christmas\",\n" +
                "\"Icon\":\"$IGNORE\"\n" +
                "\"date\":\"$IGNORE\"\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":99,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo expected vacío
    @Test
    public void verifyComparer8(){
        String expected="";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":99,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo expected JSON vacío
    @Test
    public void verifyComparer9(){
        String expected="{}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":99,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo actual vacío
    @Test
    public void verifyComparer10(){
        String expected="{\n" +
                "\"Content\":\"Christmas\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":\"$IGNORE\"\n" +
                "}";
        String actual="";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo actual JSON vacío
    @Test
    public void verifyComparer11(){
        String expected="{\n" +
                "\"Content\":\"Christmas\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":\"$IGNORE\"\n" +
                "}";
        String actual="{}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo los dos vacios
    @Test
    public void verifyComparer12(){
        String expected="";
        String actual="";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test positivo los dos JSON vacios
    @Test
    public void verifyComparer13(){
        String expected="{}";
        String actual="{}";
        boolean expectedResult=true;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo expected vacio vs actual JSON vacio
    @Test
    public void verifyComparer14(){
        String expected="";
        String actual="{}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo expected JSON vacio vs actual vacio
    @Test
    public void verifyComparer15(){
        String expected="{}";
        String actual="";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test positivo Todos iguales pero otro orden
    @Test
    public void verifyComparer16(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"Creator\":\"Nicole\"\n" +
                "}";
        String actual="{\n" +
                "\"Icon\":9,\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Creator\":\"Nicole\"\n" +
                "}";
        boolean expectedResult=true;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo Una desigualdad pero otro orden
    @Test
    public void verifyComparer17(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"Creator\":\"Nicole\"\n" +
                "}";
        String actual="{\n" +
                "\"Icon\":99,\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Creator\":\"Nicole\"\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo Dos desigualdades pero otro orden
    @Test
    public void verifyComparer18(){
        String expected="{\n" +
                "\"Content\":\"Christmas\",\n" +
                "\"Icon\":9,\n" +
                "\"Creator\":\"Nicole\"\n" +
                "}";
        String actual="{\n" +
                "\"Icon\":99,\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Creator\":\"Nicole\"\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo Todos desiguales pero otro orden
    @Test
    public void verifyComparer19(){
        String expected="{\n" +
                "\"Content\":\"Christmas\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":323231235\n" +
                "}";
        String actual="{\n" +
                "\"Icon\":99,\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo al menos una diferencia
    @Test
    public void verifyComparer20(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":1651564855\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":99,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo al menos dos diferencias
    @Test
    public void verifyComparer21(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":\"17238323278\"\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":99,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    // test positivo los dos con nested e iguales
    @Test
    public void verifyComparer22(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2,\n" +
                " \"value\":\"5\"\n" +
                " }\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2,\n" +
                " \"value\":\"5\"\n" +
                " }\n" +
                "}";
        boolean expectedResult=true;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    // test positivo los dos con nested y el orden de nested varia
    @Test
    public void verifyComparer23(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2,\n" +
                " \"value\":\"5\"\n" +
                " }\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"value\":\"5\",\n" +
                " \"key\":2\n" +
                " }\n" +
                "}";
        boolean expectedResult=true;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    // test negativo los dos con nested pero el actual sin mismo key
    @Test
    public void verifyComparer24(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2,\n" +
                " \"value\":\"5\"\n" +
                " }\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2\n" +
                " }\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    // test negativo los dos con nested pero el expected sin mismo keys
    @Test
    public void verifyComparer25(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2\n" +
                " }\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2,\n" +
                " \"value\":\"5\"\n" +
                " }\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    // test negativo los dos con nested pero el expected es un JSON vacio
    @Test
    public void verifyComparer26(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{}\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2,\n" +
                " \"value\":\"5\"\n" +
                " }\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    // test negativo los dos con nested pero el actual es un JSON vacio
    @Test
    public void verifyComparer27(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2,\n" +
                " \"value\":\"5\"\n" +
                " }\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{}\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }
    // test positivo los dos con nested pero JSON vacios
    @Test
    public void verifyComparer28(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{}\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{}\n" +
                "}";
        boolean expectedResult=true;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    // test negativo un elemento del expected vacio (JSON syntax erroneo)
    @Test
    public void verifyComparer29(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2,\n" +
                " \"value\":\"5\"\n" +
                " }\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    // test negativo un elemento del actual vacio (JSON syntax erroneo)
    @Test
    public void verifyComparer30(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2,\n" +
                " \"value\":\"5\"\n" +
                " }\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    // test negativo un elemento del actual y del expected vacios (JSON syntax erroneo)
    @Test
    public void verifyComparer31(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    // test Positivo ignora el nested para actual
    @Test
    public void verifyComparer32(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\": \"$IGNORE\",\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2,\n" +
                " \"value\":\"5\"\n" +
                " }\n" +
                "}";
        boolean expectedResult=true;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    // test Negativo falta una llave en el expected
    @Test
    public void verifyComparer33(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\": \"$IGNORE\",\n" +
                "";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2,\n" +
                " \"value\":\"5\"\n" +
                " }\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    // test Negativo falta una llave en el actual
    @Test
    public void verifyComparer34(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\": \"$IGNORE\",\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2,\n" +
                " \"value\":\"5\"\n" +
                " }\n" +
                "";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    // test Negativo un error de syntax en expected
    @Test
    public void verifyComparer35(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\";9,\n" +
                "\"elements\": \"$IGNORE\",\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2,\n" +
                " \"value\":\"5\"\n" +
                " }\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    // test Negativo un error de syntax en actual
    @Test
    public void verifyComparer36(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\": \"$IGNORE\",\n" +
                "}";
        String actual="{\n" +
                "\"Content\";\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2,\n" +
                " \"value\":\"5\"\n" +
                " }\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    // test Negativo un error de syntax en ambos
    @Test
    public void verifyComparer37(){
        String expected="{\n" +
                "\"Content\";\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\": \"$IGNORE\",\n" +
                "}";
        String actual="{\n" +
                "\"Content\";\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2,\n" +
                " \"value\":\"5\"\n" +
                " }\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    // test Negativo el $IGNORE sin $ en expected
    @Test
    public void verifyComparer38(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\": \"IGNORE\",\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2,\n" +
                " \"value\":\"5\"\n" +
                " }\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    // test Negativo el $IGNORE escrito no en mayusculas al 100% (como un keyword estatico) y no lo
    // debe de asumir como keyword de ignorar por ello
    @Test
    public void verifyComparer39(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\": \"$iGNore\",\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"elements\":{\n" +
                " \"key\":2,\n" +
                " \"value\":\"5\"\n" +
                " }\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }
}
