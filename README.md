# Room Planner

A Java Swing application that draws top-down furniture outlines on a shared canvas.

This repository contains the room planner and the `Raumplanner-Mod` Gradle subproject.
Open this repository root in IntelliJ IDEA to load both projects.

- `./gradlew :run`: run the chair demo.
- `./gradlew :Raumplanner-Mod:run`: build and run the room planner with the mod.
- `./gradlew build`: build both projects.

The mod entry point is
`Raumplanner-Mod/src/main/java/cho/info/roomplannermod/ModEntryPoint.java`.
See [the mod README](Raumplanner-Mod/README.md) for details.

## Source layout

- `cho.info.Main`: application entry point and chair demo.
- `cho.info.furniture`: the shared `Furniture` model and `Chair`, `Table`, `Piano`, `Cabinet`, `WallUnit`, and `WashingMachine` outlines.
- `cho.info.rendering`: the Swing `Window`, drawing order, and color handling.

`Furniture` handles movement, rotation, scaling, visibility, and coordinate transforms.
Each furniture class defines its outline in local coordinates through `createShape()`.

```java
Chair chair = new Chair(40, 80, "blue");
chair.show();
chair.moveHorizontally(50);
chair.rotateBy(90);
chair.scaleBy(1.5);
chair.hide();
```

The default chair, table, piano, and washing machine constructors show their objects
immediately. The parameterized chair constructor, cabinets, and wall units start hidden.

Property setters update the model. Use movement methods, `rotateTo`, `rotateBy`,
`scaleBy`, and `changeColor` to update furniture and redraw it. `setVisible`, `show`,
and `hide` update both visibility and the canvas.

Colors use English names: `red`, `black`, `blue`, `yellow`, `green`, `purple`, and
`white`. Original German color strings are also accepted. Unknown names draw in black.

The alternative scaling and snapping exercises retain their distinct rounding behavior
under descriptive English names. The empty demo and the unfinished rotation-snapping
loop have been removed; duplicate rotation methods and width getters are consolidated.
