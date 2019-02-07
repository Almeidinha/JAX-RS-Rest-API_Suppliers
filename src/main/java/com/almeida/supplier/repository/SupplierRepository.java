package com.almeida.supplier.repository;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.almeida.supplier.entity.Supplier;

import java.util.List;

@Stateless
public class SupplierRepository { 
    @PersistenceContext(name = "SupplierPU")
    EntityManager manager;

    public List<Supplier> findAll() {
        return this.manager.createNamedQuery(Supplier.FIND_ALL).getResultList();
    }

    public Supplier findById(Long id) {
        return this.manager.find(Supplier.class, id);
    }

    public void create(Supplier guestBook) {
        this.manager.persist(guestBook);
    }

    public void remove(Supplier guestBook) {
        this.manager.remove(guestBook);
    }
    
    public void update(Supplier supplier) {
    	this.manager.persist(supplier);
    }
}
