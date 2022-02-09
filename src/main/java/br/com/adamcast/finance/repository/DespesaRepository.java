package br.com.adamcast.finance.repository;

import br.com.adamcast.finance.model.Despesa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DespesaRepository extends MongoRepository<Despesa, String> {
    List<Despesa> findByDescricaoContainingIgnoreCase(String descricao);

    Optional<Despesa> findByDescricaoIgnoreCase(String descricao);

}
