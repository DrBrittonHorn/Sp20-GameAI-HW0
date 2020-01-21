package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import agents.Agent;
import agents.BasicAgent;

public class Main {
	private static ArrayList<ArrayList<Integer>> tempGrid = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String[] args) {
		Game g = new Game();
		try {
			loadMazeFile(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Agent a = new BasicAgent();
		//Agent a = new BFSAgent();
		g.Play(a);
		//g.printBoard();
	}

	private static void loadMazeFile(Game g) throws IOException {
		File file = new File("map1.txt"); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		String st;
		while ((st = br.readLine()) != null) {
			ArrayList<Integer> tempLine = new ArrayList<Integer>();
			String[] line = st.split(",");
			for (String t : line) {
				tempLine.add(Integer.parseInt(t));
			}
			tempGrid.add(tempLine); 
		} 
		br.close();
		
		ArrayList<Integer> firstLine = tempGrid.get(0);
		int width = firstLine.size();
		int height = tempGrid.size();
		g.map = new int[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int value = tempGrid.get(y).get(x);
				g.map[x][y] = value;
				if (value == 2) g.goal = new Point(x,y);
			}
		}
	}
}
