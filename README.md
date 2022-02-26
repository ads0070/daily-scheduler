# 2021년도 3학년 1학기 소프트웨어설계공학
<h2 align=left>1분반 2조 데일리 스케줄러 - 백엔드</h2>
디자인패턴을 적용한 소프트웨어설계

## Table of Contents

- [Project Structure](#project-structure)
- [Contributing](#contributing)
  - [Coding Conventions](#coding-conventions)

## Project Structure

```shell
.
+-- src/
    +-- main/
    |   +-- .../scheduler/
    |       +-- user/
    |           +-- application/
    |           +-- domain/
    |           +-- infrastructure/
    |           +-- presentation/
    +-- test/
        +-- ...
```

DDD(Domain Driven Design)를 바탕으로 하는 레이어드 아키텍처(Layered Architecture)로 구성되어 있습니다.

- Presentation Layer
  - Application의 입출력을 담당하는 Controller
- Application Layer
  - Use Cases(Business Logic)를 구현하는 Service
- Domain Layer
  - Application을 통해 해결하고자 하는 Domain Model
    - cf. Domain과 Infrastructure 영역 사이의 의존 관계를 역전하는 Repository Interface도 Domain Layer에서 구현해야 하나, 개발 편의를 위해 Spring Framework와 결합도를 높이는 형태로 진행함에 따라 별도로 Repository Interface를 구현하지는 않습니다.
- Infrastructure Layer
  - 다른 계층을 위해 사용하는 기반 기술
    - e.g. ORM을 구현하는 Spring JpaRepository 등

## Contributing

### Coding Conventions

[Google Java Style](https://google.github.io/styleguide/javaguide.html)을 적용합니다. [Google Java Format](https://github.com/google/google-java-format) 등의 도구를 사용해서 개발 중 코드 스타일을 일관적으로 유지하기를 권장합니다.
