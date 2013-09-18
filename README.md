# slickfun #

Quick example project of using scala slick with scalatra. Obviously not the best idea to have db code living in your controllers but this is strictly for demo only :)

## Example of creating a user and then retrieving a list of all users ##

If you have httpie installed, you're gonna have a good time. Otherwise, curl your heart out.

Given the server is running...

```sh
$ http post http://localhost:8080/users first=chris last=james
$ http http://localhost:8080/users
```


## Build & Run ##

```sh
$ cd slickfun
$ ./sbt
> container:start
> browse
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.
