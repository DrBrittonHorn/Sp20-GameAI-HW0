package game;

import java.util.Objects;

public class Point {
	public int x;
	public int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(Point p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
	@Override
    public boolean equals(Object o) { 
  
        // If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Point or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof Point)) { 
            return false; 
        } 
          
        // typecast o to Complex so that we can compare data members  
        Point c = (Point) o; 
          
        // Compare the data members and return accordingly  
        return Double.compare(this.x, c.x) == 0
                && Double.compare(this.y, c.y) == 0; 
    } 
	
	@Override
	   public int hashCode() {
	       return Objects.hash(this.x, this.y);
	   }
}
