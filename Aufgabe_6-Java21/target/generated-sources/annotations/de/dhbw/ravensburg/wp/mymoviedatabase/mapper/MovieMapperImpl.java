package de.dhbw.ravensburg.wp.mymoviedatabase.mapper;

import de.dhbw.ravensburg.wp.mymoviedatabase.dto.AwardDTO;
import de.dhbw.ravensburg.wp.mymoviedatabase.dto.MovieDTO;
import de.dhbw.ravensburg.wp.mymoviedatabase.model.Award;
import de.dhbw.ravensburg.wp.mymoviedatabase.model.Cast;
import de.dhbw.ravensburg.wp.mymoviedatabase.model.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-30T23:59:49+0100",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240206-1609, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class MovieMapperImpl implements MovieMapper {

    @Override
    public MovieDTO movieToMovieDTO(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setCastIds( castListToLongList( movie.getInvolvedCast() ) );
        movieDTO.setAwards( awardListToAwardDTOList( movie.getAwards() ) );
        movieDTO.setCoverImgUrl( movie.getCoverImgUrl() );
        movieDTO.setDescription( movie.getDescription() );
        movieDTO.setDuration( movie.getDuration() );
        movieDTO.setId( movie.getId() );
        movieDTO.setImdbRating( movie.getImdbRating() );
        movieDTO.setTitle( movie.getTitle() );
        movieDTO.setTrailerUrl( movie.getTrailerUrl() );

        return movieDTO;
    }

    @Override
    public List<MovieDTO> moviesToMovieDTOs(List<Movie> movies) {
        if ( movies == null ) {
            return null;
        }

        List<MovieDTO> list = new ArrayList<MovieDTO>( movies.size() );
        for ( Movie movie : movies ) {
            list.add( movieToMovieDTO( movie ) );
        }

        return list;
    }

    @Override
    public Movie movieDTOToMovie(MovieDTO movieDTO) {
        if ( movieDTO == null ) {
            return null;
        }

        Movie movie = new Movie();

        movie.setAwards( awardDTOListToAwardList( movieDTO.getAwards() ) );
        movie.setCoverImgUrl( movieDTO.getCoverImgUrl() );
        movie.setDescription( movieDTO.getDescription() );
        movie.setDuration( movieDTO.getDuration() );
        if ( movieDTO.getId() != null ) {
            movie.setId( movieDTO.getId() );
        }
        movie.setImdbRating( movieDTO.getImdbRating() );
        movie.setTitle( movieDTO.getTitle() );
        movie.setTrailerUrl( movieDTO.getTrailerUrl() );

        return movie;
    }

    @Override
    public List<Movie> movieDTOsToMovies(List<MovieDTO> movieDTOs) {
        if ( movieDTOs == null ) {
            return null;
        }

        List<Movie> list = new ArrayList<Movie>( movieDTOs.size() );
        for ( MovieDTO movieDTO : movieDTOs ) {
            list.add( movieDTOToMovie( movieDTO ) );
        }

        return list;
    }

    protected List<Long> castListToLongList(List<Cast> list) {
        if ( list == null ) {
            return null;
        }

        List<Long> list1 = new ArrayList<Long>( list.size() );
        for ( Cast cast : list ) {
            list1.add( map( cast ) );
        }

        return list1;
    }

    protected AwardDTO awardToAwardDTO(Award award) {
        if ( award == null ) {
            return null;
        }

        AwardDTO awardDTO = new AwardDTO();

        awardDTO.setAcademy( award.getAcademy() );
        awardDTO.setCategory( award.getCategory() );
        awardDTO.setId( award.getId() );

        return awardDTO;
    }

    protected List<AwardDTO> awardListToAwardDTOList(List<Award> list) {
        if ( list == null ) {
            return null;
        }

        List<AwardDTO> list1 = new ArrayList<AwardDTO>( list.size() );
        for ( Award award : list ) {
            list1.add( awardToAwardDTO( award ) );
        }

        return list1;
    }

    protected Award awardDTOToAward(AwardDTO awardDTO) {
        if ( awardDTO == null ) {
            return null;
        }

        Award award = new Award();

        award.setAcademy( awardDTO.getAcademy() );
        award.setCategory( awardDTO.getCategory() );
        if ( awardDTO.getId() != null ) {
            award.setId( awardDTO.getId() );
        }

        return award;
    }

    protected List<Award> awardDTOListToAwardList(List<AwardDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Award> list1 = new ArrayList<Award>( list.size() );
        for ( AwardDTO awardDTO : list ) {
            list1.add( awardDTOToAward( awardDTO ) );
        }

        return list1;
    }
}
