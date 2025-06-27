package phuongHoaXuan;

public class LeHoan {
    public static void main(String[] args) {
        DinhBoLinh dinhBoLinh = new DinhBoLinh();
//        dinhBoLinh.espresso = "cfd";
        dinhBoLinh.setEspresso("cfe");
        System.out.println(dinhBoLinh.getEspresso());

        dinhBoLinh.cappuchino = "Capuchino Le Hoan";
        System.out.println(dinhBoLinh.getCappuchino());
    }
}
