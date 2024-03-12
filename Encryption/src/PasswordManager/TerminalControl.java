package PasswordManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.exit;

// This class runs the prog on terminal
public class TerminalControl {

    public TerminalControl() {

        // Starts with signIn or signUp
        switch (start())
        {
            case 0: exit(0); break; // Exit program
            case 1: printSignUp(); break; // Create new account
            case 2: printSignIn(); break; // Login to your account
        }

    }
    public int start() {
        int userInput = 0;

        try {
            printNewPage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        do {
            System.out.println("SIGNUP -> Press 1");
            System.out.println("SIGNIN -> Press 2");
            System.out.println("Leave the program -> Press 0");

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

                try {
                    userInput = Integer.parseInt(in.readLine());
                }
                catch(IOException e1) {
                    System.out.println(e1.getMessage());
                }
                finally {
                    in.close();
                }
            }
            catch(Exception e2) {
                System.out.println(e2.getMessage());
            }

        }while(userInput < 0 || userInput > 2);

        return userInput;
    }

    public void printNewPage() throws IOException {
        //Runtime.getRuntime().exec("cls");
        System.out.println("\n=================================================================\n");
    }

    public void printSignUp()
    {
        System.out.println("Would you like to become a part of our platform?\nLets create your account now :)");

    }

    public void printSignIn()
    {
        System.out.println("Ready to access your account? Lets get you logged in :)");
    }
}
