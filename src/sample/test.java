package sample;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test {

    Controller con = new Controller();

    @Test
    void checkTextArray(){
        //Controller.textList.add(con.text1);
        //con.textArray[0] = con.text1;
        System.out.println(Controller.textList.get(0));
        System.out.println(con.textArray[0]);
        System.out.println(con.textArray.length);
    }

    @Test
    void checkTextList(){
        con.initiateGUI();
        System.out.println(Controller.textList.get(0));
    }

    @Test
    void checkNull(){
        HTTP.index = Integer.parseInt(null);
        System.out.println(HTTP.index);

    }

    @Test
    void testSplit(){
        String teststring = "{\"budgetInMillions\":281,\"academyAwardNominations\":30,\"runtimeInMinutes\":558,\"academyAwardWins\":17,\"rottenTomatesScore\":94,\"name\":\"The Lord of the Rings Series\",\"boxOfficeRevenueInMillions\":2917,\"_id\":\"5cd95395de30eff6ebccde56\"}";
        String[] test = teststring.split("[:,{}]");
        assertEquals("\"budgetInMillions\"",test[1]);

    }

}
