package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.http.HttpHeaders;
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
    public static int nameIndex;
    public static String stringindex;
    public static String query;
    public static int pageIndex;


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

    public void display() throws IOException, ParseException {
        if(stringindex.equals("")) {
            System.out.println("empty index");
            HTTP.index = -1;
            HTTP.sendGET(query);
            for (int i=0; i<HTTP.resultArray[0].length;i++){
                System.out.println("for loop"+i);
                System.out.println(HTTP.resultArray[0][i]);
                if(HTTP.resultArray[0][i].equals("\"name\"")){
                    nameIndex = i;
                    System.out.println(nameIndex);
                    break;
                }

            }
            for (int i = 0; i < textList.size() && i < HTTP.resultArray[0].length && i < HTTP.docs.size(); i++) {
                String in = Integer.toString(i);
                String t1 = HTTP.resultArray[i+pageIndex][nameIndex];
                String t2 = HTTP.resultArray[i+pageIndex][nameIndex+1];
                System.out.println(in+":"+ t1 + " " + t2);
                x = textList.get(i);
                x.setText(t1 + " " + t2);


            }
        }
        else {
            HTTP.index = Integer.parseInt(stringindex);
            HTTP.sendGET(query);
            for(int i = 0; i <= textList.size(); i++){
                if((i*2)+1 < HTTP.resultArray[HTTP.index].length){

                    String t1 = HTTP.resultArray[HTTP.index][((i+pageIndex) * 2) + 1];
                    String t2 = HTTP.resultArray[HTTP.index][((i+pageIndex) * 2) + 2];
                    System.out.println(t1+" "+t2);
                    x = textList.get(i);
                    x.setText(t1+" "+t2);
                }
            }

        }

    }

    public void pushButton() throws IOException, ParseException {
        pageIndex+=9;
        display();
    }

    public void pushButton2() throws IOException, ParseException {
        initiateGUI();
        pageIndex=0;
        query = textfield1.getText();
        stringindex = textfield2.getText();
        System.out.println(query);
        System.out.println(stringindex);
        display();
    }

}
