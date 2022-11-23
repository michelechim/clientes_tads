package br.edu.ifsul.tsi.clientes.api.clientes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cliente")
public class ClienteController {
    @GetMapping
    public ResponseEntity<String> selectAll() {
        return ResponseEntity.ok("selectAll");
    }

    @GetMapping("{id}")
    public ResponseEntity<String> selectById(@PathVariable("id") Long id) {
        return ResponseEntity.ok("selectById " + id);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<String> selectByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok("selectByNome " + nome);
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody String cliente){
        return ResponseEntity.ok("insert\n " + cliente);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody String cliente){
        return
                ResponseEntity.ok("update\n" + cliente);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok("delete " + id);
    }

}
