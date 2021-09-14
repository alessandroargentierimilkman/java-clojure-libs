# Example of Java libs

In this example there are some static functions that can be imported in the Clojure code.

## How to import

After having built the project with Maven through:

```bash
mvn clean install
```

The `.m2` folder in your home directory will contain the library.

Go to the Clojure project, in the `project.clj` file (because it is managed by Leiningen and not through Maven directly) and add the dependency as follows:

```
 :dependencies [
       [com.milkman.libs/milkman-java-libs "1.0"]
 ]
```
Build the project with the Leiningen plugin.

Then in the code import the library and use it:

```perl
(ns namespace.name.here
   ...
   (:import (com.milkman.core Utils))
)

(defn lets-use-java-libs []
  (Utils/concat "ciao" "a tutti")
  (Utils/getType (new Object))
  (Utils/getType {:a "b", :c "d"})
  (Utils/getMapKeywordAsString {:a "b", :c "d"})
  (Utils/getDriverInformation {:id "6d15f646-7c3c-4ce5-b8e9-842fa0f1f257", :name "Francesco" :surname "Foresti"})
)
```

## How to connect to datomic

You can use this library to perform queries and transactions to datomic database.
If you use this example application as a standalone application, you can just launch the _local-env_ and, once datomic DB is available locally, you can run the _main_ method passing the env var DB_URI.
You can pass a DB_URI using the _free_ protocol (in place of the _dev_) because in the _pom.xml_ there is the _com.datomic.datomic-free_ dependency.
If you import this project (through Leiningen or Maven) as a maven dependency, you can choose to inject the database connection from outside this way:

```perl
(ns namespace.name.here
   ...
   (:import (com.milkman.core.factories DriverRepoFactory)
)

(defn fetch-drivers-by-territory [territoryId]
  (let [repo (DriverRepoFactory/inject api.database/conn-db)
        driverInfo (. repo fetchDriverInfoByTerritoryId territoryId)]
    driverInfo
    )
  )
```

## How to organize code with this library

This library is meant to be an **intermediate step** to pass from Clojure to Java.

So please, pay attention to separate the Java business logic (services mostly), and the Java entities from the Clojure-Java conversion logic.

You can use _Factory_ classes to instantiate Java services and repos, and you can use _Mapper_ classes to convert from Clojure objects to Java ones and vice versa.
It's also recommendable to keep some _Utils_ static methods to implement some other Clojure->Java and Java->Clojure specific logic.
This allows the developer to delete _Factories_ and _Mappers_ and to use directly the Java business logic and objects as soon as all the project is converted to Java.

Implement the Single Responsibility Principle, **do not mix things, please!**

## Other useful resources:
* https://github.com/Datomic/datomic-java-examples/tree/master/src/java/datomic/samples
* https://manuelp.github.io/blog/2013/04/21/clojure-ds-interoperability.html

```Lists
Class: clojure.lang.PersistentList, 
interfaces: clojure.lang.ISeq, clojure.lang.Sequential, java.util.List, java.io.Serializable, clojure.lang.IHashEq
SuperClass: clojure.lang.ASeq, interfaces: clojure.lang.IObj, java.io.Serializable
SuperClass: clojure.lang.Obj
SuperClass: java.lang.Object
Vectors
Class: clojure.lang.PersistentVector, 
interfaces: clojure.lang.IPersistentVector, java.lang.Iterable, java.util.List, java.util.RandomAccess, java.lang.Comparable, java.io.Serializable, clojure.lang.IHashEq
SuperClass: clojure.lang.APersistentVector, interfaces: clojure.lang.IFn
SuperClass: clojure.lang.AFn
SuperClass: java.lang.Object
Maps
Class: clojure.lang.PersistentArrayMap, 
interfaces: clojure.lang.IPersistentMap, java.util.Map, java.lang.Iterable, java.io.Serializable, clojure.lang.MapEquivalence, clojure.lang.IHashEq
SuperClass: clojure.lang.APersistentMap, interfaces: clojure.lang.IFn
SuperClass: clojure.lang.AFn
SuperClass: java.lang.Object
Sets
Class: clojure.lang.PersistentHashSet, 
interfaces: clojure.lang.IPersistentSet, java.util.Collection, java.util.Set, java.io.Serializable, clojure.lang.IHashEq
SuperClass: clojure.lang.APersistentSet, interfaces: clojure.lang.IFn
SuperClass: clojure.lang.AFn
SuperClass: java.lang.Object```