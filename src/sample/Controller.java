package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    @FXML
    Text text1;
    @FXML
    Text text2;
    @FXML
    Text text3;
    @FXML
    Text text4;
    @FXML
    Text text5;
    @FXML
    Text text6;
    @FXML
    Text text7;
    @FXML
    Text text8;
    @FXML
    Text text9;



    public static ArrayList<Text> textList = new ArrayList<Text>();
    public Object[] textArray = {text1,text2,text3,text4,text5,text6,text7,text8,text9};


    Text x = text1;

    @FXML
    TextField textfield1;
    @FXML
    TextField textfield2;

    @FXML
    Button button1;
    @FXML
    Button button2;

    public void initiateGUI(){
        textList.add(text1);
        textList.add(text2);
        textList.add(text3);
        textList.add(text4);
        textList.add(text5);
        textList.add(text6);
        textList.add(text7);
        textList.add(text8);
        textList.add(text9);

        for (Text t : textList){
            t.setText("");
        }
    }

    public void pushButton() throws IOException, ParseException {
        HTTP.sendGET("movie");
        String text = HTTP.resultArray[0][1];
        System.out.println(text);
        text1.setText(text);
    }

    public void pushButton2() throws IOException, ParseException {
        initiateGUI();
        String query = textfield1.getText();
        String stringindex = textfield2.getText();
        System.out.println(query);
        System.out.println(stringindex);

        if(stringindex.equals("")) {
            System.out.println("empty index");
            HTTP.index = -1;
            HTTP.sendGET(query);
            for (int i = 0; i <= textList.size(); i++) {
                if (i < HTTP.resultArray.length) {
                    String t1 = HTTP.resultArray[0][1];
                    String t2 = HTTP.resultArray[0][2];
                    System.out.println(t1 + " " + t2);
                    x = textList.get(i);
                    x.setText(t1 + " " + t2);
                }

            }
        }
        else {
            HTTP.index = Integer.parseInt(stringindex);
            HTTP.sendGET(query);
            for(int i = 0; i <= textList.size(); i++){
                if(i < HTTP.resultArray.length){
                    String t1 = HTTP.resultArray[HTTP.index][(i * 2) + 1];
                    String t2 = HTTP.resultArray[HTTP.index][(i * 2) + 2];
                    System.out.println(t1+" "+t2);
                    x = textList.get(i);
                    x.setText(t1+" "+t2);
                }
            }

        }





    }

}
