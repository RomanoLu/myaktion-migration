package de.dpunkt.myaktion.monitor;

import io.grpc.Channel;
import io.quarkus.grpc.GrpcService;

public class MyAktionMonitorClient {
    
    @GrpcService("donation")
    Channel channel;
    public String sendDonation(String donorName, double amount) {
        
    }
}
