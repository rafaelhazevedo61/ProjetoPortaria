/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rafael
 */
public class Data {
    
    public static java.sql.Date ConvertDataFormParaBanco(java.util.Date data) {
	return new java.sql.Date(data.getTime());

    }

    public static String Format(Date data) {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	String dataString = sdf.format(data);

	return dataString;

    }

}
