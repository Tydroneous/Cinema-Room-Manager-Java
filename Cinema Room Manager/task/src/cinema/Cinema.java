package cinema;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Cinema {
    public static int currentIncome = 0;
    public static int takenSeats = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] mySize = cinemaSize(scanner);
        int rows = mySize[0];
        int columns = mySize[1];
        int selection = -1;
        String[][] myArray;
        myArray = initialPopCinema(rows, columns);
        while (selection != 0) {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            selection = scanner.nextInt();
            if (selection == 1) {
                printSeats(rows, columns, myArray);
            } else if (selection == 2) {
               myArray =  purchaseSelection(scanner, rows, columns, myArray);
            } else if (selection == 3) {
                statistics(rows, columns, takenSeats);
            }
        }

    }

    public static void printSeats(int rows, int columns, String[][] myArray) {
        System.out.println();

        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= columns; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        int count = 1;

        for (int i = 0; i <= rows - 1; i++) {
            System.out.print(count + " ");
            for (int j = 0; j <= columns - 1; j++) {
                System.out.print(myArray[i][j] + " ");
            }
            count++;
            System.out.println();
        }
    }

    public static int[] cinemaSize(Scanner scanner) {
        int[] mySize = new int[2];
        System.out.println("Enter the number of rows:");
        mySize[0] = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        mySize[1] = scanner.nextInt();
        return mySize;
    }

    public static void statistics(int rows, int columns, int takenSeats) {
        System.out.println();
        DecimalFormat myFormat = new DecimalFormat("#.00");
        if (takenSeats == 0) {
            System.out.println("Number of purchased tickets: 0");
            System.out.println("Percentage: 0.00%");
        } else {
            float percentage = (float) takenSeats / (rows * columns) * 100;
            System.out.println("Number of purchased tickets: " + takenSeats);
            System.out.println("Percentage: " + myFormat.format(percentage) + "%");
        }
            System.out.println("Current income: $" + currentIncome);


        // calculates total income
       int totalNumSeats = rows * columns;
        if (totalNumSeats <= 60) {
            System.out.println("Total income: $" + totalNumSeats * 10);
        } else {
            int firstHalf = (rows / 2) * columns * 10;
            int secondHalf = ((rows - (rows / 2)) * columns * 8);
            int total = firstHalf + secondHalf;
            System.out.println("Total income: $" + total);
        }
    }

    public static String[][] purchaseSelection(Scanner scanner, int rows, int columns, String[][] myArray) {
        int rowNum = 0;
        int seatNum = 0;
        while (true) {
            System.out.println();
            System.out.println("Enter a row number:");
            rowNum = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNum = scanner.nextInt();
            System.out.println();
            if (rowNum <= rows && seatNum <= columns) {
                if (myArray[rowNum - 1][seatNum - 1] != "B") {
                    myArray[rowNum - 1][seatNum - 1] = "B";
                    takenSeats++;
                    break;
                } else {
                    System.out.println("That ticket has already been purchased!");
                }
            } else {
                System.out.println("Wrong input!");
            }
        }
            if (rows * columns <= 60) {
                System.out.println("Ticket price: $10");
                currentIncome += 10;
            } else if (rowNum <= (rows / 2)) {
                System.out.println("Ticket price: $10");
                currentIncome += 10;
            } else {
                System.out.println("Ticket price: $8");
                currentIncome += 8;
            }
            return myArray;
    }

    public static String[][] initialPopCinema(int rows, int columns) {
        String[][] myArray = new String[rows][columns];
        for (int i = 0; i <= rows - 1; i++) {
            for (int j = 0; j <= columns - 1; j++) {
                myArray[i][j] = "S";
            }
        }
        return myArray;

    }
}