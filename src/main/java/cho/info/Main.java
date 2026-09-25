package cho.info;

import cho.info.renderer.Helix;

public class Main {

    public static Main INCACe;


    public static void main(String[] args) {
        new Helix().init();
        Helix.INSTANCE.run();

    }



    /*
            Leinwand.gibLeinwand();

        new Stuhl().registerObject();
        new Tisch().registerObject();
        new Klavier().registerObject();

        ObjectRegister.drawAllObjects();
     */

}
