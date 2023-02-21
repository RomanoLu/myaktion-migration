package de.dpunkt.myaktion.monitor;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.dpunkt.myaktion.MyaktionMonitorServiceGrpc.MyaktionMonitorServiceBlockingStub;

import io.quarkus.grpc.GrpcClient;

@Singleton
public class DonationDelegator {
    @Inject
    @GrpcClient("monitorservice")
    MyaktionMonitorServiceBlockingStub monitorService;
   
    public void sendDonation(Long campaignId, de.dpunkt.myaktion.Donation donation){
        monitorService.sendDonation(de.dpunkt.myaktion.DonationRequest.newBuilder().setCampaignId(campaignId).setDonation(donation).build());       
    }
     
}
