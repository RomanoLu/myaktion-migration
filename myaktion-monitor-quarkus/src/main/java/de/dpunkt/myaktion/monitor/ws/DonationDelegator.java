package de.dpunkt.myaktion.monitor.ws;

import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.monitor.MonitorWebSocket;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/sendDonationsToClients")
public class DonationDelegator {

    private Logger logger = Logger.getLogger(DonationDelegator.class.getName());

    //Diese Methode könnte potenziell über gRPC aufgerufen werden
    @Path("/{campaignid}")
    @POST
    public void sendDonation(@PathParam("campaignid") Long campaignId) {
        for (Session session : MonitorWebSocket.getSessions()) {
            Long clientCampaignId = (Long) session.getUserProperties().get(
                    MonitorWebSocket.CAMPAIGN_ID);
            if (campaignId.equals(clientCampaignId)) {
                try {
                    session.getBasicRemote().sendObject(new Donation());
                } catch (IOException | EncodeException e) {
                    logger.log(Level.INFO, "Keine Verbindung zu Client: "
                            + session, e);
                }
            }
        }
    }

}
