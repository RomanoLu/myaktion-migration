package de.dpunkt.myaktion.services;

import de.dpunkt.myaktion.model.Campaign;

import java.util.List;

import javax.transaction.Transactional;

public interface CampaignService {
    List<Campaign> getAllCampaigns(String  email);

    Campaign addCampaign(Campaign campaign, String email);

    void deleteCampaign(Campaign campaign);

    Campaign updateCampaign(Campaign campaign);

    void deleteCampaign(Long campaignId);

    Campaign getCampaign(Long campaignId);

    void persistCampaign(Campaign c);

    @Transactional
    Campaign findCampaign(Long id);
}
