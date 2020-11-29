package domain.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Kotgenoot {
    String naam;
    int bubbelGrootte;
    Contact laatsteContact;
    ArrayList<Contact> contacten;

    public Kotgenoot(){

    }
    public Kotgenoot(String naam) {
        this.naam = naam;
        bubbelGrootte = 0;
        laatsteContact = new Contact("niemand", LocalDate.of(2001,12,05));
        contacten = new ArrayList<>();
    }

    public void vergrootBubbelGrootte(){
        bubbelGrootte += 1;
    }
    public void verkleinBubbelGrootte(){
        bubbelGrootte -= 1;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
    public String getNaam() {
        return naam;
    }

    public void setBubbelGrootte(int bubbelGrootte) {
        this.bubbelGrootte = bubbelGrootte;
    }
    public int getBubbelGrootte() {
        return bubbelGrootte;
    }

    public void setLaatsteContact(Contact laatsteContact) {
        this.laatsteContact = laatsteContact;
    }
    public Contact getLaatsteContact() {
        return laatsteContact;
    }

    public void setContacten(ArrayList<Contact> contacten) { this.contacten = contacten; }
    public ArrayList<Contact> getContacten() {
        ArrayList<Contact> contactenlijst = new ArrayList<>();
        for (Contact c: contacten){
            contactenlijst.add(c);
        }
        return contactenlijst;
    }


    public Contact zoekContact(String naam){
        if (naam.isEmpty())throw new IllegalArgumentException("Vul een naam in.");
        Contact blub = null;
        for (Contact c: contacten){
            if (c.getNaam() == naam){
                blub = c;
            }
        }
        return blub;
    }

    public void voegContactToe(Contact contact){
        if (contact == null)throw new IllegalArgumentException("toe te voegen contact is null");
        //for (Contact c: contacten){
          //  if (c.getNaam().equals(contact.getNaam()) && c.getDatum().isEqual(contact.getDatum()))throw new IllegalArgumentException("Contact is al vernomen!");
        //}
        if (contacten.isEmpty()){
            setLaatsteContact(contact);

        }
        else{
                if (getLaatsteContact().getDatum().isBefore(contact.getDatum())){
                    setLaatsteContact(contact);
                }
            }
        contacten.add(contact);
        vergrootBubbelGrootte();
    }



    public void verwijderContactBijNaam(String contactnaam){
        if (contactnaam == null)throw new IllegalArgumentException("contactnaam om te verwijderen is leeg...");
        else{
            int index = -1;
            int i = 0;
            for (Contact c : contacten){
                index ++;
                if (c.getNaam().equalsIgnoreCase(contactnaam)){
                    i = index;
                }
            }
        contacten.remove(i);
        verkleinBubbelGrootte();
    }}
}
