package de.dpunkt.myaktion.monitor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import de.dpunkt.myaktion.DonationRequest;
import de.dpunkt.myaktion.MyaktionMonitorServiceGrpc.MyaktionMonitorServiceBlockingStub;
import io.quarkus.grpc.GrpcClient;

@Path("/test")
public class DonationDelegator {

    @GrpcClient("monitorservice")
    MyaktionMonitorServiceBlockingStub monitorServiceStub;


    public DonationRequest buildRequest(Long campaignId, de.dpunkt.myaktion.Donation donation){
        return de.dpunkt.myaktion.DonationRequest.newBuilder().setCampaignId(campaignId).setDonation(donation).build();
    }
   
    @GET
    public Response sendDonation(){
        monitorServiceStub.sendDonation( buildRequest(Long.valueOf(1),de.dpunkt.myaktion.Donation.newBuilder()
        .setAmount(10).setDonorName("Taio").build() ));       
        return Response.ok().build();
    }
     
}
