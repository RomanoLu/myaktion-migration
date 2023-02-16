package de.dpunkt.myaktion.services;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.model.Donation.Status;
import de.dpunkt.myaktion.services.exceptions.ObjectNotFoundException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequestScoped
@Transactional
public class DonationServiceBean implements DonationService {
    @Inject
    private EntityManager entityManager;

    @Override
    public List<Donation> getDonationList(Long campaignId) {
        Campaign managedCampaign = entityManager.find(Campaign.class, campaignId);
        List<Donation> donations = managedCampaign.getDonations();
        donations.size();
        return donations;
    }

    @Override
    public Donation addDonation(Long campaignId, Donation donation) {
        Campaign managedCampaign = entityManager.find(Campaign.class, campaignId);
        donation.setCampaign(managedCampaign);
        if (donation.getCampaign()!= null){
            entityManager.persist(donation);
        }else{
            System.out.println("Campaign ist angeblich null");
        }

        return donation;
    }

    @Override
    public void transferDonations() {        
        TypedQuery<Donation> query =
                entityManager.createNamedQuery(Donation.findByStatus, Donation.class);
        query.setParameter("status", Status.IN_PROCESS);
        List<Donation> donations = query.getResultList();
        donations.forEach(donation -> donation.setStatus(Status.TRANSFERRED));
        
    }

    @Override
    public void persistDonation(Donation d){
        this.entityManager.persist(d);
    }

    @Override
    public List<Donation> getDonationListPublic(Long campaignId) throws ObjectNotFoundException {
        Campaign managedCampaign = entityManager.find(Campaign.class, campaignId);
        if (managedCampaign == null) throw new ObjectNotFoundException();
        List<Donation> donations = managedCampaign.getDonations();
        final Function<Donation, Donation> donationFilter = donation -> {
            Donation filtered = new Donation();
            filtered.setAmount(donation.getAmount());
            filtered.setDonorName(donation.getDonorName());
            return filtered;
        };
        return donations.stream().map(donationFilter).collect(Collectors.toList());
    }
}
