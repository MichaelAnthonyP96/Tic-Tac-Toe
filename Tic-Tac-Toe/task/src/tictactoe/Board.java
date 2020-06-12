package tictactoe;

import java.util.Scanner;

public class Board {

	enum Outcomes {
		Draw,
		XWin,
		OWin,
		Impossible,
		ContinueGame
	}

	private char[] state;

	public Board() {
		state = new String("_________").toCharArray();
	}

	public Board(String s) {
		state = s.toCharArray();
	}

	public int[] processInput() {
		Scanner scanner = new Scanner(System.in);
		int[] move = new int[2];
		Retry:
		do {
			String[] inputs = {scanner.next(), scanner.next()};
			for (int i = 0; i < 2; ++i) {
				try {
					move[i] = Integer.parseInt(inputs[i]);
				} catch (NumberFormatException e) {
					System.out.println("You should enter numbers!");
					System.out.print("Enter the coordinates: ");
					continue Retry;
				}
				if (move[i] > 3 || move[i] < 1) {
					System.out.println("Coordinates should be from 1 to 3!");
					System.out.print("Enter the coordinates: ");
					continue Retry;
				}
			}
			int idx = 9 - (3 * move[1]) + move[0] - 1;
			if (state[idx] == 'X' || state[idx] == 'O') {
				System.out.println("This cell is occupied! Choose another one!");
				System.out.print("Enter the coordinates: ");
				continue Retry;
			} else {
				break Retry;
			}
		} while (true);
		return move;
	}

	public String outcome2String(Outcomes state) {
		switch (state) {
			case ContinueGame:
				return "Continue Game";
			case Draw:
				return "Draw";
			case OWin:
				return "O wins";
			case XWin:
				return "X wins";
			default:
				return "Not a valid outcome!";
		}


	}

	public void run() {
		boolean playerTurn = true;
		int[] currMove;
		Outcomes currentState = Outcomes.ContinueGame;
		while (currentState == Outcomes.ContinueGame) {
			printState();
			currMove = processInput();
			update(currMove, playerTurn ? 'X' : 'O');
			currentState = determineState(playerTurn ? 'X' : 'O');
			playerTurn = !playerTurn;
		}
		printState();
		System.out.println(outcome2String(currentState));
	}

	public boolean checkInput(String[] input) {
		if (input.length != 2)
			return false;
		if (input[0].length() > 9 || input[1].length() > 9) {
			System.out.println("Too much enough input!");
			return false;
		} else if (input[0].length() < 9 || input[1].length() < 9) {
			System.out.println("Not enough input!");
			return false;
		}
		return true;
	}

	public boolean isDraw(char player) {
		int idx = 0, count = 0;
		for(int i = 0; i < state.length; ++i) {
			if (state[i] == '_') {
				idx = i;
				break;
			} else {
				++count;
			}
		}
		if (count != 8) {
			return false;
		}
		// if idx is a corner check three possibilities
		if (idx == 0) {
			return (player != state[1] || player != state[2]) &&
					(player != state[3] || player != state[6]) &&
					(player != state[4] || player != state[8]);
		}
		if (idx == 2) {
			return (player != state[0] || player != state[1]) &&
					(player != state[5] || player != state[8]) &&
					(player != state[4] || player != state[6]);
		}
		if (idx == 6) {
			return (player != state[0] || player != state[3]) &&
					(player != state[4] || player != state[2]) &&
					(player != state[7] || player != state[8]);
		}
		if (idx == 8) {
			return (player != state[2] || player != state[5]) &&
					(player != state[0] || player != state[4]) &&
					(player != state[6] || player != state[7]);
		}
		// if idx is an edge check the two possibilities
		if (idx == 1) {
			return (player != state[4] || player != state[7]) &&
					(player != state[2] || player != state[3]);
		}
		if (idx == 3) {
			return (player != state[4] || player != state[5]) &&
					(player != state[0] || player != state[6]);
		}
		if (idx == 5) {
			return (player != state[2] || player != state[8]) &&
					(player != state[3] || player != state[4]);
		}
		if (idx == 7) {
			return (player != state[4] || player != state[7]) &&
					(player != state[6] || player != state[8]);
		}
		return false;
	}

	public void printState() {
		System.out.println("---------");
		for (int i = 0; i < state.length; i += 3) {
			System.out.println("| " + state[i] + " " + state[i + 1] + " " + state[i + 2] + " |");
		}
		System.out.println("---------");
		System.out.print("Enter the coordinates: ");
	}

	public boolean impossibleGame() {
		if (determineWinner('O') && determineWinner('X')) {
			return true;
		} else {
			int xCount = 0, oCount = 0;
			for (char c : state) {
				if (c == 'X') {
					++xCount;
				} else if (c == 'O') {
					++oCount;
				}
			}
			return (Math.abs(xCount - oCount) >= 2);
		}
	}

	public boolean boardFull() {
		for (int k = 0; k < state.length; ++k) {
			if (state[k] == '_') {
				return false;
			}
		}
		return true;
	}

	public boolean determineWinner(char player) {
		// we already know that the input is of length 9
		if ((state[0] == player) && (state[3] == player) && (state[6] == player)) {
			return true;
		} else if ((state[1] == player) && (state[4] == player) && (state[7] == player)) {
			return true;
		} else if ((state[2] == player) && (state[5] == player) && (state[8] == player)) {
			return true;
		} else if ((state[0] == player) && (state[4] == player) && (state[8] == player)) {
			return true;
		} else if ((state[2] == player) && (state[4] == player) && (state[6] == player)) {
			return true;
		} else if ((state[0] == player) && (state[1] == player) && (state[2] == player)) {
			return true;
		} else if ((state[3] == player) && (state[4] == player) && (state[5] == player)) {
			return true;
		} else if ((state[6] == player) && (state[7] == player) && (state[8] == player)) {
			return true;
		} else {
			return false;
		}
	}

	public Outcomes determineState(char player) {
		char otherPlayer = (player == 'X' ? 'O' : 'X');
		if (impossibleGame()) {
			return Outcomes.Impossible;
		} else if (determineWinner(player)) {
			if (player == 'X') {
				return Outcomes.XWin;
			} else {
				return Outcomes.OWin;
			}
		} else if (isDraw(otherPlayer)) {
			return Outcomes.Draw;
		} else if (!boardFull()) {
			return Outcomes.ContinueGame;
		}  else {
			return Outcomes.Impossible;
		}
	}

	public boolean draw(char player) {

		return false;
	}

	public boolean gameFinished() {
		// return !determineState().equals("Game not finished");
		return false;
	}

	public void update(int[] move, char player) {
		// (1, 1) -> 6
		// (1, 2) -> 3    1 2 3
		// (1, 3) -> 0    3 4 5
		// (2, 1) -> 7    6 7 8
		// (2, 2) -> 4
		// (2, 3) -> 1
		// (3, 1) -> 8
		// (3, 2) -> 5
		// (3, 3) -> 2
		int idx = 9 - (3 * move[1]) + move[0] - 1;
		if (state[idx] != 'X' && state[idx] != 'O') {
			state[idx] = player;
		}
	}
}
