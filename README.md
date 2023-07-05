# CP Story

CP Story is a final project of the Programming Methodology class 2017 at Chulalongkorn University, learning about object-oriented programming. It is a game inspired by MapleStory but was modified to have characters, monsters, scenes, and skills related to Computer Engineering and Chulalongkorn University.

## How to run the code on Eclipse

Since this project was initially developed without a package manager, external libraries must be imported manually.

1. Download [JavaFX SDK 17](https://gluonhq.com/products/javafx/) and extract the downloaded ZIP file.
2. Open Eclipse settings, go to `Java` > `Build Path` > `User Libraries` and create new `JavaFX 17` library
3. Add all 8 External JARs from the downloaded JavaFX SDK to the `JavaFX 17` user library.
4. Open the project on Eclipse, right click on the root directory, go to `Build Path` > `Add Libraries`, select `User Library` and select `JavaFX 17`
5. Open `src/main/Main.java` and click Run. This should not be working yet.
6. Click dropdown next to the Run button and open Run Configurations, select Arguments tab, type the following to VM arguments text field, and uncheck `Use the -XstartOnFirstThread...` option.

```
--module-path <JAVAFX_SDK_DIR>/lib --add-modules=javafx.controls,javafx.media
```

7. Click Run again, the game should now be working.

## How to run the JAR file

The JAR file is provided in the [Releases](https://github.com/sunboyy/cp-story/releases) page on GitHub. Download the latest version before continuing.

Because JavaFX library is not embedded in the JAR file, you still need to download the JavaFX SDK library like in the previous section. After having the JavaFX library downloaded and unzipped, execute this command in the terminal window to start the game.

```
java --module-path <JAVAFX_SDK_DIR>/lib \
    --add-modules=javafx.controls,javafx.media \
    -jar cpstory.jar
```

## Contributors

Thanks all these people to make this project happen!

<table>
  <tbody>
    <tr>
      <td align="center" width="50%">
        <a href="https://github.com/sunboyy">
          <img src="https://avatars.githubusercontent.com/u/22892266?v=4?s=100" width="100px;" alt="sunboyy"/><br />
          <sub><b>sunboyy</b></sub>
        </a>
      </td>
      <td align="center" width="50%">
        <a href="https://github.com/npmoewii">
          <img src="https://avatars.githubusercontent.com/u/22891156?v=4?s=100" width="100px;" alt="npmoewii"/><br />
          <sub><b>npmoewii</b></sub>
        </a>
      </td>
    </tr>
  </tbody>
</table>

## Original code

You can find the original code of this project when it was submitted in branch [final](https://github.com/sunboyy/cp-story/tree/final).
