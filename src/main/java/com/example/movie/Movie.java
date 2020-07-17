package com.example.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Size(min = 2, max = 20, message = Consts.SIZE_ERROR)
    @NotBlank(message = Consts.BLNK_ERROR)
    String name;

    @Size(min = 3, max = 20, message = Consts.SIZE_ERROR)
    @NotBlank(message = Consts.BLNK_ERROR)
    String writerName;

    @Size(min = 5, max = 250, message = Consts.SIZE_ERROR)
    @NotBlank(message = Consts.BLNK_ERROR)
    String synopsis;
    String imgUrl;

    @Positive(message = Consts.POSI_ERROR)
    Integer releaseDate;
}
