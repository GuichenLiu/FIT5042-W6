/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.calculator;

import fit5042.tutex.repository.entities.Property;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.CreateException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * 
 * @author Leo Liu
 */
@Stateful 
// Stateless - if another client (e.g. another instance of the bean) is trying to access the bean , the container may give the existing instance to that client. 
  //So the state can be modified by the new client. In other words, no separate bean instances for each client.
//Stateful, there will be separate bean instance for each client.
public class ComparePropertySessionBean implements ComparePropertySessionBeanRemote {

    ArrayList<Integer> list;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addPropertyID(int propertyID) {
        list.add(propertyID);
    }

    @Override
    public void removePropertyID(int propertyID) {
        list.remove(new Integer(propertyID));
    }    

    @Override
    public int bestPerRoom() {
        Integer bestPropertyID=0;
        Property property;
        int numberOfRooms;
        double bestPrice;
        double bestPerRoom=9999.99;
        for(Integer propertyID : list)
        {
            property=entityManager.find(Property.class, propertyID);
            numberOfRooms=property.getNumberOfBedrooms();
            bestPrice=property.getPrice();
            if(bestPrice/numberOfRooms<bestPerRoom)
            {
                bestPerRoom=bestPrice/numberOfRooms;
                bestPropertyID=property.getPropertyId();
            }
        }
        return bestPropertyID;
    }
    
    @PostConstruct
    public void init() {
        list=new ArrayList<>();
    }

}
