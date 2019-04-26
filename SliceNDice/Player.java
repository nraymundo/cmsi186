/**
* <P>A class a Player in the SliceNDice game.
* <P>Each player has a set of dice made for the game as well as a health total.
*
* @author Nicolas Raymundo
*/

public class Player {
    /**
     * The initial health given for a player if no other health total is
     * specified.
     */
    public static final int DEFAULT_STARTING_HEALTH = 50; //TODO: Define me!
    private int health;
    private CombatDie[] dice;

    /**
     * Constructs a Player with the given health and an array of dice of the
     * given length. Of those dice, the number of dice that are "risky" dice
     * should be equal to the given number of risky dice, with the remaining
     * dice as "safe" dice.
     *
     * @param startingHealth              the player's initial health value
     * @param numberOfDice                the number of the dice in the player's
     *                                    array of combatDie
     * @param numberOfRiskyDice           the number of RiskyDie in the player's
     *                                    array of combatDie
     * @throws IllegalArgumentException   if numberOfRiskyDice is greater than
     *                                    numberOfDice
     */
    public Player(int startingHealth, int numberOfDice, int numberOfRiskyDice) {
        this.health = startingHealth;
        this.dice = new CombatDie[numberOfDice];
        if (numberOfRiskyDice > numberOfDice)  {
            throw new IllegalArgumentException("Too many risky die. The first argument is the number of dice for each player. The second and third arguments are the number of risky die for each player.");
        } else if (numberOfDice <= 0 || numberOfRiskyDice < 0) {
            throw new IllegalArgumentException("Please enter a positive integer for the number of dice and nonnegative numbers for the number of risky dice for each player.");
        }
        for (int i = 0; i < this.dice.length; i++) {
            if (i < numberOfDice - numberOfRiskyDice) {
                this.dice[i] = new SafeDie();
            } else {
                this.dice[i] = new RiskyDie();
            }
        }
    }

    /**
     * Constructs a Player with the default health of 50 and an array of dice
     * of the given length. Of those dice, the number of dice that are "risky"
     * dice should be equal to the given number of risky dice, with the
     * remaining dice as "safe" dice.
     *
     * @param numberOfDice                the number of the dice in the player's
     *                                    array of combatDie
     * @param numberOfRiskyDice           the number of RiskyDie in the player's
     *                                    array of combatDie
     * @throws IllegalArgumentException   if numberOfRiskyDice is greater than
     *                                    numberOfDice
     */
    public Player(int numberOfDice, int numberOfRiskyDice) {
        this.health = DEFAULT_STARTING_HEALTH;
        this.dice = new CombatDie[numberOfDice];
        if (numberOfRiskyDice > numberOfDice) {
            throw new IllegalArgumentException("Too many risky die. The first argument is the number of dice for each player. The second and third arguments are the number of risky die for each player.");
        } else if (numberOfDice <= 0 || numberOfRiskyDice < 0) {
            throw new IllegalArgumentException("Please enter a positive integer for the number of dice and nonnegative numbers for the number of risky dice for each player.");
        }
        for (int i = 0; i < this.dice.length; i++) {
            if (i < numberOfDice - numberOfRiskyDice) {
                this.dice[i] = new SafeDie();
            } else {
                this.dice[i] = new RiskyDie();
            }
        }
    }

    public int getDiceLength() {
        return this.dice.length;
    }

    /**
     * Returns this Player's current health.
     *
     * @return this Player's health value.
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Returns the CombatDie at the given index in the Player's array of dice.
     *
     * @param index  the index of the die within the Player's arry of dice
     * @return       the CombatDie at the given index
     */
    public CombatDie getDieAtIndex(int index) {
        return this.dice[index];
    }

    /**
     * Rolls all dice belonging to this player, randomly changing the face-up
     * side of each die.
     */
    public void rollAllDice() {
        for (int i = 0; i < this.dice.length; i++) {
            (this.dice[i]).roll();
        }
    }

    /**
     * Counts the total value of the given faceType among the face-up sides of
     * this Player's dice.
     *
     * @param faceType  the FaceType being searched for
     * @return          the total values of the face-up sides with the given
     *                  FaceType among the Player's CombatDie array
     */
    public int countOfFaceType(DiceFace.FaceType faceType) {
        int countOfFaces = 0;
        for (int i = 0; i < this.dice.length; i++) {
            if (this.dice[i].getFaceUp().getFaceType() == faceType) {
                countOfFaces += this.dice[i].getFaceUp().getFaceValue();
            }
        }
        return countOfFaces;
    }

    /**
     * Returns whether there is at least one face-up Broken Shield among the
     * Player's dice.
     *
     * @return <code>true</code> if at least one CombatDie among the player's
     *         dice has a Broken Shield DiceFace as it's face up side;
     *         <code>false</code> otherwise.
     */
    public boolean containsBrokenShield(){
        return (countOfFaceType(DiceFace.FaceType.BROKEN_SHIELD) >= 1);
    }

    /**
     * Calculates the attack score of the Player's dice by totaling the values
     * of all face-up Sword faces among the player's dice.
     *
     * @return the attack score determined by totaling the values of all face-up
     *         Sword DiceFaces among the player's dice.
     */
    public int getAttackScore() {
        int attackScore = countOfFaceType(DiceFace.FaceType.SWORD);
        return attackScore;
    }

    /**
     * Calculates the defense score of the Player's dice by totaling the values
     * of all face-up Shield faces among the player's dice. If any Broken Shield
     * faces are face-up among the Player's dice, the defense score instead
     * becomes 0.
     *
     * @return the defense score determined by totaling the values of all face-up
     *         Shield DiceFaces among the player's dice; if a Broken Shield face
     *         is face-up, then the returned score is instead 0.
     */
    public int getDefenseScore() {
        int defenseScore = countOfFaceType(DiceFace.FaceType.SHIELD);
        if (containsBrokenShield()) {
            return 0;
        }
        return defenseScore;
    }

    /**
     * Calculates the heal score of the Player's dice by totaling the values
     * of all face-up Heal faces among the player's dice.
     *
     * @return the heal score determined by totaling the values of all face-up
     *         Heal DiceFaces among the player's dice.
     */
    public int getHealScore() {
        int healScore = countOfFaceType(DiceFace.FaceType.HEAL);
        return healScore;
    }

    public String getFaceUpSides() {
        String faceUpSides = "";
        for (int i = 0; i < this.dice.length; i++) {
            faceUpSides = this.dice[i].getFaceUp().toString();
        }
        return faceUpSides;
    }

    public void setHealthScore(int newHealth) {
        this.health = newHealth;
    }

    /**
     * Returns a string representation of the Player, including health, face-up
     * sides of the dice, and the scored attack, defense, and heal values based
     * upon those dice.
     *
     * @return  a String representation of the Player
     */
    @Override
    public String toString() {
        String playerString = "";
        playerString = "Health: " + getHealth() + ", " + "Face-up Side: " + getFaceUpSides() + ", " + "Attack Score: " + getAttackScore() + ", " + "Defense Score: " + getDefenseScore() + ", " + "Heal Score: " + getHealScore();
        return playerString;
    }
}
