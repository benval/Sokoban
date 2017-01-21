package sokoban2;

import java.util.ArrayList;

import sokoban2.Board;
import Sokoban1.SampleLevels;

public class Move {
	
	public Board board = new Board(SampleLevels.level4);
	public String s;

	public boolean canMove(int dx, int dy) {
		char nesteCell = (board.getCell(board.playerX + dx, board.playerY + dy));
		if (nesteCell == '#') {
			return false;
		}

		char nextCell = (board.getCell(board.playerX + dx + dx, board.playerY
				+ dy + dy));
		if (nesteCell == (' ') || (nesteCell == ('.')))
			return true;
		else if (nesteCell == ('$') && (nextCell == (' '))
				|| (nextCell == ('.')))
			return true;

		else if (nesteCell == ('*') && (nextCell == (' '))
				|| (nextCell == ('.')))
			return true;

		else
			return false;
	}

	public void doMove(int dx, int dy) {

		int posX = board.playerX;
		int posY = board.playerY;

		int nextPosX = board.playerX + dx;
		int nextPosY = board.playerY + dy;

		int next2posX = board.playerX + 2 * dx;
		int next2posY = board.playerY + 2 * dy;

		if (board.getCell(posX, posY) == '+') {
			board.setCell('.', posX, posY);

		} else
			board.setCell(' ', posX, posY);

		if (board.getCell(nextPosX, nextPosY) == '.') {
			board.setCell('+', nextPosX, nextPosY);
		} else if (board.getCell(nextPosX, nextPosY) == ' ') {
			board.setCell('@', nextPosX, nextPosY);

		} else if (board.getCell(nextPosX, nextPosY) == '$'
				&& (board.getCell(next2posX, next2posY) == (' '))) {
			board.setCell('$', next2posX, next2posY);
			board.setCell('@', nextPosX, nextPosY);
			s += "b";

		} else if (board.getCell(nextPosX, nextPosY) == '$'
				&& (board.getCell(next2posX, next2posY) == '.')) {
			board.setCell('*', next2posX, next2posY);
			board.setCell('@', nextPosX, nextPosY);
			s += "b";

		} else if (board.getCell(nextPosX, nextPosY) == '*'
				&& (board.getCell(next2posX, next2posY) == ' ')) {
			board.setCell('+', nextPosX, nextPosY);
			board.setCell('$', next2posX, next2posY);
			s += "b";

		} else if (board.getCell(nextPosX, nextPosY) == '*'
				&& (board.getCell(next2posX, next2posY) == '.')) {
			board.setCell('+', nextPosX, nextPosY);
			board.setCell('*', next2posX, next2posY);
			s += "b";
		}
		board.playerX += dx;
		board.playerY += dy;
	}

	public void undoMove(ArrayList<String> pastMoves) {
		String cell = pastMoves.get(pastMoves.size() - 1);

		if (cell == "u") {
			doMove(0, 1);
		} else if (cell == "d") {
			doMove(0, -1);
		} else if (cell == "l") {
			doMove(1, 0);
		} else if (cell == "r") {
			doMove(-1, 0);
		} else if (cell.contentEquals("ub")) {
			reverseMove(0, 1);
		} else if (cell.contentEquals("db")) {
			reverseMove(0, -1);
		} else if (cell.contentEquals("lb")) {
			reverseMove(1, 0);
		} else if (cell.contentEquals("rb")) {
			reverseMove(-1, 0);
		}
		pastMoves.remove(pastMoves.size() - 1);

	}

	public void reverseMove(int dx, int dy) {
		int posX = board.playerX;
		int posY = board.playerY;

		int boxPosX = board.playerX - dx;
		int boxPosY = board.playerY - dy;

		int lastPosX = board.playerX + dx;
		int lastPosY = board.playerY + dy;

		if (board.getCell(lastPosX, lastPosY) == ' ') {
			board.setCell('@', lastPosX, lastPosY);
		} else if (board.getCell(lastPosX, lastPosY) == '.') {
			board.setCell('+', lastPosX, lastPosY);
		}

		if (board.getCell(posX, posY) == '+'
				&& board.getCell(boxPosX, boxPosY) == '$') {
			board.setCell(' ', boxPosX, boxPosY);
			board.setCell('*', posX, posY);
		} else if (board.getCell(posX, posY) == '+'
				&& board.getCell(boxPosX, boxPosY) == '*') {
			board.setCell('.', boxPosX, boxPosY);
			board.setCell('*', posX, posY);
		} else if (board.getCell(posX, posY) == '@'
				&& board.getCell(boxPosX, boxPosY) == '$') {
			board.setCell(' ', boxPosX, boxPosY);
			board.setCell('$', posX, posY);
		} else if (board.getCell(posX, posY) == '@'
				&& board.getCell(boxPosX, boxPosY) == '*') {
			board.setCell('.', boxPosX, boxPosY);
			board.setCell('$', posX, posY);
		}
		board.playerX += dx;
		board.playerY += dy;
	}
	
	public boolean hasWon() {
		int i = 0;
		while (i < board.gameboard.size()) {
			if (board.gameboard.get(i).contains('$'))
				return false;
			i++;
		}
		return true;
	}

}
