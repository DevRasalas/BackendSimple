package PPI.ComidaRapida.servicio.OrdenesServicios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PPI.ComidaRapida.modelo.Ordenes;
import PPI.ComidaRapida.modelo.Producto;
import PPI.ComidaRapida.modelo.Producto_Ordenes;
import PPI.ComidaRapida.modelo.Usuarios;
import PPI.ComidaRapida.repositorio.OrdenesRepositorio;
import PPI.ComidaRapida.repositorio.ProductoRepositorio;
import PPI.ComidaRapida.repositorio.Producto_OrdenRepository;
import PPI.ComidaRapida.repositorio.UsuariosRepositorio;
import jakarta.transaction.Transactional;

@Service
public class OrdenesServicio implements IOrdenesServicio {

    @Autowired
    private OrdenesRepositorio ordenesRepository;
    @Autowired
    private Producto_OrdenRepository producto_OrdenRepository;
    @Autowired
    private ProductoRepositorio productoRepository;
    @Autowired 
    private UsuariosRepositorio usuariosRepositorio;
    public final static Logger LOGGER = LoggerFactory.getLogger(OrdenesServicio.class);

    @Override
    @Transactional
    public void crearOrdenConProducto(Map<String, Object> ordenes) {
        //Extraemos los elementos "orden" del json

        Map<String, Object> ordenData = (Map<String, Object>) ordenes.get("orden");
        Ordenes nuevaOrden = new Ordenes();
        Usuarios usuarios = this.usuariosRepositorio.findById(Integer.valueOf(ordenData.get("idUsuario").toString())).orElse(null);
        nuevaOrden.setUsuarios(usuarios);
        nuevaOrden.setDireccion(ordenData.get("direccion").toString());
        nuevaOrden.setMontoTotal(Integer.valueOf(ordenData.get("montoTotal").toString()));
        ordenesRepository.save(nuevaOrden);

        // Extraemos los elementos "productos" del json
        List<Map<String, Object>> productosData = (List<Map<String, Object>>) ordenes.get("productos");
        if (productosData != null) {
            
            for (Map<String, Object> productoData : productosData) {
                Integer idProducto = Integer.valueOf(productoData.get("idProducto").toString());
                Producto producto = productoRepository.findById(idProducto).orElse(null);
                Integer cantidadProd = Integer.valueOf(productoData.get("cantidad").toString());
                //Guardamos todo en la tabla intermedia
                if (producto != null) {
                    Producto_Ordenes productoOrden = new Producto_Ordenes();
                    productoOrden.setOrdenes(nuevaOrden);
                    productoOrden.setProducto(producto);
                    productoOrden.setCantidad(cantidadProd);
                    productoOrden.setPrecioFinal(producto.getPrecioProducto() * cantidadProd);
                    producto_OrdenRepository.save(productoOrden);
                }
            }
        }

    }



    @Override
    public List<Ordenes> mostrarOrdenes(Integer idUsuario) {
        return this.ordenesRepository.obtenerOrdenes(idUsuario);
        
    }



    @Override
    public List<Object[]> mostrarProducto_Ordenes(Integer idOrden) {
        return this.ordenesRepository.obtenerDatosDeOrden(idOrden);
    }

}
