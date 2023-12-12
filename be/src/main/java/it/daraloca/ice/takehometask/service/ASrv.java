package it.daraloca.ice.takehometask.service;

import it.daraloca.ice.takehometask.data.AEntity;

public abstract class ASrv {

    protected static boolean checkEntityExists(AEntity entity) {
        return entity.getCreatedDate() != null;
    }

    
}
