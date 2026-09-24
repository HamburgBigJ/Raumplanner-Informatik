package cho.info;

import cho.info.Furniture.objects.Klavier;
import cho.info.Furniture.objects.Stuhl;
import cho.info.Furniture.objects.Tisch;
import cho.info.register.ObjectRegister;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        Leinwand.gibLeinwand();

        new Stuhl().registerObject();
        new Tisch().registerObject();
        new Klavier().registerObject();

        ObjectRegister.drawAllObjects();
    }

}
