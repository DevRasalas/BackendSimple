package PPI.ComidaRapida.servicio.UsuarioServicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PPI.ComidaRapida.modelo.Usuarios;
import PPI.ComidaRapida.repositorio.UsuariosRepositorio;
@Service
public class UsuarioServicio implements IUsuarioServicio{

    @Autowired
    UsuariosRepositorio usuariosRepositorio ;
    @Override
    public Usuarios logInUsuario(String correo, String password) {
        Usuarios usuario = this.usuariosRepositorio.logInUsuario(correo, password);
        if (usuario != null){
            return usuario;
        }
        return null;
    }

    @Override
    public Usuarios registrarUsuario(Usuarios usuario) {
        return this.usuariosRepositorio.save(usuario);
    }
    
}
