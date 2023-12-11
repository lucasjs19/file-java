import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        System.out.print("Path file (.csv): ");
        String path = sc.nextLine();

        List<Product> list = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line = br.readLine();

            while(line != null){
                String product[] = line.split(",");
                String name = product[0];
                Double price = Double.parseDouble(product[1]);
                Integer quantity = Integer.parseInt(product[2]);
                list.add(new Product(name,price,quantity));
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File pathOut = new File(path);
        boolean success = new File(pathOut.getParent() + "\\out").mkdir();

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathOut.getParent() + "\\out\\summary.csv"))){
            for (Product p: list){
                bw.write(String.valueOf(p));
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("End of the program! File created successfully!");



    }
}