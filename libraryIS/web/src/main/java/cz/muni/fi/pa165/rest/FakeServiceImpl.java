/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.rest;

/**
 *
 * @author MiškoHu
 */
public class FakeServiceImpl implements FakeService {

    @Override
    public String hello() {
        return "Ahoj";
    }
    
}
