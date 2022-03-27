import Componenets.grid;
import MazeMaking.mazeMakingAlgorithm;
import MazeMaking.SideWinder;
public class testskeleton {
    public static void main(String[] args){
        grid main = new grid(5,5);
        mazeMakingAlgorithm m = new SideWinder();
        m.setR(255);
        m.makePath(main);
        System.out.println();
    }
}
