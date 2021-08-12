## Data generation
This package contains everything related to data generation, and the classes in this package and its subpackages are not used when running client or server but only when data generation is run(`runData`). `DataGenerators` class contains event-method that adds data providers to data generator. Each provider is responsible for generating certain type of resource(assets, data).
## `providers` package
This package contains all mod-defined providers that are registered in `DataGenerators` class on corresponding event.
## `api` package
This package contains interfaces that are implemented by different game objects in the `content` package. The convention is that interfaces in the package are named by pattern `I.+Source`. The interfaces contain methods named by pattern `generate.+` that are responsible for producing certain kind of resource. Mod providers take instances of the interfaces from the `Content` class and use them to create resources.

---
## NOTE!!!
Do not edit data in ${PROJECT_ROOT}/src/generated. Instead, edit a corresponding provider or a source interface implementor in `content` package to generate resource in desired way.