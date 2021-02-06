public class TestBody{
    public static void main(String[] args){
        Body c = new Body(0, 0, 0, 0, 1, "x");
        Body d = new Body(2, 3, 2, 4, 100, "y");
        System.out.println(c.calcForceExertedBy(d));
    }

}