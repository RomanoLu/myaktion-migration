package de.dpunkt.myaktion.data;
/*
import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.services.CampaignService;
import de.dpunkt.myaktion.util.Events.Added;
import de.dpunkt.myaktion.util.Events.Deleted;
import de.dpunkt.myaktion.util.Events.Updated;

import javax.annotation.PostConstruct;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;*/
import javax.enterprise.context.RequestScoped;
@RequestScoped
public class CampaignListProducer {
/*
    private List<Campaign> campaigns;

    @Inject
    private CampaignService campaignService;

    @PostConstruct
    public void init() {
       // campaigns = campaignService.getAllCampaigns();
    }

    @Produces
    @Named
    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void onCampaignAdded(@Observes @Added Campaign campaign) {
        campaignService.addCampaign(campaign, campaign.getOrganizer());
        init();
    }

    public void onCampaignDeleted(@Observes @Deleted Campaign campaign) {
        campaignService.deleteCampaign(campaign);
        init();
    }

    public void onAktionUpdated(@Observes @Updated Campaign campaign) {
        campaignService.updateCampaign(campaign);
        init();
    }
 */
}
