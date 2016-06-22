# Mizar

A very, *very* simple compiler, from a statically-typed, Pascal like language to
C.

## Usage

```bash
$ cat examples/hello.mz
int main() begin
  return puts("Hello, world!")
end

$ lein run examples/hello.mz > hello.c

$ gcc hello.c -o hello

$ ./hello
Hello, world!
```

## License

Copyright © 2016 Fernando Borretti

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
