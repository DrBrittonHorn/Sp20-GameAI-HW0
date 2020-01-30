package agents;

import java.util.ArrayList;

import game.Direction;
import game.Game;
import game.Point;

public class BasicAgent implements Agent {
	
	private class Node {
		Point p;
		int distance;
		Node parent;
		public Node(Point p, int d) {
			this.p = p;
			distance = d;
			parent = null;
		}
		public Node(Point p, int d, Node par) {
			this.p = p;
			distance = d;
			parent = par;
		}
	}
	
	public ArrayList<Direction> moves = new ArrayList<Direction>();

	@Override
	public Direction getMove(Game game) {
		if(moves.size() == 0) {
			moves = makePathAStar(game);
		}
		if(moves.size() == 0) {
			System.err.println("No paths found");
			return Direction.DOWN;
		}
		return moves.remove(0);
	}
	
	private ArrayList<Direction> makePathAStar(Game game) {
		ArrayList<Direction> solutions = new ArrayList<Direction>();
		ArrayList<Node> openLocations = new ArrayList<Node>();
		ArrayList<Node> closedLocations = new ArrayList<Node>();
		openLocations.add(new Node(game.p.getPosition(), 0));
		
		Point goal = game.goal;
		
		
		while(openLocations.size() > 0) {
			// Get node from open with min d
			Node n;
			int nIndex = 0;
			for(int i = 0; i < openLocations.size(); i++) {
				if(f(openLocations.get(i), goal) < f(openLocations.get(nIndex), goal)) 
					nIndex = i;
			}
			if (openLocations.get(nIndex).p.equals(goal)) {
				Node solution = openLocations.get(nIndex);
				System.out.println("Solving from graph");
				while(solution.parent != null) {
					solutions.add(0, getDirection(solution.parent, solution));
					solution = solution.parent;
				}
				for(Direction d : solutions) {
					System.out.print(d + ", ");
				}
				System.out.println("\n");
				return solutions;
			} else {
				n = openLocations.remove(nIndex);
				closedLocations.add(n);
				for (Point p : game.getAdjacentLocations(n.p)) {
					int inClosedIndex = -1;
					int inOpenIndex = -1;
					for (int i = 0; i < closedLocations.size(); i++) {
						if(closedLocations.get(i).p.equals(p)) inClosedIndex = i;
					} 
					if (inOpenIndex != -1 && f(openLocations.get(inOpenIndex), goal) <= f(n.distance, p, goal) ){
						System.out.println("Better node in open list");
						continue;
					}
					if(inClosedIndex != -1 && f(closedLocations.get(inClosedIndex), goal) <= f(n.distance, p, goal)) {
						System.out.println("Better node in closed list");
						continue;
					}
					if (inClosedIndex != -1) {
						closedLocations.remove(inClosedIndex);
						System.out.println("Removed node from closed list");
					}
					if (inOpenIndex != -1) {
						openLocations.remove(inOpenIndex);
						System.out.println("Removed node from open list");
					}
					openLocations.add(new Node(p, n.distance+1, n));
					System.out.println("Added node to open list");
				}
			}
		}
		System.err.println("No solutions found");
		return solutions;
	}
	
	private int l0(Point p1, Point p2) {
		return Math.max(Math.abs(p1.x - p2.x),
						Math.abs(p1.y - p2.y));
	}
	private int l0(Node n, Point p) {
		return l0(n.p, p);
	}
	private Direction getDirection(Node from, Node to) {
		int xd = to.p.x - from.p.x;
		int yd = to.p.y - from.p.y;
		
		if (xd == 1 && yd == 1) return Direction.DOWN_RIGHT;
		if (xd == -1 && yd == 1) return Direction.DOWN_LEFT;
		if (xd == 1 && yd == -1) return Direction.UP_RIGHT;
		if (xd == -1 && yd == -1) return Direction.DOWN_LEFT;
		if (xd == 1 && yd == 0) return Direction.RIGHT;
		if (xd == 0 && yd == 1) return Direction.DOWN;
		if (xd == -1 && yd == 0) return Direction.LEFT;
		if (xd == 0 && yd == -1) return Direction.UP;
		
		System.err.println("Not adjacent");
		return Direction.UP;
					
	}
	private int f(Node n, Point goal) {
		return l0(n, goal) + n.distance;
	}
	private int f(int prevDist, Point p, Point goal) {
		return l0(p, goal) + prevDist + 1;
	}
}
