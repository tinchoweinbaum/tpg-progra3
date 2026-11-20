package CapaDatos.bitacora;

import java.util.Date;

/**
 * Registro de bitácora especializado en documentar enfrentamientos o eventos de combate de la CapaDatos.nave.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de RegistroBitacora.</p>
 */
public class RegistroCombate extends RegistroBitacora{

    /**
     * Crea un registro de combate con la fecha actual del sistema y una descripción.
     *
     * @param descripcionRegistro Detalle del evento de combate.
     * @pre descripcionRegistro != null && !descripcionRegistro.trim().isEmpty()
     * @post Se crea el registro de combate con la fecha actual ajustada y la descripción especificada.
     */
    public RegistroCombate(String descripcionRegistro) {
        super(descripcionRegistro);
    }

    /**
     * Crea un registro de combate con una fecha específica y una descripción.
     *
     * @param fechaRegistro Fecha base del evento de combate.
     * @param descripcionRegistro Detalle del evento de combate.
     * @pre fechaRegistro != null && descripcionRegistro != null && !descripcionRegistro.trim().isEmpty()
     * @post Se crea el registro de combate con la fecha ajustada (+5000 ms) y la descripción especificada.
     */
    public RegistroCombate(Date fechaRegistro, String descripcionRegistro) {
        super(fechaRegistro, descripcionRegistro);
    }
}
