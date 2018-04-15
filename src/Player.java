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
    private Double bet;

    /**
     * Instantiates a new player
     *
     * @param i
     */
    public Player(double i) {
        this.money = 100;
        this.bet = i;
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
    public double win(int payoff) {
        double added = this.bet * payoff;
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
    public Double getBet() {
        return this.bet;
    }

    /**
     * Sets the bet.
     *
     * @param betAmt
     *            the new bet
     */
    public void setBet(Double betAmt) {
        this.bet = betAmt;
    }
}
