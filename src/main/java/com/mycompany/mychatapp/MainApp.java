/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mychatapp;
import java.util.Scanner;

/**
 *
 * @author avela
 */
public class MainApp {
    public static void main(String[] args){
    
        //Declaring a new scanner
        
        Scanner input = new Scanner(System.in);
        
        //This gets the login page ready for the user
        
        Login login = new Login();
        
        // --- REGISTRATION SECTION ---
        
        /*This method where the user is going  to be asked to insert their 
        username, password and their cellphone number in order to create their
        profile
        */
        
        System.out.println("=== USER REGISTRATION ===");
        while(true){
        //Prompting the user to insert a name
        System.out.println("Enter a name:");
        String name = input.nextLine();
        //Prompting the user to insert a surname
        System.out.println("Enter a surname");
        String surname = input.nextLine();
        //Prompting the user to insert username
        System.out.println("Enter a username: ");
        String username = input.nextLine();
        
        //Prompting the user to insert password
        System.out.println("Enter a password: ");
        String password = input.nextLine();
        
        //Prompting the user to insert number
        System.out.println("Enter your South African (+27...):");
        String phone = input.nextLine();
        
        //Checks and save the username, passwoed, and phonenumber
        String response = login.registerUser(username, password,  phone);
        
        //Shows the user if their registration was succesfull or not.
        System.out.println(response);
        
        if(response.equals("User registered successfully")){
            break;
        }
        System.out.println("Please try again");
        }
        //---LOGIN SECTIION---
        
        /* This method is where it ask the registered user for their 
        username and password in order for the user's to login
        */
        System.out.println("\n === USER LOGIN ===");
        
        //Prompting the user to enter username
        System.out.println("Enter your username: ");
        String loginUsername = input.nextLine();
       
        //Prompting user to enter password
        System.out.print("Enter your passwor:");
        String loginPassword = input.nextLine();
       
        /*This checks if the username, password  entered by the user
        is corresponding with the one that was saved when they
        were registering
       */
       boolean loggedIn = login.loginUser(loginUsername, loginPassword);
       
       /* If the user was able to login succesfully or not
       an appropriate message will appear on the user's screen
       */
       String loginMessage = login.returnLoginStatus(loggedIn);
       System.out.println(loginMessage);
       
        //--- QUICKCHAT SECTION---
        
       if (loggedIn) {
        // Displaying a welcom message to the user
        System.out.println("Welcome to QuickChat.");
        boolean running = true;

        // While loop keeps application running
        while (running) {

        // Display menu to the user
        System.out.println("\n===== CHAT APP MENU =====");
        System.out.println("1) Send Messages");
        System.out.println("2) Show recently sent messages");
        System.out.println("3) Quit");

        //Prompting theuser to choose menu option
        System.out.println("Choose an option:");

        // Store menu choice
        int choice = input.nextInt();
         input.nextLine();
         
        // Switch statement handles menu options
        switch (choice) 
        {
        case 1:

        //Asking the user how many messages they want to send
        System.out.println("How many messages would you like to send?");

        // Store number of messages
        int numMessages = input.nextInt();
         input.nextLine();

        // For loop runs based on number entered
        for (int i = 0; i < numMessages; i++) {

        // Message number from loop counter
        int messageNumber = i;

        // Display message heading
        System.out.println("\n--- Message " + (i + 1) + " ---");
        
        // Prompting the user for recipient number
        System.out.println("Enter recipient number:");
        String recipient = input.nextLine();

        //Prompting the user to type a message
         System.out.println("Enter your message:");
         String messageText = input.nextLine();

        // We are creating A Message object
        Message message = new Message(messageNumber, recipient, messageText);

        // Display recipient validation result
        System.out.println(message.checkRecipientCell());

        // Display message length result
        System.out.println(message.checkMessageLength());

        // Continue only if inputs are valid
        if (message.checkRecipientCell().equals("Cell phone number successfully captured.")
           && message.checkMessageLength().equals("Message ready to send.")) {

        // Ask user what to do with message
        String result = message.sentMessage();

        // Display result
        System.out.println(result);

        // Print message details
        System.out.println(message.printMessages());
           }                 
        }
        break;
        case 2:
            
        //This is going to display to the user that this feature has been not created yet
        System.out.println("Coming Soon.");
        
        // Display total messages sent
        System.out.println("Total messages sent: "
        + Message.returnTotalMessages());
        break;
        case 3:

        //This is going to display a exit message to the user
        System.out.println("Exiting ChatApp...");

        // Stops while loop
        running = false;

        break;
        // This handles invalid menu choices entered by the user
        default:

        System.out.println("You have entered an nvalid option, please choose between 1 and 3.");
           }
        }
        
        } else {

        // This is the message that is going to be displayed if login fails
        System.out.println("Login failed, please try again.");
        }
      }  
   }
       
