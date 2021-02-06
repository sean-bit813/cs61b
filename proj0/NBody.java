public class NBody {
    public static double readRadius(String FilePath){
        if (FilePath.isEmpty()) {
			System.out.println("Please supply a FilePath as a command line argument.");
		}
        In in = new In(FilePath);
        int N = in.readInt();
        double radius = in.readDouble();
        return radius; 
    }
    public static Body[] readBodies(String FilePath){
        if (FilePath.isEmpty()) {
			System.out.println("Please supply a FilePath as a command line argument.");
		}
        In in = new In(FilePath);
        int i = 0;
        int N = in.readInt();
        double radius = in.readDouble();
        Body[] planets = new Body[N];
       
        while(i<N){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            i = i + 1;
        }
        return planets;
    }
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] bodies = readBodies(filename);
        double radius = readRadius(filename);

        String backgroundimg = "images/starfield.jpg";
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, backgroundimg);
        for (int i = 0; i < bodies.length; i++){
            bodies[i].draw();
        }
        StdDraw.enableDoubleBuffering();
        double t = 0;
        while (t < T){
             double[] xForces = new double[bodies.length];
             double[] yForces = new double[bodies.length];
             for (int j = 0; j < bodies.length; j++){
                 xForces[j] = bodies[j].calcNetForceExertedByX(bodies);
                 yForces[j] = bodies[j].calcNetForceExertedByY(bodies);
             }
             for (int k = 0; k < bodies.length; k++){
                 bodies[k].update(dt, xForces[k], yForces[k]);
             }
             StdDraw.setScale(-radius, radius);
             StdDraw.clear();
             StdDraw.picture(0, 0, backgroundimg);
             for (int m = 0; m < bodies.length; m++){
                 bodies[m].draw();
                }
             StdDraw.show();
             StdDraw.pause(10);
             t += dt;
         }
        /*double time = 0;
        int N = bodies.length;
		while (time < T) {
			double[] xForces = new double[N];
			double[] yForces = new double[N];
			
			for (int i = 0; i < N; i++) {
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}

			for (int i = 0; i < N; i++) {
				bodies[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Body b: bodies) {
				b.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}*/
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
                }
    } 

}

