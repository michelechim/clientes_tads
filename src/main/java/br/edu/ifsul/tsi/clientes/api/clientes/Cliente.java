package br.edu.ifsul.tsi.clientes.api.clientes;

import lombok.Data;
//import org.modelmapper.ModelMapper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import java.math.BigDecimal;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String endereco;
    

    //public static Cliente create(ClienteDTO c){
    //    ModelMapper modelMapper = new ModelMapper();
    //    return modelMapper.map(c, Cliente.class);
    //}
}
