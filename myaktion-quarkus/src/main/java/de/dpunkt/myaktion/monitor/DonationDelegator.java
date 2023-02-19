package de.dpunkt.myaktion.monitor;

import de.dpunkt.myaktion.MyaktionMonitorServiceClient;
import de.dpunkt.myaktion.Myaktionmonitor.Donation;
import de.dpunkt.myaktion.Myaktionmonitor.DonationRequest;
import io.quarkus.grpc.GrpcClient;

public class DonationDelegator {
    
    @GrpcClient
    MyaktionMonitorServiceClient monitorService;
   
    public void sendDonation(Long campaignId, Donation donation){
        monitorService.sendDonation(DonationRequest.newBuilder().setCampaignId(campaignId).setDonation(donation).build());       
    }
}
