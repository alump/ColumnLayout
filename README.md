# ColumnLayout Add-on for Vaadin 7

ColumnLayout is an UI component add-on for Vaadin 7.3+ and Valo based themes. It provides alternative for
Horizontal+VerticalLayout and GridLayout usage when building columned layouts.

[![Build Status](http://siika.fi:8888/jenkins/job/ColumnLayout%20(Vaadin)/badge/icon)](http://siika.fi:8888/jenkins/job/ColumnLayout%20(Vaadin)/)

## Online demo

Try the add-on demo at http://app.siika.fi/ColumnLayoutDemo

## Download release

Official releases of this add-on are available at Vaadin Directory. For Maven instructions, download and reviews, go to https://vaadin.com/directory#addon/columnlayout

## Release notes

### Version 0.2.2
- TBD

### Version 0.2.1
- Cleaned up severe logging on client side

### Version 0.2.0
- Adds click details to tooltip click event of MaterialColumnLayout
- Adds no tooltips versions of inline/popup datefield, slider and label
- Add support for Reindeer theme (issue #11), by making Valo depending material parts optional. If you used material parts, update your theme by: 
```scss
// ... these lines should follow importing your base theme (that is valo based)
@import "addons";
// ADD NEXT ROW:
@import "../../../VAADIN/addons/columnlayout/columnlayout-material.scss";
// ...
@include addons;
// Include optional material version, ADD NEXT ROW:
@include columnlayout-material-default;
// ...
```
If you have used advanced theming, so variable and mixing names have changed.

### Version 0.1.6
- Material tooltip texts (descriptions) now assumed to be HTML (issue #6)
- Material tooltip clicks will be notified to server side (issue #6)
- Fixes bad SCSS variable naming in 0.1.5 (issue #5)

### Version 0.1.5
- Parametrized column-layout SCSS mixin. Allows to override parameters of specific stylenamed ColumnLayouts (issue #4)

### Version 0.1.4
- Fix client side exceptions when invisible child components (issue #3)

### Version 0.1.3
- Fix client side exception caused when slots made (issue #3)
- Documentation improvements

### Version 0.1.2
- setComponent functionality added (with placeholders)
- More Material design CSS fixes
- Fixing wrapping issue when used inside Vertical/HorizontalLayouts
- More OSGi manifest fine tuning

### Version 0.1.1
- CSS fixes
- OSGi manifest fine tuning

### Version 0.1.0
- Initial release
- Material design as example extension of ColumnLayout
- Add-on packaged as bundle for OSGi users
- To be used with Valo based themes! (You can report issues with other themes, but those might not get fixed)

## Roadmap

Feature requests and plans can be found from project's GitHub issue tracker

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated. 

## Building and running demo

git clone <url of the ColumnLayout repository>
mvn clean install
cd demo
mvn jetty:run

To see the demo, navigate to http://localhost:8080/

## Development with Eclipse IDE

For further development of this add-on, the following tool-chain is recommended:
- Eclipse IDE
- m2e wtp plug-in (install it from Eclipse Marketplace)
- Vaadin Eclipse plug-in (install it from Eclipse Marketplace)
- JRebel Eclipse plug-in (install it from Eclipse Marketplace)
- Chrome browser

### Importing project

Choose File > Import... > Existing Maven Projects

Note that Eclipse may give "Plugin execution not covered by lifecycle configuration" errors for pom.xml. Use "Permanently mark goal resources in pom.xml as ignored in Eclipse build" quick-fix to mark these errors as permanently ignored in your project. Do not worry, the project still works fine. 

### Debugging server-side

If you have not already compiled the widgetset, do it now by running vaadin:install Maven target for columnlayout-root project.

If you have a JRebel license, it makes on the fly code changes faster. Just add JRebel nature to your columnlayout-demo project by clicking project with right mouse button and choosing JRebel > Add JRebel Nature

To debug project and make code modifications on the fly in the server-side, right-click the columnlayout-demo project and choose Debug As > Debug on Server. Navigate to http://localhost:8080/columnlayout-demo/ to see the application.

### Debugging client-side

The most common way of debugging and making changes to the client-side code is dev-mode. To create debug configuration for it, open columnlayout-demo project properties and click "Create Development Mode Launch" button on the Vaadin tab. Right-click newly added "GWT development mode for columnlayout-demo.launch" and choose Debug As > Debug Configurations... Open up Classpath tab for the development mode configuration and choose User Entries. Click Advanced... and select Add Folders. Choose Java and Resources under columnlayout/src/main and click ok. Now you are ready to start debugging the client-side code by clicking debug. Click Launch Default Browser button in the GWT Development Mode in the launched application. Now you can modify and breakpoints to client-side classes and see changes by reloading the web page. 

Another way of debugging client-side is superdev mode. To enable it, uncomment devModeRedirectEnabled line from the end of DemoWidgetSet.gwt.xml located under columnlayout-demo resources folder and compile the widgetset once by running vaadin:compile Maven target for columnlayout-demo. Refresh columnlayout-demo project resources by right clicking the project and choosing Refresh. Click "Create SuperDevMode Launch" button on the Vaadin tab of the columnlayout-demo project properties panel to create superder mode code server launch configuration and modify the class path as instructed above. After starting the code server by running SuperDevMode launch as Java application, you can navigate to http://localhost:8080/columnlayout-demo/?superdevmode. Now all code changes you do to your client side will get compiled as soon as you reload the web page. You can also access Java-sources and set breakpoints inside Chrome if you enable source maps from inspector settings. 

## Contributions

Contributions are welcome, but there are no guarantees that they are accepted as such. Process for contributing is the following:
- Fork this project
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- Refer to the fixed issue in commit
- Send a pull request for the original project
- Comment on the original issue that you have implemented a fix for it

## License & Author

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

ColumnLayout is written by Sami Viitanen sami.viitanen@gmail.com

# Developer Guide

## Getting started

Here is a simple example on how to try out the add-on component:

<...>

For a more comprehensive example, see columnlayout-demo/src/main/java/org/vaadin/alump/columnlayout/demo/views/BasicView.java

## Features

### Alternative for GridLayout

GridLayout is not always ideal for building forms with colums. ColumnLayout offers simplified alternative to
GridLayout with different behavior.

### Client side that can be extended

Client side has been designed to allow extending and changing behavior. Anyhow remember that before 1.0 release the
client side API can change in each minor release.

### Start point for Material design forms

As proof of extending support of ColumnLayout this add-on provides Material design version of it. This will style
fields in style of MaterialDesign. Valo based theme should be used with this add-on.

## API

ColumnLayout JavaDoc is available online at TBD