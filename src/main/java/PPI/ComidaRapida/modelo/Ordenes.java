package PPI.ComidaRapida.modelo;


import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Ordenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="num_gen", sequenceName="num_seq", allocationSize=30)
    Integer idOrden;
    @ManyToOne
    Usuarios usuarios;
    String direccion;
    Integer montoTotal;
    String fechaCompra = LocalDateTime.now().toString();
    
   
}
