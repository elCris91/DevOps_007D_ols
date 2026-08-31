package Anfri.Resenas.Service;

import Anfri.Resenas.DTO.ResenasRequest;
import Anfri.Resenas.Model.ResenasModel;
import Anfri.Resenas.Repository.ResenasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResenasService {

    @Autowired
    private ResenasRepository resenasRepository;

    public List<ResenasModel> listarTodas() {
        return resenasRepository.findAll();
    }

    public List<ResenasModel> listarPorPedido(Integer pedidoId) {
        return resenasRepository.findByPedidoId(pedidoId);
    }

    public ResenasModel crearResena(ResenasRequest request) {
        ResenasModel resena = new ResenasModel();
        resena.setClienteId(request.getClienteId());
        resena.setPedidoId(request.getPedidoId());
        resena.setCalificacion(request.getCalificacion());
        resena.setComentario(request.getComentario());
        resena.setFechaResena(LocalDateTime.now());

        return resenasRepository.save(resena);
    }
}