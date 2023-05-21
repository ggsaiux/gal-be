package com.asociatialocatari.gal.apartment;

import com.asociatialocatari.gal.base.models.User;
import com.asociatialocatari.gal.build_stair.BuildStair;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ApartmentService {

    protected Set<Apartment> apartmentSetByUser(User user){
        return null;
    }

    protected Set<Apartment> apartmentSetByBuildStair(BuildStair buildStair){
        return null;
    }

}
