package de.dpunkt.myaktion.monitor.ws;

import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.monitor.MonitorWebSocket;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import de.dpunkt.myaktion.MyaktionMonitorService;
import de.dpunkt.myaktion.Myaktionmonitor;
import de.dpunkt.myaktion.Myaktionmonitor.DonationRequest;
import de.dpunkt.myaktion.Myaktionmonitor.DonationResponse;

import javax.inject.Singleton;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import javax.websocket.server.PathParam;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@GrpcService
public class DonationDelegator implements MyaktionMonitorService{

    private Logger logger = Logger.getLogger(DonationDelegator.class.getName());

    @Override
    public Uni<DonationResponse> sendDonation(DonationRequest request) {
        Long campaignId = request.getCampaignId();
        de.dpunkt.myaktion.Myaktionmonitor.Donation donation = request.getDonation();

        for (Session session : MonitorWebSocket.getSessions()) {
            Long clientCampaignId = (Long) session.getUserProperties().get(
                    MonitorWebSocket.CAMPAIGN_ID);
            if (campaignId.equals(clientCampaignId)) {
                try {
                    session.getBasicRemote().sendObject(donation);
                } catch (IOException | EncodeException e) {
                    logger.log(Level.INFO, "Keine Verbindung zu Client: "
                            + session, e);
                }
            }
        }
        return null;
    }

}
