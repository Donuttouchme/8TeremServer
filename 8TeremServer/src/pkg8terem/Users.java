/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8terem;

import java.io.IOException;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author polgar
 */

public interface Users
{  
    Users Registration(String username)throws IOException;
    Users Login();
}