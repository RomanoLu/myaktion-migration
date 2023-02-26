package de.dpunkt.myaktion.monitor;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import de.dpunkt.myaktion.model.Donation;

@RegisterRestClient(baseUri = "http://localhost:8443/donation/organizer/donation/list")
public interface DonationListProvider {

    @GET
    @Path("/{campaignId}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Donation> getDonations(@PathParam("campaignId") Long campaignId);
}
