package de.dhbw.ravensburg.wp.mymoviedatabase.mapper;

import de.dhbw.ravensburg.wp.mymoviedatabase.dto.AwardDTO;
import de.dhbw.ravensburg.wp.mymoviedatabase.model.Award;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-30T23:25:21+0100",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240206-1609, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class AwardMapperImpl implements AwardMapper {

    @Override
    public AwardDTO awardToAwardDTO(Award award) {
        if ( award == null ) {
            return null;
        }

        AwardDTO awardDTO = new AwardDTO();

        awardDTO.setCelebrationYear( award.getYear() );
        awardDTO.setAcademy( award.getAcademy() );
        awardDTO.setCategory( award.getCategory() );
        awardDTO.setId( award.getId() );

        return awardDTO;
    }

    @Override
    public List<AwardDTO> awardsToAwardsDTO(List<Award> awards) {
        if ( awards == null ) {
            return null;
        }

        List<AwardDTO> list = new ArrayList<AwardDTO>( awards.size() );
        for ( Award award : awards ) {
            list.add( awardToAwardDTO( award ) );
        }

        return list;
    }

    @Override
    public Award awardDTOToAward(AwardDTO awardDTO) {
        if ( awardDTO == null ) {
            return null;
        }

        Award award = new Award();

        award.setYear( awardDTO.getCelebrationYear() );
        award.setAcademy( awardDTO.getAcademy() );
        award.setCategory( awardDTO.getCategory() );
        if ( awardDTO.getId() != null ) {
            award.setId( awardDTO.getId() );
        }

        return award;
    }

    @Override
    public List<Award> awardDTOsToAwards(List<AwardDTO> awardDTOList) {
        if ( awardDTOList == null ) {
            return null;
        }

        List<Award> list = new ArrayList<Award>( awardDTOList.size() );
        for ( AwardDTO awardDTO : awardDTOList ) {
            list.add( awardDTOToAward( awardDTO ) );
        }

        return list;
    }
}
