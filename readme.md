# Ejemplos de Problemas de Planeación

Este repositorio contiene algunos ejemplos de problemas de
palneación junto con sus soluciones utilizando OptaPlanner y otras
metodologías sugeridas en la literatura.

Soluciones que no usan OptaPlanner utilizan python y algunas otras
dependencias listadas abajo.

## Dependencias en Java

Los principales requerimientos para ejecutar los proyectos maven
son el Java SE Development Kit versión 7 o superior
(preferiblemente de oracle), y maven versión 3.0.4 (en los
repositorios estándard de ubuntu). Depués de cumplir con estas
dependencias, dentro de cada carpeta de proyectos maven (carpetas
que contienen un archivo `pom.xml`), las pruebas pueden ser
ejecutadas mediante:

```sh
   mvn test
```

## Dependencias en Python

### Ubuntu 13.10

Los problemas de programación lineal son declarados y resueltos
usando la [librería PyGLPK](http://tfinley.net/software/pyglpk/discussion.html), el paquete incluido en ubuntu 13.10 no
es lo suficientemente reciente, por lo que se recomienda la
instalación con [pip](http://www.pip-installer.org/en/latest/) dentro de un virtual environment aunque no es
necesario. Las librerías para PyGLPK pueden ser obtenidas de los
repositorios de ubuntu mediante los comandos:

```sh
    aptitude install glpk libgmp-dev
```

Luego los paquetes de PyGLPK pueden ser instalados, de manera
opcional dentro de un [virtualenv](http://virtualenvwrapper.readthedocs.org/en/latest/), con:

```sh
    pip install glpk
```

Una vez instalados los paquetes anteriores, las soluciones en
python se pueden ejecutar mediante:

```sh
    python $(nombre_archivo_a_ejecutar).py
```


### Otras versiones

El paquete de python es el mismo pero las fuentes necesarias para
las librerías dependen del sistema, no se han realizado pruebas en
sistemas diferentes a Ubuntu, pero deberían funcionar.
