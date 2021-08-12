## Subfolders
Every subfolder here represents an aspect of modding. For example the `blocks` folder contain all classes related to blocks, `recipes` contains everything related to recipes and so on. 

## Base classes
Within the folders you will find classes named by pattern `Mod.+`(e.g. `ModBlock`, `ModItem`, `ModTileEntity`, ...). These represent base classes for most of other classes in their subfolder. 

## Content class
The `Content` class contains fields with instances of all game objects such as blocks, items, recipes, multiblock creators and nearly everything there is default Forge registry for. Below the fields there are methods that automatically register the game objects using reflection, the `RegistryHelper` class contains useful static methods that help to achieve this. The `additionalGameObjects` field is a list that contains additional objects to register automatically. It is mostly used by blocks that place their items there.    