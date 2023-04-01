//AbMail.java
//The email frame for Abniac
//Andy Harris, 2/12/98

import java.awt.*;
import java.io.*;
import java.net.*;

public class AbMail extends Frame{

  //instance variables
  TextField txtProg = new TextField(20);
  TextField txtName = new TextField(20);
  TextField txtAddress = new TextField(20);
  TextArea txtMessage = new TextArea(10,20);
  Button btnOK = new Button("OK");
  Button btnCancel = new Button("Cancel");
  GridBagLayout gbl = new GridBagLayout();
  GridBagConstraints constraints = new GridBagConstraints();
  
  String message;			//holds the message from calling prog.

  public static void main(String args[]){
    AbMail am = new AbMail("This is a test\n");
    am.resize(400,200);
    am.pack();
    am.show();
  }

  public AbMail(String msg){
    //constructor

    super("Email your program");

    message = new String(msg);

    setLayout(gbl);
    addComp(new Label("Program Name:"),0,0,1);
    addComp(txtProg,0,1,1);
    addComp(new Label("Author:"),1,0,1);
    addComp(txtName,1,1,1);
    addComp(new Label("Instructor's email:"),2,0,1);
    addComp(txtAddress,2,1,1);
    addComp(new Label("Message:"),3,0,2);
    addComp(txtMessage,4,0,2);
    addComp(btnOK,5,0,2);
    addComp(btnCancel,6,0,2);

    txtMessage.setText(message);

  } // end constructor

  private void addComp(Component comp, int row, int col, int gW){
    //These are the only things that will vary in my layout, I think
    constraints.gridx = col;
    constraints.gridy = row;
    constraints.weightx = 1;
    constraints.weighty = 1;
    constraints.gridheight = 1;
    constraints.gridwidth = gW;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.anchor = GridBagConstraints.CENTER;

    gbl.setConstraints(comp, constraints);
    add(comp);
  } // end addComp

  public boolean handleEvent(Event e){
    switch (e.id){
      case Event.WINDOW_DESTROY:
        this.hide();
        this.dispose();
        //System.exit(0);     taken out... causes applet probs
        return true;
      case Event.ACTION_EVENT:
        if (e.target == btnOK){
          sendEmail();
          return true;
        } else if (e.target == btnCancel){
          this.hide();
          this.dispose();
          //System.exit(0);
          return true;
        } else {
          return false;
        } // end if
      default:
        return false;
    } // end switch
  } // end handleEvent

  private void sendEmail(){
    //validates the data and sends the message

    String prog;			//The name of the program being sent
    String author;			//The programmer's name
    String address;			//The address of the instructor
    boolean valid = false;		//All fields have been correctly filled
    int i;
    PrintStream out;
    DataInputStream in;

    //get variables from form
    prog = txtProg.getText();
    author = txtName.getText();
    address = txtAddress.getText();

    //validate variables
    if (prog.equals("")) {
      valid = false;
      System.out.println("Must have a program name");
    } else {
      valid = true;
    } // end if

    if (author.equals("")){
      valid = false;
      System.out.println("Must have a programmer name");
    } else {
      valid = true;
    } // end if
  
    if (address.equals("")){
      valid = false;
      System.out.println("Must have the instructor's email address");
    } else {
      valid = true;
    } // end if
  
    if (valid){
      System.out.println("EMAILING...");

      System.out.print(message);

      try{
        //send the message
        Socket sock = new Socket("cs.iupui.edu", 25);
        out = new PrintStream(sock.getOutputStream());
        in = new DataInputStream(sock.getInputStream());
        out.println("HELO <abniac>");
        System.out.println(in.readLine());
        out.println("MAIL from: <Abniac>");
        System.out.println(in.readLine());
        out.println("RCPT to: <" + address + ">");
        System.out.println(in.readLine());

        out.println("DATA");
        out.println("");
        out.println("");
        out.println("Program: " + prog + "\n");
        out.println("Author: " + author  + "\n");
        out.print(message);
        out.println("");
        out.println(".");
        System.out.println(in.readLine());
        out.close();
        sock.close();
      } catch (Exception exc){
        System.out.println("Problem: "+ exc.toString());
      } //end try
      // Close down the window
      this.hide();
      this.dispose();
      //System.exit(0);
    } // end if

  } // end sendEmail

} // end class def
