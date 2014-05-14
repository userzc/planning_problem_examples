# Planning Problem Examples

This repository holds a few planning problems examples and its
solutions using OptaPlanner and other suggested methods in the
literature.

Solutions other than OptaPlanner use python and a few dependencies
listed below.

## Java dependencies

The main requirements to run the java maven projects are the java
se development kit version 7 or above (preferably from oracle), and
maven version 3.0.4 (in the standard repositories) are the main
requirements. After these dependencies are met, inside each of the
maven project's directory (directories containing the `pom.xml`
file), the tests can be run with:

```sh
   mvn test
```


## Python dependencies

### Ubuntu 13.10

The linear programming problems are declared and solved using the
[PyGLPK library](http://tfinley.net/software/pyglpk/discussion.html), the ubuntu package boundled in 13.10 is not recent
enough, installation with [pip](http://www.pip-installer.org/en/latest/) inside a virtual environment is
recommended but not mandatory. The required libraries for PyGLPK
can be obtained from the ubuntu repositories with the commands:

```sh
    aptitude install glpk libgmp-dev
```

Then the PyGLPK package can be installed, optionally inside a
virtualenv, with:

```sh
    pip install glpk
```


### Other Versions

The python package is the same but the sources for the needed
libraries are dependant on the system, no testing has been made on
systems other than ubuntu, but it should work.
