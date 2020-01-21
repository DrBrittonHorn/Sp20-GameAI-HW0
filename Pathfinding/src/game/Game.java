package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import agents.Agent;

public class Game {
	public Player p = new Player();
	public int[][] map;
	private boolean gameOver = false;
	public Agent agent;
	public Point goal;
	
	public void Play(Agent a) {
		this.agent = a;
		printBoard();
		while (!gameOver)
		{
			Direction mv = agent.getMove(this);
			if (mv == null) {
				System.out.println("null move returned from agent. Exiting.");
				gameOver = true;
				return;
			}
			p.movePlayer(mv);
			if(!p.isAtValidLocation(map)) {
				System.out.println("Invalid move! Exiting.");
				System.out.println("Player location: " + p.getPosition());
				System.out.println("Move: " + mv.toString());
				return;
			}
			if (isAtGoal()) {
				System.out.println("Success!");
				gameOver = true;
			}
			printBoard();
		}
	}
	
	public void printBoard() {
		System.out.println("===============================");
		for (int y = 0; y < map[0].length; y++) {
			for (int x = 0; x < map.length; x++) {
				if (x == 0) {
					if (x == p.getPosition().x && y == p.getPosition().y)
						System.out.print("x");
					else
						System.out.print(map[x][y]);
				}
				else {
					if (x == p.getPosition().x && y == p.getPosition().y)
						System.out.print(",X");
					else
						System.out.print("," + map[x][y]);
				}
			}
			System.out.println();
		}
		System.out.println("===============================");
	}
	
	public boolean isAtGoal() {
		if (p.getPosition().x == goal.x && p.getPosition().y == goal.y)
			return true;
		return false;
	}
	
	public List<Direction> getValidMoves() {
		List<Direction> possibleDirections = new ArrayList<Direction>();
		
		for (Direction d : Direction.values()) {
			if (isValidMove(d)) {
				possibleDirections.add(d);
			}
		}
		
		return possibleDirections;
	}
	
	public boolean isValidMove(Direction d) {
		Point originalPosition = p.getPosition();
		p.movePlayer(d);
		if (p.isAtValidLocation(map)) {
			p.position = originalPosition; 
			return true;
		}
		p.position = originalPosition;
		return false;
	}
	
	public List<Direction> getValidMoves(Point playerLoc) {
		List<Direction> possibleDirections = new ArrayList<Direction>();
		
		for (Direction d : Direction.values()) {
			Point locCopy = new Point(playerLoc);
			
			switch(d) {
			case LEFT:
				locCopy.x -= 1;
				break;
			case RIGHT:
				locCopy.x += 1;
				break;
			case UP:
				locCopy.y -= 1;
				break;
			case DOWN:
				locCopy.y += 1;
				break;
			case UP_LEFT:
				locCopy.y -= 1;
				locCopy.x -= 1;
				break;
			case UP_RIGHT:
				locCopy.y -= 1;
				locCopy.x += 1;
				break;
			case DOWN_LEFT:
				locCopy.y += 1;
				locCopy.x -= 1;
				break;
			case DOWN_RIGHT:
				locCopy.y += 1;
				locCopy.x += 1;
				break;
			default:
				System.out.println("Unknown Direction in getValidMoves()");
			}
			
			if (isValidPoint(locCopy)) {
				possibleDirections.add(d);
			}
		}
		
		return possibleDirections;
	}
	
	public List<Point> getAdjacentLocations(Point p) {
		List<Point> adjPoints = new LinkedList<Point>();
		List<Direction> availableMoves = this.getValidMoves(p);
		for (Direction d : availableMoves) {
			Point locCopy = new Point(p);
			
			switch(d) {
			case LEFT:
				locCopy.x -= 1;
				break;
			case RIGHT:
				locCopy.x += 1;
				break;
			case UP:
				locCopy.y -= 1;
				break;
			case DOWN:
				locCopy.y += 1;
				break;
			case UP_LEFT:
				locCopy.y -= 1;
				locCopy.x -= 1;
				break;
			case UP_RIGHT:
				locCopy.y -= 1;
				locCopy.x += 1;
				break;
			case DOWN_LEFT:
				locCopy.y += 1;
				locCopy.x -= 1;
				break;
			case DOWN_RIGHT:
				locCopy.y += 1;
				locCopy.x += 1;
				break;
			default:
				System.out.println("Unknown Direction in getAdjacentLocations()");
			}
			
			if (isValidPoint(locCopy)) {
				adjPoints.add(locCopy);
			}
		}
		
		return adjPoints;
	}
	
	public boolean isValidPoint(Point p) {
		if (p.x < 0 || p.y < 0 || 
				p.x >= map.length || p.y >= map[p.x].length ||
				map[p.x][p.y] == 1 )
			return false;
		return true;
	}
}
