/*Написать программу вычисления n-ого треугольного числа
 */
import java.util.Scanner;
public class test1{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите число n: ");
        int n = in.nextInt();
        in.close();
        for(int i = 1; i < n; i++) {
            double triangle = 0.5*i*(i+1);
            System.out.println(triangle);
        }
    }
}

