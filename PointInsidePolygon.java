package intersectionoftwolines;

import java.util.Scanner;

public class PointInsidePolygon {
	
	
	static class points{
		float x,y;
		points(float x,float y){
			this.x=x;
			this.y=y;
		}
	}
	
	public boolean doesIntersect(points p1,points q1,points p2,points q2) {
		
		int o1 = orientation(p1, q1, p2);
		int o2 = orientation(p1, q1, q2);
		int o3 = orientation(p2, q2, p1);
		int o4 = orientation(p2, q2, q1);
		
		if(o1!=o2 && o3!=o4)
			return true;
		
		if(o1==0 && onsegment(p1, q1, p2) ) return true;
		if(o2==0 && onsegment(p1, q1, q2) ) return true;
		if(o3==0 && onsegment(p2, q2, p1) ) return true;
		if(o4==0 && onsegment(p2, q2, q1) ) return true;
		
		
		return false;
	}
	
	public int orientation(points p,points q, points r) {
		
		int val = (int) ((q.y-p.y)*(r.x-q.x)-(r.y-q.y)*(q.x-p.x));
		
		if(val==0)
			return 0;
		
		return (val>0)?1:2;
	}
	
	public boolean onsegment(points p,points q, points r) {
		
		if( r.x <= Math.max(p.x, q.x) && r.x >= Math.min(p.x, q.x) && r.y <= Math.max(p.y, q.y) && r.y >= Math.min(p.y, q.y) )
		return true;
		
		return false;
	}
	
	public boolean ispointinside(points[] polygon,points point) {
		
		float INF=10000;
		int count=0;
		int next=0;
		points infinite = new points(INF,point.y);
		
		for(int i=0; i<polygon.length ; i++) {
			
			next = (i+1)%polygon.length;
			
			if(doesIntersect(polygon[i],polygon[next],point,infinite)) {
				
				if(orientation(polygon[i],polygon[next],point) == 0) {
					
					return onsegment(polygon[i], polygon[next], point);
				}
				
				count++;
			}
		}
		
		return (count%2 == 1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter no of vertices:");
		int vertices = scan.nextInt();
		
		points polygon[] = new points[vertices] ;
		
		for(int i =0 ; i<vertices ; i++) {
				System.out.println("Enter point "+(i+1)+": x coordinate:");
				float x = scan.nextFloat();
				System.out.println("Enter point "+(i+1)+": y coordinate:");
				float y = scan.nextFloat();
				polygon[i] = new points(x,y);
		}
		
		boolean exit = true;
		int check=1;
		
		while(exit)
		{	
			if(check == 1) {
				System.out.println("Enter point to be checked:");
				System.out.println("x coordinate:");
				float x = scan.nextFloat();
				System.out.println("y coordinate:");
				float y = scan.nextFloat();
				points point = new points(x,y);
				
				PointInsidePolygon obj = new PointInsidePolygon();
				
				if(obj.ispointinside(polygon, point)) 
					System.out.println("Yes");
				else
					System.out.println("No");
			}
			else{
				exit = false;
				break;
			}
				
			System.out.println("Continue (1=yes|0=no):");
			check = scan.nextInt();
			
		}
		
		
	}

}
