public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public double G = 6.67e-11;

	public Body(double xP, double yP, double xV, 
		double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}
	public double calcDistance(Body b2){
		return Math.sqrt((this.xxPos-b2.xxPos)*(this.xxPos-b2.xxPos)+(this.yyPos-b2.yyPos)*(this.yyPos-b2.yyPos));
	}
	public double calcForceExertedBy(Body b3){
		return this.mass*b3.mass*G/this.calcDistance(b3)/this.calcDistance(b3);
	}
	public double calcForceExertedByX(Body b4){
		return -1*this.calcForceExertedBy(b4)*(this.xxPos-b4.xxPos)/this.calcDistance(b4);
	}
	public double calcForceExertedByY(Body b5){
		return -1*this.calcForceExertedBy(b5)*(this.yyPos-b5.yyPos)/this.calcDistance(b5);
	}
	public double calcNetForceExertedByX(Body[] allBodys){
		int i = 0;
		double NFX = 0;
		while (i < allBodys.length){
			if(this.equals(allBodys[i])){
				continue;
			}
			NFX = NFX + this.calcForceExertedByX(allBodys[i]);
			i = i+1;
		}
		return NFX;
	}
	public double calcNetForceExertedByY(Body[] allBodys){
		int i = 0;
		double NFY = 0;
		while (i < allBodys.length){
			if(this.equals(allBodys[i])){
				continue;
			}
			NFY = NFY + this.calcForceExertedByY(allBodys[i]);
			i = i+1;
		}
		return NFY;
	}
	public void update(double dt, double fX, double fY){
		double ax = fX/this.mass;
		double ay = fY/this.mass;
		this.xxVel = this.xxVel + dt*ax;
		this.yyVel = this.yyVel + dt*ay;
		this.xxPos = this.xxPos + dt*this.xxVel;
		this.yyPos = this.yyPos + dt*this.yyVel;
	}
	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
	}
}
