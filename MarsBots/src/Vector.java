import java.util.ArrayList;
import java.util.Arrays;


public class Vector implements Comparable{
	private float x,y;
	static Vector NORM_RIGHT = new Vector(1,0);
	static Vector NORM_UP = new Vector(0,1);
	Vector(float x, float y){
		this.x = x;
		this.y = y;
	}
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	public static boolean isNullVector(Vector a){
		if(a.getX()==0 && a.getY()==0){
			return true;
		}
		else return false;
	}
	
	public static Vector calcBotVector(Robot a, Robot b){
		return new Vector(b.getX()-a.getX(),b.getY()-a.getY());
	}
	
	public static Vector addition(Vector a, Vector b){
		return new Vector(a.getX()+b.getX(),a.getY()+b.getY());
	}
	
	public static Vector addition(Vector[] vectors){
		Vector result = new Vector(0,0);
		for (Vector v : vectors){
			result = addition(result,v);
		}
		return result;
	}
	
	public static float scalarProd(Vector a, Vector b){
		return ((a.getX()*b.getX())+(a.getY()*b.getY()));
	}
	
	public static float absolute(Vector a){
		return (float) Math.hypot(a.getX(),a.getY());
	}
	
	public static Vector linearProduct(float x, Vector a){
		return new Vector(x*a.getX(), x*a.getY());
	}
	
	public static Vector average(Vector[] vectors){
		Vector v = addition(vectors);
		v = linearProduct(1/absolute(v),v);
		Arrays.sort(vectors);
		v = linearProduct(absolute(vectors[vectors.length-1]),v);
		return v;
	}
	
	public static float angle(Vector a, Vector b){
		if(isNullVector(a)||isNullVector(b)){
			return -1;
		}
		
		float cos = Vector.scalarProd(a, b)/(Vector.absolute(a)*Vector.absolute(b));
		if(cos>1){
			System.out.println("Vector: angle() cos="+cos+"rounded to: 1");
			cos = 1;
		}
		float angle = (float) Math.acos(cos);
		if(!(angle>-1)){
			System.out.println("ERROR: Vector: angle="+angle+" Vector a:"+a+" Vector b:"+b+" cos="+cos);
		}
		return angle;
	}
	
	public String toString(){
		return getX()+", "+getY();
	}
	
	public static void main(String[] args){
		average(new Vector[] {new Vector(2,1), new Vector(1,1), new Vector(0,1), new Vector(3,3), new Vector(2,2)});
	}
	@Override
	public int compareTo(Object o) {
		
		return ((Float) absolute(this)).compareTo(absolute(((Vector)o)));
	}
	
}
