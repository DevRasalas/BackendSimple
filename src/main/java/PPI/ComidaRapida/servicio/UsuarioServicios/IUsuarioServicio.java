package PPI.ComidaRapida.servicio.UsuarioServicios;

import PPI.ComidaRapida.modelo.Usuarios;

public interface IUsuarioServicio {
    Usuarios logInUsuario(String correo, String password);
    Usuarios registrarUsuario(Usuarios usuario);
}
