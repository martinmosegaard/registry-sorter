# registry-sorter
Sorts lines of text based on indentation.

## Usage

```sh
$ ./gradlew installDist
$ build/install/registry-sorter/bin/registry-sorter
```

## Test

```sh
$ ./gradlew cobertura
$ open build/reports/tests/index.html
$ open build/reports/cobertura/index.html
```

## Check

```sh
$ ./gradlew check
$ open build/reports/codenarc/main.html
$ open build/reports/codenarc/test.html
```

## CI

https://travis-ci.org/martinmosegaard/registry-sorter
