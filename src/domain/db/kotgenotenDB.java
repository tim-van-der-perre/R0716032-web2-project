package domain.db;

import domain.model.Contact;
import domain.model.Kotgenoot;

import java.time.LocalDate;
import java.util.ArrayList;

public class kotgenotenDB {

    ArrayList<Kotgenoot> kotgenoten;

    public kotgenotenDB() {
        kotgenoten = new ArrayList<>();

        Kotgenoot tim = new Kotgenoot("Tim");
        Kotgenoot craenen = new Kotgenoot("Craenen");
        Kotgenoot elisa = new Kotgenoot("Elisa");
        Kotgenoot marie = new Kotgenoot("Marie");
        Kotgenoot ali = new Kotgenoot("Ali");
        Kotgenoot alix = new Kotgenoot("Alix");

        voegToe(tim);
        voegToe(craenen);
        voegToe(elisa);
        voegToe(marie);
        voegToe(ali);
        voegToe(alix);

        Contact helene = new Contact("Helene", LocalDate.of(2020,10,25));
        Contact appie = new Contact("Appie", LocalDate.of(2020,10,24));
        Contact bas = new Contact("Sebastian", LocalDate.of(2020,10,24));
        Contact liese = new Contact("Liese", LocalDate.of(2020,10,22));
        Contact vanderbiest = new Contact("Verbiest", LocalDate.of(2020,10,20));
        Contact mara = new Contact("mathias", LocalDate.of(2020,10,24));
        Contact marieHaarLief = new Contact("Daan", LocalDate.of(2020,10,24));

        tim.voegContactToe(helene);
        tim.voegContactToe(appie);
        elisa.voegContactToe(bas);
        alix.voegContactToe(liese);
        ali.voegContactToe(vanderbiest);
        craenen.voegContactToe(mara);
        marie.voegContactToe(marieHaarLief);

    }
    public void voegToe(Kotgenoot kotgenoot){
        if (kotgenoot == null)throw new IllegalArgumentException("ja contact is null");
        kotgenoten.add(kotgenoot);
    }

    public ArrayList<Kotgenoot> getKotgenoten() {
        return kotgenoten;
    }

    public Kotgenoot zoek(String naam){
        if (naam.isEmpty())throw new IllegalArgumentException("Vul een naam in.");
        else{
            Kotgenoot blub = null;
            for (Kotgenoot k: kotgenoten){
                if (naam.equalsIgnoreCase(k.getNaam())){
                    blub = k; }
            }
            if (blub == null)throw new IllegalArgumentException("kotgenoot is niet gevonden");
            return blub;
        }
    }

    public Kotgenoot berekenGrootsteBubbel(){
        int bubbel = 0;
        Kotgenoot besmetter = null;
        for (Kotgenoot k: kotgenoten){
            if (k.getBubbelGrootte()>bubbel){
                besmetter = k;
                bubbel = k.getBubbelGrootte();
            }
        }
        return besmetter;
    }

}

