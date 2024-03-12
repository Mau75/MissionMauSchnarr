package PasswordManager;
/*
    Requirements:
        - AES or RSA encryption and decryption ?
        - Implement database (MySQL or SQLite) to store user credentials
        - Password generator
*/

public class Main {
    public static void main(String[] args) {
        System.out.print("\n\nWelcome to your Console Password Management System! \n\n");
        new TerminalControl();
    }
}
