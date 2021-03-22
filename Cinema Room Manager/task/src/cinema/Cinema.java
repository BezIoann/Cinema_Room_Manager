package cinema;
import java.util.Scanner;
public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        int total = row*seats;
        char[][] seatsArray = new char[row][seats];
        createCinema(seatsArray, row, seats);
        //Start
        boolean flag = true;
        while (flag) {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            int select = scanner.nextInt();
            switch (select) {
                case 1:
                    showTheSeats(seatsArray, row, seats);
                    break;
                case 2:
                    System.out.println(buyTickets(seatsArray, row, seats));
                    break;
                case 3:
                    System.out.println(statistics(seatsArray, row, seats));
                    break;
                case 0:
                    flag = false;
                    break;
            }
        }

    }

    public static String buyTickets(char[][] seatsArray, int row, int seats) {
        int total = row*seats;
        Scanner scanner = new Scanner(System.in);
        int row1 = 0;
        int seats1 = 0;
        boolean flag = false;
        do {
            System.out.println("Enter a row number:");
            row1 = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seats1 = scanner.nextInt();
            if (row1 > row || seats1 > seats) {
                System.out.println("Wrong input!");
                flag = true;
            } else {
                if (seatsArray[row1-1][seats1-1] == 'B') {
                    System.out.println("That ticket has already been purchased!");
                    flag = true;
                } else {
                    flag = false;
                }
            }

        } while (flag);
        seatsArray[row1-1][seats1-1] = 'B';

        int price = 0;
        if (total <= 60) {
            price = 10;
        } else if (total > 60) {
            if (row1 <= row/2) {
                price = 10;
            } else {
                price = 8;
            }
        }
        return "Ticket price: $" + price;
    }

    public static void showTheSeats(char[][] seatsArray,int row, int seats) {
        System.out.println("Cinema: ");
        System.out.print(" ");
        for (int i = 1; i <= seats; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < row; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(seatsArray[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void createCinema(char[][] seatsArray, int row, int seats) {
        for (int i = 0; i < row; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < seats; j++) {
                seatsArray[i][j] = 'S';
                System.out.print(seatsArray[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static String statistics(char[][] seatsArray, int row, int seats) {
        int numberOfPurchcase = 0;
        int total = row*seats;
        int currentIncome = 0;
        int price = 0;
        int totalIncome = 0;
        if (total < 60) {
            totalIncome = total * 10;
        } else {
            totalIncome = 10 * (row/2) * seats + 8 * (row - row/2) * seats;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < seats; j++) {
              if (seatsArray[i][j] == 'B') {
                  numberOfPurchcase++;
                  if (total < 60) {
                      price = 10;
                  } else if (total >= 60) {
                      if (i < row/2) {
                          price = 10;
                      } else {
                          price = 8;
                      }
                  }
                  currentIncome += price;
              }
            }
        }
        double t = total;
        double percent = (numberOfPurchcase * 100)/t;
        String str1 = String.format("Number of purchased tickets: %d\n",numberOfPurchcase);
        String str2 = String.format("Percentage: %.2f%%\n",percent);
        String str3 = String.format("Current income: $%d\n",currentIncome);
        String str4 = String.format("Total income: $%d",totalIncome);
        return str1 + str2 + str3 + str4;
    }
}