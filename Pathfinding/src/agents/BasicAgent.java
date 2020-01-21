package agents;

import game.Direction;
import game.Game;

public class BasicAgent implements Agent {

	@Override
	public Direction getMove(Game game) {
		
		return Direction.RIGHT;
	}

}
