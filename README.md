# slickfun #

Quick example project of using scala slick with scalatra. Obviously not the best idea to have db code living in your controllers but this is strictly for demo only :)

## Build & Run ##

```sh
$ cd slickfun
$ ./sbt
> container:start
```

## Creating a user and then retrieving a list of all users ##

If you have httpie (https://github.com/jkbr/httpie) installed, you're gonna have a good time. Otherwise, curl your heart out.

Given the server is running...

```sh
$ http post http://localhost:8080/users first=chris last=james
$ http http://localhost:8080/users
```

## Notes ##

The interesting code more or less all lives in src/main/scala/com/quii/slickfun/MyScalatraServlet.scala

