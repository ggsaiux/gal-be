package com.asociatialocatari.gestiune.apartment;

import com.asociatialocatari.gestiune.base.models.User;
import com.asociatialocatari.gestiune.build_stair.BuildStair;
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
