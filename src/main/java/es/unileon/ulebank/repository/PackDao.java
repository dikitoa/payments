package es.unileon.ulebank.repository;

import es.unileon.ulebank.domain.Pack;

public interface PackDao {
    
    public void persist(Pack transientInstance);
    
    public void remove(Pack persistentInstance);
    
    public Pack merge(Pack detachedInstance);
    
    public Pack findById(Integer id);

}
