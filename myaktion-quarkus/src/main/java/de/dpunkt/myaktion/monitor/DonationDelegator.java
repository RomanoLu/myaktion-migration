package de.dpunkt.myaktion.monitor;

import javax.inject.Singleton;

import de.dpunkt.myaktion.MyaktionMonitorServiceGrpc.MyaktionMonitorServiceBlockingStub;
import de.dpunkt.myaktion.Myaktionmonitor.Donation;
import de.dpunkt.myaktion.Myaktionmonitor.DonationRequest;
import io.quarkus.grpc.GrpcClient;

@Singleton
public class DonationDelegator {
    
    @GrpcClient("monitorservice")
    MyaktionMonitorServiceBlockingStub monitorService;
   
    public void sendDonation(Long campaignId, Donation donation){
        monitorService.sendDonation(DonationRequest.newBuilder().setCampaignId(campaignId).setDonation(donation).build());       
    }
}
