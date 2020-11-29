package domain.model;

import java.time.LocalDate;
import java.util.Date;

public class Contact {
    private String naam;
    private LocalDate datum;

    public Contact(){

    }
    public Contact(String naam, LocalDate datum) {
        if (naam.isEmpty())throw new IllegalArgumentException("Vul een contactnaam in.");
        if (datum == null)throw new IllegalArgumentException("Vul een datum in.");
        this.naam = naam;
        this.datum = datum;
    }
    public void setNaam(String naam) {
        if (naam.isEmpty())throw new IllegalArgumentException("Vul een contactnaam in.");
        this.naam = naam;
    }
    public String getNaam() {
        return naam;
    }

    public void setDatum(LocalDate datum) {
        if (datum == null)throw new IllegalArgumentException("Vul een datum in.");
        this.datum = datum;
    }
    public LocalDate getDatum() {
        return datum;
    }

}


