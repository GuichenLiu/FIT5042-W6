/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.calculator;

import javax.ejb.Remote;

/**
 *
 * @author Leo Liu
 */
@Remote
public interface ComparePropertySessionBeanRemote {
    
    public void addPropertyID(int propertyID);
    
    public void removePropertyID(int propertyID);
    
    public int bestPerRoom();

}
