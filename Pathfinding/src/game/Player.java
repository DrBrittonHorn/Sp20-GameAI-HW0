package game;

public class Player {
	protected Point position = new Point(0,0);
	
	public Point getPosition() {
		return new Point(this.position);
	}

	public Point movePlayer(Direction d) {
		Point newPos = new Point(0,0);
		switch(d) {
		case LEFT:
			this.position.x -= 1;
			break;
		case RIGHT:
			this.position.x += 1;
			break;
		case UP:
			this.position.y -= 1;
			break;
		case DOWN:
			this.position.y += 1;
			break;
		case UP_LEFT:
			this.position.y -= 1;
			this.position.x -= 1;
			break;
		case UP_RIGHT:
			this.position.y -= 1;
			this.position.x += 1;
			break;
		case DOWN_LEFT:
			this.position.y += 1;
			this.position.x -= 1;
			break;
		case DOWN_RIGHT:
			this.position.y += 1;
			this.position.x += 1;
			break;
		default:
			System.out.println("Unknown Direction");
			break;
		}
		return newPos;
	}
	
	public boolean isAtValidLocation(int [][] map) {
		if (this.position.x < 0 || this.position.y < 0 || 
				this.position.x >= map.length || this.position.y >= map[this.position.x].length ||
				map[this.position.x][this.position.y] == 1 )
			return false;
		return true;
	}
}
