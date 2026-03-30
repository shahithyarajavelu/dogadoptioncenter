package controller;

import java.sql.SQLException;
import java.util.Scanner;

import dao.*;
import entiry.*;

public class MainController {

    static Scanner sc = new Scanner(System.in);
    static UserDAO userDAO = new UserDAO();
    static DogDAO dogDAO = new DogDAO();
    static AdoptionDAO adoptionDAO = new AdoptionDAO();

    static int loggedInUser = -1;

    public static void main(String[] args) throws SQLException {

        while (true) {

            System.out.println("\n1 Register\n2 Login\n3 Exit");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    User u = new User();

                    sc.nextLine();
                    System.out.print("Name: ");
                    u.setName(sc.nextLine());

                    System.out.print("Email: ");
                    u.setEmail(sc.nextLine());

                    System.out.print("Password: ");
                    u.setPassword(sc.nextLine());

                    userDAO.register(u);
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Password: ");
                    String pass = sc.nextLine();

                    loggedInUser = userDAO.login(email, pass);

                    if (loggedInUser != -1) {
                        System.out.println("Login Successful!");
                        userMenu();
                    } else {
                        System.out.println("Invalid Credentials");
                    }
                    break;
            }
        }
    }

    public static void userMenu() throws SQLException {

        while (true) {

            System.out.println("\n1 View Dogs\n2 Adopt Dog\n3 Logout");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    dogDAO.viewAvailableDogs();
                    break;

                case 2:
                    System.out.print("Enter Dog ID: ");
                    int dogId = sc.nextInt();
                    adoptionDAO.adoptDog(loggedInUser, dogId);
                    break;

                case 3:
                    return;
            }
        }
    }
}