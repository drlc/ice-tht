package it.daraloca.ice.takehometask.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
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
public abstract class ADTO implements Serializable {

    @ToString.Include
    @EqualsAndHashCode.Include
    @Null(groups = {ValidationProfile.New.class})
    private UUID id;
    @ToString.Include
    private Date createdDate;
    
}
