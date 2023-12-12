package it.daraloca.ice.takehometask.dto;

import java.util.HashSet;
import java.util.Set;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DetailedSongDTO extends SongDTO{

    private static final long serialVersionUID = 49879427543748L;

    @NotNull
    private String artistName;
    private String albumName;
    @Builder.Default
    private Set<String> genreNames = new HashSet<>(0);
    
}

