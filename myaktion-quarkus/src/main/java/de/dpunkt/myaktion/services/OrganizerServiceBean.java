package de.dpunkt.myaktion.services;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import de.dpunkt.myaktion.model.Organizer;

@RequestScoped
@Transactional
public class OrganizerServiceBean implements OrganizerService {

    @Inject
    EntityManager entityManager;

    @Override
    public void addOrganizer(Organizer organizer) {
        entityManager.persist(organizer);
    }

    @Override
    public void loginOrganizer(Organizer organizer) {
        
        
    }
    
}
