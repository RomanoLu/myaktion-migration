package de.dpunkt.myaktion.services;

import de.dpunkt.myaktion.model.Organizer;



public interface OrganizerService {

    void addOrganizer(Organizer organizer);

    boolean isValidUser(String email, String passwort);

    
}
