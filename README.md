N-GENES 2
=========

N-genes 2 is a java general purpose genetic algorithm framework. It is
free and open source.


Description
-----------

N-genes 2 features include:

* Modular architecture allowing to easily replace components (operators, workflow, fitness functions, etc.)
* Individual can have arbitrary gene types (Boolean, Integers, Strings or custom objects) and most operator works whatever the gene type is.
* The project uses extensive unit testing to assert code quality and avoid regressions between releases.
* All components exploit java generics to enforce type safety at compilation time.
* Builders using dependency injection allow to quickly configure and instanciate
 a GA simulation.

N-genes 2 is a complete rewrite of a preceding similar framework
called [n-genes](http://spc.unige.ch/tools/n-genes/).

### Dependencies

Although n-genes 2 relies on several thrid-party libraries, only java
and ant are required for installation (see below).

We are trying to make use of the excellent FOSS libraries available
for java each time we need features which are not directly related to
GA. The dependencies for the current release are:

* [slf4j](http://www.slf4j.org/): Logging facade api
* [Logback](http://logback.qos.ch/): Logger implementation
* [PicoContainer](http://www.picocontainer.org/): Dependency injection
* [XOM(http://www.xom.nu/): XML parsing
* [JUnit4](http://www.junit.org/): Unit testing
* [Mockito](http://mockito.org/): Mock objects for testing
* [Cobertura](http://cobertura.sourceforge.net/): Test coverage


Install
-------

To install, build and use n-genes 2, you need:

* *Java JDK*: The project was tested with SUN JDK 1.6 but any version since 1.5 should work.
* *Apache Ant*: At least version 1.6

First get a reopsitory copy, either by cloning the git repository:

//TODO: url

or by dowloading an snapshot //TODO: where

Then go inside the repository and type:

$ ant

This will first download all the needed dependencies in the project
directory using [Apache Ivy](http://ant.apache.org/ivy/) and then
build n-genes 2 and documentation. The first time you launch the
process, it can take several minutes depending of dowload speed.

Once everything is built, you can try to launch the test suite by
typing:

$ ant test

Results will be formatted in html and can be accessed by pointing your
web browser at 'reports/junit/index.html' and
'reports/junit/index.hmtl'.


Usage
-----

Look at the examples in 'src/ngenes2/examples/' folder.

Documentation
-------------

A full javadoc reference is generated during build in the 'doc/' folder.

License
-------

N-genes 2 is free and open source software licensed with the General Public License
version 3. Check the 'LICENSE.txt' file for details.