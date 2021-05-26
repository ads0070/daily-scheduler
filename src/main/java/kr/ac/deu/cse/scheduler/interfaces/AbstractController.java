package kr.ac.deu.cse.scheduler.interfaces;

import org.springframework.http.ResponseEntity;

public abstract class AbstractController<R, ID> {

  public abstract ResponseEntity<?> create(R request);

  public abstract ResponseEntity<?> readAll();

  public abstract ResponseEntity<?> readOne(ID id);

  public abstract ResponseEntity<?> update(ID id, R request);

  public abstract ResponseEntity<?> delete(ID id);
}
