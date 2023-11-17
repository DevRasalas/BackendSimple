package PPI.ComidaRapida.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import PPI.ComidaRapida.modelo.UsuarioRequest;
import PPI.ComidaRapida.modelo.Usuarios;
import PPI.ComidaRapida.servicio.UsuarioServicios.UsuarioServicio;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("http://localhost:4200")
public class UsuarioController {
    @Autowired
    public UsuarioServicio usuarioServicio;
    public final static Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);
    @GetMapping("/login")
    public Usuarios login (@RequestParam("correo") String correo, @RequestParam("password") String password){
        return usuarioServicio.logInUsuario(correo, password);
    }
    @PostMapping("/registrar")
    public Usuarios registrar (
        @RequestBody UsuarioRequest usuarioRequest){
        Usuarios usuarios = new Usuarios();
            usuarios.setNombre(usuarioRequest.getNombre());
            usuarios.setApellido(usuarioRequest.getApellido());
            usuarios.setCorreo(usuarioRequest.getCorreo());
            usuarios.setPassword(usuarioRequest.getPassword());
            usuarios.setEdad(usuarioRequest.getEdad());
            usuarios.setRoles(usuarioRequest.getRol());
            LOGGER.info(usuarios.getNombre());
            LOGGER.info(usuarios.getApellido());
            LOGGER.info(usuarios.getCorreo());
            LOGGER.info(usuarios.getPassword());
            LOGGER.info(usuarios.getEdad().toString());
            LOGGER.info(usuarios.getRoles());
        return usuarioServicio.registrarUsuario(usuarios);
    }


}
