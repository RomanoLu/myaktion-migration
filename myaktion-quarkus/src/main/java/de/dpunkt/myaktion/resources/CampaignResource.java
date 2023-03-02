package de.dpunkt.myaktion.resources;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.services.CampaignService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;

    
@Path("/organizer/campaign")
@RolesAllowed("organizer")
public class CampaignResource {

    @Inject
    private CampaignService campaignService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/helloorganizer")
    public Response helloOrganizer(@Context SecurityContext sec){
        return Response.ok("hello " + sec.getUserPrincipal().getName()).build();
    }


    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Campaign> getAllCampaigns(@Context SecurityContext sec) {
        List<Campaign> allCampaigns = campaignService.getAllCampaigns(sec.getUserPrincipal().getName());
        allCampaigns.forEach(campaign -> {
            campaign.setDonations(null);
            campaign.setOrganizer(null);
        });
        return allCampaigns;
    }

    @GET
    @Path("/{campaignId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Campaign getCampaignbyID(@PathParam(value = "campaignId") Long campaignId){
        Campaign requestedCampaign = campaignService.findCampaign(campaignId);
        requestedCampaign.setDonations(null);
        return requestedCampaign;
    }

    @DELETE
    @Path("/{campaignId}")
    public void deleteCampaign(@PathParam(value = "campaignId") Long campaignId, @Context SecurityContext sec) {
        campaignService.deleteCampaign(campaignId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Campaign addCampaign(Campaign campaign, @Context SecurityContext sec) {
        return campaignService.addCampaign(campaign, sec.getUserPrincipal().getName());
    }

    @PUT
    @Path("/{campaignId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Campaign updateCampaign(@PathParam(value = "campaignId") Long campaignId,
                                   Campaign newCampaign, @Context SecurityContext sec) {
                                    
        Campaign campaign = campaignService.getCampaign(campaignId);
        campaign.setName(newCampaign.getName());
        campaign.setDonationMinimum(newCampaign.getDonationMinimum());
        campaign.setTargetAmount(newCampaign.getTargetAmount());
        newCampaign = campaignService.updateCampaign(campaign);
        newCampaign.setDonations(null);
        newCampaign.setOrganizer(null);
        return newCampaign;
    }

}

