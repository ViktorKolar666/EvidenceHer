import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SpravceDeskovek extends JFrame {
    private final ArrayList<Deskovka> seznamDeskovek = new ArrayList<>();
    public SpravceDeskovek() {
        cteni();
    }
    private void cteni() {
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader("deskovky.txt")))) {
            while (sc.hasNextLine()) {
                String radek = sc.nextLine();
                String[] polozky = radek.split(";");
                String nazev = polozky[0];
                boolean zakoupeno = polozky[1].equals("ano");
                int oblibenost = Integer.parseInt(polozky[2]);
                seznamDeskovek.add(new Deskovka(nazev, zakoupeno, oblibenost));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (OblibenostException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }
    public void pridejDeskovku(Deskovka deskovka) {
        seznamDeskovek.add(deskovka);
    }

    public Deskovka getDeskovka(int index) {
        return seznamDeskovek.get(index);
    }

    public int getPocetDeskovek() {
        return seznamDeskovek.size();
    }

    public void setDeskovka(int indexAktualniDeskovky, Deskovka aktualniDeskovka) {
        seznamDeskovek.set(indexAktualniDeskovky, aktualniDeskovka);
        zapis();
    }

    private void zapis(){
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("deskovky.txt")))) {
            for (Deskovka deskovka : seznamDeskovek) {
                printWriter.println(deskovka.getNazevHry() + ";" + (deskovka.isZakoupeno() ? "ano" : "ne") + ";" + deskovka.getOblibenost());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}