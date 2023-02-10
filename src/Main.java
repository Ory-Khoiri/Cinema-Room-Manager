import java.util.Scanner;

public class Main {

    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        int inputRow = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int inputSeat = scanner.nextInt();
        String[][] x = cinemaSeating(inputRow + 1, inputSeat + 1);
        menu(x, inputRow, inputSeat, true);

    }

    static void menu(String[][] seatingArrangement, int inputRow, int inputSeat, boolean bool) {
        double ticketPurchased = 0;
        double inputTotalSeat = inputRow * inputSeat;
        double currentIncome = 0;
        double totalIncome = income(inputRow, inputSeat);
        while (bool) {
            String menuList = """
                    \n1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit
                    """;
            System.out.println(menuList);
            int index = scanner.nextInt();

            switch (index) {
                case 0:
                    bool = false;
                    break;
                case 1:
                    seatingDisplay(seatingArrangement, inputRow + 1, inputSeat + 1);
                    break;
                case 2:
                    System.out.println("\nEnter a row number:");
                    int rowNumber = scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    int seatNumber = scanner.nextInt();
                    while (rowNumber > inputRow || seatNumber > inputSeat) { //ExceptionHandling
                        System.out.println("Wrong input!");
                        System.out.println("\nEnter a row number:");
                        rowNumber = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        seatNumber = scanner.nextInt();
                    }
                    while (seatingArrangement[rowNumber][seatNumber] == "B") { //ExceptionHandling
                        System.out.println("That ticket has already been purchased!");
                        System.out.println("\nEnter a row number:");
                        rowNumber = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        seatNumber = scanner.nextInt();
                    }
                    currentIncome += ticketPrice(rowNumber, seatNumber, inputRow + 1, inputSeat + 1, seatingArrangement);
                    ticketPurchased += 1;
                    break;
                case 3:
                    // to get decimal both current and total has to be double type
                    // use number of ticket sold as percentage to get average seat sold
                    double percentage = (ticketPurchased / inputTotalSeat) * 100;
                    System.out.printf("Number of purchased tickets: %.0f \n", ticketPurchased);
                    System.out.printf("Percentage: %.2f%% \n", percentage);
                    System.out.printf("Current income: $%.0f \n", currentIncome);
                    System.out.printf("Total income: $%.0f \n", totalIncome);
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }


    static String[][] cinemaSeating(int row, int column) {
        //Read two positive integer numbers that represent the number of rows and seats in each row and print the seating arrangement like in the first stage.
        //Then, read two integer numbers from the input: a row number and a seat number in that row.
        // These numbers represent the coordinates of the seat according to which the program should print the ticket price.
        // The ticket price is determined by the same rules as the previous stage:
        String[][] seatingArrangement = new String[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i == 0 && j == 0) {
                    seatingArrangement[i][j] = " ";
                } else if (i == 0) {
                    seatingArrangement[i][j] = String.valueOf(j);
                } else if (j == 0) {
                    seatingArrangement[i][j] = String.valueOf(j + i);
                } else {
                    seatingArrangement[i][j] = "S";
                }
            }
        }

        return seatingArrangement;
    }

    static void seatingDisplay(String[][] seatingArrangement, int row, int column) {
        System.out.println("Cinema:");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.printf("%s ", seatingArrangement[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static double ticketPrice(int rowNumber, int seatNumber, int row, int column, String[][] seatingArrangement) {

        seatingArrangement[rowNumber][seatNumber] = "B";
        int totalSeat = (row - 1) * (column - 1);
        int midpoint = (row - 1) / 2;
        if (totalSeat < 60) {
            System.out.printf("ticket price: $%d\n", 10);
            return 10;
        } else {
            if (rowNumber > midpoint) {
                System.out.printf("ticket price: $%d\n", 8);
                return 8;
            } else {
                System.out.printf("ticket price: $%d\n", 10);
                return 10;

            }
        }
    }

        static double income(int row, int seat){
            //If the total number of seats in the screen room is not more than 60, then the price of each ticket is 10 dollars.
            //In a larger room, the tickets are 10 dollars for the front half of the rows and 8 dollars for the back half.
            // Please note that the number of rows can be odd, for example, 9 rows. In this case, the first half is the first 4 rows,
            // and the second half is the other 5 rows.
            int ticket;
            int totalSeat = row * seat;
            int rowDivision;
            int firstHalf;
            int secondHalf;
            if (totalSeat < 60) { // total seat less than 60 each ticket cost 10 dollar
                ticket = 10;
                return totalSeat * ticket;
            } else {
                if (row % 2 == 0) { // if the row is even
                    rowDivision = (row / 2);
                    firstHalf = rowDivision * 10 * seat; // first half row seat cost 10 $
                    secondHalf = rowDivision * 8 * seat; // second half row seat cost 8$
                } else { // if the row is odd
                    rowDivision = (row - 1) / 2;
                    firstHalf = (rowDivision) * 10 * seat; // first half (n rows)
                    secondHalf = (rowDivision + 1) * 8 * seat; // second half (n + 1) rows
                }
                return firstHalf + secondHalf;
            }
        }
    }

