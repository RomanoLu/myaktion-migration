package de.dpunkt.myaktion.resources;

import de.dpunkt.myaktion.DonationRequest;
import de.dpunkt.myaktion.MyaktionMonitorServiceGrpc.MyaktionMonitorServiceBlockingStub;
import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.services.DonationService;
import de.dpunkt.myaktion.services.exceptions.ObjectNotFoundException;
import io.quarkus.grpc.GrpcClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/donation")
public class DonationResource {

    @Inject
    private DonationService donationService;

    @GrpcClient("monitorservice")
    MyaktionMonitorServiceBlockingStub monitorServiceStub;

    @GET
    @Path("/organizer/donation/list/{campaignId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Donation> getDonationList(@PathParam(value = "campaignId") Long campaignId) {
        List<Donation> donations = donationService.getDonationList(campaignId);
        donations.forEach(donation -> donation.setCampaign(null));
        return donations;
    }

    @POST
    @Path("/donation/{campaignId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDonation(Donation donation) {  
        monitorServiceStub.sendDonation( buildRequest(donation.getCampaign().getId(),de.dpunkt.myaktion.Donation.newBuilder()
        .setAmount(donation.getAmount()).setDonorName(donation.getDonorName()).build() ));       
        
        donationService.addDonation(donation.getCampaign().getId(), donation); 
        return Response.ok().build();
    }

    @GET
    @Path("/donation/list/{campaignId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDonationListPublic(@PathParam(value = "campaignId") Long campaignId) {
        List<Donation> donations;
        try {
            donations = donationService.getDonationListPublic(campaignId);
            return Response.ok(donations).build();
        } catch (ObjectNotFoundException e) {
            return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).build();
        }
    }

    public DonationRequest buildRequest(Long campaignId, de.dpunkt.myaktion.Donation donation){
        return de.dpunkt.myaktion.DonationRequest.newBuilder().setCampaignId(campaignId).setDonation(donation).build();
    }

}
