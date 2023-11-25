package RegistroPersona;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class RegistroPersona {

    public static void main(String[] args) {
        // ArrayList para almacenar datos de personas registradas
        ArrayList<String[]> personasRegistradas = new ArrayList<>();

        while (true) {
            String[] botones = {"Nueva persona", "Ver personas registradas", "Salir"};
            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione una opción:",
                    "Registro de Personas",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    botones,
                    botones[0]);

            switch (opcion) {
                case 0:
                    nuevaPersona(personasRegistradas);
                    break;
                case 1:
                    listaPersonas(personasRegistradas);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Saliendo de la aplicación.");
                    System.exit(0);
                    break;
            }
        }
    }

    private static void nuevaPersona(ArrayList<String[]> personasRegistradas) {

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido:");

        // Validaciones para que los campos de nombre y apellido no estén en blanco
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar en blanco. Registro cancelado.");
            return;
        } else if (apellido.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El apellido no puede estar en blanco. Registro cancelado.");
            return;
        }
        
        //Solicitar el dni y validar que tenga 8 digitos
        String dni = JOptionPane.showInputDialog("Ingresa el DNI (8 dígitos):");
        if (!dni.matches("\\d{8}")) { 
            JOptionPane.showMessageDialog(null, "El DNI debe tener 8 dígitos. Registro cancelado.");
            return;
        }
        
        //Solicitar fecha de nacimiento indicando el formato esperado
        String formatoFecha = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);

        Scanner scanner = new Scanner(System.in);
        String fechaNacimiento = JOptionPane.showInputDialog("Ingrese una fecha en formato " + formatoFecha + ":");

        try {
            Date fecha = sdf.parse(fechaNacimiento);
            
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "La fecha ingresada no tiene el formato correcto. Registro Cancelado");
            return;
        }

        // Guarda los datos en una lista
        String[] persona = {nombre, apellido, dni, fechaNacimiento};
        personasRegistradas.add(persona);

        JOptionPane.showMessageDialog(null, "Persona registrada exitosamente.");
    }

    private static void listaPersonas(ArrayList<String[]> personasRegistradas) {
        if (personasRegistradas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay personas registradas.");
        } else {
            StringBuilder mensaje = new StringBuilder("Personas registradas:\n");
            for (String[] persona : personasRegistradas) {
                mensaje.append("Nombre: ").append(persona[0]).append(", Apellido: ").append(persona[1])
                        .append(", DNI: ").append(persona[2]).append(", Fecha de Nacimiento: ").append(persona[3])
                        .append("\n");
            }
            // Mostrar el mensaje con la lista de personas
            JOptionPane.showMessageDialog(null, mensaje.toString());
        }
    }
    
}
