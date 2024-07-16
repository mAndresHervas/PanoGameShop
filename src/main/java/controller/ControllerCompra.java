package controller;

import DAO.Venta;
import model.CompraModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

public class ControllerCompra
{
    CompraModel cm = new CompraModel();
    public int id_generator()
    {
        return cm.asignarId();
    }
    public int idUsu(String nombre)
    {
        return cm.comprobarId(nombre);
    }
    public int idJue(String nombre)
    {
        return cm.comprobarIdJue(nombre);
    }
    public boolean contruirVenta(String nombreUsu, String nombreJue, int precio)
    {
        Venta venta = new Venta(nombreUsu, nombreJue, precio);
        if (cm.guardarVenta(venta))
        {
            incrementarPuntosCompra(nombreUsu, nombreJue);
            return true;
        }
        return false;
    }
    private void incrementarPuntosCompra(String nombreUsu, String nombreJuego)
    {
        ControllerJuego cj = new ControllerJuego();
        ControllerLogin cl = new ControllerLogin();
        int puntos = (int) Math.round(cj.precioJuego(nombreJuego) * 0.35);
        cl.incrementarPuntos(nombreUsu, puntos);

    }
    public boolean prepararTicket(String nombreUsu, String nombreJuego, int precio)
    {
        String nombreCarpeta = "panosgameshop"; // Nombre de la carpeta a crear

        // Obtener la ruta al escritorio del usuario actual
        String escritorio = "C:/Users/marca/Desktop";

        // Crear un objeto File que representa la carpeta en el escritorio
        File carpeta = new File(escritorio, nombreCarpeta);

        {
            // Intentar crear la carpeta
            if (carpeta.mkdirs())
            {
                if (escribirTicket(nombreUsu, nombreJuego, precio, carpeta))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                if (escribirTicket(nombreUsu, nombreJuego, precio, carpeta))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
    }
    public boolean escribirTicket(String nombreUsu, String nombreJuego, int precio, File carpeta)
    {
        int num = generarNum();
        File archivo = new File(carpeta,"Factura " + String.valueOf(num) + " --- " + LocalDate.now().toString() + " --- " + nombreUsu+".txt");

        // Escribir contenido en el archivo usando FileWriter
        try (FileWriter writer = new FileWriter(archivo))
        {
            // Escribir contenido en el archivo
            writer.write("PANOS GAME SHOP\n");
            writer.write("********************\n");
            writer.write("COMPRA REALIZADA EL DIA: " + LocalDate.now().toString() + " ADQUIRIENDO EL SIGUIENTE PRODUCTO: " + nombreJuego + "\n");
            writer.write("*******************\n");
            writer.write("NUMERO FACTURA: " + num + "\n");
            writer.write("************\n");
            writer.write("¡GRACIAS POR SU COMPRA!");

            System.out.println("Archivo creado y contenido escrito correctamente en: " + archivo.getAbsolutePath());

        } catch (IOException e)
        {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
            return false;
        }
        return true;
    }
    public static int generarNum()
    {
        Random rand = new Random();
        int num = rand.nextInt(100);
        int num2 = rand.nextInt(100);
        int num3 = rand.nextInt(1000);
        return (num * num2) + num3;
    }
}
