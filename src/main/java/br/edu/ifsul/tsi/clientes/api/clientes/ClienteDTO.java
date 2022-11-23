package br.edu.ifsul.tsi.clientes.api.clientes;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Data
public class ClienteDTO {
    private Long id;
    private String nome;
    private String endereco;

    public static ClienteDTO create(Cliente c){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(c, ClienteDTO.class);
    }
}
