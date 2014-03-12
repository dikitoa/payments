package es.unileon.Payments;

import java.util.Scanner;

public class Tester {
	public static void main(String[] args) {
		boolean exit = false;
		int option = 0;
		int buyLimit = 0;
		int cashLimit = 0;
		Scanner scan = new Scanner(System.in);
		
		showPresentation();
		
		while (!exit) {
			showMenu();
			prompt();
			option = scan.nextInt();
			switch (option) {
			case 1:
				System.out.println("Choose card type: \n\t 1-Devit Card \n\t 2-Credit Card \n\t 3-Return");
				prompt();
				option = scan.nextInt();
				if (option == 1 || option == 2) {
					System.out.print("Write your buy limit: ");
					buyLimit = scan.nextInt();
					System.out.print("Write your cash limit: ");
					cashLimit = scan.nextInt();
					createCard(option, buyLimit, cashLimit);
				}
				
				break;
			case 2:
				
				break;
			case 3:
				exit = true;
				System.out.println("Bye, see you soon :)");
				break;
			default:
				System.out.println("-- This option is not available\n");
				break;
			}
		}
		scan.close();
	}
	
	private static void prompt() {
		System.out.print("ULE-Bank $ ");
	}
	
	private static void showPresentation() {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("$  WELCOME TO ULE-BANK $");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$");
	}

	private static void showMenu() {
		System.out.println("\n\nWhat do you want to do? (Choose an option)");
		System.out.println("\n\t 1-Create a new Card");
		System.out.println("\n\t 2-Delete a Card");
		System.out.println("\n\t 3-Exit");
		System.out.println();
	}
	
	private static void createCard(int type, int buyLimit, int cashLimit) {
		if (type == 1) {
			createDevitCard();
		} else if (type == 2) {
			createCreditCard();
		}
	}
	
	private static void createDevitCard() {
		DevitCard card = new DevitCard();
		System.out.println("CARD INFO:");
		System.out.println("\t Card Type: " + card.getCardType());
		System.out.println("\t Card Number: " + card.getCardId());
		System.out.println("\t PIN: " + card.getPin());
		System.out.println("\t Cash limit: " + card.getCashLimit());
		System.out.println("\t Buy limit: " + card.getBuyLimit());
		System.out.println("\t Expiration Date: " + card.getExpirationDate());
		System.out.println("\t CVV: " + card.getCvv());
		System.out.println("\t Commissions: " + card.getCommission());
	}
	
	private static void createCreditCard() {
		CreditCard card = new CreditCard();
		System.out.println("CARD INFO:");
		System.out.println("\t Card Type: " + card.getCardType());
		System.out.println("\t Card Number: " + card.getCardId());
		System.out.println("\t PIN: " + card.getPin());
		System.out.println("\t Cash limit: " + card.getCashLimit());
		System.out.println("\t Buy limit: " + card.getBuyLimit());
		System.out.println("\t Expiration Date: " + card.getExpirationDate());
		System.out.println("\t CVV: " + card.getCvv());
		System.out.println("\t Commissions: " + card.getCommission());
	}
}
