package sokoban2;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Sokoban1.SampleLevels;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;

public class GUI extends GraphicsProgram {
	int level = 0;
	Move move = new Move();
	ArrayList<String> pastMoves = new ArrayList<String>();

	public void init() {
		addKeyListeners();
		paint(move.board);
	}


	public void paint(Board board) {
		removeAll();
		int x = 0, y = 0;
		for (ArrayList<Character> row : board.gameboard) {
			x = 0;
			for (Character c : row) {
				switch (c) {
				case '$':
					add(new GImage("redw.png"), x, y);
					break;
				case '#':
					add(new GImage("bluew.png"), x, y);
					break;
				case ' ':
					add(new GImage("grayw.png"), x, y);
					break;
				case '.':
					add(new GImage("target.png"), x, y);
					break;
				case '+':
					add(new GImage("face3.png"), x, y);
					break;
				case '*':
					add(new GImage("greenw.png"), x, y);
					break;
				case '@':
					add(new GImage("face.png"), x, y);
					break;
				}
				x += 28;
			}
			y += 28;
		}

	}

	public void run() {

	}
	
	public void keyPressed(KeyEvent event) {
		int key;

		if (Character.isLetter(event.getKeyChar()))
			key = event.getKeyChar();
		else
			key = event.getKeyCode();

		if (!move.hasWon()) {
			switch (key) {
			case KeyEvent.VK_UP:
			case 'w':
				if (move.canMove(0, -1)) {
					System.out.println("move up");
					move.s = "u";
					move.doMove(0, -1);
					pastMoves.add(move.s);

				}
				break;
			case KeyEvent.VK_LEFT:
			case 'a':
				if (move.canMove(-1, 0)) {
					System.out.println("move left");
					move.s = "l";
					move.doMove(-1, 0);
					pastMoves.add(move.s);

				}
				break;
			case KeyEvent.VK_DOWN:
			case 's':
				if (move.canMove(0, 1)) {
					System.out.println("move down");
					move.s = "d";
					move.doMove(0, 1);
					pastMoves.add(move.s);

				}
				break;
			case KeyEvent.VK_RIGHT:
			case 'd':
				if (move.canMove(1, 0)) {
					System.out.println("move right");
					move.s = "r";
					move.doMove(1, 0);
					pastMoves.add(move.s);

				}
				break;

			case KeyEvent.VK_BACK_SPACE:
			case 'r':
				if (pastMoves.size() > 0) {
					System.out.println("undo move");
					move.undoMove(pastMoves);
				}
				break;
			}
			System.out.println(pastMoves);
			paint(move.board);
			if (move.hasWon()) {
				System.out.println("Level solved");
				level += level;
				paint(move.board);
			}
		}
	}
}