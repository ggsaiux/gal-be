package com.asociatialocatari.gal.asso;

import com.asociatialocatari.gal.base.Utils;
import com.asociatialocatari.gal.base.exception.ErrorEnum;
import com.asociatialocatari.gal.base.exception.GalException;
import com.asociatialocatari.gal.base.models.Stt;
import com.asociatialocatari.gal.base.models.User;
import com.asociatialocatari.gal.base.repositories.SttRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AssoService {

    private static final Logger logger = LoggerFactory.getLogger(AssoService.class);

    private final AssoMapper mapper = Mappers.getMapper(AssoMapper.class);

    private final String ASSO = "asso";

    private final Utils utils;

    private final AssoRepository assoRepository;

    private final SttRepository sttRepository;
    private final UserAssoRepository userAssoRepository;

    public AssoService(Utils utils, AssoRepository assoRepository, SttRepository sttRepository, UserAssoRepository userAssoRepository) {
        this.utils = utils;
        this.assoRepository = assoRepository;
        this.sttRepository = sttRepository;
        this.userAssoRepository = userAssoRepository;
    }

    //todo for ADMINS
    public Map<String, List<AssoDto>> getAllAsso() {
        return null;
    }

    public Map<String,AssoDto> saveAsso(AssoDto assoDto) throws GalException {

        Map<String,AssoDto> mapAssoDto = new HashMap<>();
        try {
            if(assoDto != null) {
                User user = utils.getUserFromContext();
                Asso asso = new Asso();
                if(assoDto.getId() != null){
                    Optional<Asso> assoOpt = assoRepository.findById(assoDto.getId());
                    if (assoOpt.isPresent()) {
                        asso = assoOpt.get();
                        if (assoDto.getName() != null)
                            asso.setName(assoDto.getName());
                        if (assoDto.getDescription() != null)
                            asso.setDescription(assoDto.getDescription());
                        if (assoDto.getAddress() != null)
                            asso.setAddress(assoDto.getAddress());
                        asso.setUpdated(LocalDateTime.now());
                        assoRepository.save(asso);
                    } else {
                        logger.error("Error on Association saving!");
                        throw new GalException("Error on Association saving!", ErrorEnum.RUNTIME_ERROR);
                    }
                } else {
                    Optional<Stt> sttActiveOpt = sttRepository.findById(1l);
                    asso = mapper.toAsso(assoDto);
                    asso.setStt(sttActiveOpt.get()); //set state active
                    assoRepository.save(asso);
                    userAssoRepository.save(new UserAsso(user, asso, sttActiveOpt.get()));
                }
                mapAssoDto.put(ASSO, mapper.toAssoDto(asso));
            }
        } catch(Exception e) {
            logger.error(e.getMessage(), e.getStackTrace());
            throw new GalException("Error on Association saving!", ErrorEnum.RUNTIME_ERROR);
        }
        return mapAssoDto;
    }

    public Map<String,AssoDto> getAssoById(long id) throws GalException {
        //todo logs
        //todo exceptions

        // User user = utils.getUserFromContext();
        //Set<String> roles = utils.getAuthoritiesFromContext();
        Map<String, AssoDto> mapAssoDto = new HashMap<>();
        try{
            Optional<Asso> assoOpt = assoRepository.findById(id);
            if(assoOpt.isPresent())
                mapAssoDto.put(ASSO, mapper.toAssoDto(assoOpt.get()));
            else {
                logger.error("No association found!");
                throw new GalException("No association found!", ErrorEnum.RUNTIME_ERROR);
            }
        } catch( Exception e) {
            logger.error(e.getMessage(), e.getStackTrace());
            throw new GalException("Error on Association finding!", ErrorEnum.RUNTIME_ERROR);
        }
        return mapAssoDto;
    }

    public void deleteAssoById(long id) {}

    public Page<Asso> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
        return null;
    }
}
