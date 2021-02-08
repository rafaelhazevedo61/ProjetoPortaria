/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Rafael
 */
public class Mascaras {
  
    public static void formataCPF(JFormattedTextField campo){
               
        try {
            MaskFormatter mascara = new MaskFormatter("###.###.###-##");
            mascara.install(campo);
            
        } catch (ParseException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao formatar cpf!");
        }
        
    }
    
    public static void formataData(JFormattedTextField campo){
               
        try {
            MaskFormatter mascara = new MaskFormatter("##/##/####");
            mascara.install(campo);
            
        } catch (ParseException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao formatar data!");
        }
        
    }
    
    public static void formataTelefoneCelular(JFormattedTextField campo){
               
        try {
            MaskFormatter mascara = new MaskFormatter("(##)#####-####");
            mascara.install(campo);
            
        } catch (ParseException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao formatar celular!");
        }
        
    }
    
    public static void formataTelefone(JFormattedTextField campo){
               
        try {
            MaskFormatter mascara = new MaskFormatter("(##)####-####");
            mascara.install(campo);
            
        } catch (ParseException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao formatar telefone!");
        }
        
    }
    
    public static void formataCEP(JFormattedTextField campo){
               
        try {
            MaskFormatter mascara = new MaskFormatter("##.###-###");
            mascara.install(campo);
            
        } catch (ParseException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao formatar cep!");
        }
        
    }
    
}
