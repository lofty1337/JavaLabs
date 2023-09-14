import java.util.Scanner;
import java.util.ArrayList;
class TwiceEven {
    int n;
    TwiceEven(int n){
        if (n>99 && n<1000)
            this.n = n;
        else
            this.n=100;
    }
    public boolean isTwiceEven() {
        int sum = 0;
        int mul = 1;
        while (n >= 10) {
            sum += n % 10;
            mul *= n % 10;
            n /= 10;
        }
        if (mul % 2 == 0)
            if (sum % 2 == 0)
                return true;
        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        //#1
        System.out.println(Syracuse(10));
        //#2
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        System.out.println(rowSum(n));
        //#5
        n=scanner.nextInt();
        TwiceEven num=new TwiceEven(n);
        System.out.println(num.isTwiceEven());
        //#3
        int xEnd=scanner.nextInt();
        int yEnd=scanner.nextInt();
        int x=0;
        int y=0;
        String command;
        int step;
        ArrayList<String> listOfCommands = new ArrayList<>();
        ArrayList<Integer> listOfSteps = new ArrayList<>();

        while (true){
            scanner.nextLine();
            command = scanner.nextLine();
            if(command.equals("стоп")) {
                break;
            }
            System.out.println(command);
            listOfCommands.add(command);
            step=scanner.nextInt();
            System.out.println(step);
            listOfSteps.add(step);
        }

        int count=0;
        while ((x!=xEnd) || (y!=yEnd)){
            if (listOfCommands.get(count).equals("север")) {
                y += listOfSteps.get(count);
                count++;
            }
            else if (listOfCommands.get(count).equals("юг")) {
                y -= listOfSteps.get(count);
                count++;
            }
            else if (listOfCommands.get(count).equals("запад")) {
                x -= listOfSteps.get(count);
                count++;
            }
            else if (listOfCommands.get(count).equals("восток")) {
                x += listOfSteps.get(count);
                count++;
            }
        }
        System.out.println(count);

        //#4
        n = scanner.nextInt();
        scanner.nextLine();
        System.out.println(n);
        int[][] arrayOfArrays = new int[n][];
        for (int i = 0; i < n; i++) {
            int m = scanner.nextInt();
            System.out.println(m);
            arrayOfArrays[i] = new int[m];
            for (int j = 0; j < m; j++) {
                scanner.nextLine();
                arrayOfArrays[i][j] = scanner.nextInt();
                System.out.println(arrayOfArrays[i][j]);
            }
        }
        System.out.println("!!!");
        for (int i = 0;i<n;i++){
            for (int j=0;j<arrayOfArrays[i].length;j++){
                System.out.println(arrayOfArrays[i][j]);
            }
        }
        int max=-1;
        int road = 0;
        for (int i=0;i<n;i++){
            int tmpMin=Integer.MAX_VALUE;
            for (int j=0;j<arrayOfArrays[i].length;j++){
                if (arrayOfArrays[i][j]<tmpMin)
                    tmpMin=arrayOfArrays[i][j];
            }
            if (tmpMin>max){
                max=tmpMin;
                road=i;
            }
        }
        System.out.println(road+1);
        System.out.println(max);
    }

    public static int Syracuse(int n){
        int count=0;
        while (n!=1){
            if (n%2==0){
                n/=2;
            }
            else{
                n*=3;
                n+=1;
            }
            count+=1;
        }
        return count;
    }
    public static int rowSum(int n){
        Scanner scanner=new Scanner(System.in);
        int sum = 0;
        for (int i = 0;i<n;i++){
            int val=scanner.nextInt();
            if(i%2==0)
                sum+=val;
            else
                sum-=val;
        }
        return sum;
    }
}