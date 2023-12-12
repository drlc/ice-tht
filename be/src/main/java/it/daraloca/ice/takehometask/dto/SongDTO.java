package it.daraloca.ice.takehometask.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
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
public class SongDTO extends ADTO{

    private static final long serialVersionUID = 5985743874953897L;

    @NotNull
    @ToString.Include
    @NotBlank
    private String name;
    @NotNull
    @ToString.Include
    private Integer year;
    @NotNull
    private Integer length;

    @NotNull
    private UUID userId;
    @NotNull
    private UUID artistId;
    private UUID albumId;
    @Builder.Default
    private Set<UUID> genreIds = new HashSet<>(0);
    
}
