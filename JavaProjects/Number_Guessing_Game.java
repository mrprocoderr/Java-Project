import java.util.Random;
import java.util.Scanner;
public class NumberGussingGame {
    
    
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();
            
            int lowerRange = 1;
            int upperRange = 100;
            int maxAttempts = 5;
            int score = 0;
            
            System.out.println("Welcome to the Number Guessing Game!");
            
            do {
                int targetNumber = random.nextInt(upperRange - lowerRange + 1) + lowerRange;
                System.out.println("I've selected a number between " + lowerRange + " and " + upperRange + ".");
                
                for (int attempts = 1; attempts <= maxAttempts; attempts++) {
                    System.out.print("Attempt " + attempts + "/" + maxAttempts + ": Enter your guess: ");
                    int userGuess = scanner.nextInt();
                    
                    if (userGuess < targetNumber) {
                        System.out.println("Too low! Try again.");
                    } else if (userGuess > targetNumber) {
                        System.out.println("Too high! Try again.");
                    } else {
                        System.out.println("Congratulations! You guessed the number " + targetNumber + " in "
                         + attempts + " attempts.");
                         score++;
                         break;
                     }
                 }
                 
                 System.out.print("Do you want to play again? (yes/no): ");
                 String playAgain = scanner.next();
                 
                 if (!playAgain.equalsIgnoreCase("yes")) {
                     break;
                 }
             } while (true);
             
             System.out.println("Your final score is: " + score + " rounds won.");
             scanner.close();
            
            }
     }
     
    
