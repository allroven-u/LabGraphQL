package com.cenfotec.graphql.mutation;

import com.cenfotec.graphql.entities.Vehicle;
import com.cenfotec.graphql.services.VehicleService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class VehicleMutation implements GraphQLMutationResolver {

    @Autowired
    private VehicleService vehicleService;

    public Vehicle createVehicle(String type, String modelCode,
                                 String brandName,
                                 String launchDate) {
        return this.vehicleService.createVehicle(type, modelCode, brandName, launchDate);
    }

    public Vehicle updateVehicle(Integer id, String type,
                                 String modelCode,String brandName,
                                 String launchDate) {
        Optional<Vehicle> vehicle = vehicleService.getVehicle(id);
        if (vehicle.isPresent()){
            Vehicle vehicleToUpdate = vehicle.get();
            vehicleToUpdate.setBrandName(brandName);
            vehicleToUpdate.setType(type);
            vehicleToUpdate.setModelCode(modelCode);
            vehicleToUpdate.setLaunchDate(LocalDate.parse(launchDate));
            vehicleService.updateVehicle(vehicleToUpdate);
            return vehicleToUpdate;
        } else {
            return null;
        }
    }
}
