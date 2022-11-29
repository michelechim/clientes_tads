package br.edu.ifsul.tsi.clientes.api.clientes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/clientes")
@Api(value  = "Clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping
    @ApiOperation(value = "Retorna todos os clientes cadastrados.")
    public ResponseEntity<List<ClienteDTO>> selectAll() {
        List<ClienteDTO> clientes = service.getClientes();
        return ResponseEntity.ok(clientes);
    }
    /*public ResponseEntity<String> selectAll() {
        return ResponseEntity.ok("selectAll");
    }*/
    @GetMapping("{id}")
    @ApiOperation(value = "Retorna um produto pelo campo identificador.")
    public ResponseEntity<ClienteDTO> selectById(@PathVariable("id") Long id) {
        ClienteDTO cliente = service.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }
    /*public ResponseEntity<String> selectById(@PathVariable("id") Long id) {
        return ResponseEntity.ok("selectById " + id);
    }*/
    @GetMapping("/nome/{nome}")
    @ApiOperation(value = "Retorna uma lista de produtos pela chave nome.")
    public ResponseEntity<List<ClienteDTO>> selectByNome(@PathVariable("nome") String nome) {
        List<ClienteDTO> clientes = service.getClienteByNome(nome);
        return clientes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(clientes);
    }
    /*public ResponseEntity<String> selectByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok("selectByNome " + nome);
    }*/

    @PostMapping
    @Secured({"ROLE _ADMIN"})
    @ApiOperation(value = "Insere um novo cliente.")
    public ResponseEntity<String> insert(@RequestBody Cliente cliente){
        ClienteDTO c = service.insert(cliente);
        URI location = getUri(c.getId());
        return ResponseEntity.created(location).build();
    }
    /*public ResponseEntity<String> insert(@RequestBody String cliente){
        return ResponseEntity.ok("insert\n " + cliente);
    }*/
    @PutMapping("{id}")
    @ApiOperation(value = "Altera cliente existente.")
    public ResponseEntity<ClienteDTO> update(@PathVariable("id") Long id, @RequestBody Cliente cliente){
        cliente.setId(id);
        ClienteDTO c = service.update(cliente, id);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }
    /*public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody String cliente){
        return
                ResponseEntity.ok("update\n" + cliente);
    }*/
    @DeleteMapping("{id}")
    @ApiOperation(value = "Deleta um cliente.")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    /*public ResponseEntity<String> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok("delete " + id);
    }*/

    //utilitário
    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

}