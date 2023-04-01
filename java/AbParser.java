//AbParser
//reads an array of numbers and parses it according to Abniac specs.

import java.awt.*;
import java.util.*;

public class AbParser extends Frame{

  //Instance variables
  TextField txtInput = new TextField("This is input");
  TextArea txtOutput = new TextArea("This is output");

  public static void main(String args[]){
    AbParser ap = new AbParser();
    ap.resize(300,300);
    ap.show();
  } // end main
  
  public AbParser(){
    super("ABNIAC");
    setLayout(new BorderLayout());
    add("Center", txtOutput);
    add("South", txtInput);
  } // end constructor
  
  public boolean action(Event e, Object o){
    if(e.target==txtInput){
      parseString(txtInput.getText());
      return true;
    } else {
      return false;
    } // end if
  } // end action
  
  public void parseString (String commands){
  
    String temp;
    StringTokenizer st = new StringTokenizer(commands);
    while(st.hasMoreTokens()){
      temp = st.nextToken();
      if (temp.equals("1")){
        txtOutput.appendText("Store \n");
        temp = st.nextToken();
        txtOutput.appendText("...the value " + temp + "\n");
        temp = st.nextToken();
        txtOutput.appendText("...to address" + temp + "\n");
      } else if (temp.equals("2")){
        txtOutput.appendText("Add \n");
        temp = st.nextToken();
        txtOutput.appendText("...the value in " + temp + "\n");
        temp = st.nextToken();
        txtOutput.appendText("...to the value " + temp + "\n");
        temp = st.nextToken();
        txtOutput.appendText("...and put the answer in " + temp + "\n");
      } else {
        txtOutput.appendText("Something else \n");
      } // end if
    } // end while
  } // end parseString 
} // end class def
