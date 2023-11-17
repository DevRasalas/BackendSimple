package PPI.ComidaRapida.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {
    
        private String nombre;
        private String apellido;
        private String correo;
        private String password;
        private String rol;
        private Integer edad;
    
    
    
}
