package de.dhbw.ravensburg.wp.mymoviedatabase.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class AwardDTO {
    private Long id;
    @NotBlank
    private String academy;
    private String category;
    @Min(0)
    @Max(2024)
    private int celebrationYear;

    public AwardDTO(Long id, String academy, String category, int celebrationYear){
        this.id = id;
        this.academy = academy;
        this.category = category;
        this.celebrationYear = celebrationYear;
    }

}
