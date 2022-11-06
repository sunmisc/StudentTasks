package zelvalea.tasks.lab9;

import java.util.Arrays;

// signature polymorphic
public class vagon2 extends vagon {

    public void IzmenitxChisloMest(int NovoeChisloMest) {
        super.ChisloMest = NovoeChisloMest;
    }
    @Override
    public void ZanyatxMesto(int NomerMesta) {
        int i = NomerMesta - 1;
        if (Mesta[i] != 0) {
            System.out.printf("Место %s уже занято в вагоне %s%n",
                    NomerMesta,
                    super.NomerEtogoVagona
            );
        } else {
            Mesta[i] = 1;
        }
    }
    @Override
    public void VagonInfo() {
        long c = Arrays
                .stream(Mesta)
                .filter(x -> x == 0)
                .count();
        System.out.printf("Номер вагона: %s\nСвободно мест: %s%n", NomerEtogoVagona, c);
        super.VagonInfo();
    }
}
