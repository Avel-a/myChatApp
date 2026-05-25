/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mychatapp;
import java.util.Scanner;
import java.util.Random;
import java.io.IOException;
import java.io.FileWriter;
import org.json.JSONObject;


/*
 *
 * @author avela
 */
public class Message {

    
    //Declaring all the variables that are going to be used.
     private String messageID;
     private int messageNumber;
     private String recipientCell;
     private String messageText;
     private String messageHash;
     private String messageStatus;
     private String messageStore;
     private  static int totalMessages;
  
    //Initialising all string fields to empty strings
     public Message(){
     this.messageID = "";
     this.messageNumber= 0;
     this.recipientCell= "";
     this.messageText = "";
     this.messageHash = "";
     this.messageStatus = "";
  
    }
    //Allows creating messages objecct with these specified values
    public Message(int messageNumber, String recipient, String messageText) {

    this.messageNumber = messageNumber;
    this.recipientCell = recipient;
    this.messageText = messageText;
    this.messageID = generateMessageID();
    this.messageHash = createMessageHash();
    this.messageStatus = "";
    
    }
    //Generating a random 10-digit message id
     public String generateMessageID() {
       
    //Creating random object used to create random numbers
     Random random = new Random();
        
    //Empty string used to create random numbers
     String messageID= "";
 
    //The loop have to run 10 times
     for (int i = 0; i < 10; i++) {

   //Add random digit to ID
     messageID = messageID + random.nextInt(10);
    }
   //Returning
    return messageID;
    }
     
    //We are checking if the message ID is valid and if it contains only 10 characters.
     public boolean checkMessageID() {
     
    //we are checking the length of message ID
     if (messageID.length() <= 10) {

    return true;

    } else {

    return false;
       }
    }
   //We are checking if recipient number is correct
     public String checkRecipientCell() { 
   
    //we are checking if the number start wih +27 and if the length is correct
     if (recipientCell.startsWith("+27") && recipientCell.length()>= 12) {

     return "Cell phone number successfully captured.";

    } else {

     return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    //This method checks if the message is under 250 characters
     public String checkMessageLength() {
     
    //We are if the length of the message is correct
    if (messageText.length() <= 250) {

     return "Message ready to send.";

    } else {
        
    //Calculate how many characters are over the limit
     int over = messageText.length() - 250;

     return "Message exceeds 250 characters by " + over + "; please reduce the size.";
        }
    }

    // We are creating a message hash
     public String createMessageHash() {

    //Get first 2 numbers from message ID
     String firstTwoDigits = messageID.substring(0, 2);
     
    //Split message into words
     String[] words = messageText.split(" ");

    //Get first word
     String firstWord = words[0];

    //Get last word
     String lastWord = words[words.length - 1];

    //Build hash format
     String hash = firstTwoDigits + ":" + messageNumber + ":" + firstWord + lastWord;

    //Return hash in uppercase
     return hash.toUpperCase();
    }
     
   //Allows the user to select what to do with the message
   public String sentMessage() {

    //Scanner for user input
     Scanner input = new Scanner(System.in);

    //Display menu options
     System.out.println("What would you like to do with this message?");
     System.out.println("1) Send Message");
     System.out.println("2) Disregard Message");
     System.out.println("3) Store Message to send later");
    
    //Store user option
     int choice = input.nextInt();

    //Switch statement handles options
     switch (choice) 
    { 
     case 1:
    //updating message status
     messageStatus = "Sent";

    //Increase total messages count
     totalMessages++;

     return "Message successfully sent.";
    
   //Disregard message
     case 2:

   //Updating message status
     messageStatus = "Disregarded";
    
     return "Press 0 to delete the message.";

    //Store message
     case 3:
    
    //Updating message status
    messageStatus = "Stored";

    //Save message to JSON file
    storeMessage();

    return "Message successfully stored.";
             
    //Handles wrong menu options
     default:
         
     return "Invalid option selected.";
        }
    }
    // This second sentMessage method is added to help JUnit test works properly
    public String sentMessage(int choice) {

    switch (choice) {

     case 1:
    messageStatus = "Sent";
    totalMessages++;
    return "Message successfully sent.";
    
    case 2:
    messageStatus = "Disregarded";
    return "Press 0 to delete the message.";

    case 3:
    messageStatus = "Stored";
    storeMessage();

    return "Message successfully stored.";

    default:

    return "Invalid option selected.";
    }
}
    //Displays all message details
     public String printMessages() {

    //we are going to use empty string used to build output
     String MessageDetails = "";

    //We are going to add message ID
     MessageDetails += "Message ID: " + messageID + "\n";

    //We are going to ad message hash
    MessageDetails += "Message Hash: " + messageHash + "\n";

    // We are going to add recipient number
     MessageDetails += "Recipient: " + recipientCell + "\n";

    // We are going message text
     MessageDetails += "Message: " + messageText + "\n";

    // This is going to return the completed message details
     return MessageDetails;
    }
     
     // Returns total number of sent messages
     public static int returnTotalMessages() {

    return totalMessages;
    
    }  
    //We are going to store the message into our JSON file
     public void storeMessage() {

    //We are creating JSON object
     JSONObject obj = new JSONObject();

    //We are going to add the message ID to JSON
    obj.put("messageID", messageID);

    //We are going to add message hash to JSON
     obj.put("messageHash", messageHash);

    //We are going to add recipient number to JSON
     obj.put("recipient", recipientCell);

    //Add message text to JSON
     obj.put("message", messageText);

    //We are going to try writing to JSON file
     try {
            
    //We are going to open file
     FileWriter file = new FileWriter("messages.json", true);

    //We are going to write JSON object into file
     file.write(obj.toString());

    // We are going to move to the next line
     file.write("\n");

    //We are going to close the file
     file.close();

    } catch (IOException e) {

    //Display error if file fails
    System.out.println("Error writing to JSON file.");
        }
    }

}
     
       

    
