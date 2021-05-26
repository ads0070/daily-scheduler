package kr.ac.deu.cse.scheduler.user.domain;

public interface UserDtoAdapter<T, R> {

  R getEntity(T user);
}
