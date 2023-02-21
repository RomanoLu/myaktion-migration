package de.dpunkt.myaktion.monitor.grpc;

import de.dpunkt.myaktion.monitor.MonitorWebSocket;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import de.dpunkt.myaktion.MyaktionMonitorService;
import de.dpunkt.myaktion.model.Donation;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@GrpcService
public class DonationDelegator implements MyaktionMonitorService{

    private Logger logger = Logger.getLogger(DonationDelegator.class.getName());   

    @Override
    public Uni<de.dpunkt.myaktion.DonationResponse> sendDonation(de.dpunkt.myaktion.DonationRequest request) {
        Long campaignId = request.getCampaignId();
        Donation donation = new Donation();
        donation.setAmount(request.getDonation().getAmount());
        donation.setDonorName(request.getDonation().getDonorName());

        System.out.println("DonorName: " + donation.getDonorName());
   
        for (Session session : MonitorWebSocket.getSessions()) {
            Long clientCampaignId = (Long) session.getUserProperties().get(
                    MonitorWebSocket.CAMPAIGN_ID);
            if (campaignId.equals(clientCampaignId)) {
                try {
                    System.out.println("Donation vom gRPC Client empfangen: "+ donation);
                    session.getBasicRemote().sendObject(donation);
                } catch (IOException | EncodeException e) {
                    logger.log(Level.INFO, "Keine Verbindung zu Client: "
                            + session, e);
                }
            }
        }
       
        return Uni.createFrom().item("Alles gut").map(msg -> de.dpunkt.myaktion.DonationResponse.newBuilder().setMessage(msg).build());
        
    }

}