package de.dpunkt.myaktion.monitor;

import com.oracle.svm.core.annotate.Inject;

import io.quarkus.grpc.GrpcClient;

public class DonationDelegator {
    //hier sollte ein Grpc Client definiert werden welcher die methode sendDonation vom monitor 

    @Inject
    MyAktionMonitorClient MyAktionMonitor;

    
    
}
