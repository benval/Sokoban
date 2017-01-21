package sokoban2;

import java.util.ArrayList;


public class Board {

	ArrayList<ArrayList<Character>> gameboard;

	public int playerX, playerY;

	public Board(String[] inputString) {
		gameboard = new ArrayList<ArrayList<Character>>();

		for (int i = 0; i < inputString.length; i++) {
			
			ArrayList<Character> row = new ArrayList<Character>();
			
			for (int j = 0; j < inputString[i].length(); j++) {
				char c = inputString[i].charAt(j);
				row.add(c);
				if (c == '@') {
					playerX = j;
					playerY = i;
				} else if (c == '+') {		
					playerX = j;
					playerY = i;
				}
			}
			gameboard.add(row);
		}
	}

	public void setCell(char c, int x, int y) {
		gameboard.get(y).set(x,c);
	}

	public char getCell(int x, int y) {	
		return gameboard.get(y).get(x);  
	}

	public boolean isTarget(int x, int y) {
		return gameboard.get(y).get(x) == '.';

	}
}
