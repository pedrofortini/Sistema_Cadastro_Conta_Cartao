package com.analise.fraude.module.application.util;

import com.analise.fraude.module.application.MessageConstants;
import com.analise.fraude.module.application.exception.DataFormatoInvalidoException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date stringToDate(String dateString){

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {

            Date date = formatter.parse(dateString);
            return date;

        } catch (ParseException e) {

            throw new DataFormatoInvalidoException(
                    String.format(MessageConstants.MENSAGEM_DATA_FORMATO_INVALIDO, dateString));
        }
        catch (Exception e) {

            throw new DataFormatoInvalidoException(
                    String.format(MessageConstants.MENSAGEM_DATA_FORMATO_INVALIDO, dateString));
        }
    }

    public static String dateToString(Date date){

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        String dateString = formatter.format(date);
        return dateString;
    }

    public static Date obtemDataSemanaAnterior(Date date){

        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.add(Calendar.DATE, -7);

        return cal.getTime();
    }

}
