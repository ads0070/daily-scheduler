package kr.ac.deu.cse.scheduler.user.domain;

public interface UserDtoAdapter<T, R> {

  public R getEntity(T user);
}
