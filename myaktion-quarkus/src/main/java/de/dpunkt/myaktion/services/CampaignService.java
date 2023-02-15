package de.dpunkt.myaktion.services;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Organizer;

import java.util.List;

public interface CampaignService {
    List<Campaign> getAllCampaigns();

    Campaign addCampaign(Campaign campaign, Organizer organizer);

    void deleteCampaign(Campaign campaign);

    Campaign updateCampaign(Campaign campaign);

    void deleteCampaign(Long campaignId);

    Campaign getCampaign(Long campaignId);

    void persistCampaign(Campaign c);

    Campaign findCampaign(Long id);
}
