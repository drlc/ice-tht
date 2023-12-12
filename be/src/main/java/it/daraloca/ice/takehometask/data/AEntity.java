package it.daraloca.ice.takehometask.data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
public class AEntity implements Serializable {

    private static final long serialVersionUID = -7464065571558253163L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @ToString.Include
    @EqualsAndHashCode.Include
    private UUID id;

    @CreatedDate
    @Temporal(jakarta.persistence.TemporalType.TIMESTAMP)
    private Date createdDate;
    
}
