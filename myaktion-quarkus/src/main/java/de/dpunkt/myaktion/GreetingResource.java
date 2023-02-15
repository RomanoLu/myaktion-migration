package de.dpunkt.myaktion;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.inject.Inject;

import de.dpunkt.myaktion.model.Account;
import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.services.CampaignService;
import de.dpunkt.myaktion.services.DonationService;

@Path("/hello")
public class GreetingResource {
    

    @Inject
    private CampaignService campaignService;

    @Inject 
    private DonationService donationService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Campaign c = new Campaign();
        c.setName("Trikots");
        c.setAccount(null);
        c.setAmountDonatedSoFar(1.0);
        c.setDonationMinimum(1.0);
        c.setDonations(null);
        c.setOrganizer(null);
        c.setTargetAmount(100.0);

        campaignService.persistCampaign(c);
        
        return "successfully persited: " + campaignService.findCampaign(Long.valueOf(1)).getName();
    }

    @POST
    public Response addCampaign() {
        Donation d = new Donation();
        d.setAccount(new Account("LucaRomano", "Sparkasse", "DE6400000000000081234"));
        d.setCampaign(campaignService.findCampaign(Long.valueOf(1)));
        d.setAmount(1000d);
        d.setDonorName("Luca Romano");
        d.setReceiptRequested(true);
        d.setStatus(Donation.Status.TRANSFERRED);
        donationService.persistDonation(d);
        return Response.ok(d).build();
    }

    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response objekt() {
        Campaign c = new Campaign();
        c.setName("Trikots");
        c.setAccount(null);
        c.setAmountDonatedSoFar(1.0);
        c.setDonationMinimum(1.0);
        c.setDonations(null);
        c.setOrganizer(null);
        c.setTargetAmount(100.0);

        campaignService.persistCampaign(c);
        
        return Response.ok(c).build();
    }
}