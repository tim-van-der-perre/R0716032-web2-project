package ui.controller;

import domain.db.kotgenotenDB;
import domain.model.Contact;
import domain.model.Kotgenoot;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

@WebServlet("/KotInfo")
public class KotInfo extends HttpServlet {

    kotgenotenDB kot = new kotgenotenDB();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }



    private void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        String command = "home";
        String destination;

        if (request.getParameter("command")!=null){
            command = request.getParameter("command");
        }
        switch (command){
            case "home":
                destination = home(request,response);
                break;
            case "overzicht":
                destination = overzicht(request,response);
                break;
            case "formulier":
                destination = formulier(request, response);
                break;
            case "verwijderNavraag" :
                destination = verwijderNaVraag(request, response);
                break;
            case "delete" :
                destination = delete(request, response);
                break;
            case "zoek" :
                destination = zoek(request, response);
                break;
            case "updateNaVraag" :
                destination = updateNaVraag(request, response);
                break;
            case "update" :
                destination = update(request, response);
                break;
            case "switchKotgenoot":
                destination = switchKotgenoot(request, response);
                break;
            default :
                destination = overzicht(request,response);
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }




    private String home(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("kotgenoten", kot.getKotgenoten());
        request.setAttribute("grootstebubbel", kot.berekenGrootsteBubbel().getNaam());
        return "index.jsp";
    }

    private String overzicht(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("kotgenoten", kot.getKotgenoten());
        request.setAttribute("grootstebubbel", kot.berekenGrootsteBubbel().getNaam());

        if (getCookieWithKey(request, "responsecookie") == null){
            request.setAttribute("requestcookie", "");

        }

        else {
            if (getCookieWithKey(request, "responsecookie").getValue().isEmpty()) {
                request.setAttribute("requestcookie", "");
            }
            else {
                request.setAttribute("requestcookie", kot.zoek(getCookieWithKey(request, "responsecookie").getValue()));
                request.setAttribute("placeholder", getCookieWithKey(request, "responsecookie").getValue());
            }
        }

        return "overzicht.jsp";
    }

    private String formulier(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();

        String naam = request.getParameter("naam");
        String contact = request.getParameter("contact");
        String datum = request.getParameter("datum");

        Contact nieuwcontact = new Contact();
        vindNaam(request, errors);
        setContact(nieuwcontact, request, errors);
        setDatum(nieuwcontact, request, errors);

        if (errors.size() == 0) {
            try {
                kot.zoek(naam).voegContactToe(nieuwcontact);
                return overzicht(request, response);
            } catch (IllegalArgumentException exc) {
                errors.add(exc.getMessage());
                request.setAttribute("errors", errors);
                return "formulier.jsp";
            }
        }
        request.setAttribute("errors", errors);
        return "formulier.jsp";
    }


    private String verwijderNaVraag(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("verwijderde", kot.getKotgenoten());
        request.setAttribute("grootstebubbel", kot.berekenGrootsteBubbel().getNaam());
        return "verwijder.jsp";
    }
    private String delete (HttpServletRequest request, HttpServletResponse response) {
        String kotgenoot = request.getParameter("naam");
        String contact = request.getParameter("contact");

        if (kotgenoot.isEmpty() || contact.isEmpty()) return "index.jsp";
        (kot.zoek(kotgenoot)).verwijderContactBijNaam(contact);
        return overzicht(request, response);
    }
    private String updateNaVraag(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("verwijderde", kot.getKotgenoten());
        request.setAttribute("grootstebubbel", kot.berekenGrootsteBubbel().getNaam());
        return "update.jsp";
    }
    private String update (HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        String kotgenoot = request.getParameter("naam");
        String contact = request.getParameter("contact");
        String datum = request.getParameter("datum");
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localdatum = LocalDate.parse(datum, formatter);
            kot.zoek(kotgenoot).verwijderContactBijNaam(contact);
            kot.zoek(kotgenoot).voegContactToe(new Contact(contact, localdatum));
            return overzicht(request, response);
        } catch (DateTimeParseException dtpe) {
            errors.add("Gelieve een datum te selecteren.");
            request.setAttribute("errors", errors);
            return "update.jsp";
        } catch (IllegalArgumentException e){
            errors.add(e.getMessage());
            request.setAttribute("errors", errors);
            return "update.jsp";
        }
    }

    private String zoek (HttpServletRequest request ,HttpServletResponse response){

        String contactnaam = request.getParameter("contactnaam");
        if (contactnaam.isEmpty()){
            request.setAttribute("error", "gelieve een naam in te vullen");
            return "zoek.jsp";
        }
        for (Kotgenoot k: kot.getKotgenoten()){
            for (Contact c: k.getContacten()){
                if (c.getNaam().equalsIgnoreCase(contactnaam)){
                    request.setAttribute("gevondencontact", k.zoekContact(c.getNaam()));
                    request.setAttribute("gevondenkotgenoot", k);
                }
            }
        }
        return "gevonden.jsp";
    }
    private void vindNaam(HttpServletRequest request, ArrayList<String> errors){
        String naam = request.getParameter("naam");
        try {
            kot.zoek(naam);
            request.setAttribute("ingevuldeNaam", naam);
        }
        catch (IllegalArgumentException exc){
            errors.add(exc.getMessage());
        }
    }
    private void setContact(Contact nieuwcontact, HttpServletRequest request, ArrayList<String> errors){
        String contact = request.getParameter("contact");
        try {
            nieuwcontact.setNaam(contact);
            request.setAttribute("ingevuldContact", contact);
        }
        catch (IllegalArgumentException exc){
            errors.add(exc.getMessage());
        }
    }
    private void setDatum(Contact nieuwcontact, HttpServletRequest request, ArrayList<String> errors){
        String datum = request.getParameter("datum");
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localdatum = LocalDate.parse(datum, formatter);
            nieuwcontact.setDatum(localdatum);
            request.setAttribute("ingevuldeDatum", datum);
        }
        catch (Exception exc){
            errors.add("Vul een datum in.");
        }
    }
    private String switchKotgenoot(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("kotgenoten", kot.getKotgenoten());
        request.setAttribute("grootstebubbel", kot.berekenGrootsteBubbel().getNaam());
        String kotgenoot = request.getParameter("kotgenootkeuze");

        Cookie c = new Cookie("responsecookie", kotgenoot);
        response.addCookie(c);
        switch (kotgenoot){
            case "tim":
                request.setAttribute("requestcookie", kot.zoek("tim"));
                request.setAttribute("placeholder", "tim");
                break;
            case "craenen":
                request.setAttribute("requestcookie", kot.zoek("craenen"));
                request.setAttribute("placeholder", "craenen");
                break;
            case "elisa":
                request.setAttribute("requestcookie", kot.zoek("elisa"));
                request.setAttribute("placeholder", "elisa");
                break;
            case "marie" :
                request.setAttribute("requestcookie", kot.zoek("marie"));
                request.setAttribute("placeholder", "marie");
                break;
            case "ali" :
                request.setAttribute("requestcookie", kot.zoek("ali"));
                request.setAttribute("placeholder", "ali");
                break;
            case "alix" :
                request.setAttribute("requestcookie", kot.zoek("alix"));
                request.setAttribute("placeholder", "alix");
                break;
            default :
                request.setAttribute("requestcookie", "");
                request.setAttribute("placeholder", "iedereen");
        }
        return "overzicht.jsp";
    }

    private Cookie getCookieWithKey(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        for (Cookie cookie: cookies){
            if (cookie.getName().equals(key)){
                return cookie;
            }
        }
        return null;
    }
}
