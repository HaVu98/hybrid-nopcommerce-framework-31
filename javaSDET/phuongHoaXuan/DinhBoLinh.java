package phuongHoaXuan;

public class DinhBoLinh {
    // Có access modifier là Private thì chỉ được phép sử dụng trong Class chứa nó

    // Private (Chỉ cho phép trong Class này dùng)
    // Variable / Property
    private String espresso;

    // Getter / Setter
    public void setEspresso(String espresso) {
        this.espresso = espresso;
    }

    // Method / Function
    public String getEspresso() {
        return espresso;
    }

    // Default chỉ cho phép các class trong cùng package truy cập
    String cappuchino;

    String getCappuchino() {
        return cappuchino;
    }

    // Protected (Chỉ cho phép kế thừa mới sử dụng được)
    protected String cherry;

    protected String getCherry() {
        return cherry;
    }

    public String catinor;

    public String getCatinor() {
        return catinor;
    }

    public static void main(String[] args) {
        // Hàm là static không thể gọi trực tiếp đến 1 biến / hàm non-static khác
        DinhBoLinh dinhBoLinh = new DinhBoLinh();
        dinhBoLinh.espresso = "Espresso";
        System.out.println(dinhBoLinh.getEspresso());

        dinhBoLinh.cappuchino = "Capuchino";
        System.out.println(dinhBoLinh.getCappuchino());

        dinhBoLinh.cherry = "Cherry";
        System.out.println(dinhBoLinh.getCherry());

        dinhBoLinh.catinor = "Catinor";
        System.out.println(dinhBoLinh.getCatinor());

    }
}
