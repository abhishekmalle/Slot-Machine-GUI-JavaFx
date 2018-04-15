
// I worked on this project alone using only the java API
import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The Class Slot contains the implementation for a GUI slot machine
 *
 * @author Abhishek Mallemadugula
 * @version 1
 */
public class Slot extends Application {

    /** The Constant REG_OPTIONS. */
    private static final String[] REG_OPTIONS  =
            {"grape.png", "cherry.png", "bell.png", "line.png", "line.png",
                    "line.png", "line.png", "line.png"};

    /** The Constant TEST_OPTIONS. */
    private static final String[] TEST_OPTIONS =
            {"grape.png", "cherry.png", "bell.png"};

    /** The player. */
    private Player                player       = new Player();

    /** The rng. */
    private Random                rng          = new Random();

    /**
     * Builds the pane.
     *
     * @return the pane
     */
    protected GridPane getPane() {

        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setAlignment(Pos.CENTER);
        pane.setStyle("-fx-background-color: lightblue;");

        GridPane smallPane = new GridPane();
        smallPane.setVgap(5);
        smallPane.setHgap(5);
        smallPane.setAlignment(Pos.CENTER);

        Button spinButton = new Button("SPIN!");
        RadioButton rbReg = new RadioButton();
        RadioButton rbTest = new RadioButton();
        ToggleGroup g = new ToggleGroup();
        rbReg.setToggleGroup(g);
        rbTest.setToggleGroup(g);
        rbReg.fire();

        Text betText = new Text("Bet Amount:");
        Text regText = new Text("Regular");
        Text testText = new Text("Test");
        Text titleText = new Text("SPIN GAME!");
        Text creditText = new Text("Current Credit");
        Text moneyText = new Text("$100");
        Text msgText = new Text();

        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 28px");
        spinButton.setStyle("-fx-font-weight: bold; -fx-font-size: 28px");

        TextField tf = new TextField();

        smallPane.add(regText, 0, 0);
        smallPane.add(rbReg, 1, 0);
        smallPane.add(testText, 0, 1);
        smallPane.add(rbTest, 1, 1);

        pane.add(titleText, 1, 0);
        pane.add(getImageView("grape.png"), 0, 1);
        pane.add(getImageView("grape.png"), 1, 1);
        pane.add(getImageView("grape.png"), 2, 1);
        pane.add(betText, 0, 2);
        pane.add(tf, 1, 2);
        pane.add(creditText, 0, 3);
        pane.add(moneyText, 1, 3);
        pane.add(spinButton, 1, 4);
        pane.add(smallPane, 2, 5);
        pane.add(msgText, 1, 5);
        // pane.add(getImageView("gif.gif"), 0, 5);

        spinButton.setOnAction(e -> {
            String op1 = null, op2 = null, op3 = null;

            try {
                Double betAmt = Double.valueOf(tf.getText());

                if (player.getMoney() == 0) {
                    msgText.setText("Oh No! You have no money left!");

                } else if (!(betAmt > 0)) {
                    msgText.setText("Your bet is not a positive number");
                } else if (betAmt > player.getMoney()) {
                    msgText.setText("Your bet is too high");
                } else {
                    player.setBet(betAmt);

                    if (rbReg.isSelected()) {

                        // finding the random options
                        op1 = REG_OPTIONS[rng.nextInt(8)];
                        op2 = REG_OPTIONS[rng.nextInt(8)];
                        op3 = REG_OPTIONS[rng.nextInt(8)];
                    } else if (rbTest.isSelected()) {
                        // finding the random options
                        op1 = TEST_OPTIONS[rng.nextInt(3)];
                        op2 = TEST_OPTIONS[rng.nextInt(3)];
                        op3 = TEST_OPTIONS[rng.nextInt(3)];
                    }
                    // adding the new images to the pane
                    pane.getChildren()
                            .removeIf(n -> GridPane.getRowIndex(n) == 1);
                    pane.add(getImageView(op1), 0, 1);
                    pane.add(getImageView(op2), 1, 1);
                    pane.add(getImageView(op3), 2, 1);

                    int payoff = getScore(new String[] {op1, op2, op3});
                    System.out.println(payoff);
                    if (payoff > 0) {
                        double added = player.win(payoff);
                        msgText.setText("Congrats! You won $" + added);
                        moneyText.setText("$" + player.getMoney());
                        System.out.println("won");
                    } else {
                        player.lose();
                        msgText.setText("Sorry, you lost $" + betAmt);
                        moneyText.setText("$" + player.getMoney());
                        System.out.println("lost");
                    }

                    moneyText.setText("$" + player.getMoney());
                }
            } catch (Exception exp) {
                msgText.setText("You didn't enter an number");
            }
        });

        return pane;
    }

    /**
     * Gets the image view.
     *
     * @param file
     *            name
     * @return the image view
     */
    private ImageView getImageView(String s) {
        ImageView iv = new ImageView(s);
        iv.setFitWidth(160);
        iv.setFitHeight(160);
        return iv;
    }

    /**
     * Gets the score.
     *
     * @param array
     *            of strings
     * @return the score
     */
    private int getScore(String[] arr) {
        int bell = 0;
        int grape = 0;
        int cherry = 0;

        for (String s : arr) {
            if (s.equals("bell.png")) {
                bell++;
            } else if (s.equals("grape.png")) {
                grape++;
            } else if (s.equals("cherry.png")) {
                cherry++;
            }
        }

        if (bell == 3) {
            return 10;
        } else if (grape == 3) {
            return 7;
        } else if (cherry == 3) {
            return 5;

        } else if (cherry == 2) {
            return 3;
        } else if (cherry == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a scene and place it in the stage
        Scene scene = new Scene(getPane(), 800, 600);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Slot Machine"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     *
     * @param args
     *            the arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
