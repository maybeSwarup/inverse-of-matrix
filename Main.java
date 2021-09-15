import java.util.*;
import swarup.matrix.*;
public class Main {
    public static void main(String[] args) throws Exception{
        System.out.print("Enter Dimension of N x N Matrix:\nN = ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println("Enter Matrix A: ");
        matrix mat = new matrix();
        mat.enterMatrix(n,n);
        float[][] invMat = mat.invMat();
        if(mat.D!=0){
            System.out.println("Matrix of Minors : ");
            mat.displayMatrix(mat.minors());
            System.out.println("Matrix of Cofactors : ");
            mat.displayMatrix(mat.cofactors());
            float[][] trnMat = mat.trn(mat.cofactors());
            System.out.println("Transpose of Cofactors : ");
            mat.displayMatrix(trnMat);
            System.out.println("Iverse of matrix A = 1/D x [Transpose of Cofactors] ");
            mat.displayMatrix(invMat);
            System.out.print("Do you want to cross check the results? (y/n) ");
            char c = sc.next().charAt(0);
            if(c=='y') mat.crossCheckInvMat(invMat);
        }
    }
}