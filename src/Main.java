import java.util.Scanner;

public class Main {

    final static Scanner scanner= new Scanner(System.in);
    public static void main(String[] args) {
        String seating = """   
            1 2 3 4 5 6 7 8
          1 S S S S S S S S
          2 S S S S S S S S
          3 S S S S S S S S
          4 S S S S S S S S
          5 S S S S S S S S
          6 S S S S S S S S
          7 S S S S S S S S """;
        System.out.println("Cinema");
        System.out.println(seating);
//If the total number of seats in the screen room is not more than 60, then the price of each ticket is 10 dollars.
//In a larger room, the tickets are 10 dollars for the front half of the rows and 8 dollars for the back half.
// Please note that the number of rows can be odd, for example, 9 rows. In this case, the first half is the first 4 rows,
// and the second half is the other 5 rows.
        System.out.println("Enter the number of rows:");
        int inputRow = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int inputSeat = scanner.nextInt();
        System.out.println("Total income:");
        income(inputRow, inputSeat);


    }

    static int income(int row, int seat) {
        int ticket;
        int totalSeat = row * seat;
        int rowDivision;
        int firstHalf;
        int secondHalf;
        if (totalSeat < 60) { // total seat less than 60 each ticket cost 10 dollar
            ticket = 10;
            System.out.printf("$%d", totalSeat * ticket);
        } else  {
            if (row % 2 == 0) { // if the row is even
                rowDivision = (row / 2);
                firstHalf = rowDivision * 10 * seat; // first half row seat cost 10 $
                secondHalf = rowDivision * 8 * seat; // second half row seat cost 8$
                System.out.printf("$%d", firstHalf + secondHalf);
            } else { // if the row is odd
                rowDivision = (row - 1)/2;
                firstHalf = (rowDivision) * 10 * seat; // first half (n rows)
                secondHalf = (rowDivision + 1) * 8 * seat; // second half (n + 1) rows
                System.out.printf("$%d", firstHalf + secondHalf);
            }
        }
        return 0;
    }
}

