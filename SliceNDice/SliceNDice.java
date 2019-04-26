public class SliceNDice {
    public static void main(String[] args) {
        Player playerOne = new Player(1, 1);
        Player playerTwo = new Player(1, 1);
        try {
            for (int i = 0; i < args.length; i++) {
                if (Integer.parseInt(args[i]) < 0) {
                    throw new IllegalArgumentException("Please enter a positive integer for the number of dice and nonnegative numbers for the number of risky dice for each player.");
                }
            }
            if (args.length == 0) {
                playerOne = new Player(7, 1);
                playerTwo = new Player(7, 1);
            } else if (args.length == 1) {
                playerOne = new Player(Integer.parseInt(args[0]), 1);
                playerTwo = new Player(Integer.parseInt(args[0]), 1);
            } else if (args.length == 2) {
                playerOne = new Player(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
                playerTwo = new Player(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            } else if (args.length == 3) {
                playerOne = new Player(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
                playerTwo = new Player(Integer.parseInt(args[0]), Integer.parseInt(args[2]));
            } else {
                throw new IllegalArgumentException("Please enter between 0 and 3 arguments in the command line.");
            }
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Input must be an integer");
        }
        String playerOneString = "Player One: ";
        String playerTwoString = "Player Two: ";
        String playerOneHealthString = "Player One Health: ";
        String playerTwoHealthString = "Player Two Health: ";
        String playerOneDamage = "Player One Damage Received: ";
        String playerTwoDamage = "Player Two Damage Received: ";
        String playerOneDamageDefended = "Player One Damage Defended: ";
        String playerTwoDamageDefended = "Player Two Damage Defended: ";
        String playerOneTotalDamage = "Player One Total Damage Received: ";
        String playerTwoTotalDamage = "Player Two Total Damage Received: ";
        String playerOneHealValue = "Player One Damage Healed: ";
        String playerTwoHealValue = "Player Two Damage Healed: ";
        boolean gameNotOver = true;
        int round = 0;
        while (gameNotOver) {
            round++;
            System.out.println("***Round " + round + "***");
            playerOne.rollAllDice();
            playerTwo.rollAllDice();

            int playerOneDamageDealt = (playerTwo.getAttackScore() - playerOne.getDefenseScore());
            int playerTwoDamageDealt = playerOne.getAttackScore() - playerTwo.getDefenseScore();
            int playerOnePostDamage = playerOne.getHealth() - (playerTwo.getAttackScore() - playerOne.getDefenseScore());
            int playerTwoPostDamage = playerTwo.getHealth() - (playerOne.getAttackScore() - playerTwo.getDefenseScore());

            System.out.println(playerOneString + playerOne.toString());
            System.out.println(playerTwoString + playerTwo.toString());
            System.out.println("");

            if (playerOnePostDamage <= 0) {
                playerOne.setHealthScore(0);
                gameNotOver = false;
            } else {
                playerOne.setHealthScore(playerOnePostDamage + playerOne.getHealScore());
            }
            if (playerTwoPostDamage <= 0) {
                playerTwo.setHealthScore(0);
                gameNotOver = false;
            } else {
                playerTwo.setHealthScore(playerTwoPostDamage + playerTwo.getHealScore());
            }
            if (round == 25) {
                gameNotOver = false;
            }
            System.out.println(playerOneDamage + playerTwo.getAttackScore() + ". " + playerOneDamageDefended + playerOne.getDefenseScore() + ". " + playerOneTotalDamage + playerOneDamageDealt + ".");
            System.out.println(playerTwoDamage + playerOne.getAttackScore() + ". " + playerTwoDamageDefended + playerTwo.getDefenseScore() + ". " + playerTwoTotalDamage + playerTwoDamageDealt + ".");
            System.out.println("");
            System.out.println(playerOneHealValue + playerOne.getHealScore() + ".");
            System.out.println(playerTwoHealValue + playerTwo.getHealScore() + ".");
            System.out.println("");
            System.out.println(playerOneHealthString + playerOne.getHealth());
            System.out.println(playerTwoHealthString + playerTwo.getHealth());
            System.out.println("");
        }
        if (playerOne.getHealth() == playerTwo.getHealth()) {
            System.out.println("There is no winner! It's a tie!");
        } else if (playerOne.getHealth() > playerTwo.getHealth()) {
            System.out.println("The winner is Player One!");
        } else if (playerTwo.getHealth() > playerOne.getHealth()) {
            System.out.println("The winner is Player Two!");
        }
    }
}
