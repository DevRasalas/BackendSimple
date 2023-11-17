package PPI.ComidaRapida.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import PPI.ComidaRapida.modelo.Usuarios;

@Repository
public interface UsuariosRepositorio extends JpaRepository<Usuarios, Integer> {
    @Query(value = "select * from Usuarios where correo = :correo and password= :password", nativeQuery = true)
    public Usuarios logInUsuario(@Param("correo") String correo, @Param("password") String password);

}
