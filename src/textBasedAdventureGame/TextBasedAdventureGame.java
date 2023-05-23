package textBasedAdventureGame;

import java.util.Random;
import java.util.Scanner;

public class TextBasedAdventureGame {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();

		String[] enemies = { "Knight", "Warrior", "Skeleton", "Samurai", "Mage", "Assassin", "Ninja", "Zombie" };
		int maxEnemyHealth[] = { 80, 75, 70, 65, 60, 55, 50, 45 };
		int maxEnemyDamage[] = { 20, 25, 30, 35, 40, 45, 50, 55 };

		int playerHealth = 150;
		int playerDamage = 50;
		int numHealthPotions = 3;
		int healthPotionHealAmount = 30;
		int healthPotionDropChance = 50;// percent

		boolean running = true;

		System.out.println("Welcome, adventurer!");

		GAME: while (running) {
			System.out.println("---------------------------------------");
			int random = rand.nextInt(enemies.length);

			String enemy = enemies[random];
			int enemyHealth = rand.nextInt(maxEnemyHealth[random] / 2, maxEnemyHealth[random] + 1);

			System.out.println("\t# " + enemy + " has appeared! #\n");

			while (enemyHealth > 0 && playerHealth > 0) {
				System.out.println("\tYour HP: " + playerHealth);
				System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
				System.out.println("\t" + enemy + "'s MAX attack power: " + maxEnemyDamage[random]);
				System.out.println("\nWhat would you like to do?");
				System.out.println("\t1. Attack");
				System.out.println("\t2. Drink Health Potion");
				System.out.println("\t3. Run!");

				String input = sc.nextLine();
				switch (input) {
				case "1":
					int damageDealt = rand.nextInt(playerDamage / 2, playerDamage + 1);
					int damageTaken = rand.nextInt(maxEnemyDamage[random] / 2, maxEnemyDamage[random] + 1);

					enemyHealth -= damageDealt;
					playerHealth -= damageTaken;

					System.out.println("\t> You strike the " + enemy + " with " + damageDealt + " damage.");
					System.out.println("\t> You receive " + damageTaken + " in retaliation!");

					if (playerHealth < 1) {
						System.out.println("\t> You have taken too much damage, you are too weak to go on!");
					}
					break;
				case "2":
					if (numHealthPotions > 0) {
						playerHealth += healthPotionHealAmount;
						numHealthPotions--;
						System.out.println(
								"\t> You drink a health potion, healing yorself for " + healthPotionHealAmount);
						System.out.println("\t> You now have " + playerHealth + " HP");
						System.out.println("\t> You have " + numHealthPotions + " health potions left");
					} else {
						System.out.println(
								"\t> You have no health potions left! Defeat enemies for a chance to get one!");
					}
					break;
				case "3":
					System.out.println("\t> You run away from the " + enemy + "!");
					continue GAME;
				default:
					System.out.println("\t> Invalid Command");
				}
			}
			if (playerHealth < 1) {
				System.out.println("You are weakaned from battle, you cannot go on!");
				break;
			}
			System.out.println("---------------------------------------");
			System.out.println(" # " + enemy + " was defeated! # ");
			System.out.println(" # You have " + playerHealth + " HP left #");
			if (rand.nextInt(100) < healthPotionDropChance) {
				numHealthPotions++;
				System.out.println(" # The " + enemy + " dropped a health potion! #");
				System.out.println(" # You now have " + numHealthPotions + " potion(s)! #");
			}
			System.out.println("---------------------------------------");
			System.out.println("What would you like to do now?");
			System.out.println("1. Continue fighting");
			System.out.println("2. Exit dungeon");
			String input = sc.nextLine();
			while (!input.equals("1") && !input.equals("2")) {
				System.out.println("Invalid command");
				System.out.println("Try again");
				input = sc.nextLine();
			}
			switch (input) {
			case "1":
				System.out.println("You continue on your adventure!");
				break;
			case "2":
				System.out.println("You exit the dungeon, successful from your adventures!");
				break GAME;
			}
		}
		System.out.println("#########################");
		System.out.println("# THANK YOU FOR PLAYING #");
		System.out.println("#########################");
	}

}
