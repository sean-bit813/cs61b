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
        StdDraw.show();
        for (int i = 0; i < bodies.length; i++){
            bodies[i].draw();
        }
        StdDraw.enableDoubleBuffering();

        for (double t = 0; t < T; t+= dt){
             double[] xForces = new double[bodies.length];
             double[] yForces = new double[bodies.length];
             for (int j = 0; j < bodies.length; j++){
                 xForces[j] = bodies[j].calcNetForceExertedByX(bodies);
                 yForces[j] = bodies[j].calcNetForceExertedByY(bodies);
             }
             for (int k = 0; k < bodies.length; k++){
                 bodies[k].update(dt, xForces[k], yForces[k]);
             }
           
             for (int m = 0; m < bodies.length; m++){
                 bodies[m].draw();
                }
             StdDraw.show();
             StdDraw.pause(10);
         }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
                }
    } 

}

