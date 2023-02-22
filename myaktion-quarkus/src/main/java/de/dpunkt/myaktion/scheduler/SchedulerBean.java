package de.dpunkt.myaktion.scheduler;

import de.dpunkt.myaktion.services.DonationService;
import io.quarkus.scheduler.Scheduled;

import javax.inject.Singleton;
import javax.inject.Inject;

@Singleton
public class SchedulerBean {

    @Inject
    private DonationService donationService;

    @Scheduled(every="60s", delay = 60)
    public void doTransferDonations() {
        donationService.transferDonations();
    }
}

