package de.dhbw.ravensburg.wp.mymoviedatabase.service;

import de.dhbw.ravensburg.wp.mymoviedatabase.dto.AwardDTO;
import de.dhbw.ravensburg.wp.mymoviedatabase.model.Award;
import de.dhbw.ravensburg.wp.mymoviedatabase.repository.AwardRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {

    private AwardRepository awardRepository;

    public AwardServiceImpl (AwardRepository awardRepository){
        this.awardRepository = awardRepository;
    }

    @Override
    public AwardDTO addAward(AwardDTO new_award){
        Award tmp = new Award(new_award.getAcademy(), new_award.getCategory(), new_award.getCelebrationYear());
        this.awardRepository.save(tmp);
        return new AwardDTO(tmp.getId(), tmp.getAcademy(), tmp.getCategory(), tmp.getYear());
    }

    @Override
    public AwardDTO getAwardById(long id){
        Award tmp = this.awardRepository.findById(id).get();
        return new AwardDTO(tmp.getId(), tmp.getAcademy(), tmp.getCategory(), tmp.getYear());
    }

    @Override
    public List<AwardDTO> getAllAwards(){
        List<Award> tmp = this.awardRepository.findAll();
        List<AwardDTO> awards = new ArrayList<>();
        for(Award award:tmp){
            awards.add(new AwardDTO(award.getId(), award.getAcademy(), award.getCategory(), award.getYear()));
        }
        return awards;
    }

    @Override
    public AwardDTO updateAward(AwardDTO updated_award){
        if(awardRepository.existsById(updated_award.getId())){
            Award tmp = new Award(updated_award.getAcademy(), updated_award.getCategory(), updated_award.getCelebrationYear());
            this.awardRepository.save(tmp);
            return new AwardDTO(tmp.getId(), tmp.getAcademy(), tmp.getCategory(), tmp.getYear());
        }else{
            throw new EntityNotFoundException("MovieId does not exist");
        }
    }

    @Override
    public void removeAwardById(long id){
        this.awardRepository.deleteById(id);
    }


}
