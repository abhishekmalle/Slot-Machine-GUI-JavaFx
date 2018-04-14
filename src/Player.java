// I worked on this project alone using only the java API
/**
 * The Class Player.
 *
 * @author Abhishek Mallemadugula
 * @version 1
 */
public class Player {

    /** The money. */
    private int money;

    /** The bet. */
    private int bet;

    /**
     * Instantiates a new player
     *
     * @param bet
     */
    public Player(int bet) {
        this.money = 100;
        this.bet = bet;
    }

    /**
     * Instantiates a new player.
     */
    public Player() {
        this(0);
    }

    /**
     * Gets the money.
     *
     * @return the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Sets the money.
     *
     * @param money
     *            the new money
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Player Wins.
     *
     * @param payoff
     * @return the amount of money added
     */
    public int win(int payoff) {
        int added = this.bet * payoff;
        this.money += added;
        return added;
    }

    /**
     * Player Loses.
     */
    public void lose() {
        this.money -= this.bet;
    }

    /**
     * Gets the bet.
     *
     * @return the bet
     */
    public int getBet() {
        return this.bet;
    }

    /**
     * Sets the bet.
     *
     * @param betAmount
     *            the new bet
     */
    public void setBet(int betAmount) {
        this.bet = betAmount;
    }
}
