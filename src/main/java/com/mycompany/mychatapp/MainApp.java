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
       System.out.println("Login successful");
    }
}
