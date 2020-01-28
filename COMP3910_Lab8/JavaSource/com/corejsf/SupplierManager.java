package com.corejsf;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.corejsf.Supplier;

@Dependent
@Stateless
public class SupplierManager implements Serializable {
    private static final long serialVersionUID = 1L; 
    @PersistenceContext(unitName = "supplier-table")
    EntityManager entMan;
    
    public Supplier find(int id) {
    	return entMan.find(Supplier.class, id);
    }
    
    public void persist(Supplier sup) {
    	entMan.persist(sup);
    }
    
    public void merge(Supplier sup) {
    	entMan.merge(sup);
    }
    
    public void remove(Supplier sup) {
    	sup = find(sup.getSupID());
    	entMan.remove(sup);
    }
    
    public Supplier[] getAll() {
    	TypedQuery<Supplier> query = entMan.createQuery("select s from Supplier s", Supplier.class);
    	List<Supplier> suppliers = query.getResultList();
    	Supplier[] supArray = new Supplier[suppliers.size()];
    	for(int i = 0; i < supArray.length; i++) {
    		supArray[i] = suppliers.get(i);
    	}
    	return supArray;
    }
    
    public Supplier findSupplierByName(String name) {
        TypedQuery<Supplier> query = entMan.createQuery("select s from Supplier s", Supplier.class);
        List<Supplier> suppliers = query.getResultList();
        for (Supplier s : suppliers)
            if (s.getSupName().equals(name))
                return s;
        return null;
    }
}
