package parrot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import org.sfvl.doctesting.junitextension.SimpleApprovalsExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParrotDocTest {
    @RegisterExtension
    static SimpleApprovalsExtension doc = new SimpleApprovalsExtension();


    @Test
    public void parrotList() {
        List<Parrot> parrots = Arrays.stream(ParrotTypeEnum.values())
                .map(t -> new Parrot(t, 0, 0, false))
                .toList();


        doc.write("Here is the list of parrots with their default speeds (voltage and load set to 0.\n");
        doc.write("|===\n");
        doc.write("| Name | Default Speed\n");
        doc.write("\n");

        for (Parrot parrot : parrots) {
            doc.write("| " + parrot.getType() + " | " + parrot.getSpeed() + "\n");
        }

        doc.write("|===\n");
    }

    @Test
    public void norwegianBlueIsReallySpecial(){
        doc.write("== Norwegian Parrot\n\n");
        doc.write("The Norwegian Parrot is special, because it is an eletric one. So you need to apply voltage" +
                " to make it work (unlike other parrots, where voltage is unlikely to have the expected effect).\n\n");

        doc.write("|===\n");
        doc.write("| Voltage | Speed\n");


        for (int v = 0; v <= 220; v = v + 12 ) {
            Parrot parrot = new Parrot(ParrotTypeEnum.NORWEGIAN_BLUE, 0, v, false);
            doc.write("| " + v + " | " + parrot.getSpeed() + "\n");

        }
        doc.write("|===\n");

    }

}