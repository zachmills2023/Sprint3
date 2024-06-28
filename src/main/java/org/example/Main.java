package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args)
    {
        mainMenu();
    }

    public static void mainMenu()
    {
        Scanner scanner = new Scanner(System.in);
        int initialInput = 0;
        while (initialInput != 1 || initialInput != 2)
        {
            System.out.print("Please choose from one of the options below:" +
                    "\n1.) Get a list of stock symbol suggestions" +
                    "\n2.) Enter a stock symbol.");
            if(scanner.hasNextInt())
            {
                initialInput = scanner.nextInt();
                // Display the suggested stock symbols and their full company names.
                if (initialInput == 1)
                {
                    displaySymbols();
                }
                else if (initialInput == 2)
                {
                    // Create the needed objects.
                    StockService stockService = new StockService();
                    // Scanner scanner = new Scanner(System.in);
                    List<String> symbols = new ArrayList<>();

                    // Get the input for the stock symbols.
                    String input;
                    while (true)
                    {
                        System.out.println("Enter a stock symbol (or type 'done' to finish)");
                        input = scanner.nextLine().trim().toUpperCase();
                        if (input.equalsIgnoreCase("done"))
                        {
                            scanner.close();
                            fetchAndDisplayStockData(stockService, symbols);
                            return;
                        }
                        if (!input.isEmpty())
                        {
                            symbols.add(input);
                        }
                        // An error will already display if there is an issue with the input.
                    }
                }
                else
                {
                    System.out.println("Invalid input. Try again. Make sure to enter either option 1, or 2.");
                }
            }


        }
    }

    public static void displaySymbols()
    {
        System.out.print(
        "\nAUTOMOTIVE:\n" +
        "* Tesla:            TSLA\n" +
        "* Ford:             F\n" +
        "* Subaru:           FUJHY\n" +
        "* Nissan:           NSANY\n" +
        "* Rivian:           RIVN\n" +
        "* BMW:              BMWYY\n" +
        "\n" +
        "GROCERY:\n" +
        "* Walmart:          WMT\n" +
        "* Amazon:           AMZN\n" +
        "* Costco:           COST\n" +
        "* Kroger:           KR\n" +
        "* Walgreens:        WBA\n" +
        "\n" +
        "TECHNOLOGY:\n" +
        "* Apple:            AAPL\n" +
        "* Microsoft:        MSFT\n" +
        "* Google:           GOOG\n" +
        "* Meta:             META\n" +
        "* Sony:             SONY\n" +
        "\n" +
        "AEROSPACE:\n" +
        "* Boeing:           BA\n" +
        "* Lockheed Martin:  LMT\n" +
        "* Raytheon:         RTX\n" +
        "* General Dynamics: GD\n" +
        "* Northrop Grumman: NOC\n\n");
    }

    // This seems redundant, but it is done in the function to help it to call just one symbol at a time from the loop.
    public static void fetchAndDisplayStockData(StockService stockService, List<String> symbols)
    {
        for (String symbol : symbols)
        {
            stockService.fetchStockData(symbol);
        }
    }
}

