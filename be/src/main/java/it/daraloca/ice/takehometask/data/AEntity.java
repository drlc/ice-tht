package it.daraloca.ice.takehometask.data;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
// JPA
@MappedSuperclass
public class AEntity implements Serializable {

    private static final long serialVersionUID = -7464065571558253163L;

    @CreatedDate
    @Temporal(jakarta.persistence.TemporalType.TIMESTAMP)
    @ToString.Include
    private Date createdDate;

    @LastModifiedDate
    @Temporal(jakarta.persistence.TemporalType.TIMESTAMP)
    @ToString.Include
    private Date lastModifiedDate;
    
}
