package common;

public class Pair<firstThing, secondThing>{
	private firstThing first;//first member of pair
	private secondThing second;//second member of pair

	public Pair(firstThing first, secondThing second){
		this.first = first;
		this.second = second;
	}
	
	public Pair(){
		this.first = null;
		this.second = null;
	}
	
	public void put(firstThing first, secondThing second){
		this.first = first;
		this.second = second;
	}
	
	public firstThing getFirst(){
		return this.first;
	}
	
	public secondThing getSecond(){
		return this.second;
	}
	
	public boolean compare(Pair<firstThing, secondThing> b){
		if(this.first.equals(b.first) && this.second.equals(b.second))
			return true;
		else
			return false;
	}
	
	public void print(){
		System.out.println(this.first+", "+this.second);
	}
}
