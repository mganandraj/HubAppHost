> [!IMPORTANT]
> This article describes the older, manual way of creating a teams RN app. The
> same can now be done quickly using the `create-teams-rn-app` command line 
> utility. Refer to [Creating a new app](xref:create_a_new_app_auto) for 
> instructions.

# Creating a new app
1. Create a blank app directory and navigate to it. 
	```
	mkdir testapp && cd testapp
	```
2. Initialize the app as a NodeJS application. 
	```
	yarn init
	```
3. Create a `.npmrc` file with the below content:
	```
	registry=https://domoreexp.pkgs.visualstudio.com/_packaging/teams-mobile-platform/npm/registry/
	always-auth=true
	```
4. Add React, React Native, and Teams Mobile SDK dependencies to the app's `package.json`:
	```json
	{
	  "name": "testapp",
	  "version": "0.0.1",
	  "description": "A sample application that outputs Hello World!",
	  "main": "index.js",
	  "license": "ISC",
	  "dependencies": {
	    "react": "17.0.1",
	    "react-native": "0.64.2",
	    "teams-mobile-sdk": "<sdk-version>"
	  }
	}
	```
5. Install the dependencies: 
	* Before installing dependencies, configure VSTS auth:
    	* For Windows:
        	 ```
           yarn global add vsts-npm-auth --registry https://registry.npmjs.com --always-auth false
           vsts-npm-auth -config .npmrc 
           ```
    	* For Mac/Linux:
        	 ```
           npm install -g better-vsts-npm-auth
           better-vsts-npm-auth -config .npmrc
           ```
           
	* Install dependencies:
		```
		yarn
		``` 
6. It's recommended to use TypeScript.
    * Install TypeScript: `yarn add --save-dev typescript @types/typescript`
    * Install typings for React and React Native. `yarn add --save-dev @types/react @types/react-native`
    * Define TS config file named `tsconfig.json`:
    ```json
    {
        "compilerOptions": {
            "module": "commonjs",
            "target": "es6",
            "outDir": "build",
            "experimentalDecorators": true,
            "jsx": "react",
            "sourceMap": false,
            "strictNullChecks": true,
            "baseUrl": ".",
            "paths": {
                "*": [
                    "*",
                    "*.ios",
                    "*.android"
                ]
            },
            "types": [
              "react", 
              "react-native"
            ]
        },
        "include": [
            "src/**/*.ts",
            "src/**/*.tsx"
        ],
        "exclude": [
            "node_modules"
        ],
        "compileOnSave": true
    }
    ```

## Add a manifest for your app
Create a new file named `manifest.json` in your app root directory. App views, modules, extensions, and other configurations are defined in this manifest file.

## Defining a view
Views are implemented as [React Native components](https://facebook.github.io/react-native/docs/components-and-apis.html#basic-components). An app can define multiple views. All views must extend [TeamsView](xref:teams-mobile-sdk.TeamsView).

### Example
```typescript
import * as React from 'react';
import {
  Text,
  StyleSheet,
  View
} from 'react-native';
import {
  ArgumentsValidator,
  TeamsView
} from 'teams-mobile-sdk';

let styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10
  }
});

export interface GreetingComponentProps {
  userGreeting: string;
}

export class GreetingComponent extends TeamsView<ApplicationComponentProps, any> {
  private userGreeting: any;

  constructor (props: GreetingComponentProps, state?: any) {
    super(props, state);
  }

  render () {
    let greeting = this.userGreeting;
    return (
      <View style={styles.container}>
        <Text
          style={styles.hello}>
          Hello World!
        </Text>
      </View>
    );
  }
}
```

Each view should be added to the views array in the apps's `manifest.json`.

```json
{
  "views": [
    {
      "name": "greeting",
      "title": "res://strings/hello_world_view_title",
      "componentName": "Greeting"
    }
  ]
}
```

The above view definition declares a view named `greeting`.

Every component defining a view must also be registered with the `AppRegistry` like follows:
```typescript
AppRegistry.registerComponent("Greeting", () => GreetingComponent);
```

# Module Icons
A module must have both `default` and `selected` icons defined. See
[Resources](04_Resources.md) to learn how to add and use images.

These icons are used to be shown in bottom bar of the mobile app and app tray. 

```json
{
  "id": "9a1f0cd2-ff89-443f-9618-01993b1c5ff0",
  "name": "sample",
  "title": "res://strings/app_name",
  "icons": {
    "default": "res://images/icn_default.svg",
    "selected": "res://images/icn_selected.svg"
  }
}
```


> [!NOTE]
> Fluent icons can be specified as app icons using the `fluent://` URI scheme.
> See [Resources](xref:resources#fluent-icons) for more info.
> ```json
> {
>   ...
>   "icons": {
>     "default": "fluent://notepad"
>   }
> }
> ```
> When using Fluent icons, if the selected icon is not specified (as above),
> then the filled version of the specified default icon is used. In the above
> example, the filled version of the "notepad" icon is automatically used as
> the selected state icon.

## **Guidelines for app icons :**
1. The module needs to provide two icons for default and selected states. 
2. Provide svg icons for efficient rendering of icons. since they can scale to fit the required space of the icon place holders at different places.
3. Icons should not have any padding/ space around it. Since the icon is expected to scale to the size of the place holders where the icons are inflated into. 
4. Icons should have only white and transparent pixels. Mobile Client will color them depending on the Teams app design themes and also to account for light and dark modes.
5. The default icon will usually look like a outline with transparent filling inside it. The chat, meetings, calls icons are in their default state. The icons have transparent filling inside them. The blue color is applied by the mobile client on the icon's white pixel.

![default icon in bottom bar](~/images/bottombar_chat_icon_default.png)

6. The selected icon will usually look like a filled icon in white color. Below example shows the chat icon in its selected state. The blue color is applied by the mobile client on the icon's white pixel.

![default icon in bottom bar](~/images/bottombar_chat_icon_selected.png)

7. The app tray will use the selected icon. Below example shows the icons in app tray. The selected icon is overlapped on blue (since blue is the teams app them) rounded square background.

<img src="~/images/app_tray.png" alt="drawing" style="width:500px;"/>

8. Below is a sample bottom bar in dark mode. the icons are used with white color in dark mode. This is controlled by the mobile client UX design theme. So if you provide icon images with only white and transparent pixels, mobile client can render them as need depending on the Teams app theme.

<img src="~/images/dark_mode_bottom_bar.png" alt="drawing" style="width:500px;"/>
![default icon in bottom bar]()

#### **Sample svg icons**

1. Default Icon (black background is added for representation and not part of the icon) [Default svg Icon file](~/images/icn_default.svg)
   
<img src="~/images/icn_default.svg" alt="drawing" style="width:250px;background-color:black;"/>

2. Selected Icon (black background is added for representation and not part of the icon) [Selected svg Icon file](~/images/icn_selected.svg)
   
<img src="~/images/icn_selected.svg" alt="drawing" style="width:250px;background-color:black;"/>


### **Caveats**

In the Resource folder structure, you are required to provide icons for default and dark mode separately. But in case of icons we can have the same icon files duplicated in both folders. Since the icons are colour less and app can color them as need.

currently you cant change the color of the background in the app tray icons. This is a work in progress. and this section will be updated when the implementation is done.
