public class Main {
    public static void main(String[] args) throws OblibenostException {
        SpravceDeskovek spravceDeskovek = new SpravceDeskovek();
        spravceDeskovek.pridejDeskovku(new Deskovka("Krycí jména", true, 2));
        GUI gui = new GUI(spravceDeskovek);
        gui.setVisible(true);
    }
}