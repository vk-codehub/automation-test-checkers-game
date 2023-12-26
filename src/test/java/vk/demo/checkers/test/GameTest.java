package vk.demo.checkers.test;

import static org.testng.Assert.assertTrue;

import javax.swing.JOptionPane;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import vk.demo.checkers.Board;
import vk.demo.checkers.Checkers;
import vk.demo.checkers.StepDirection;

public class GameTest extends BaseTest {

	Checkers game;
	WebDriver driver;
	Board board;

	@BeforeClass
	public void setUp() {
		driver = super.driver;
		game = new Checkers(driver);
		board = game.getBoard();

	}
	
	@Test (description  = "Test case to verify if the Checkers page is loaded succesfully.")
	public void verifyCheckersPageIsLoadedAndVisible() {
		System.out.println(game.isBoardVisible());
		Assert.assertTrue(game.isBoardVisible());
		
	}

	@Test(description = "Test case to perform five moves both simple moves and jumps.", dependsOnMethods= {"verifyCheckersPageIsLoadedAndVisible"})
	public void testToMakeFIVEOrangeMovesIncludingTWOJumps() throws Exception {
		board.select("space62");
		board.move(StepDirection.FWD_RIGHT_DIAGONAL);
		
		board.select("space22");
		board.move(StepDirection.FWD_LEFT_DIAGONAL);

		board.select("space11");
		board.move(StepDirection.FWD_LEFT_DIAGONAL);

		board.select("space22");
		board.move(StepDirection.JUMP_FWD_RIGHT_DIAGONAL);

		board.select("space02");
		board.move(StepDirection.JUMP_FWD_LEFT_DIAGONAL);
		

	}
	
	@Test(description = "Test case to verify if the game has been loaded correctly after restart/refresh", dependsOnMethods= {"testToMakeFIVEOrangeMovesIncludingTWOJumps"})
	public void testToRestartTheGame() throws Exception {
		JOptionPane.showMessageDialog(null, "Game is going to be restarted now...");
		board = game.restartGameUsingRestartLink();
		Boolean isGameRestarted = board.getStatusMsg().equals("Select an orange piece to move.")? true : false;
		assertTrue(isGameRestarted);
	}

}
