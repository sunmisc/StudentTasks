package zelvalea.tasks.lab9;

public class VagonTest {


    public static void main(String[] args) {
        vagon2 vagon2 = new vagon2();
        vagon2.PustojVagon(0);

        vagon2.ZanyatxMesto(1);
        vagon2.ZanyatxMesto(1);

        for (int i = 2; i < 10; ++i) {
            vagon2.ZanyatxMesto(i);
        }
        vagon2.VagonInfo();
    }
}
