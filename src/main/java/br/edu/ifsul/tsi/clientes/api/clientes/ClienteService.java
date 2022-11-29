package br.edu.ifsul.tsi.clientes.api.clientes;

import br.edu.ifsul.tsi.clientes.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository rep;

    public List<ClienteDTO> getClientes() {
        return rep.findAll().stream().map(ClienteDTO::create).collect(Collectors.toList());
    }
    public ClienteDTO getClienteById(Long id) {
        Optional<Cliente> cliente = rep.findById(id);
        return cliente.map(ClienteDTO::create).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
    }
    public List<ClienteDTO> getClienteByNome(String nome) {
        return rep.findByNome(nome).stream().map(ClienteDTO::create).collect(Collectors.toList());
    }
    public ClienteDTO insert(Cliente cliente) {
        Assert.isNull(cliente.getId(),"Não foi possível inserir o registro");
        return ClienteDTO.create(rep.save(cliente));
    }
    public ClienteDTO update(Cliente cliente, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o cliente no banco de dados
        Optional<Cliente> optional = rep.findById(id);
        if(optional.isPresent()) {
            Cliente db = optional.get();
            // Copiar as propriedades
            db.setNome(cliente.getNome());
            db.setEndereco(cliente.getEndereco());
            System.out.println("Cliente id " + db.getId());

            // Atualiza o cliente
            rep.save(db);
            return ClienteDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }
    public void delete(Long id) {
        rep.deleteById(id);
    }
}

/*
package br.edu.ifsul.tsi.clientes.api.clientes;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository rep;

    public List<ClienteDTO> getClientes() {
        return rep.findAll().stream().map(ClienteDTO::create).collect(Collectors.toList());
    }
    public ClienteDTO getClientesById(Long id) {
        Optional<Cliente> cliente = rep.findById(id);
        return cliente.map(ClienteDTO::create).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
    }
    public List<ClienteDTO> getClienteByNome(String nome) {
        return rep.findByNome(nome).stream().map(ClienteDTO::create).collect(Collectors.toList());
    }
    public ClienteDTO insert(Cliente cliente) {
        Assert.isNull(cliente.getId(),"Não foi possível inserir o registro");
        return ClienteDTO.create(rep.save(cliente));

        // Busca o cliente no banco de dados
        Optional<Cliente> optional = rep.findById(id);
        if(optional.isPresent()) {
            Cliente db = optional.get();
            // Copiar as propriedades
            db.setNome(cliente.getNome());
            db.setTelefone(cliente.getTelefone());
            db.setEndereco(cliente.getEndereco());
            System.out.println("Cliente id " + db.getId());

            // Atualiza o cliente
            rep.save(db);
            return ClienteDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }
    public void delete(Long id) {
        rep.deleteById(id);
    }
}
*/
