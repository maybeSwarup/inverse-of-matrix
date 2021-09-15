package swarup.matrix;
import java.util.*;
public class cursor {
    public void moveCursor(String a, int x){
        char z='A',esc=0x1B;
        switch(a){
            case "up": z='A';
            break;
            case "down": z='B';
            break;
            case "right": z='C';
            break;
            case "left": z='D';
            break;
            default: System.out.println("Please Enter A Valid Input!");
        }
        System.out.printf("%c[%d%c",esc,x,z);
    }

    public void getCursor(){
        Scanner scan = new Scanner(System.in);
        //System.out.printf("%c[%dn",0x1b,6);
        //Scan.scanf("%c[%dn",0x1b,6);
        String pos = scan.nextLine();
        //char[] pos = new char[2];
        //pos[0] = scan.next().charAt(0);
        //pos[1] = scan.nextInt();
        //return pos;
    }
}
