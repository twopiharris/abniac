//AbAscii
//Creates an ASCII chart for ABNIAC
//Andy Harris, 9/17/97

import java.awt.*;

public class AbAscii extends Frame{

  List lstAsc = new List();
  Button btnOK = new Button("OK");

  public static void main(String args[]){
    AbAscii aa = new AbAscii();
    aa.resize(300,300);
    aa.show();
  } // end main
  
  public AbAscii(){
    super("ASCII Chart");

    String theLine;

    setLayout(new BorderLayout());
    add("Center", lstAsc);
    add("South", btnOK);
    
    //setup lstAsc
    lstAsc.setFont(new Font("Courier", Font.BOLD, 25));
    lstAsc.setForeground(Color.yellow);
    lstAsc.setBackground(Color.blue);
    
    for (int i =0; i<= 255; i++){
      theLine = String.valueOf(i);
      if (i < 10){
        theLine += " ";
      } // end if
      if (i < 100){
        theLine += " ";
      } // end if
      
      theLine += "     |     ";
      theLine += (char) i;
      lstAsc.addItem(theLine);
    } // end for
    
  } // end constructor

  public boolean handleEvent(Event e){
    if (e.id == Event.ACTION_EVENT){
      if (e.target == btnOK){
        this.hide();
        this.dispose();
        return true;
      } else {
        return false;
      } // end target if
    } else if (e.id == Event.WINDOW_DESTROY){
      this.hide();
      this.dispose();
      return true;
    } else {
      return false;
    } // end action if
  } // end handleEvent

} // end class def

