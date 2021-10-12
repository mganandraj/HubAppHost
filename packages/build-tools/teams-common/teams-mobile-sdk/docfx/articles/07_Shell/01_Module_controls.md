# Module controls

## Close module
A module can close itself, as shown below:

```typescript
this.closeModule();
```

**Note:** A module cannot close itself if the app is in the bottom navigation bar. In this case, `closeModule()` will no-op.

A boolean value can be provided in scenarios in which a result is required (for example, when editing a contact). This value is `false` by default.

```typescript
this.closeModule(true);
```

## Set background color
A module can set the background of the native root view.

The color value must be a valid hexadecimal color.

```typescript
this.getTeamsShell().setBackgroundColor('#FFFFFF');
```

## Set Home Indicator Background Color in iOS
A module can set the background of the home indicator area at the bottom.

The color must be a valid hexadecimal color in the format "#AARRGGBB" where is RR is for red, GG is for Green, BB is for Blue and AA is for opacity.
Also, opacity is optional. So, format "#RRGGBB" is also valid.

```typescript
this.getTeamsShell().setHomeIndicatorBackgroundColorIOS('#FF00FF00');
```
